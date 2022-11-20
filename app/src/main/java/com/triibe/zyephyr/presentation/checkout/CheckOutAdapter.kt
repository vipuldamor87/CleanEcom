package com.triibe.zyephyr.presentation.checkout

import com.triibe.zyephyr.databinding.ItemProductListBinding
import com.triibe.zyephyr.domain.model.product_list.ProductModel


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.triibe.zyephyr.databinding.ItemCartListBinding

class CheckOutAdapter : RecyclerView.Adapter<CheckOutAdapter.MyViewHolder>() {



    private var listener :((ArrayList<ProductModel>)->Unit)?=null

    var list = mutableListOf<ProductModel>()

    fun setContentList(list: MutableList<ProductModel>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: ItemCartListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckOutAdapter.MyViewHolder {
        val binding =
            ItemCartListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(ArrayList<ProductModel>)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: CheckOutAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.product = this.list[position]

        holder.viewHolder.btnReduce.setOnClickListener {
            listener?.let {
                if (this.list[position].quantity > 1) {
                    this.list[position].quantity = this.list[position].quantity - 1
                    it(this.list as ArrayList<ProductModel>)
                }
            }
        }

        holder.viewHolder.btnAdd.setOnClickListener {
            listener?.let {
                    this.list[position].quantity = this.list[position].quantity + 1
                    it(this.list as ArrayList<ProductModel>)

            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}