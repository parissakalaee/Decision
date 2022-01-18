package ir.parka.decision;

import ir.noghteh.JustifiedTextView;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentHelp extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_help, container, false);

		Typeface txt_font    = Typeface.createFromAsset(getActivity().getAssets(), "BZar_bold.ttf");

		JustifiedTextView[] txtHelp = new JustifiedTextView[10];
		txtHelp[0] = (JustifiedTextView) rootView.findViewById(R.id.txt_help00);
		txtHelp[1] = (JustifiedTextView) rootView.findViewById(R.id.txt_help01);
		txtHelp[2] = (JustifiedTextView) rootView.findViewById(R.id.txt_help02);
		txtHelp[3] = (JustifiedTextView) rootView.findViewById(R.id.txt_help03);
		txtHelp[4] = (JustifiedTextView) rootView.findViewById(R.id.txt_help04);
		txtHelp[5] = (JustifiedTextView) rootView.findViewById(R.id.txt_help05);
		txtHelp[6] = (JustifiedTextView) rootView.findViewById(R.id.txt_help06);
		txtHelp[7] = (JustifiedTextView) rootView.findViewById(R.id.txt_help07);
		txtHelp[8] = (JustifiedTextView) rootView.findViewById(R.id.txt_help08);
		txtHelp[9] = (JustifiedTextView) rootView.findViewById(R.id.txt_help09);
		
		txtHelp[0].setText("شما چگونه تصمیم‌گیری می‌کنید؟ در انتخاب گزینه‌هایی که با آن‌ها مواجه می‌شوید، چگونه پارامترهای تاثیرگذار را اولویت بندی می‌کنید؟");
		txtHelp[1].setText("همگی می‌دانیم که هنگام تصميم گيري بايد تمام پارامترهای مؤثر را به دقت در نظر گرفته و با وزن دهي مناسب به هر كدام از اين پارامترهاي مؤثر، اولویت نسبی آن‌ها را ارزيابي كرد."); 
		txtHelp[2].setText("به‌عنوان نمونه، بررسی فرآيند تصميم‌گيري در مورد خريد يك سيستم کامپیوتری، نتایج جالبی به دنبال دارد. این فرآیند شامل بررسي هزينه، مشخصات فني، هم‌خواني با دانش و تكنولوژي ما در استفاده از آن، سهولت ارتقا و توسعه آن، اعتبار سازنده آن، امكانات جانبي نرم‌افزاري و سخت‌افزاري، سابقه بهره‌برداري، سهولت خدمات پس از فروش و ... است.");
		txtHelp[3].setText("سوال این که در خصوص انتخاب خرید یک سیستم کامپیوتری، اولویت این پارامترها را چگونه باید ارزیابی کرد؟ پس از تدوین این اولویت‌ها، از میان گزینه‌های متعدد موجود، کدام سیستم کامپیوتری، به بهترین شکل، نیاز ما را برآورده می‌کند.");
		txtHelp[4].setText("نکته قابل توجه این است که تصمیم‌هایی که به صورت روزمره با آن‌ها مواجه می‌شویم، بسیار متنوع‌تر از تصمیم خرید نوع خاصی از محصولات کامپیوتری است. تصمیم گیری در خصوص ادامه فعالیت‌های ورزشی، ثبت نام در یک باشگاه ورزشی، خارج شدن از یک گروه دوستانه، تعیین مقصد مسافرت، تغییر محل کار/مدرسه/باشگاه ورزشی، انتخاب رشته تحصیلی و ... و حتی تصمیم‌گیری‌های ساده‌تری نظیر سوال تکراری : امروز ناهار چی درست کنم؟ دو راهی یا چندراهی‌های متعددی را در برابر ما قرار می‌دهند.");
		txtHelp[5].setText("در بسیاری از این موارد، تنها انتخاب ممکن، مشورت با افرادی است که دوست یا معتمد ما هستند و در خصوص موضوع تصمیم گیری، تجربه و دانش دارند. اما آیا همیشه به چنین افرادی دسترسی داریم؟ آیا این دسترسی، همواره رایگان است؟");
		txtHelp[6].setText("مساله بسیار مهم تر این است که این مراجع تصمیم گیری، عمدتاً پاسخ خود را به صورت: خود شما کدامیک را بیشتر می پسندید؟ کدامیک بیش تر نیازهای شما را برآورده می کنند؟ و عباراتی کلی از این دست بیان می کنند.");
		txtHelp[7].setText("حالت دیگر بسیار متداول، این که ما با فرد معتمد خبره مواجه شده‌ایم و هزینه مشاوره را نیز پرداخت کرده‌ایم اما آنچه که او پیشنهاد می‌کند،متاسفانه به دل ما نمی‌نشیند! گمان می‌کنیم یا چیزی را در نظر نگرفته است و یا جوابی کلی به ما داده است. حتی ممکن است تصور کنیم که او  شرایط خاص  ما را درست درک نکرده است.");
		txtHelp[8].setText("در چنین حالت‌هایی، که هر یک از ما، تجارب متعددی از مواجهه با آن داریم، چاره چیست؟ ما عمدتاً می دانیم چه چیزی مهم است یا حداقل چه پارامترهایی برای ما مهم هستند (بعنوان نمونه، پنج یا سه پارامتر موثر). از سوی دیگر، گزینه‌های تصمیم گیری نیز مشخص و محدود هستند (بعنوان نمونه، دو یا سه گزینه). آنچه که نمی‌دانیم، چگونگی اولویت‌بندی مجموعه پارامترها و تصمیم گیری نهایی در خصوص گزینه یا گزینه‌های مطلوب و اولویت‌بندی نهایی گزینه‌های برتر است.");
		txtHelp[9].setText("تلاش شده است  که برنامه کاربردی تصمیم، پاسخ سوالات مطرح‌شده را فراهم کرده و در تصمیم گیری‌های ما، نقش یاوری معتمد را ایفا کند.");

		for(int i = 0; i < 10 ; i++){
			txtHelp[i].setTypeFace(Typeface.createFromAsset(getActivity().getAssets(), "BZar.ttf"));
			txtHelp[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,19);
			txtHelp[i].setLineSpace(8);
		}

		TextView txtHelpSubject  = (TextView) rootView.findViewById(R.id.txt_helpsubj1);
		txtHelpSubject.setTypeface(txt_font);
		return rootView;
	}
}
