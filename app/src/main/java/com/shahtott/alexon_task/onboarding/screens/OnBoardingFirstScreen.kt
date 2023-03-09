package com.shahtott.alexon_task.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.shahtott.alexon_task.R
import com.shahtott.alexon_task.databinding.FragmentFirstScreenBinding

class OnBoardingFirstScreen : Fragment() {
    private lateinit var binding: FragmentFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentFirstScreenBinding.inflate(layoutInflater)
        setOnNextListener()
        return binding.root
    }

    private fun setOnNextListener() {
        binding.btnNext.setOnClickListener{
          val viewPager = requireActivity().findViewById<ViewPager2>(R.id.vp_onBoarding)
            viewPager?.currentItem=1
        }
    }


}