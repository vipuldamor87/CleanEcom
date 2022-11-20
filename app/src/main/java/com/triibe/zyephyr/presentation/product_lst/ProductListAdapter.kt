package com.triibe.zyephyr.presentation.product_lst

import com.triibe.zyephyr.databinding.ItemProductListBinding
import com.triibe.zyephyr.domain.model.product_list.ProductModel


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {



    private var listener :((ProductModel)->Unit)?=null

    var list = mutableListOf<ProductModel>()

    fun setContentList(list: MutableList<ProductModel>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: ItemProductListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.MyViewHolder {
        val binding =
            ItemProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(ProductModel)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: ProductListAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.product = this.list[position]

        holder.viewHolder.btnAddToCart.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}