package com.triibe.zyephyr.presentation.store_lst

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.triibe.zyephyr.databinding.ItemStoreListBinding
import com.triibe.zyephyr.domain.model.store_list.StoreModel

class StoreListAdapter : RecyclerView.Adapter<StoreListAdapter.MyViewHolder>() {



    private var listener :((StoreModel)->Unit)?=null

    var list = mutableListOf<StoreModel>()

    fun setContentList(list: MutableList<StoreModel>) {
        this.list = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val viewHolder: ItemStoreListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoreListAdapter.MyViewHolder {
        val binding =
            ItemStoreListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(StoreModel)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: StoreListAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.store = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}