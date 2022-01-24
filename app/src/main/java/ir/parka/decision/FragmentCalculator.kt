package ir.parka.decision

import android.app.Dialog
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.Fragment

class FragmentCalculator() : Fragment() {
    var inputDecisionValue = ""
    var parameterValue = arrayOf("", "", "", "", "")
    var alternativeValue = arrayOf("", "", "", "", "")
    var viewAlternativeComp = arrayListOf<ViewAlternativeComp>()
    var viewParameterComp = arrayListOf<ViewParameterComp>()
    var alternativePriorityValue = Array(ARRAY_SIZE) { DoubleArray(ARRAY_SIZE) }
    var parameterPriorityValue = Array(ARRAY_SIZE) { DoubleArray(ARRAY_SIZE) }
    var myParameterPriorityValue = Array(ARRAY_SIZE) { DoubleArray(ARRAY_SIZE) }
    var edtParameter = arrayOfNulls<EditText>(ARRAY_SIZE)
    var edtAlternatives = arrayOfNulls<EditText>(ARRAY_SIZE)
    var edtInputDecision: EditText? = null
    var btnQ = arrayOfNulls<Button>(ARRAY_SIZE)
    var btnCompute: Button? = null
    var btnReset: Button? = null
    var inputParam = arrayOf("param0", "param1", "param2", "param3", "param4")
    var inputAlter = arrayOf("alter0", "alter1", "alter2", "alter3", "alter4")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_calculator, container, false)
        val txt_font = Typeface.createFromAsset(requireActivity().assets, "BZar_bold.ttf")
        btnQ[0] = rootView.findViewById<View>(R.id.btnQ_1) as Button
        btnQ[1] = rootView.findViewById<View>(R.id.btnQ_2) as Button
        btnQ[2] = rootView.findViewById<View>(R.id.btnQ_3) as Button
        btnQ[3] = rootView.findViewById<View>(R.id.btnQ_4) as Button
        btnQ[4] = rootView.findViewById<View>(R.id.btnQ_5) as Button
        btnCompute = rootView.findViewById<View>(R.id.btnCompute) as Button
        btnReset = rootView.findViewById<View>(R.id.btnReset) as Button

        //		btnQ[3].setEnabled(false);
        //		btnQ[4].setEnabled(false);
        //		btnCompute.setEnabled(false);
        for (i in 0..4) btnQ[i]!!.setTypeface(txt_font)
        btnCompute!!.setTypeface(txt_font)
        btnReset!!.setTypeface(txt_font)
        btnQ[0]!!.setOnClickListener(View.OnClickListener { inputSubjectDialog("لطفاً مورد تصمیم گیری را وارد کنید (به عنوان مثال: انتخاب مقصد سفر، انتخاب هدیه، ....) ") })
        btnQ[1]!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                inputAlternativeDialog("لطفاً گزینه‌های موجود در تصمیم گیری را وارد کنید (به عنوان مثال در خصوص مقصد سفر: همدان، اصفهان....)")
            }
        })
        btnQ[2]!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                inputParameterDialog("لطفاً پارامترهای تاثیرگذار در تصمیم گیری را وارد کنید (به عنوان مثال در خصوص مقصد سفر: هزینه اقامت، مسیر جاده....)")
            }
        })
        btnQ[3]!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                prioritizeAlternativeDialog("لطفاً به هر گزینه از دیدگاه پارامترها امتیاز دهید، به عنوان مثال در خصوص مقصد سفر، از دیدگاه پارامتر مسیر جاده، گزینه همدان چه امتیازی می گیرد (عالی، خوب، ...)؟")
            }
        })
        btnQ[4]!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                prioritizeParameterDialog("لطفاً پارامترها را نسبت به هم طبقه بندی کنید(به عنوان مثال در خصوص مقصد سفر، مسیر جاده مهمتر است یا هزینه اقامت....)")
            }
        })
        btnCompute!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                for (i in 0 until ARRAY_SIZE) {
                    for (j in 0 until ARRAY_SIZE) {
                        myParameterPriorityValue[i][j] = 0.0
                    }
                }
                myParameterPriorityValue[0][0] = 1.0
                myParameterPriorityValue[1][1] = 1.0
                myParameterPriorityValue[2][2] = 1.0
                myParameterPriorityValue[3][3] = 1.0
                myParameterPriorityValue[4][4] = 1.0
                for (i in 0 until ARRAY_SIZE) {
                    for (j in i + 1 until ARRAY_SIZE) {
                        if (parameterPriorityValue[i][j] != 0.0) {
                            myParameterPriorityValue[i][j] = parameterPriorityValue[i][j]
                            myParameterPriorityValue[j][i] =
                                1.toDouble() / parameterPriorityValue[i][j]
                        }
                    }
                }
                if ((myParameterPriorityValue[0][1] == 0.0) && (myParameterPriorityValue[0][2] == 0.0) && (myParameterPriorityValue[0][3] == 0.0) && (myParameterPriorityValue[0][4] == 0.0)) {
                    myParameterPriorityValue[0][0] = 0.0
                }
                if ((myParameterPriorityValue[1][0] == 0.0) && (myParameterPriorityValue[1][2] == 0.0) && (myParameterPriorityValue[1][3] == 0.0) && (myParameterPriorityValue[1][4] == 0.0)) {
                    myParameterPriorityValue[1][1] = 0.0
                }
                if ((myParameterPriorityValue[2][0] == 0.0) && (myParameterPriorityValue[2][1] == 0.0) && (myParameterPriorityValue[2][3] == 0.0) && (myParameterPriorityValue[2][4] == 0.0)) {
                    myParameterPriorityValue[2][2] = 0.0
                }
                if ((myParameterPriorityValue[3][0] == 0.0) && (myParameterPriorityValue[3][1] == 0.0) && (myParameterPriorityValue[3][2] == 0.0) && (myParameterPriorityValue[3][4] == 0.0)) {
                    myParameterPriorityValue[3][3] = 0.0
                }
                if ((myParameterPriorityValue[4][0] == 0.0) && (myParameterPriorityValue[4][1] == 0.0) && (myParameterPriorityValue[4][2] == 0.0) && (myParameterPriorityValue[4][3] == 0.0)) {
                    myParameterPriorityValue[4][4] = 0.0
                }
                val sumVerticalParam = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0)
                for (i in 0 until ARRAY_SIZE) {
                    for (j in 0 until ARRAY_SIZE) {
                        sumVerticalParam[i] += myParameterPriorityValue[j][i]
                    }

                    //					Toast.makeText(G.context, "" + sumVerticalParam[i], Toast.LENGTH_SHORT).show();
                }
                val tmpParam = Array(ARRAY_SIZE) { DoubleArray(ARRAY_SIZE) }
                for (i in 0 until ARRAY_SIZE) {
                    for (j in 0 until ARRAY_SIZE) {
                        if (sumVerticalParam[i] != 0.0) {
                            tmpParam[j][i] = (myParameterPriorityValue[j][i] / sumVerticalParam[i])
                            //							Toast.makeText(G.context, "" + tmpParam[j][i], Toast.LENGTH_SHORT).show();
                        } else tmpParam[j][i] = 0.0
                    }
                }
                val sumHorizontalParam = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0)
                for (i in 0 until ARRAY_SIZE) {
                    for (j in 0 until ARRAY_SIZE) {
                        sumHorizontalParam[i] += tmpParam[i][j]
                    }
                    //					Toast.makeText(G.context, "" + sumHorizontalParam[i], Toast.LENGTH_SHORT).show();
                }
                val tmpAlternative = Array(ARRAY_SIZE) { DoubleArray(ARRAY_SIZE) }
                for (i in 0 until ARRAY_SIZE) {
                    for (j in 0 until ARRAY_SIZE) {
                        if (sumHorizontalParam[i] != 0.0) {
                            tmpAlternative[j][i] =
                                (alternativePriorityValue[i][j] * sumHorizontalParam[i])
                            //							Toast.makeText(G.context, "" + alternativePriorityValue[i][j], Toast.LENGTH_SHORT).show();
                        } else tmpAlternative[j][i] = 0.0
                    }
                }
                var finalSum = 0.0
                val sumHorizontalAlternative = arrayOf(0.0, 0.0, 0.0, 0.0, 0.0)
                for (i in 0 until ARRAY_SIZE) {
                    for (j in 0 until ARRAY_SIZE) {
                        sumHorizontalAlternative[i] += tmpAlternative[i][j]
                    }
                    //					Toast.makeText(G.context, "" + sumHorizontalAlternative[i], Toast.LENGTH_SHORT).show();
                    finalSum += sumHorizontalAlternative[i]
                }
                val maxResult = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0)
                val maxIndex = intArrayOf(0, 0, 0, 0, 0)
                for (i in 0 until ARRAY_SIZE) {
                    if (sumHorizontalAlternative[i] >= maxResult[0]) {
                        maxResult[0] = sumHorizontalAlternative[i]
                        maxIndex[0] = i
                    }
                }
                sumHorizontalAlternative[maxIndex[0]] = 0.0
                for (i in 0 until ARRAY_SIZE) {
                    if (sumHorizontalAlternative[i] >= maxResult[1]) {
                        maxResult[1] = sumHorizontalAlternative[i]
                        maxIndex[1] = i
                    }
                }
                sumHorizontalAlternative[maxIndex[1]] = 0.0
                for (i in 0 until ARRAY_SIZE) {
                    if (sumHorizontalAlternative[i] >= maxResult[2]) {
                        maxResult[2] = sumHorizontalAlternative[i]
                        maxIndex[2] = i
                    }
                }
                sumHorizontalAlternative[maxIndex[2]] = 0.0
                for (i in 0 until ARRAY_SIZE) {
                    if (sumHorizontalAlternative[i] >= maxResult[3]) {
                        maxResult[3] = sumHorizontalAlternative[i]
                        maxIndex[3] = i
                    }
                }
                sumHorizontalAlternative[maxIndex[3]] = 0.0
                for (i in 0 until ARRAY_SIZE) {
                    if (sumHorizontalAlternative[i] >= maxResult[4]) {
                        maxResult[4] = sumHorizontalAlternative[i]
                        maxIndex[4] = i
                    }
                }
                resultDialog(maxIndex, maxResult, finalSum)
            }
        })
        btnReset!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                prefsEditor.clear().commit()
                inputDecisionValue = ""
                for (i in 0 until ARRAY_SIZE) {
                    alternativeValue[i] = ""
                    parameterValue[i] = ""
                }
                for (i in 0 until ARRAY_SIZE) {
                    for (j in 0 until ARRAY_SIZE) {
                        parameterPriorityValue[i][j] = 0.0
                        alternativePriorityValue[i][j] = 0.0
                    }
                }
            }
        })
        return rootView
    }

    private fun inputSubjectDialog(prgMessage: String) {
        val dialog = Dialog((activity)!!)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_input_subject)
        val txtMessage1 = dialog.findViewById<View>(R.id.txtMessage1) as TextView
        txtMessage1.text = prgMessage
        edtInputDecision = dialog.findViewById<View>(R.id.edtInputDecision) as EditText
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        val preferenceSubjectValue = sharedPref.getString("subject", null)
        if (preferenceSubjectValue != null) edtInputDecision!!.setText(preferenceSubjectValue)
        val btnOk = dialog.findViewById<View>(R.id.btnOk) as Button
        val btnCancel = dialog.findViewById<View>(R.id.btnCancel) as Button
        btnOk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                if (edtInputDecision!!.text.toString().length > 0) {
                    inputDecisionValue = edtInputDecision!!.text.toString()
                } else {
                    inputDecisionValue = ""
                }
                prefsEditor.putString("subject", inputDecisionValue).commit()
                dialog.dismiss()
            }
        })
        btnCancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                edtInputDecision!!.setText("")
                prefsEditor.remove("subject").commit()
                inputDecisionValue = ""
            }
        })
        dialog.show()
    }

    private fun inputAlternativeDialog(prgMessage: String) {
        val dialog = Dialog((activity)!!)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_input_alternatives)
        val txtMessage2 = dialog.findViewById<View>(R.id.txtMessage2) as TextView
        txtMessage2.text = prgMessage
        edtAlternatives[0] = dialog.findViewById<View>(R.id.edtAlternative1) as EditText
        edtAlternatives[1] = dialog.findViewById<View>(R.id.edtAlternative2) as EditText
        edtAlternatives[2] = dialog.findViewById<View>(R.id.edtAlternative3) as EditText
        edtAlternatives[3] = dialog.findViewById<View>(R.id.edtAlternative4) as EditText
        edtAlternatives[4] = dialog.findViewById<View>(R.id.edtAlternative5) as EditText
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        for (i in 0 until ARRAY_SIZE) {
            val preferenceAlterValue = sharedPref.getString(inputAlter[i], null)
            if (preferenceAlterValue != null) edtAlternatives[i]!!.setText(preferenceAlterValue)
        }
        val btnOk = dialog.findViewById<View>(R.id.btnOK1) as Button
        val btnCancel = dialog.findViewById<View>(R.id.btnCancel1) as Button
        btnOk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                for (i in 0 until ARRAY_SIZE) {
                    if (edtAlternatives[i]!!.text.toString().length > 0) {
                        alternativeValue[i] = edtAlternatives[i]!!.text.toString()
                    } else {
                        alternativeValue[i] = ""
                    }
                    prefsEditor.putString(inputAlter[i], alternativeValue[i]).commit()
                }
                dialog.dismiss()
            }
        })
        btnCancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                for (i in 0..4) {
                    edtAlternatives[i]!!.setText("")
                    prefsEditor.remove(inputAlter[i]).commit()
                    alternativeValue[i] = ""
                }
            }
        })
        dialog.show()
    }

    private fun inputParameterDialog(prgMessage: String) {
        val dialog = Dialog((activity)!!)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_input_parameters)
        val txtMessage2 = dialog.findViewById<View>(R.id.txtMessage2) as TextView
        txtMessage2.text = prgMessage
        edtParameter[0] = dialog.findViewById<View>(R.id.edtParameter1) as EditText
        edtParameter[1] = dialog.findViewById<View>(R.id.edtParameter2) as EditText
        edtParameter[2] = dialog.findViewById<View>(R.id.edtParameter3) as EditText
        edtParameter[3] = dialog.findViewById<View>(R.id.edtParameter4) as EditText
        edtParameter[4] = dialog.findViewById<View>(R.id.edtParameter5) as EditText
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        for (i in 0 until ARRAY_SIZE) {
            val preferenceParamValue = sharedPref.getString(inputParam[i], null)
            if (preferenceParamValue != null) edtParameter[i]!!.setText(preferenceParamValue)
        }
        val btnOk = dialog.findViewById<View>(R.id.btnOK2) as Button
        val btnCancel = dialog.findViewById<View>(R.id.btnCancel2) as Button
        btnOk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                for (i in 0 until ARRAY_SIZE) {
                    if (edtParameter[i]!!.text.toString().length > 0) {
                        parameterValue[i] = edtParameter[i]!!.text.toString()
                    } else {
                        parameterValue[i] = ""
                    }
                    prefsEditor.putString(inputParam[i], parameterValue[i]).commit()
                }

                //				btnQ[3].setEnabled(true);
                //				btnQ[4].setEnabled(true);
                dialog.dismiss()
            }
        })
        btnCancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                for (i in 0..4) {
                    edtParameter[i]!!.setText("")
                    prefsEditor.remove(inputParam[i]).commit()
                    parameterValue[i] = ""
                }
            }
        })
        dialog.show()
    }

    private fun prioritizeAlternativeDialog(prgMessage: String) {
        var prgMessage: String? = prgMessage
        val dialog = Dialog((activity)!!)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_prioritize_alternative)
        val txtMessage3 = dialog.findViewById<View>(R.id.txtMessage3) as TextView
        viewAlternativeComp[0] =
            dialog.findViewById<View>(R.id.viewAlternativeComp1) as ViewAlternativeComp
        viewAlternativeComp[1] =
            dialog.findViewById<View>(R.id.viewAlternativeComp2) as ViewAlternativeComp
        viewAlternativeComp[2] =
            dialog.findViewById<View>(R.id.viewAlternativeComp3) as ViewAlternativeComp
        viewAlternativeComp[3] =
            dialog.findViewById<View>(R.id.viewAlternativeComp4) as ViewAlternativeComp
        viewAlternativeComp[4] =
            dialog.findViewById<View>(R.id.viewAlternativeComp5) as ViewAlternativeComp
        val btnOk = dialog.findViewById<View>(R.id.btnOk3) as Button
        val btnCancel = dialog.findViewById<View>(R.id.btnCancel3) as Button
        for (i in 0 until ARRAY_SIZE) viewAlternativeComp[i]!!.getID(i)
        for (i in 0 until ARRAY_SIZE) viewAlternativeComp[i]!!
            .setAltText(parameterValue[i], alternativeValue)
        var iCnt = 0
        for (i in 0 until ARRAY_SIZE) {
            if (parameterValue[i].isEmpty()) {
                iCnt++
                viewAlternativeComp.get(i)!!.visibility = View.GONE
            }
            if (iCnt == 5) {
                prgMessage = "لطفاً ورودی‌های برنامه را کامل کنید"
                btnCancel.visibility = View.GONE
                btnOk.text = "باشه"
            }
            var jCnt = 0
            for (j in 0 until ARRAY_SIZE) {
                if (alternativeValue[j].isEmpty()) jCnt++
                if (jCnt == 5) {
                    prgMessage = "لطفاً ورودی‌های برنامه را کامل کنید"
                    viewAlternativeComp.get(i)!!.visibility = View.GONE
                    btnCancel.visibility = View.GONE
                    btnOk.text = "باشه"
                }
            }
        }
        for (i in 0 until ARRAY_SIZE) {
            for (j in 0 until ARRAY_SIZE) {
                if (alternativeValue[j].isEmpty()) {
                    viewAlternativeComp[i]!!.setVisibilityText(j)
                }
            }
        }
        txtMessage3.text = prgMessage
        btnOk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                for (i in 0 until ARRAY_SIZE) for (j in 0 until ARRAY_SIZE) {
                    alternativePriorityValue[i][j] = viewAlternativeComp[i].getMyAlternativeItem()[j]
                    val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                    prefsEditor.putInt(
                        ViewAlternativeComp.Companion.spinnerAlterSelection.get(i).get(j),
                        (5 - alternativePriorityValue[i][j]).toInt()
                    ).commit()
                }
                dialog.dismiss()
            }
        })
        btnCancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                for (i in 0..4) for (j in 0..4) prefsEditor.remove(
                    ViewAlternativeComp.Companion.spinnerAlterSelection.get(
                        i
                    ).get(j)
                ).commit()
                for (i in 0 until ARRAY_SIZE) viewAlternativeComp[i]!!
                    .clearID(i)
                for (i in 0 until ARRAY_SIZE) {
                    for (j in 0 until ARRAY_SIZE) {
                        alternativePriorityValue[i][j] = 0.0
                    }
                }
            }
        })
        dialog.show()
    }

    private fun prioritizeParameterDialog(prgMessage: String) {
        var prgMessage: String? = prgMessage
        val dialog = Dialog((activity)!!)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_prioritize_parameter)
        val txtMessage3 = dialog.findViewById<View>(R.id.txtMessage3) as TextView
        viewParameterComp[0] =
            dialog.findViewById<View>(R.id.viewParameterComp1) as ViewParameterComp
        viewParameterComp[1] =
            dialog.findViewById<View>(R.id.viewParameterComp2) as ViewParameterComp
        viewParameterComp[2] =
            dialog.findViewById<View>(R.id.viewParameterComp3) as ViewParameterComp
        viewParameterComp[3] =
            dialog.findViewById<View>(R.id.viewParameterComp4) as ViewParameterComp
        viewParameterComp[4] =
            dialog.findViewById<View>(R.id.viewParameterComp5) as ViewParameterComp
        viewParameterComp[5] =
            dialog.findViewById<View>(R.id.viewParameterComp6) as ViewParameterComp
        viewParameterComp[6] =
            dialog.findViewById<View>(R.id.viewParameterComp7) as ViewParameterComp
        viewParameterComp[7] =
            dialog.findViewById<View>(R.id.viewParameterComp8) as ViewParameterComp
        viewParameterComp[8] =
            dialog.findViewById<View>(R.id.viewParameterComp9) as ViewParameterComp
        viewParameterComp[9] =
            dialog.findViewById<View>(R.id.viewParameterComp10) as ViewParameterComp
        val btnOk = dialog.findViewById<View>(R.id.btnOk4) as Button
        val btnCancel = dialog.findViewById<View>(R.id.btnCancel4) as Button
        for (i in 0 until 2 * ARRAY_SIZE) viewParameterComp[i]!!.getID(i)
        var cnt = 0
        var jCnt = 0
        for (i in 0 until ARRAY_SIZE) {
            for (j in i + 1 until ARRAY_SIZE) {
                viewParameterComp[cnt]!!.setParamText(parameterValue[i], parameterValue[j])
                if (parameterValue[i].isEmpty() || parameterValue[j].isEmpty()) {
                    viewParameterComp.get(cnt)!!.visibility = View.GONE
                    jCnt++
                }
                cnt++
                if (jCnt == 10) {
                    prgMessage = "لطفاً ورودی‌های برنامه را کامل کنید"
                    btnCancel.visibility = View.GONE
                    btnOk.text = "باشه"
                }
            }
        }
        txtMessage3.text = prgMessage
        btnOk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                var cnt = 0
                for (i in 0 until ARRAY_SIZE) {
                    for (j in i + 1 until ARRAY_SIZE) {
                        parameterPriorityValue[i][j] = viewParameterComp[cnt].getMyParamItem()
                        val prefsEditor =
                            PreferenceManager.getDefaultSharedPreferences(activity).edit()
                        if (parameterPriorityValue[i][j] == 1.toDouble() / 5.0) prefsEditor.putInt(
                            ViewParameterComp.Companion.spinnerParamSelection.get(cnt),
                            0
                        ).commit()
                        if (parameterPriorityValue[i][j] == 1.toDouble() / 3.0) prefsEditor.putInt(
                            ViewParameterComp.Companion.spinnerParamSelection.get(cnt),
                            1
                        ).commit()
                        if (parameterPriorityValue[i][j] == 1.0) prefsEditor.putInt(
                            ViewParameterComp.Companion.spinnerParamSelection.get(
                                cnt
                            ), 2
                        ).commit()
                        if (parameterPriorityValue[i][j] == 3.0) prefsEditor.putInt(
                            ViewParameterComp.Companion.spinnerParamSelection.get(
                                cnt
                            ), 3
                        ).commit()
                        if (parameterPriorityValue[i][j] == 5.0) prefsEditor.putInt(
                            ViewParameterComp.Companion.spinnerParamSelection.get(
                                cnt
                            ), 4
                        ).commit()
                        cnt++
                    }
                }
                //				btnCompute.setEnabled(true);
                dialog.dismiss()
            }
        })
        btnCancel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                for (i in 0 until 2 * ARRAY_SIZE) {
                    prefsEditor.remove(ViewParameterComp.Companion.spinnerParamSelection.get(i))
                        .commit()
                    viewParameterComp[i]!!.clearID(i)
                }
                for (i in 0 until ARRAY_SIZE) {
                    for (j in 0 until ARRAY_SIZE) {
                        parameterPriorityValue[i][j] = 0.0
                    }
                }
            }
        })
        dialog.show()
    }

    private fun resultDialog(index: IntArray, maxResult: DoubleArray, sum: Double) {
        val dialog = Dialog((G.Companion.currentActivity)!!)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_result)
        val txt_numberfont = Typeface.createFromAsset(requireActivity().assets, "font.ttf")
        val btnOk = dialog.findViewById<View>(R.id.btnOk5) as Button
        val txtResult = arrayOfNulls<TextView>(6)
        val txtNumber = arrayOfNulls<TextView>(5)
        val txtPercentage = arrayOfNulls<TextView>(5)
        val lytLayoutResult = arrayOfNulls<LinearLayout>(5)
        txtResult[0] = dialog.findViewById<View>(R.id.txt_result1) as TextView
        txtResult[1] = dialog.findViewById<View>(R.id.txt_result2) as TextView
        txtResult[2] = dialog.findViewById<View>(R.id.txt_result3) as TextView
        txtResult[3] = dialog.findViewById<View>(R.id.txt_result4) as TextView
        txtResult[4] = dialog.findViewById<View>(R.id.txt_result5) as TextView
        txtNumber[0] = dialog.findViewById<View>(R.id.txt_number1) as TextView
        txtNumber[1] = dialog.findViewById<View>(R.id.txt_number2) as TextView
        txtNumber[2] = dialog.findViewById<View>(R.id.txt_number3) as TextView
        txtNumber[3] = dialog.findViewById<View>(R.id.txt_number4) as TextView
        txtNumber[4] = dialog.findViewById<View>(R.id.txt_number5) as TextView
        txtPercentage[0] = dialog.findViewById<View>(R.id.txt_percentage1) as TextView
        txtPercentage[1] = dialog.findViewById<View>(R.id.txt_percentage2) as TextView
        txtPercentage[2] = dialog.findViewById<View>(R.id.txt_percentage3) as TextView
        txtPercentage[3] = dialog.findViewById<View>(R.id.txt_percentage4) as TextView
        txtPercentage[4] = dialog.findViewById<View>(R.id.txt_percentage5) as TextView
        lytLayoutResult[0] = dialog.findViewById<View>(R.id.lyt_result1) as LinearLayout
        lytLayoutResult[1] = dialog.findViewById<View>(R.id.lyt_result2) as LinearLayout
        lytLayoutResult[2] = dialog.findViewById<View>(R.id.lyt_result3) as LinearLayout
        lytLayoutResult[3] = dialog.findViewById<View>(R.id.lyt_result4) as LinearLayout
        lytLayoutResult[4] = dialog.findViewById<View>(R.id.lyt_result5) as LinearLayout
        txtResult[5] = dialog.findViewById<View>(R.id.txt_output) as TextView
        for (i in 0..4) {
            txtNumber[i]!!.setTypeface(txt_numberfont)
            txtPercentage[i]!!.setTypeface(txt_numberfont)
        }
        var cnt = 0
        val resultMessage = arrayOf("", "", "", "", "")
        for (i in 0..4) {
            if (!alternativeValue[index[i]].isEmpty()) {
                resultMessage[i] = alternativeValue[index[i]]
                txtResult.get(i)!!.text = resultMessage.get(i)
                txtNumber.get(i)!!.text = "" + (i + 1)
                txtPercentage.get(i)!!.text =
                    Math.round((maxResult.get(i) * 100) / sum).toString() + "%"
            } else {
                txtResult.get(i)!!.visibility = View.GONE
                txtNumber.get(i)!!.visibility = View.GONE
                txtPercentage.get(i)!!.visibility = View.GONE
                lytLayoutResult.get(i)!!.visibility = View.GONE
                cnt++
            }
        }
        if (cnt == 5) txtResult.get(5)!!.text =
            "لطفاً ورودی‌های برنامه را کامل کنید" else txtResult.get(5)!!.text =
            "میزان تشابه گزینه‌های معرفی شده با اولویت‌های شما به شرح زیر است، پیشنهاد می‌شود گزینه‌هایی با بیشترین درصد تشابه انتخاب شود."
        btnOk.setOnClickListener(object : View.OnClickListener {
            override fun onClick(arg0: View) {
                dialog.dismiss()
            }
        })
        dialog.show()
    }

    companion object {
        const val ARRAY_SIZE = 5
    }
}