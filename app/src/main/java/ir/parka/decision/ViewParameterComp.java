package ir.parka.decision;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ViewParameterComp extends LinearLayout{

	public TextView txtPri1;
	public TextView txtPri2;
	public Spinner spinner;
	public static String[] spinnerParamSelection = new String[]{"paramSelection0","paramSelection1",
		"paramSelection2","paramSelection3","paramSelection4","paramSelection5",
		"paramSelection6","paramSelection7","paramSelection8","paramSelection9"};

	public ViewParameterComp(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializer(context);

	}

	public ViewParameterComp(Context context) {
		super(context);
		initializer(context);
	}

	double item = 0;
	int indexSpinner = 0;
	private void initializer(Context context)
	{

		if(isInEditMode())
		{
			return;
		}

		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflator.inflate(R.layout.view_param_comp, this);

		spinner = (Spinner) layout.findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		list.add("بسیار کم اهمیت تر از");
		list.add("کم اهمیت تر از");
		list.add("دارای اهمیت مشابه با");
		list.add("اندکی مهم تر از");
		list.add("بسیار مهم تر از");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, 
				R.layout.custom_spinner_item, list);
		dataAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {

				item = parent.getItemIdAtPosition(pos);	

				switch((int)parent.getItemIdAtPosition(pos)){
				case 0:
					item = (double)1/5;
					break;
				case 1:
					item = (double)1/3;
					break;
				case 2:
					item = 1.0;
					break;
				case 3:
					item = 3.0;
					break;
				case 4:
					item = 5.0;
					break;					
				}
				//				Toast.makeText(G.context, "" + item, Toast.LENGTH_SHORT).show(); 
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		txtPri1 = (TextView) layout.findViewById(R.id.txtPri1);
		txtPri2 = (TextView) layout.findViewById(R.id.txtPri2);
	}

	public void setParamText (String input1, String input2)
	{
		txtPri1.setText(input1);
		txtPri2.setText(input2);
	}

	public double getPriParamItem ()
	{
		//		Toast.makeText(G.context, "" + item, Toast.LENGTH_SHORT).show();
		return item;
	}

	public void getID(int input)
	{
		int spinnerValue = 0;
		indexSpinner = input;

		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
		spinnerValue = sharedPref.getInt(spinnerParamSelection[indexSpinner], 10);

		if(spinnerValue != 10) 
			spinner.setSelection(spinnerValue);
	}

	public void clearID(int input)
	{
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());

		Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
		prefsEditor.putInt(spinnerParamSelection[input], 0).commit();
		spinner.setSelection(0);
	}
}
