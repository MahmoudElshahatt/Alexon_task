package com.shahtott.alexon_task.auth.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.shahtott.alexon_task.R
import com.shahtott.alexon_task.databinding.FragmentLoginBinding
import com.shahtott.alexon_task.util.toMainActivity
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

        }

        viewModel.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                requireContext().toMainActivity()
            }
        }

        viewModel.generalError.observe(viewLifecycleOwner) { errorMessage ->

        }

        viewModel.networkError.observe(viewLifecycleOwner) { isNoInternet ->
            if (isNoInternet) {
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT)
                    .show()
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
            Log.i("LoginFragment", "$email + $password")
            viewModel.loginClicked(email, password)
        }

    }

}