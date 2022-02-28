package com.parissakalaee.parkadecisionmaker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.parissakalaee.parkadecisionmaker.R
import com.parissakalaee.parkadecisionmaker.databinding.FragmentGetCriteriaBinding

class GetCriteriaFragment : Fragment() {
    private var _binding: FragmentGetCriteriaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetCriteriaBinding.inflate(inflater, container, false)
        val criteria= ArrayList<String>(mutableListOf("","","","",""))
        binding.criteria = criteria

        binding.btnOKDialogGetCriteria.setOnClickListener {  view: View ->
            criteria[0] = binding.edtParameter1.text?.toString() ?: ""
            criteria[1] = binding.edtParameter2.text?.toString() ?: ""
            criteria[2] = binding.edtParameter3.text?.toString() ?: ""
            criteria[3] = binding.edtParameter4.text?.toString() ?: ""
            criteria[4] = binding.edtParameter5.text?.toString() ?: ""
            view.findNavController().navigate(R.id.action_getCriteriaFragment_to_prioritizeAlternativesFragment)
        }
        binding.btnCancelDialogGetCriteria.setOnClickListener {
            binding.edtParameter1.setText("")
            binding.edtParameter2.setText("")
            binding.edtParameter3.setText("")
            binding.edtParameter4.setText("")
            binding.edtParameter5.setText("")
            criteria[0] = ""
            criteria[1] = ""
            criteria[2] = ""
            criteria[3] = ""
            criteria[4] = ""
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
