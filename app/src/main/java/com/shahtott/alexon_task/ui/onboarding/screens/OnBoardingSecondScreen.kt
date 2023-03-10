package com.shahtott.alexon_task.ui.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.shahtott.alexon_task.R
import com.shahtott.alexon_task.databinding.FragmentSecondScreenBinding

class OnBoardingSecondScreen : Fragment() {
    private lateinit var binding: FragmentSecondScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondScreenBinding.inflate(layoutInflater)
        setOnNextListener()
        return binding.root
    }

    private fun setOnNextListener() {
        binding.btnNext.setOnClickListener {
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.vp_onBoarding)
            viewPager?.currentItem = 2
        }
    }

}