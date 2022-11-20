package com.triibe.zyephyr.data.model.order_list

import com.triibe.zyephyr.data.model.product_list.ProductsListDtoItem
import com.triibe.zyephyr.data.model.product_list.toDomain
import com.triibe.zyephyr.domain.model.order.OrderModel
import com.triibe.zyephyr.domain.model.order.ProductsModel
import com.triibe.zyephyr.domain.model.product_list.ProductModel

data class OrderDtoItem(
    val Products: List<Products>,
    val address: String,
    val id: Int,
    val total: Int
){
    fun toDomain(): OrderModel {
        var productsModel = if (Products != null) Products.map { it -> it.toDomain() } else emptyList()

        return OrderModel(
            Products = productsModel,
            address = this.address?: "",
            id = this.id ?: 0,
            total = this.total ?: 0,
        )
    }

}