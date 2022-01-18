package ir.parka.decision;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class G extends Application{

	public static Context context;
	public static final String LOG_TAG = "Decision";
	public static Activity currentActivity;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		context = getApplicationContext();
	}

}
