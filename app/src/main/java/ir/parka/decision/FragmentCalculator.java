package ir.parka.decision;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBar.LayoutParams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentCalculator extends Fragment {

	final static int ARRAY_SIZE = 5;
	public String inputDecisionValue = "";
	public String[] parameterValue = new String[]{"","","","",""};
	public String[] alternativeValue = new String[]{"","","","",""};
	public ViewAlternativeComp[] viewAlternativeComp = new ViewAlternativeComp[ARRAY_SIZE];
	public ViewParameterComp[] viewParameterComp = new ViewParameterComp[10];
	public double[][] alternativePriorityValue = new double[ARRAY_SIZE][ARRAY_SIZE];
	public double[][] parameterPriorityValue = new double[ARRAY_SIZE][ARRAY_SIZE];
	public double[][] myParameterPriorityValue = new double[ARRAY_SIZE][ARRAY_SIZE];
	public EditText[] edtParameter = new EditText[ARRAY_SIZE];
	public EditText[] edtAlternatives = new EditText[ARRAY_SIZE];
	public EditText   edtInputDecision;
	Button[] btnQ = new Button[ARRAY_SIZE];
	Button btnCompute, btnReset;
	public String[] inputParam = new String[]{"param0","param1","param2","param3","param4"};
	public String[] inputAlter = new String[]{"alter0","alter1","alter2","alter3","alter4"};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);

		Typeface txt_font    = Typeface.createFromAsset(getActivity().getAssets(), "BZar_bold.ttf");

		btnQ[0] = (Button) rootView.findViewById(R.id.btnQ_1);
		btnQ[1] = (Button) rootView.findViewById(R.id.btnQ_2);
		btnQ[2] = (Button) rootView.findViewById(R.id.btnQ_3);
		btnQ[3] = (Button) rootView.findViewById(R.id.btnQ_4);
		btnQ[4] = (Button) rootView.findViewById(R.id.btnQ_5);
		btnCompute 	= (Button) rootView.findViewById(R.id.btnCompute);
		btnReset 	= (Button) rootView.findViewById(R.id.btnReset);

		//		btnQ[3].setEnabled(false);
		//		btnQ[4].setEnabled(false);
		//		btnCompute.setEnabled(false);

		for(int i = 0; i < 5; i++)
			btnQ[i].setTypeface(txt_font);

		btnCompute.setTypeface(txt_font);
		btnReset.setTypeface(txt_font);

		btnQ[0].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				inputSubjectDialog("لطفاً مورد تصمیم گیری را وارد کنید (به عنوان مثال: انتخاب مقصد سفر، انتخاب هدیه، ....) ");
			}
		});

		btnQ[1].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				inputAlternativeDialog("لطفاً گزینه‌های موجود در تصمیم گیری را وارد کنید (به عنوان مثال در خصوص مقصد سفر: همدان، اصفهان....)");
			}
		});

		btnQ[2].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				inputParameterDialog("لطفاً پارامترهای تاثیرگذار در تصمیم گیری را وارد کنید (به عنوان مثال در خصوص مقصد سفر: هزینه اقامت، مسیر جاده....)");
			}
		});

		btnQ[3].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				prioritizeAlternativeDialog("لطفاً به هر گزینه از دیدگاه پارامترها امتیاز دهید، به عنوان مثال در خصوص مقصد سفر، از دیدگاه پارامتر مسیر جاده، گزینه همدان چه امتیازی می گیرد (عالی، خوب، ...)؟");
			}
		});

		btnQ[4].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				prioritizeParameterDialog("لطفاً پارامترها را نسبت به هم طبقه بندی کنید(به عنوان مثال در خصوص مقصد سفر، مسیر جاده مهمتر است یا هزینه اقامت....)");
			}
		});


		btnCompute.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {			

				for(int i=0; i < ARRAY_SIZE; i++){
					for(int j=0; j < ARRAY_SIZE; j++){			
						myParameterPriorityValue[i][j] = 0.0;
					}
				}

				myParameterPriorityValue[0][0] = 1.0;
				myParameterPriorityValue[1][1] = 1.0;
				myParameterPriorityValue[2][2] = 1.0;
				myParameterPriorityValue[3][3] = 1.0;
				myParameterPriorityValue[4][4] = 1.0;

				for(int i = 0; i < ARRAY_SIZE; i++){
					for(int j = i + 1; j < ARRAY_SIZE; j++){

						if(parameterPriorityValue[i][j] != 0)
						{
							myParameterPriorityValue[i][j] = parameterPriorityValue[i][j];
							myParameterPriorityValue[j][i] = (double)1/parameterPriorityValue[i][j];
						}
					}
				}

				if(myParameterPriorityValue[0][1] == 0 && myParameterPriorityValue[0][2] == 0 && myParameterPriorityValue[0][3] == 0 && myParameterPriorityValue[0][4] == 0){
					myParameterPriorityValue[0][0] = 0;
				}
				if(myParameterPriorityValue[1][0] == 0 && myParameterPriorityValue[1][2] == 0 && myParameterPriorityValue[1][3] == 0 && myParameterPriorityValue[1][4] == 0){
					myParameterPriorityValue[1][1] = 0;
				}
				if(myParameterPriorityValue[2][0] == 0 && myParameterPriorityValue[2][1] == 0 && myParameterPriorityValue[2][3] == 0 && myParameterPriorityValue[2][4] == 0){
					myParameterPriorityValue[2][2] = 0;
				}
				if(myParameterPriorityValue[3][0] == 0 && myParameterPriorityValue[3][1] == 0 && myParameterPriorityValue[3][2] == 0 && myParameterPriorityValue[3][4] == 0){
					myParameterPriorityValue[3][3] = 0;
				}
				if(myParameterPriorityValue[4][0] == 0 && myParameterPriorityValue[4][1] == 0 && myParameterPriorityValue[4][2] == 0 && myParameterPriorityValue[4][3] == 0){
					myParameterPriorityValue[4][4] = 0;
				}

				double[] sumVerticalParam = new double[]{0.0,0.0,0.0,0.0,0.0};
				for(int i = 0; i < ARRAY_SIZE; i++){
					for(int j = 0; j < ARRAY_SIZE; j++){						
						sumVerticalParam[i] +=  myParameterPriorityValue[j][i];
					}

					//					Toast.makeText(G.context, "" + sumVerticalParam[i], Toast.LENGTH_SHORT).show();
				}

				double[][] tmpParam = new double[ARRAY_SIZE][ARRAY_SIZE];
				for(int i = 0; i < ARRAY_SIZE; i++){
					for(int j = 0; j < ARRAY_SIZE; j++){		
						if(sumVerticalParam[i] != 0){
							tmpParam[j][i]= (double)( myParameterPriorityValue[j][i] / sumVerticalParam[i]);
							//							Toast.makeText(G.context, "" + tmpParam[j][i], Toast.LENGTH_SHORT).show();
						}
						else
							tmpParam[j][i] = 0.0;
					}
				}

				double[] sumHorizontalParam = new double[]{0.0,0.0,0.0,0.0,0.0};
				for(int i = 0; i < ARRAY_SIZE; i++){
					for(int j = 0; j < ARRAY_SIZE; j++){						
						sumHorizontalParam[i] +=  tmpParam[i][j];
					}
					//					Toast.makeText(G.context, "" + sumHorizontalParam[i], Toast.LENGTH_SHORT).show();
				}

				double[][] tmpAlternative = new double[ARRAY_SIZE][ARRAY_SIZE];
				for(int i = 0; i < ARRAY_SIZE; i++){
					for(int j = 0; j < ARRAY_SIZE; j++){						
						if(sumHorizontalParam[i] != 0){
							tmpAlternative[j][i]= (double)( alternativePriorityValue[i][j] * sumHorizontalParam[i]);
							//							Toast.makeText(G.context, "" + alternativePriorityValue[i][j], Toast.LENGTH_SHORT).show();
						}
						else
							tmpAlternative[j][i] = 0.0;
					}
				}

				double finalSum = 0;
				Double[] sumHorizontalAlternative = new Double[]{0.0,0.0,0.0,0.0,0.0};
				for(int i = 0; i < ARRAY_SIZE; i++){
					for(int j = 0; j < ARRAY_SIZE; j++){						
						sumHorizontalAlternative[i] +=  tmpAlternative[i][j];
					}
					//					Toast.makeText(G.context, "" + sumHorizontalAlternative[i], Toast.LENGTH_SHORT).show();

					finalSum += sumHorizontalAlternative[i]; 
				}

				double[] maxResult = new double[]{0.0,0.0,0.0,0.0,0.0};
				int[]    maxIndex  = new int[]{0,0,0,0,0};
				for(int i = 0; i < ARRAY_SIZE; i++){
					if( sumHorizontalAlternative[i] >= maxResult[0] ){
						maxResult[0] = sumHorizontalAlternative[i];
						maxIndex[0]  = i;
					}
				}

				sumHorizontalAlternative[maxIndex[0]] = 0.0;

				for(int i = 0; i < ARRAY_SIZE; i++){
					if( sumHorizontalAlternative[i] >= maxResult[1] ){
						maxResult[1] = sumHorizontalAlternative[i];
						maxIndex[1]  = i;
					}
				}

				sumHorizontalAlternative[maxIndex[1]] = 0.0;

				for(int i = 0; i < ARRAY_SIZE; i++){
					if( sumHorizontalAlternative[i] >= maxResult[2] ){
						maxResult[2] = sumHorizontalAlternative[i];
						maxIndex[2]  = i;
					}
				}

				sumHorizontalAlternative[maxIndex[2]] = 0.0;

				for(int i = 0; i < ARRAY_SIZE; i++){
					if( sumHorizontalAlternative[i] >= maxResult[3] ){
						maxResult[3] = sumHorizontalAlternative[i];
						maxIndex[3]  = i;
					}
				}

				sumHorizontalAlternative[maxIndex[3]] = 0.0;

				for(int i = 0; i < ARRAY_SIZE; i++){
					if( sumHorizontalAlternative[i] >= maxResult[4] ){
						maxResult[4] = sumHorizontalAlternative[i];
						maxIndex[4]  = i;
					}
				}

				resultDialog(maxIndex, maxResult, finalSum);
			}
		});

		btnReset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
				prefsEditor.clear().commit();

				inputDecisionValue = "";
				for(int i=0; i<ARRAY_SIZE; i++)
				{
					alternativeValue[i] = "";
					parameterValue[i] = "";
				}

				for(int i=0; i < ARRAY_SIZE; i++){
					for(int j=0; j < ARRAY_SIZE; j++){			
						parameterPriorityValue[i][j] = 0.0;
						alternativePriorityValue[i][j] = 0.0;
					}
				}
			}
		});

		return rootView;
	}

	private void inputSubjectDialog(String prgMessage) {
		final Dialog dialog = new Dialog(getActivity());
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_input_subject);

		TextView txtMessage1 = (TextView) dialog.findViewById(R.id.txtMessage1);
		txtMessage1.setText(prgMessage);

		edtInputDecision = (EditText) dialog.findViewById(R.id.edtInputDecision);

		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		String preferenceSubjectValue = sharedPref.getString("subject", null);
		if( preferenceSubjectValue != null )
			edtInputDecision.setText(preferenceSubjectValue);

		Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
		Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();

				if (edtInputDecision.getText().toString().length() > 0) {
					inputDecisionValue = edtInputDecision.getText().toString();
				}
				else{
					inputDecisionValue = "";
				}

				prefsEditor.putString("subject", inputDecisionValue).commit();
				dialog.dismiss();
			}
		});


		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
				edtInputDecision.setText("");
				prefsEditor.remove("subject").commit();
				inputDecisionValue = "";
			}
		});

		dialog.show();
	}

	private void inputAlternativeDialog(String prgMessage) {
		final Dialog dialog = new Dialog(getActivity());
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_input_alternatives);

		TextView txtMessage2 = (TextView) dialog.findViewById(R.id.txtMessage2);
		txtMessage2.setText(prgMessage);

		edtAlternatives[0] = (EditText) dialog.findViewById(R.id.edtAlternative1);
		edtAlternatives[1] = (EditText) dialog.findViewById(R.id.edtAlternative2);
		edtAlternatives[2] = (EditText) dialog.findViewById(R.id.edtAlternative3);
		edtAlternatives[3] = (EditText) dialog.findViewById(R.id.edtAlternative4);
		edtAlternatives[4] = (EditText) dialog.findViewById(R.id.edtAlternative5);

		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		for(int i=0; i<ARRAY_SIZE; i++)
		{
			String preferenceAlterValue = sharedPref.getString(inputAlter[i], null);
			if( preferenceAlterValue != null )
				edtAlternatives[i].setText(preferenceAlterValue);
		}

		Button btnOk = (Button) dialog.findViewById(R.id.btnOK1);
		Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel1);

		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();

				for(int i=0;i<ARRAY_SIZE;i++){
					if (edtAlternatives[i].getText().toString().length() > 0) {
						alternativeValue[i] = edtAlternatives[i].getText().toString();
					}
					else{
						alternativeValue[i] = "";
					}

					prefsEditor.putString(inputAlter[i], alternativeValue[i]).commit();
				}

				dialog.dismiss();
			}
		});


		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
				for(int i = 0; i < 5; i++){
					edtAlternatives[i].setText("");
					prefsEditor.remove(inputAlter[i]).commit();
					alternativeValue[i] = "";
				}
			}
		});

		dialog.show();
	}

	private void inputParameterDialog(String prgMessage) {
		final Dialog dialog = new Dialog(getActivity());
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_input_parameters);

		TextView txtMessage2 = (TextView) dialog.findViewById(R.id.txtMessage2);
		txtMessage2.setText(prgMessage);

		edtParameter[0] = (EditText) dialog.findViewById(R.id.edtParameter1);
		edtParameter[1] = (EditText) dialog.findViewById(R.id.edtParameter2);
		edtParameter[2] = (EditText) dialog.findViewById(R.id.edtParameter3);
		edtParameter[3] = (EditText) dialog.findViewById(R.id.edtParameter4);
		edtParameter[4] = (EditText) dialog.findViewById(R.id.edtParameter5);

		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		for(int i=0; i<ARRAY_SIZE; i++)
		{
			String preferenceParamValue = sharedPref.getString(inputParam[i], null);
			if( preferenceParamValue != null )
				edtParameter[i].setText(preferenceParamValue);
		}

		Button btnOk = (Button) dialog.findViewById(R.id.btnOK2);
		Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel2);

		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();

				for(int i=0;i<ARRAY_SIZE;i++){
					if (edtParameter[i].getText().toString().length() > 0) {
						parameterValue[i] = edtParameter[i].getText().toString();				
					}
					else{
						parameterValue[i] = "";
					}

					prefsEditor.putString(inputParam[i], parameterValue[i]).commit();
				}

				//				btnQ[3].setEnabled(true);
				//				btnQ[4].setEnabled(true);
				dialog.dismiss();
			}
		});


		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
				for(int i = 0; i < 5; i++){
					edtParameter[i].setText("");
					prefsEditor.remove(inputParam[i]).commit();
					parameterValue[i] = "";
				}

			}
		});

		dialog.show();
	}

	private void prioritizeAlternativeDialog(String prgMessage) {
		final Dialog dialog = new Dialog(getActivity());
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_prioritize_alternative);

		TextView txtMessage3 = (TextView) dialog.findViewById(R.id.txtMessage3);

		viewAlternativeComp[0] = (ViewAlternativeComp) dialog.findViewById(R.id.viewAlternativeComp1);
		viewAlternativeComp[1] = (ViewAlternativeComp) dialog.findViewById(R.id.viewAlternativeComp2);
		viewAlternativeComp[2] = (ViewAlternativeComp) dialog.findViewById(R.id.viewAlternativeComp3);
		viewAlternativeComp[3] = (ViewAlternativeComp) dialog.findViewById(R.id.viewAlternativeComp4);
		viewAlternativeComp[4] = (ViewAlternativeComp) dialog.findViewById(R.id.viewAlternativeComp5);
		Button btnOk 		   = (Button) dialog.findViewById(R.id.btnOk3);
		Button btnCancel 	   = (Button) dialog.findViewById(R.id.btnCancel3);

		for(int i = 0; i < ARRAY_SIZE; i++)
			viewAlternativeComp[i].getID(i);

		for(int i=0; i < ARRAY_SIZE; i++)
			viewAlternativeComp[i].setAltText( parameterValue[i], alternativeValue );

		int iCnt = 0;
		for(int i=0; i < ARRAY_SIZE; i++){

			if( parameterValue[i].isEmpty() )
			{
				iCnt++;
				viewAlternativeComp[i].setVisibility(View.GONE);
			}

			if(iCnt == 5){
				prgMessage = "لطفاً ورودی‌های برنامه را کامل کنید";
				btnCancel.setVisibility(View.GONE);
				btnOk.setText("باشه");
			}

			int jCnt = 0;
			for(int j=0; j < ARRAY_SIZE; j++){			
				if( alternativeValue[j].isEmpty() )
					jCnt++;

				if(jCnt == 5){
					prgMessage =  "لطفاً ورودی‌های برنامه را کامل کنید";
					viewAlternativeComp[i].setVisibility(View.GONE);
					btnCancel.setVisibility(View.GONE);
					btnOk.setText("باشه");
				}
			}
		}

		for(int i=0; i < ARRAY_SIZE; i++){
			for(int j=0; j < ARRAY_SIZE; j++){			
				if( alternativeValue[j].isEmpty() ){
					viewAlternativeComp[i].setVisibilityText(j);
				}
			}
		}

		txtMessage3.setText(prgMessage);

		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				for(int i=0; i<ARRAY_SIZE; i++)	
					for(int j=0; j<ARRAY_SIZE; j++){
						alternativePriorityValue[i][j] = viewAlternativeComp[i].getAlternativeItem()[j];

						Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
						prefsEditor.putInt(ViewAlternativeComp.spinnerAlterSelection[i][j], (int)(5-alternativePriorityValue[i][j])).commit();
					}

				dialog.dismiss();
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
				for(int i=0; i < 5; i++)
					for(int j=0; j < 5; j++)
						prefsEditor.remove(ViewAlternativeComp.spinnerAlterSelection[i][j]).commit();

				for(int i = 0; i < ARRAY_SIZE; i++)
					viewAlternativeComp[i].clearID(i);

				for(int i=0; i < ARRAY_SIZE; i++){
					for(int j=0; j < ARRAY_SIZE; j++){			
						alternativePriorityValue[i][j] = 0.0;
					}
				}
			}
		});

		dialog.show();
	}

	private void prioritizeParameterDialog(String prgMessage) {
		final Dialog dialog = new Dialog(getActivity());
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_prioritize_parameter);

		TextView txtMessage3 = (TextView) dialog.findViewById(R.id.txtMessage3);

		viewParameterComp[0] = (ViewParameterComp) dialog.findViewById(R.id.viewParameterComp1);
		viewParameterComp[1] = (ViewParameterComp) dialog.findViewById(R.id.viewParameterComp2);
		viewParameterComp[2] = (ViewParameterComp) dialog.findViewById(R.id.viewParameterComp3);
		viewParameterComp[3] = (ViewParameterComp) dialog.findViewById(R.id.viewParameterComp4);
		viewParameterComp[4] = (ViewParameterComp) dialog.findViewById(R.id.viewParameterComp5);
		viewParameterComp[5] = (ViewParameterComp) dialog.findViewById(R.id.viewParameterComp6);
		viewParameterComp[6] = (ViewParameterComp) dialog.findViewById(R.id.viewParameterComp7);
		viewParameterComp[7] = (ViewParameterComp) dialog.findViewById(R.id.viewParameterComp8);
		viewParameterComp[8] = (ViewParameterComp) dialog.findViewById(R.id.viewParameterComp9);
		viewParameterComp[9] = (ViewParameterComp) dialog.findViewById(R.id.viewParameterComp10);
		Button btnOk 		 = (Button) dialog.findViewById(R.id.btnOk4);
		Button btnCancel 	 = (Button) dialog.findViewById(R.id.btnCancel4);

		for(int i=0; i < 2*ARRAY_SIZE; i++)
			viewParameterComp[i].getID(i);

		int cnt = 0;
		int jCnt = 0;
		for(int i = 0; i< ARRAY_SIZE; i++){
			for(int j = i+1; j< ARRAY_SIZE; j++){
				viewParameterComp[cnt].setParamText(parameterValue[i], parameterValue[j]);			

				if( parameterValue[i].isEmpty() || parameterValue[j].isEmpty() ){
					viewParameterComp[cnt].setVisibility(View.GONE);
					jCnt++;
				}
				cnt++;		

				if(jCnt == 10){
					prgMessage = "لطفاً ورودی‌های برنامه را کامل کنید";
					btnCancel.setVisibility(View.GONE);
					btnOk.setText("باشه");
				}
			}
		}


		txtMessage3.setText(prgMessage);
		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				int cnt = 0;
				for(int i = 0; i< ARRAY_SIZE; i++){
					for(int j = i+1; j< ARRAY_SIZE; j++){
						parameterPriorityValue[i][j] = viewParameterComp[cnt].getPriParamItem();					

						Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
						if(parameterPriorityValue[i][j] == (double)1/5 )
							prefsEditor.putInt(ViewParameterComp.spinnerParamSelection[cnt], 0 ).commit();
						if(parameterPriorityValue[i][j] == (double)1/3 )
							prefsEditor.putInt(ViewParameterComp.spinnerParamSelection[cnt], 1 ).commit();
						if(parameterPriorityValue[i][j] == 1 )
							prefsEditor.putInt(ViewParameterComp.spinnerParamSelection[cnt], 2 ).commit();
						if(parameterPriorityValue[i][j] == 3 )
							prefsEditor.putInt(ViewParameterComp.spinnerParamSelection[cnt], 3 ).commit();
						if(parameterPriorityValue[i][j] == 5 )
							prefsEditor.putInt(ViewParameterComp.spinnerParamSelection[cnt], 4 ).commit();

						cnt++;		
					}
				}
				//				btnCompute.setEnabled(true);
				dialog.dismiss();
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();

				for(int i = 0; i < 2*ARRAY_SIZE; i++){
					prefsEditor.remove(ViewParameterComp.spinnerParamSelection[i]).commit();
					viewParameterComp[i].clearID(i);
				}

				for(int i=0; i < ARRAY_SIZE; i++){
					for(int j=0; j < ARRAY_SIZE; j++){			
						parameterPriorityValue[i][j] = 0.0;
					}
				}
			}
		});

		dialog.show();
	}

	private void resultDialog(int[] index, double[] maxResult, double sum) {
		final Dialog dialog = new Dialog(G.currentActivity);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_result);

		Typeface txt_numberfont= Typeface.createFromAsset(getActivity().getAssets(), "font.ttf");

		Button btnOk = (Button) dialog.findViewById(R.id.btnOk5);
		TextView[] txtResult = new TextView[6];
		TextView[] txtNumber = new TextView[5];
		TextView[] txtPercentage = new TextView[5];
		LinearLayout[] lytLayoutResult = new LinearLayout[5];
		txtResult[0] = (TextView) dialog.findViewById(R.id.txt_result1);
		txtResult[1] = (TextView) dialog.findViewById(R.id.txt_result2);
		txtResult[2] = (TextView) dialog.findViewById(R.id.txt_result3);
		txtResult[3] = (TextView) dialog.findViewById(R.id.txt_result4);
		txtResult[4] = (TextView) dialog.findViewById(R.id.txt_result5);
		txtNumber[0] = (TextView) dialog.findViewById(R.id.txt_number1);
		txtNumber[1] = (TextView) dialog.findViewById(R.id.txt_number2);
		txtNumber[2] = (TextView) dialog.findViewById(R.id.txt_number3);
		txtNumber[3] = (TextView) dialog.findViewById(R.id.txt_number4);
		txtNumber[4] = (TextView) dialog.findViewById(R.id.txt_number5);
		txtPercentage[0] = (TextView) dialog.findViewById(R.id.txt_percentage1);
		txtPercentage[1] = (TextView) dialog.findViewById(R.id.txt_percentage2);
		txtPercentage[2] = (TextView) dialog.findViewById(R.id.txt_percentage3);
		txtPercentage[3] = (TextView) dialog.findViewById(R.id.txt_percentage4);
		txtPercentage[4] = (TextView) dialog.findViewById(R.id.txt_percentage5);
		lytLayoutResult[0] = (LinearLayout) dialog.findViewById(R.id.lyt_result1);
		lytLayoutResult[1] = (LinearLayout) dialog.findViewById(R.id.lyt_result2);
		lytLayoutResult[2] = (LinearLayout) dialog.findViewById(R.id.lyt_result3);
		lytLayoutResult[3] = (LinearLayout) dialog.findViewById(R.id.lyt_result4);
		lytLayoutResult[4] = (LinearLayout) dialog.findViewById(R.id.lyt_result5);
		
		txtResult[5] = (TextView) dialog.findViewById(R.id.txt_output);	

		for(int i = 0; i < 5; i++){
			txtNumber[i].setTypeface(txt_numberfont);
			txtPercentage[i].setTypeface(txt_numberfont);
		}

		int cnt = 0;
		String[] resultMessage = new String[]{"","","","",""};
		for(int i = 0; i < 5; i++){
			if( !alternativeValue[index[i]].isEmpty() )	{
				resultMessage[i] = alternativeValue[index[i]];
				txtResult[i].setText(resultMessage[i]);
				txtNumber[i].setText(""+(i+1));
				txtPercentage[i].setText( Math.round((maxResult[i]*100)/sum) + "%");
			}else{
				txtResult[i].setVisibility(View.GONE);
				txtNumber[i].setVisibility(View.GONE);
				txtPercentage[i].setVisibility(View.GONE);
				lytLayoutResult[i].setVisibility(View.GONE);
				cnt++;
			}
		}

		if(cnt == 5)
			txtResult[5].setText("لطفاً ورودی‌های برنامه را کامل کنید");
		else
			txtResult[5].setText("میزان تشابه گزینه‌های معرفی شده با اولویت‌های شما به شرح زیر است، پیشنهاد می‌شود گزینه‌هایی با بیشترین درصد تشابه انتخاب شود.");


		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}

}
