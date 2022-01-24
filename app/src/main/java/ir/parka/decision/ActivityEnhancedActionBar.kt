package ir.parka.decision

import android.app.Dialog
import android.content.Intent
import android.graphics.Typeface
import android.util.Log
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import ir.noghteh.JustifiedTextView

abstract class ActivityEnhancedActionBar constructor() : AppCompatActivity(),
    PopupMenu.OnMenuItemClickListener {
    override fun onPause() {
        Log.i(G.Companion.LOG_TAG, "Pause from " + javaClass.getSimpleName() + " activity")
        super.onPause()
    }

    override fun onResume() {
        Log.i(G.Companion.LOG_TAG, "Resume from " + javaClass.getSimpleName() + " activity")
        G.Companion.currentActivity = this
        super.onResume()
    }

    fun onCreatePopupMenu(v: View?) {
        val myPopupMenu: PopupMenu = PopupMenu(this, (v)!!)
        myPopupMenu.setOnMenuItemClickListener(this)
        getMenuInflater().inflate(R.menu.main, myPopupMenu.getMenu())

        //registering popup with OnMenuItemClickListener  
        myPopupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            public override fun onMenuItemClick(item: MenuItem): Boolean {
                when (item.getItemId()) {
                    R.id.menu_feedback -> aboutDialog()
                    R.id.menu_close -> {
                        G.Companion.currentActivity!!.finish()
                        val intent: Intent = Intent(Intent.ACTION_MAIN)
                        intent.addCategory(Intent.CATEGORY_HOME)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        G.Companion.currentActivity!!.startActivity(intent)
                        System.exit(0)
                    }
                }
                return true
            }
        })
        myPopupMenu.show()
    }

    private fun aboutDialog() {
        val dialog: Dialog = Dialog((G.Companion.currentActivity)!!)
        dialog.getWindow()!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_about)
        val txtAbout: Array<JustifiedTextView?> = arrayOfNulls(3)
        txtAbout[0] = dialog.findViewById<View>(R.id.txt_about1) as JustifiedTextView?
        txtAbout[1] = dialog.findViewById<View>(R.id.txt_about2) as JustifiedTextView?
        txtAbout[2] = dialog.findViewById<View>(R.id.txt_about3) as JustifiedTextView?
        txtAbout[0]!!
            .setText("گروه نرم افزاری پرکا در سال 1393 و با تمرکز بر توسعه برنامه‌های کاربردی موبایل در محیط اندروید تشکیل گردید و نخستین محصول خود را در فروردین 1394 و تحت عنوان پس انداز سنج منتشر نمود.")
        txtAbout[1]!!
            .setText("درحال حاضر این گروه شامل چهار بخش مجزا است که معرفی ایده، تهیه نرم افزار، تست محصول و مدیریت را بر عهده دارند. این گروه قصد دارد در ادامه فعالیت خود، در زمینه رابط‌های سخت افزاری و میان‌افزارها نیز خدمات کاربردی ارائه کند.")
        txtAbout[2]!!
            .setText("در صورت تمایل به مشارکت در پروژه‌های این گروه و یا درخواست تدوین برنامه‌های کاربردی موبایل توسط پرکا، خواهشمند است از طریق آدرس ایمیلی که در ادامه ارائه شده است، تماس حاصل نمایید.")
        for (i in 0..2) {
            txtAbout.get(i)!!.setTypeFace(Typeface.createFromAsset(getAssets(), "BZar.ttf"))
            txtAbout.get(i)!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19f)
            txtAbout.get(i)!!.setLineSpace(8)
        }
        val btnOk: Button = dialog.findViewById<View>(R.id.btnOk5) as Button
        btnOk.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(arg0: View) {
                dialog.dismiss()
            }
        })
        dialog.show()
    }
}