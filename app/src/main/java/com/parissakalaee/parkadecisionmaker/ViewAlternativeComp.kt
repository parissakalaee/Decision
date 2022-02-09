package com.parissakalaee.parkadecisionmaker

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import java.util.*

class ViewAlternativeComp : LinearLayout {
    var txtParameter: TextView? = null
    var txtAlt: Array<TextView?> = arrayOfNulls(FragmentCalculator.ARRAY_SIZE)
    var spinner: Array<Spinner?> = arrayOfNulls(FragmentCalculator.ARRAY_SIZE)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializer(context)
    }

    constructor(context: Context) : super(context) {
        initializer(context)
    }

    var alternativeItem: DoubleArray = DoubleArray(FragmentCalculator.ARRAY_SIZE)
    var indexSpinner: Int = 0
    private fun initializer(context: Context) {
        if (isInEditMode()) {
            return
        }
        val inflator: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout: View = inflator.inflate(R.layout.view_alternative_comp, this)
        val list: MutableList<String> = ArrayList()
        list.add("عالی")
        list.add("بسیار خوب")
        list.add("خوب")
        list.add("متوسط")
        list.add("بد")
        val dataAdapter: ArrayAdapter<String> = ArrayAdapter(
            context,
            R.layout.item_spinner, list
        )
        dataAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinner[0] = layout.findViewById<View>(R.id.spinner2) as Spinner?
        spinner[1] = layout.findViewById<View>(R.id.spinner3) as Spinner?
        spinner[2] = layout.findViewById<View>(R.id.spinner4) as Spinner?
        spinner[3] = layout.findViewById<View>(R.id.spinner5) as Spinner?
        spinner[4] = layout.findViewById<View>(R.id.spinner6) as Spinner?
        for (i in 0 until FragmentCalculator.ARRAY_SIZE) spinner.get(i)!!
            .setAdapter(dataAdapter)
        spinner.get(0)!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                // TODO Auto-generated method stub
                alternativeItem[0] = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> alternativeItem[0] = 5.0
                    1 -> alternativeItem[0] = 4.0
                    2 -> alternativeItem[0] = 3.0
                    3 -> alternativeItem[0] = 2.0
                    4 -> alternativeItem[0] = 1.0
                }
            }

            public override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        })
        spinner.get(1)!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                // TODO Auto-generated method stub
                alternativeItem[1] = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> alternativeItem[1] = 5.0
                    1 -> alternativeItem[1] = 4.0
                    2 -> alternativeItem[1] = 3.0
                    3 -> alternativeItem[1] = 2.0
                    4 -> alternativeItem[1] = 1.0
                }
            }

            public override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        })
        spinner.get(2)!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                // TODO Auto-generated method stub
                alternativeItem[2] = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> alternativeItem[2] = 5.0
                    1 -> alternativeItem[2] = 4.0
                    2 -> alternativeItem[2] = 3.0
                    3 -> alternativeItem[2] = 2.0
                    4 -> alternativeItem[2] = 1.0
                }
            }

            public override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        })
        spinner.get(3)!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                // TODO Auto-generated method stub
                alternativeItem[3] = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> alternativeItem[3] = 5.0
                    1 -> alternativeItem[3] = 4.0
                    2 -> alternativeItem[3] = 3.0
                    3 -> alternativeItem[3] = 2.0
                    4 -> alternativeItem[3] = 1.0
                }
            }

            public override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        })
        spinner.get(4)!!.setOnItemSelectedListener(object : OnItemSelectedListener {
            public override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                // TODO Auto-generated method stub
                alternativeItem[4] = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> alternativeItem[4] = 5.0
                    1 -> alternativeItem[4] = 4.0
                    2 -> alternativeItem[4] = 3.0
                    3 -> alternativeItem[4] = 2.0
                    4 -> alternativeItem[4] = 1.0
                }
            }

            public override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        })
        txtParameter = layout.findViewById<View>(R.id.txtParameter) as TextView?
        txtAlt[0] = layout.findViewById<View>(R.id.txtAlt1) as TextView?
        txtAlt[1] = layout.findViewById<View>(R.id.txtAlt2) as TextView?
        txtAlt[2] = layout.findViewById<View>(R.id.txtAlt3) as TextView?
        txtAlt[3] = layout.findViewById<View>(R.id.txtAlt4) as TextView?
        txtAlt[4] = layout.findViewById<View>(R.id.txtAlt5) as TextView?
    }

    fun setAltText(param: String?, input: Array<String>) {
        txtParameter!!.setText(param)
        for (i in 0 until FragmentCalculator.ARRAY_SIZE) txtAlt.get(i)!!
            .setText(input.get(i))
    }

    fun setVisibilityText(number: Int) {
        txtAlt.get(number)!!.setVisibility(GONE)
        spinner.get(number)!!.setVisibility(GONE)
    }

    fun getMyAlternativeItem(): DoubleArray {
        return alternativeItem
    }

    fun getID(input: Int) {
        var spinnerValue: Int = 0
        indexSpinner = input
        val sharedPref: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(getContext())
        for (i in 0..4) {
            spinnerValue = sharedPref.getInt(spinnerAlterSelection.get(indexSpinner).get(i), 5)
            if (spinnerValue != 5) spinner.get(i)!!.setSelection(spinnerValue)
        }
    }

    fun clearID(input: Int) {
        val sharedPref: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(getContext())
        for (i in 0..4) {
            val prefsEditor: SharedPreferences.Editor =
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit()
            prefsEditor.putInt(spinnerAlterSelection.get(input).get(i), 0).commit()
            spinner.get(i)!!.setSelection(0)
        }
    }

    companion object {
        var spinnerAlterSelection: Array<Array<String>> = arrayOf(
            arrayOf("Alter0", "Alter1", "Alter2", "Alter3", "Alter4"),
            arrayOf("Alter5", "Alter6", "Alter7", "Alter8", "Alter9"),
            arrayOf("Alter10", "Alter11", "Alter12", "Alter13", "Alter14"),
            arrayOf("Alter15", "Alter16", "Alter17", "Alter18", "Alter19"),
            arrayOf("Alter20", "Alter21", "Alter22", "Alter23", "Alter24")
        )
    }
}