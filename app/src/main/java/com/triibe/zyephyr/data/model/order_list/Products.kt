package com.triibe.zyephyr.data.model.order_list

import com.triibe.zyephyr.data.model.product_list.ProductsListDtoItem
import com.triibe.zyephyr.domain.model.order.ProductsModel
import com.triibe.zyephyr.domain.model.product_list.ProductModel

data class Products(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
){

    fun toDomain(): ProductsModel {
        return ProductsModel(
            brand = this.brand?:"",
            category = this.category?: "",
            description = this.description?: "",
            discountPercentage = this.discountPercentage ?: 0.0,
            id = this.id ?: 0,
            images = this.images ?: listOf(),
            price = this.price ?: 0,
            rating = this.rating ?: 0.0,
            stock = this.stock ?: 0,
            thumbnail = this.thumbnail?:"",
            title = this.title?:"",
        )
    }
}