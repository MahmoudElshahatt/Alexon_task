package com.shahtott.alexon_task.ui.main.products.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shahtott.alexon_task.R
import com.shahtott.alexon_task.databinding.ProductItemBinding
import com.shahtott.alexon_task.ui.main.products.models.ProductsResponse.Product

class ProductsAdapter() : ListAdapter<Product, ProductsAdapter.ViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val itemBinding: ProductItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(product: Product) {
            itemBinding.txtItemTitle.text = product.title
            itemBinding.txtRate.text = product.rating.toString()
            itemBinding.txtItemPrice.text = product.price.toString()
            itemBinding.txtItemDesc.text = product.description

            val imageUrl = product.thumbnail
            Glide
                .with(itemBinding.root)
                .load(imageUrl)
                .placeholder(R.drawable.bx_image)
                .into(itemBinding.imgItem)

        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }
    }
}