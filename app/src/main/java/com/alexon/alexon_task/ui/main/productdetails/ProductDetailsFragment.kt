package com.alexon.alexon_task.ui.main.productdetails

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexon.alexon_task.databinding.FragmentProductDetailsBinding
import com.alexon.alexon_task.ui.main.adapter.ProductImagesAdapter
import com.alexon.alexon_task.ui.main.products.models.ProductsResponse.Product

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var currentProduct: Product
    private var productAmount: Int = 0
    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        currentProduct = args.product
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListeners()
        setUpImageSlider()
        setProductData()
    }

    private fun setUpImageSlider() {
        val imagesUrls = currentProduct.images
        val adapter = getListAdapter()
        adapter.submitList(imagesUrls)
    }

    private fun getListAdapter(): ProductImagesAdapter {
        val adapter = ProductImagesAdapter()
        binding.rvProductImages.apply {
            this.adapter = adapter

            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        return adapter
    }

    private fun setProductData() {
        binding.apply {
            txtProductTitle.text = currentProduct.title
            txtProductRate.text = currentProduct.rating.toString()
            txtProductDesc.text = currentProduct.description
            txtProductPrice.text = currentProduct.price.toString()
            txtDiscountPercentage.text = "-${currentProduct.discountPercentage.toString()}%"
        }
    }

    private fun onClickListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnPlus.setOnClickListener {
            binding.txtAmount.text = (++productAmount).toString()
        }
        binding.btnMinus.setOnClickListener {
            if (productAmount == 0) return@setOnClickListener
            binding.txtAmount.text = (--productAmount).toString()
        }
        binding.btnAddToCart.setOnClickListener {
            startAndStopLottieAnim()
        }

    }

    private fun startAndStopLottieAnim() {
        binding.cartAnim.isVisible = true
        binding.cartAnim.playAnimation()
        binding.cartAnim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {}

            override fun onAnimationEnd(p0: Animator) {
                binding.cartAnim.visibility = View.GONE
            }

            override fun onAnimationCancel(p0: Animator) {}

            override fun onAnimationRepeat(p0: Animator) {}
        }
        )
    }

    override fun onPause() {
        super.onPause()
        binding.cartAnim.cancelAnimation()
    }
}