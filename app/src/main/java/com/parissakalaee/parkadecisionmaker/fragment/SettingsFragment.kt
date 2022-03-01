package com.parissakalaee.parkadecisionmaker.fragment

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.parissakalaee.parkadecisionmaker.R
import com.parissakalaee.parkadecisionmaker.databinding.FragmentGetAlternativesBinding
import com.parissakalaee.parkadecisionmaker.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
//        binding.btnEnglishFragmentSetting.setOnClickListener{
//            PreferenceManager(this).updateLanguage("en")
//        }
//        binding.btnFarsiFragmentSetting.setOnClickListener{
//            PreferenceManager(this).updateLanguage("ir")
//        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}