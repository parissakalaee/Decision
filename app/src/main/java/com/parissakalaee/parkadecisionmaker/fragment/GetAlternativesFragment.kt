package com.parissakalaee.parkadecisionmaker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.parissakalaee.parkadecisionmaker.R
import com.parissakalaee.parkadecisionmaker.databinding.FragmentGetAlternativesBinding

class GetAlternativesFragment : Fragment() {
    private var _binding: FragmentGetAlternativesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetAlternativesBinding.inflate(inflater, container, false)
        val alternatives= ArrayList<String>(mutableListOf("","","","",""))
        binding.alternatives = alternatives

        binding.btnOKDialogGetAlternatives.setOnClickListener {  view: View ->
            alternatives[0] = binding.edtAlternative1.text?.toString() ?: ""
            alternatives[1] = binding.edtAlternative2.text?.toString() ?: ""
            alternatives[2] = binding.edtAlternative3.text?.toString() ?: ""
            alternatives[3] = binding.edtAlternative4.text?.toString() ?: ""
            alternatives[4] = binding.edtAlternative5.text?.toString() ?: ""
            view.findNavController().navigate(GetAlternativesFragmentDirections.actionGetAlternativesFragmentToGetCriteriaFragment())
        }
        binding.btnCancelDialogGetAlternatives.setOnClickListener {
            binding.edtAlternative1.setText("")
            binding.edtAlternative2.setText("")
            binding.edtAlternative3.setText("")
            binding.edtAlternative4.setText("")
            binding.edtAlternative5.setText("")
            alternatives[0] = ""
            alternatives[1] = ""
            alternatives[2] = ""
            alternatives[3] = ""
            alternatives[4] = ""
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}