package com.alexon.alexon_task.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alexon.alexon_task.R
import com.alexon.alexon_task.databinding.FragmentLoginBinding
import com.alexon.alexon_task.util.snakebar.showSnakeBar
import com.alexon.alexon_task.util.system.hideKeypad
import com.alexon.alexon_task.util.toMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListeners()
        observations()
    }


    private fun observations() {
        //Login States

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.pgLogin.isVisible = isLoading
        }

        viewModel.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                requireContext().toMainActivity()
            }
        }

        viewModel.generalError.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                this.view?.let {
                    showSnakeBar(it, errorMessage)
                }
            }
        }

        viewModel.networkError.observe(viewLifecycleOwner) { isNoInternet ->
            if (isNoInternet) {
                this.view?.let {
                    showSnakeBar(it, getString(R.string.no_internet))
                }
            }
        }
    }

    private fun onClickListeners() {
        binding.btnBack.setOnClickListener {
            viewModel.cancelCurrentJob()
            requireActivity().finish()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edEmail.text.toString().trim()
            val password = binding.edPassword.text.toString().trim()

            requireActivity().hideKeypad()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.enter_your_email_and_password),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            viewModel.loginClicked(email, password)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.apply {
            resetErrorStates()
            cancelCurrentJob()
        }
    }
}