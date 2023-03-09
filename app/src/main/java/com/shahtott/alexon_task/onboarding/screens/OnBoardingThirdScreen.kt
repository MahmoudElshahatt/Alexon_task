package com.shahtott.alexon_task.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shahtott.alexon_task.databinding.FragmentThirdScreenBinding
import com.shahtott.alexon_task.onboarding.util.toAuthActivity


class OnBoardingThirdScreen : Fragment() {
    private lateinit var binding: FragmentThirdScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdScreenBinding.inflate(layoutInflater)
        setOnFinishListener()
        return binding.root
    }

    private fun setOnFinishListener() {
        binding.btnFinishOnboarding.setOnClickListener {
            requireContext().toAuthActivity()
        }
    }

}