package com.shahtott.alexon_task.onboarding

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.shahtott.alexon_task.R
import com.shahtott.alexon_task.databinding.ActivityOnboardingBinding
import com.shahtott.alexon_task.onboarding.screens.OnBoardingFirstScreen
import com.shahtott.alexon_task.onboarding.screens.OnBoardingSecondScreen
import com.shahtott.alexon_task.onboarding.screens.OnBoardingThirdScreen
import com.shahtott.alexon_task.onboarding.viewpager.OnboardingPagerAdapter
import com.shahtott.alexon_task.util.toAuthActivity
import com.shahtott.alexon_task.util.toMainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        handleDirections()
    }

    private fun handleDirections() {
        lifecycleScope.launch {
            if (viewModel.isOnBoardingFinished() && !viewModel.isUserLoggedIn()) {
                toAuthActivity()
            } else if (viewModel.isUserLoggedIn()) {
                toMainActivity()
            } else {
                startOnBoarding()
            }
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