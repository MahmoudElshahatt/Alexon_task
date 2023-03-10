package com.shahtott.alexon_task.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shahtott.alexon_task.R
import com.shahtott.alexon_task.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

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

    }

    private fun onClickListeners() {
        binding.btnBack.setOnClickListener{
            requireActivity().finish()
        }
        binding.btnLogin.setOnClickListener {

        }
    }

}