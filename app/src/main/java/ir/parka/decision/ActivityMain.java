package ir.parka.decision;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityMain extends ActivityEnhancedActionBar {

	public long back_pressed;
	@Override
	public void onBackPressed()
	{
		if (back_pressed + 2000 > System.currentTimeMillis()) {
			super.onBackPressed();
			G.currentActivity.finish();
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			G.currentActivity.startActivity(intent);
			System.exit(0);
		}
		else 
			Toast.makeText(G.context, "جهت خروج کلید بازگشت را مجدداً بفشارید", Toast.LENGTH_LONG).show();

		back_pressed = System.currentTimeMillis();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initilization
		ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		PagerAdapter  myAdapter = new PagerAdapter(getSupportFragmentManager());
//		PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_tab_strip);
//		myPagerTabStrip.setTabIndicatorColor(Color.parseColor("#00FFFF"));
		
		
		LayoutInflater Inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

		View view = Inflater.inflate(R.layout.custom_action_bar, null );
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00FFFF")));

		viewPager.setAdapter(myAdapter);
		viewPager.setCurrentItem(1);
		
		ImageView imgClose = (ImageView) findViewById(R.id.imgClose);
		imgClose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				onCreatePopupMenu(view);
			}
		});

	}

	@Override
	public boolean onMenuItemClick(MenuItem arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
