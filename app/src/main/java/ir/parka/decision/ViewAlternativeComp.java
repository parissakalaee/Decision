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

public class ViewAlternativeComp extends LinearLayout{

	public TextView txtParameter;
	public TextView[] txtAlt = new TextView[FragmentCalculator.ARRAY_SIZE];
	public Spinner[] spinner = new Spinner[FragmentCalculator.ARRAY_SIZE];
	public static String[][] spinnerAlterSelection = new String[][]{
			{"Alter0" ,"Alter1" ,"Alter2" ,"Alter3" ,"Alter4"},
			{"Alter5" ,"Alter6" ,"Alter7" ,"Alter8" ,"Alter9"},
			{"Alter10","Alter11","Alter12","Alter13","Alter14"},
			{"Alter15","Alter16","Alter17","Alter18","Alter19"},
			{"Alter20","Alter21","Alter22","Alter23","Alter24"},
	};


	public ViewAlternativeComp(Context context, AttributeSet attrs) {
		super(context, attrs);
		initializer(context);

	}

	public ViewAlternativeComp(Context context) {
		super(context);
		initializer(context);
	}

	long[] item = new long[FragmentCalculator.ARRAY_SIZE];
	int indexSpinner = 0;
	private void initializer(Context context)
	{

		if(isInEditMode())
		{
			return;
		}

		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflator.inflate(R.layout.view_alternative_comp, this);

		List<String> list = new ArrayList<String>();
		list.add("عالی");
		list.add("بسیار خوب");
		list.add("خوب");
		list.add("متوسط");
		list.add("بد");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, 
				R.layout.custom_spinner_item, list);
		dataAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

		spinner[0] = (Spinner) layout.findViewById(R.id.spinner2);
		spinner[1] = (Spinner) layout.findViewById(R.id.spinner3);
		spinner[2] = (Spinner) layout.findViewById(R.id.spinner4);
		spinner[3] = (Spinner) layout.findViewById(R.id.spinner5);
		spinner[4] = (Spinner) layout.findViewById(R.id.spinner6);

		for(int i=0; i<FragmentCalculator.ARRAY_SIZE; i++)
			spinner[i].setAdapter(dataAdapter);

		spinner[0].setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				item[0] = parent.getItemIdAtPosition(pos);
				
				switch((int)parent.getItemIdAtPosition(pos)){
				case 0:
					item[0] = 5;
					break;
				case 1:
					item[0] = 4;
					break;
				case 2:
					item[0] = 3;
					break;
				case 3:
					item[0] = 2;
					break;
				case 4:
					item[0] = 1;
					break;					
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		spinner[1].setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				item[1] = parent.getItemIdAtPosition(pos);
				
				switch((int)parent.getItemIdAtPosition(pos)){
				case 0:
					item[1] = 5;
					break;
				case 1:
					item[1] = 4;
					break;
				case 2:
					item[1] = 3;
					break;
				case 3:
					item[1] = 2;
					break;
				case 4:
					item[1] = 1;
					break;	
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});


		spinner[2].setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				item[2] = parent.getItemIdAtPosition(pos);
				
				switch((int)parent.getItemIdAtPosition(pos)){
				case 0:
					item[2] = 5;
					break;
				case 1:
					item[2] = 4;
					break;
				case 2:
					item[2] = 3;
					break;
				case 3:
					item[2] = 2;
					break;
				case 4:
					item[2] = 1;
					break;	
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		spinner[3].setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				item[3] = parent.getItemIdAtPosition(pos);
				
				switch((int)parent.getItemIdAtPosition(pos)){
				case 0:
					item[3] = 5;
					break;
				case 1:
					item[3] = 4;
					break;
				case 2:
					item[3] = 3;
					break;
				case 3:
					item[3] = 2;
					break;
				case 4:
					item[3] = 1;
					break;	
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		spinner[4].setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				item[4] = parent.getItemIdAtPosition(pos);
				
				switch((int)parent.getItemIdAtPosition(pos)){
				case 0:
					item[4] = 5;
					break;
				case 1:
					item[4] = 4;
					break;
				case 2:
					item[4] = 3;
					break;
				case 3:
					item[4] = 2;
					break;
				case 4:
					item[4] = 1;
					break;	
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		txtParameter = (TextView) layout.findViewById(R.id.txtParameter);
		txtAlt[0] = (TextView) layout.findViewById(R.id.txtAlt1);
		txtAlt[1] = (TextView) layout.findViewById(R.id.txtAlt2);
		txtAlt[2] = (TextView) layout.findViewById(R.id.txtAlt3);
		txtAlt[3] = (TextView) layout.findViewById(R.id.txtAlt4);
		txtAlt[4] = (TextView) layout.findViewById(R.id.txtAlt5);
	}

	public void setAltText (String param, String[] input)
	{
		txtParameter.setText(param);

		for(int i = 0; i < FragmentCalculator.ARRAY_SIZE; i++)
			txtAlt[i].setText(input[i]);
	}

	public void setVisibilityText (int number)
	{
		txtAlt[number].setVisibility(View.GONE);
		spinner[number].setVisibility(View.GONE);
	}

	public long[] getAlternativeItem()
	{
		return item;
	}

	public void getID(int input)
	{
		int spinnerValue = 0;
		indexSpinner = input;

		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());

		for(int i = 0; i < 5; i++){
		spinnerValue = sharedPref.getInt(spinnerAlterSelection[indexSpinner][i], 5);
		if(spinnerValue != 5) 
			spinner[i].setSelection(spinnerValue);
		}
	}
	
	public void clearID(int input)
	{
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());

		for(int i = 0; i < 5; i++){
			Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
			prefsEditor.putInt(spinnerAlterSelection[input][i], 0).commit();
			spinner[i].setSelection(0);
		}
	}
}
