package com.parissakalaee.parkadecisionmaker

import android.content.Context
import android.preference.PreferenceManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import java.util.*

class ViewParameterComp : LinearLayout {
    private var txtPri1: TextView? = null
    private var txtPri2: TextView? = null
    private var spinner: Spinner? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializer(context)
    }

    constructor(context: Context) : super(context) {
        initializer(context)
    }

    var priParamItem = 0.0
    var indexSpinner = 0
    private fun initializer(context: Context) {
        if (isInEditMode) {
            return
        }
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflator.inflate(R.layout.view_param_comp, this)
        spinner = layout.findViewById<View>(R.id.spinner1) as Spinner
        val list: MutableList<String> = ArrayList()
        list.add("بسیار کم اهمیت تر از")
        list.add("کم اهمیت تر از")
        list.add("دارای اهمیت مشابه با")
        list.add("اندکی مهم تر از")
        list.add("بسیار مهم تر از")
        val dataAdapter = ArrayAdapter(
            context,
            R.layout.item_spinner, list
        )
        dataAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinner!!.adapter = dataAdapter
        spinner!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                priParamItem = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> priParamItem = 1.toDouble() / 5
                    1 -> priParamItem = 1.toDouble() / 3
                    2 -> priParamItem = 1.0
                    3 -> priParamItem = 3.0
                    4 -> priParamItem = 5.0
                }
                //				Toast.makeText(G.context, "" + item, Toast.LENGTH_SHORT).show(); 
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
        txtPri1 = layout.findViewById<View>(R.id.txtPri1) as TextView
        txtPri2 = layout.findViewById<View>(R.id.txtPri2) as TextView
    }

    fun setParamText(input1: String?, input2: String?) {
        txtPri1!!.text = input1
        txtPri2!!.text = input2
    }

    fun getMyParamItem(): Double {
        return priParamItem
    }

    fun getID(input: Int) {
        var spinnerValue = 0
        indexSpinner = input
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        spinnerValue = sharedPref.getInt(spinnerParamSelection[indexSpinner], 10)
        if (spinnerValue != 10) spinner!!.setSelection(spinnerValue)
    }

    fun clearID(input: Int) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val prefsEditor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        prefsEditor.putInt(spinnerParamSelection[input], 0).commit()
        spinner!!.setSelection(0)
    }

    companion object {
        var spinnerParamSelection = arrayOf(
            "paramSelection0", "paramSelection1",
            "paramSelection2", "paramSelection3", "paramSelection4", "paramSelection5",
            "paramSelection6", "paramSelection7", "paramSelection8", "paramSelection9"
        )
    }
}