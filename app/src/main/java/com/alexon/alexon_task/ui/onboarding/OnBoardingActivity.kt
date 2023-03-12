package com.alexon.alexon_task.ui.onboarding

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.alexon.alexon_task.R
import com.alexon.alexon_task.databinding.ActivityOnboardingBinding
import com.alexon.alexon_task.ui.onboarding.pageradapter.OnboardingPagerAdapter
import com.alexon.alexon_task.ui.onboarding.screens.OnBoardingFirstScreen
import com.alexon.alexon_task.ui.onboarding.screens.OnBoardingSecondScreen
import com.alexon.alexon_task.ui.onboarding.screens.OnBoardingThirdScreen
import com.alexon.alexon_task.util.toAuthActivity
import com.alexon.alexon_task.util.toMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    val viewModel: OnBoardingViewModel by viewModels()

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var splashScreen: SplashScreen
    private lateinit var indicatorContainer: LinearLayout

    private val fragmentList = listOf(
        OnBoardingFirstScreen(),
        OnBoardingSecondScreen(),
        OnBoardingThirdScreen()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        observations()
    }

    private fun observations() {
        viewModel.isUserLoggedIn.observe(this) {
            toMainActivity()
        }

        viewModel.userPassedOnBoarding.observe(this) {
            toAuthActivity()
        }

        viewModel.startOnBoarding.observe(this) {
            startOnBoarding()
        }
    }

    private fun startOnBoarding() {
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreen.setKeepOnScreenCondition { false }

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