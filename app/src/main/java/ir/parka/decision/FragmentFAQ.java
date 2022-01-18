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

public class FragmentFAQ extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_faq, container, false);
			
		Typeface txt_font    = Typeface.createFromAsset(getActivity().getAssets(), "BZar_bold.ttf");

		JustifiedTextView[] txtFAQ = new JustifiedTextView[28];
		txtFAQ[0] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ00);
		txtFAQ[1] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ01);
		txtFAQ[2] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ02);
		txtFAQ[3] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ03);
		txtFAQ[4] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ04);
		txtFAQ[5] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ05);
		txtFAQ[6] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ06);
		txtFAQ[7] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ07);
		txtFAQ[8] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ08);
		txtFAQ[9] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ09);
		txtFAQ[10] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ10);
		txtFAQ[11] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ11);
		txtFAQ[12] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ12);
		txtFAQ[13] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ13);
		txtFAQ[14] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ14);
		txtFAQ[15] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ15);
		txtFAQ[16] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ16);
		txtFAQ[17] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ17);
		txtFAQ[18] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ18);
		txtFAQ[19] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ19);
		txtFAQ[20] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ20);
		txtFAQ[21] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ21);
		txtFAQ[22] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ22);
		txtFAQ[23] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ23);
		txtFAQ[24] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ24);
		txtFAQ[25] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ25);
		txtFAQ[26] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ26);
		txtFAQ[27] = (JustifiedTextView) rootView.findViewById(R.id.txt_FAQ27);
		
		txtFAQ[0].setText("این بخش بدین منظور طراحی شده است که قبل از ورود به مراحل بعد، موضوع تصمیم‌گیری برای شما روشن باشد. در واقع با پاسخ صریح به این سئوال، فرآیند تصمیم‌گیری را شفاف شروع خواهید کرد. به عنوان مثال، انتخاب مقصد سفر، می‌تواند موضوع تصمیم‌گیری باشد.");
		txtFAQ[1].setText("یکی از مفاهیم اصلی در تصمیم‌گیری، گزینه‌ها هستند. گزینه‌ها، انتخاب‌های مختلفی هستند که یکی از آن‌ها در نهایت خروجی فرآیند تصمیم‌گیری خواهد بود. به عنوان مثال، در فرآیند تصمیم‌گیری انتخاب مقصد سفر، گزینه‌ها می‌توانند شامل شیراز، اصفهان، همدان و ... باشند. برنامه به گونه‌ای طراحی شده است که تا 5 گزینه به عنوان ورودی می‌پذیرد، از لحاظ منطقی در کمتر از 2 گزینه تصمیم‌گیری معنا ندارد."); 
		txtFAQ[2].setText(".از پارامترها، به عنوان عوامل تاثیرگذار بر انتخاب گزینه‌ها یاد شده و ریشه و مبانی فرآیند تصمیم‌گیری هستند، به عنوان مثال در فرآیند تصمیم‌گیری انتخاب مقصد سفر، پارامترهای موثر می‌توانند شامل هزینه اقامت، نظر اعضای خانواده و مسیر سفر باشد. برنامه به گونه ای طراحی شده است که تا 5 پارامتر به عنوان ورودی می‌پذیرد، از آنجا که در کمتر از 2 پارامتر نیازی به محاسبات پیچیده برای تصمیم‌گیری نیست، حداقل پارامترها 2 در نظر گرفته شده‌است");
		txtFAQ[3].setText("در ادامه و با اولویت‌بندی گزینه‌های بر اساس پارامترها مختلف، میزان برتری گزینه‌های مختلف، از دید تک تک پارامترها مورد ارزیابی قرار خواهند گرفت. در این مرحله، به هر گزینه و از نگاه هر پارامتر، امتیازی از بد تا عالی داده می‌شود.");
		txtFAQ[4].setText("گام بعدی، ارزیابی و اولویت‌بندی پارامترهای تصمیم‌گیری است. این کار از طریق مقایسه دو به دو پارامترها صورت گرفته و برنامه به صورت خودکار، محاسبات اولولویت‌بندی را انجام خواهد داد. در خلال مقایسه دو به دو پارامترها، به تعداد مورد نیاز سوالاتی در قالب: (پارامتر 1 ...... پارامتر 2 است) مطرح می‌شود. جای خالی در این سوالات به انتخاب کاربر و و از میان عبارت‌های بسيار کم اهمیت‌تر از، کم اهمیت‌تر، دارای اهمیت تقریباً مشابه با، اندکی مهم‌تر از، بسيار مهم‌تر از انتخاب خواهد شد.");
		txtFAQ[5].setText("وقتی که به این مرحله برسیم، عملاً برنامه به پایان رسیده و بعد از لمس کلید (محاسبه کن) همه گزینه‌ها به ترتیب، اولویت بندی شده و به همراه درصد اهمیت ارائه خواهند شد.");
		txtFAQ[6].setText("در این برنامه، گزینه (پاک شود) در صفحه اصلی، تمام اطلاعات ورودی را پاک می‌کند اما گزینه‌ (پاک شود) در صفحات داخلی، تنها اطلاعات همان صفحه را پاک خواهد کرد.");
		txtFAQ[7].setText("مطمئن شوید که تمام چهار مورد اطلاعات ورودی را به برنامه داده و تایید کرده‌اید. این چهار گروه، شامل گزینه‌ها، پارامترها، اولویت‌بندی گزینه ها بر اساس تک تک پارامترها و نهایتاً مقایسه دو به دو پارامترها هستند. باید دقت کرد که دسترسی به اولویت‌بندی گزینه‌ها بر اساس تک تک پارامترها و همچنین مقایسه دو به دو پارامترها، تنها پس از معرفی و تایید گزینه‌ها و پارامترها امکان‌پذیر است." );
		txtFAQ[8].setText("فرض کنید که می‌خواهیم تصمیم بگیریم که در یک باشگاه ورزشی مشخص، که دوستمان در آنجا مشغول است، ثبت نام کنیم یا زمان مربوط به این کار را به موسیقی اختصاص دهیم." );
		txtFAQ[9].setText("این مساله، فعلاً دو گزینه دارد؛ ورزش با دوستان و تمرین موسیقی. اما پارامترهای ما چیست؟ به عبارت دیگر، چه مسائلی در این تصمیم‌گیری مهم هستند؟ مسائلی که اگر هر یک از آنها را به تنهایی در نظر بگیریم، ممکن است پاسخ مخصوص به خود را به عنوان انتخاب و خروجی نهایی این فرآیند تصمیم‌گیری معرفی کنند." );
		txtFAQ[10].setText("فرض کنید که پارامترهای تصمیم‌گیری مساله شامل سه پارامتر به شرح زیر باشند." );
		txtFAQ[11].setText("1- زمان، 2- توان مالی، 3- همراهی با دوستان." );
		txtFAQ[12].setText("گام اول، اولویت بندی گزینه‌ها بر اساس تک تک پارامترها است. بدین منظور باید جذابیت گزینه‌های (ورزش با دوستان) و (تمرین موسیقی) را بر اساس پارارمترهای (زمان)، (توان مالی) و (همراهی با دوستان) بررسی کرده و از نگاه هر پارامتر، امتیازی از بد تا عالی به هر یک از گزینه‌ها داد." );
		txtFAQ[13].setText("امتیاز از نظر زمان" );
		txtFAQ[14].setText(" ورزش با دوستان = عالی و تمرین موسیقی = متوسط" );
		txtFAQ[15].setText("ممکن است ورزش با دوستان در این باشگاه خاص، نیازمند صرف زمان قابل توجهی نباشد یا نسبت به تمرین موسیقی، به زمان کمتری نیاز داشته باشد. بنابراین از دید زمان، ورزش با دوستان دارای اولویت بیش تر نسبت به تمرین موسیقی است." );
		txtFAQ[16].setText("امتیاز از نظر توان مالی" );
		txtFAQ[17].setText(" ورزش با دوستان = متوسط و تمرین موسیقی = عالی");
		txtFAQ[18].setText("ممکن است توان مالی کافی پرداخت شهریه باشگاه را نداشته باشیم یا ممکن است تمرین موسیقی در فرهنگسرای محله، کم هزینه تر از شهریه باشگاه باشد. بنابراین اگر ما قرار بود فقط از این نظر تصمیم‌گیری کنیم، تمرین موسیقی امتیاز و اولویت بسیار بیش تری داشت." );
		txtFAQ[19].setText("امتیاز از نظر همراهی با دوستان" );
		txtFAQ[20].setText(" ورزش با دوستان = عالی و  تمرین موسیقی = بد" );
		txtFAQ[21].setText("هرچند ما دوستان خود را در محله، مدرسه، محیط کار، مهمانی خانوادگی و ... می‌بینیم و با آن‌ها همراهی داریم اما با هم بودن بیش تر، کماکان جذابیت دارد  یا ممکن است در کلاس موسیقی دوستی نداشته باشیم. بنابراین از نظر همراهی با دوستان، ورزش با دوستان، بهترین تصمیم است." );
		txtFAQ[22].setText("همان‌طور که مشاهده شد، تا این مرحله نمی‌توان تصمیم‌گیری کرد چون از دید پارارمترهای مختلف، گزینه‌های نهایی تصمیم‌گیری یکسان نیستند و ما برای تصمیم‌گیری نهایی نیاز داریم که اهمیت پارامترها نسبت به یکدیگر را نیز بدانیم. این کار از طریق پاسخ به سوالات زیر صورت می‌گیرد." );
		txtFAQ[23].setText("زمان .......... بسيار کم اهمیت تر از........ توان مالی است." );
		txtFAQ[24].setText("زمان........... دارای اهمیت مشابه با....... همراهی با دوستان است." );
		txtFAQ[25].setText("توان مالی ........ بسيار کم اهمیت تر از...... همراهی با دوستان است." );
		txtFAQ[26].setText("هرچند برنامه به صورت اتوماتیک، اولویت‌بندی پارامترها را محاسبه خواهد کرد، اما باید توجه داشت که برنامه بر اساس اطلاعاتی که از ما می‌گیرد، این کار را انجام می‌دهد. بنابراین، باید تلاش کنیم که پاسخ به سوالات اولویت بندی، پاسخی دقیق و درست باشد." );
		txtFAQ[27].setText("وقتی که به این مرحله برسیم، عملاً برنامه به پایان رسیده و باید تصمیم نهایی را اجرا کرد. امیدواریم که در اجرای تصمیم منتخب موفق باشید." );

		for(int i = 0; i < 28 ; i++){
			txtFAQ[i].setTypeFace(Typeface.createFromAsset(getActivity().getAssets(), "BZar.ttf"));
			txtFAQ[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,19);
			txtFAQ[i].setLineSpace(8);
		}

		TextView[] txtFAQSubject = new TextView[3]; 
		txtFAQSubject[0]  = (TextView) rootView.findViewById(R.id.txt_FAQsubj1);
		txtFAQSubject[1]  = (TextView) rootView.findViewById(R.id.txt_FAQsubj2);
		txtFAQSubject[2]  = (TextView) rootView.findViewById(R.id.txt_FAQsubj3);
		
		for(int i = 0; i < 3 ; i++)
			txtFAQSubject[i].setTypeface(txt_font);
		
		
		TextView[] txtFAQInput = new TextView[8]; 
		txtFAQInput[0]  = (TextView) rootView.findViewById(R.id.txt_FAQinput1);
		txtFAQInput[1]  = (TextView) rootView.findViewById(R.id.txt_FAQinput2);
		txtFAQInput[2]  = (TextView) rootView.findViewById(R.id.txt_FAQinput3);
		txtFAQInput[3]  = (TextView) rootView.findViewById(R.id.txt_FAQinput4);
		txtFAQInput[4]  = (TextView) rootView.findViewById(R.id.txt_FAQinput5);
		txtFAQInput[5]  = (TextView) rootView.findViewById(R.id.txt_FAQinput6);
		txtFAQInput[6]  = (TextView) rootView.findViewById(R.id.txt_FAQinput7);
		txtFAQInput[7]  = (TextView) rootView.findViewById(R.id.txt_FAQinput8);
		
		for(int i = 0; i < 8 ; i++)
			txtFAQInput[i].setTypeface(txt_font);
		
		return rootView;
	}

}


