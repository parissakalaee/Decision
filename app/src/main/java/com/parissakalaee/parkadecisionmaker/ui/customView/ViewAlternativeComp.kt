package com.parissakalaee.parkadecisionmaker.ui.customView

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.parissakalaee.parkadecisionmaker.R
import com.parissakalaee.parkadecisionmaker.databinding.ViewCompareAlternativesBinding
import com.parissakalaee.parkadecisionmaker.util.Constants.ARRAY_SIZE
import java.util.*

class ViewAlternativeComp : LinearLayout {
    private var _binding: ViewCompareAlternativesBinding? = null
    private val binding get() = _binding!!

    private var txtParameter: TextView? = null
    var txtAlt: Array<TextView?> = arrayOfNulls(ARRAY_SIZE)
    private var spinner: Array<Spinner?> = arrayOfNulls(ARRAY_SIZE)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializer(context)
    }

    constructor(context: Context) : super(context) {
        initializer(context)
    }

    var alternativeItem: DoubleArray = DoubleArray(ARRAY_SIZE)
    private var indexSpinner: Int = 0
    private fun initializer(context: Context) {
        if (isInEditMode) {
            return
        }
        _binding = ViewCompareAlternativesBinding.inflate(LayoutInflater.from(context), this, false)

        val list = ArrayList<String>()
        list.add(context.getString(R.string.all_msg_excellent))
        list.add(context.getString(R.string.all_msg_very_good))
        list.add(context.getString(R.string.all_msg_good))
        list.add(context.getString(R.string.all_msg_so_so))
        list.add(context.getString(R.string.all_msg_bad))
        val dataAdapter: ArrayAdapter<String> = ArrayAdapter(
            context,
            R.layout.item_spinner,
            list
        )
        dataAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinner[0] = binding.spinner2
        spinner[1] = binding.spinner3
        spinner[2] = binding.spinner4
        spinner[3] = binding.spinner5
        spinner[4] = binding.spinner6
        for (i in 0 until ARRAY_SIZE) spinner[i]!!.adapter = dataAdapter
        spinner[0]!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                alternativeItem[0] = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> alternativeItem[0] = 5.0
                    1 -> alternativeItem[0] = 4.0
                    2 -> alternativeItem[0] = 3.0
                    3 -> alternativeItem[0] = 2.0
                    4 -> alternativeItem[0] = 1.0
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
            }
        }
        spinner[1]!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                alternativeItem[1] = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> alternativeItem[1] = 5.0
                    1 -> alternativeItem[1] = 4.0
                    2 -> alternativeItem[1] = 3.0
                    3 -> alternativeItem[1] = 2.0
                    4 -> alternativeItem[1] = 1.0
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
            }
        }
        spinner[2]!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                alternativeItem[2] = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> alternativeItem[2] = 5.0
                    1 -> alternativeItem[2] = 4.0
                    2 -> alternativeItem[2] = 3.0
                    3 -> alternativeItem[2] = 2.0
                    4 -> alternativeItem[2] = 1.0
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
            }
        }
        spinner[3]!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                alternativeItem[3] = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> alternativeItem[3] = 5.0
                    1 -> alternativeItem[3] = 4.0
                    2 -> alternativeItem[3] = 3.0
                    3 -> alternativeItem[3] = 2.0
                    4 -> alternativeItem[3] = 1.0
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
            }
        }
        spinner[4]!!.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                pos: Int, id: Long
            ) {
                alternativeItem[4] = parent.getItemIdAtPosition(pos).toDouble()
                when (parent.getItemIdAtPosition(pos).toInt()) {
                    0 -> alternativeItem[4] = 5.0
                    1 -> alternativeItem[4] = 4.0
                    2 -> alternativeItem[4] = 3.0
                    3 -> alternativeItem[4] = 2.0
                    4 -> alternativeItem[4] = 1.0
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
            }
        }
        txtParameter = binding.txtParameter
        txtAlt[0] = binding.txtAlt1
        txtAlt[1] = binding.txtAlt2
        txtAlt[2] = binding.txtAlt3
        txtAlt[3] = binding.txtAlt4
        txtAlt[4] = binding.txtAlt5
    }

    fun setAltText(param: String?, input: Array<String>) {
        txtParameter!!.text = param
        for (i in 0 until ARRAY_SIZE) txtAlt[i]!!.text = input[i]
    }

    fun setVisibilityText(number: Int) {
        txtAlt[number]!!.visibility = GONE
        spinner[number]!!.visibility = GONE
    }

    fun getMyAlternativeItem(): DoubleArray {
        return alternativeItem
    }

    fun getID(input: Int) {
        var spinnerValue: Int
        indexSpinner = input
        val sharedPref: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(getContext())
        for (i in 0..4) {
            spinnerValue = sharedPref.getInt(spinnerAlterSelection[indexSpinner][i], 5)
            if (spinnerValue != 5) spinner[i]!!.setSelection(spinnerValue)
        }
    }

    fun clearID() {
        for (i in 0..4) {
            spinner[i]!!.setSelection(0)
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
        private const val TAG = "Custom_View_Kotlin"
    }
}