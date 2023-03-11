package com.alexon.alexon_task.ui.main.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.alexon.alexon_task.R
import com.alexon.alexon_task.databinding.ProductHeaderBinding
import com.alexon.alexon_task.databinding.ProductItemBinding
import com.alexon.alexon_task.ui.main.products.models.ProductsResponse.Product

private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_ITEM = 1

class ProductsAdapter(
    private val productClickListener: ProductClickListener
) : ListAdapter<Product, RecyclerView.ViewHolder>(ProductDiffCallback()) {


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val headerBinding =
                    ProductHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HeaderViewHolder(headerBinding)
            }
            VIEW_TYPE_ITEM -> {
                val itemBinding =
                    ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(itemBinding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_HEADER -> {
                val headerHolder = holder as HeaderViewHolder
                headerHolder.bind()
            }
            VIEW_TYPE_ITEM -> {
                val productHolder = holder as ItemViewHolder
                val product = getItem(position - 1)

                productHolder.bind(product,productClickListener)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun getItemCount(): Int {
        //Current Items and Header
        return currentList.size + 1
    }

    inner class HeaderViewHolder(private val itemBinding: ProductHeaderBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind() {
            itemBinding.txtHeader.text = "Found \n${currentList.size} results"
        }

    }

     class ItemViewHolder(private val itemBinding: ProductItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(product: Product, productClickListener: ProductClickListener) {
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
            itemBinding.itemProductContainer.setOnClickListener {
                productClickListener.onProductClick(product)
            }

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

    interface ProductClickListener {
        fun onProductClick(product: Product)
    }
}
