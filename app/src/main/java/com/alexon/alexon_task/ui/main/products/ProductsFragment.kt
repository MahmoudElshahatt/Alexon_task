package com.alexon.alexon_task.ui.main.products

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alexon.alexon_task.R
import com.alexon.alexon_task.databinding.FragmentProductsBinding
import com.alexon.alexon_task.ui.main.adapter.ProductsAdapter
import com.alexon.alexon_task.ui.main.products.models.ProductsResponse.Product
import com.alexon.alexon_task.util.toAuthActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment(), ProductsAdapter.ProductClickListener {

    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListeners()
        observations()
    }

    private fun onClickListeners() {
        binding.btnBack.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.btnLogOut.setOnClickListener {
            viewModel.clearUserData()
            requireActivity().toAuthActivity()
        }

    }


    private fun observations() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.pgProducts.isVisible = isLoading
        }

        viewModel.products.observe(viewLifecycleOwner) { products ->
            val adapter = getListAdapter()
            adapter.submitList(products)
        }

        viewModel.generalError.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                showError(errorMessage, R.drawable.img_general_error)
            }
        }

        viewModel.networkError.observe(viewLifecycleOwner) { isNoInternet ->
            if (isNoInternet) {
                showError(getString(R.string.no_internet), R.drawable.img_no_internet_connection)
            }
        }

    }


    private fun getListAdapter(): ProductsAdapter {
        val adapter = ProductsAdapter(this)
        binding.rvProducts.apply {
            this.adapter = adapter

            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
        }
        return adapter
    }

    override fun onProductClick(product: Product) {
        findNavController().navigate(
            ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(product)
        )
    }

    private fun showError(errorMessage: String?, errorImg: Int) {
        binding.apply {
            txtErrorState.text = errorMessage
            imgErrorState.setImageResource(errorImg)
            errorStateGroup.isVisible = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.apply {
            resetErrorStates()
            cancelCurrentJob()
        }
    }


}