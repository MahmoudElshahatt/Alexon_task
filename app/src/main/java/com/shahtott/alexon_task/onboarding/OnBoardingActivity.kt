package com.shahtott.alexon_task.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.shahtott.alexon_task.R
import com.shahtott.alexon_task.databinding.ActivityOnboardingBinding
import com.shahtott.alexon_task.onboarding.screens.OnBoardingFirstScreen
import com.shahtott.alexon_task.onboarding.screens.OnBoardingSecondScreen
import com.shahtott.alexon_task.onboarding.screens.OnBoardingThirdScreen

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var indicatorContainer: LinearLayout

    private val fragmentList = listOf(
        OnBoardingFirstScreen(),
        OnBoardingSecondScreen(),
        OnBoardingThirdScreen()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeOnBoardingViewPager()
        setupIndicators()
    }

    private fun initializeOnBoardingViewPager() {
        val adapter = OnboardingPagerAdapter(this, fragmentList)
        binding.apply {
            vpOnBoarding.adapter = adapter
            indicatorContainer = indicator
        }
    }

    private fun setupIndicators() {
        val firstIndicator = indicatorContainer.getChildAt(0) as ImageView
        firstIndicator.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.onboarding_indicator_active
            )
        )

        binding.vpOnBoarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicators(position)
            }
        })
    }

    private fun updateIndicators(position: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.onboarding_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.onboarding_indicator_inactive
                    )
                )
            }
        }
    }

}