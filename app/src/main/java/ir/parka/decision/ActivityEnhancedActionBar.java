package ir.parka.decision;

import ir.noghteh.JustifiedTextView;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public abstract class ActivityEnhancedActionBar extends AppCompatActivity implements OnMenuItemClickListener{

	@Override
	protected void onPause() {
		Log.i(G.LOG_TAG, "Pause from " + getClass().getSimpleName() + " activity");
		super.onPause();
	}


	@Override
	protected void onResume() {
		Log.i(G.LOG_TAG, "Resume from " + getClass().getSimpleName() + " activity");
		G.currentActivity = this;
		super.onResume();
	}

	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu) {
	//
	//		// Inflate the menu; this adds items to the action bar if it is present.
	//		getMenuInflater().inflate(R.menu.main, menu);
	//
	//		return super.onCreateOptionsMenu(menu);
	//	}
	//
	//
	//	@Override
	//	public boolean onOptionsItemSelected(MenuItem item) {
	//		switch (item.getItemId()) {
	//		case R.id.menu_setting:
	//			Toast.makeText(G.context, item.getTitle() + " Clicked", Toast.LENGTH_SHORT).show();
	//			break;
	//
	//		case R.id.menu_close:
	//			Toast.makeText(G.context, item.getTitle() + " Clicked", Toast.LENGTH_SHORT).show();
	//			break;
	//		}
	//		return super.onOptionsItemSelected(item);
	//	}


	public void onCreatePopupMenu(View v) {

		PopupMenu myPopupMenu = new PopupMenu(this, v);

		myPopupMenu.setOnMenuItemClickListener(this);
		getMenuInflater().inflate(R.menu.main, myPopupMenu.getMenu());

		//registering popup with OnMenuItemClickListener  
		myPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
			@Override
			public boolean onMenuItemClick(MenuItem item) {  

				switch (item.getItemId()) {
				case R.id.menu_feedback:
					aboutDialog();						
					break;

				case R.id.menu_close:
					G.currentActivity.finish();
					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_HOME);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					G.currentActivity.startActivity(intent);
					System.exit(0);

					break;
				}          
				return true;  
			}  
		});  

		myPopupMenu.show();
	}

	private void aboutDialog() {
		final Dialog dialog = new Dialog(G.currentActivity);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_about);

		JustifiedTextView[] txtAbout = new JustifiedTextView[3];
		txtAbout[0]	= (JustifiedTextView) dialog.findViewById(R.id.txt_about1);
		txtAbout[1] = (JustifiedTextView) dialog.findViewById(R.id.txt_about2);
		txtAbout[2] = (JustifiedTextView) dialog.findViewById(R.id.txt_about3);

		txtAbout[0].setText("گروه نرم افزاری پرکا در سال 1393 و با تمرکز بر توسعه برنامه‌های کاربردی موبایل در محیط اندروید تشکیل گردید و نخستین محصول خود را در فروردین 1394 و تحت عنوان پس انداز سنج منتشر نمود.");
		txtAbout[1].setText("درحال حاضر این گروه شامل چهار بخش مجزا است که معرفی ایده، تهیه نرم افزار، تست محصول و مدیریت را بر عهده دارند. این گروه قصد دارد در ادامه فعالیت خود، در زمینه رابط‌های سخت افزاری و میان‌افزارها نیز خدمات کاربردی ارائه کند.");
		txtAbout[2].setText("در صورت تمایل به مشارکت در پروژه‌های این گروه و یا درخواست تدوین برنامه‌های کاربردی موبایل توسط پرکا، خواهشمند است از طریق آدرس ایمیلی که در ادامه ارائه شده است، تماس حاصل نمایید.");

		for(int i = 0; i < 3 ; i++){
			txtAbout[i].setTypeFace(Typeface.createFromAsset(getAssets(), "BZar.ttf"));
			txtAbout[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,19);
			txtAbout[i].setLineSpace(8);
		}

		Button btnOk = (Button) dialog.findViewById(R.id.btnOk5);
		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}
}
