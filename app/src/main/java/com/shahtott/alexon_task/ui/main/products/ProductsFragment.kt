package com.shahtott.alexon_task.ui.main.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shahtott.alexon_task.R
import com.shahtott.alexon_task.databinding.FragmentLoginBinding
import com.shahtott.alexon_task.databinding.FragmentProductsBinding
import com.shahtott.alexon_task.ui.main.products.adapter.ProductsAdapter
import com.shahtott.alexon_task.util.snakebar.showSnakeBar
import com.shahtott.alexon_task.util.toMainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListeners()
        observations()
    }

    private fun observations() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.pgProducts.isVisible = isLoading
        }

        viewModel.products.observe(viewLifecycleOwner) { products ->
            val adapter = setUpListAdapter()
            adapter.submitList(products)
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
               binding.errorStateGroup.isVisible=isNoInternet
            }
        }

    }

    private fun setUpListAdapter(): ProductsAdapter {
        val adapter = ProductsAdapter()
        binding.rvProducts.apply {
            this.adapter = adapter
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
        }
        return adapter
    }

    private fun onClickListeners() {

        binding.btnBack.setOnClickListener {
            requireActivity().finish()
        }

    }

}