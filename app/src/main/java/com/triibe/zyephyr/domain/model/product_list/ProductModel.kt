package com.triibe.zyephyr.domain.model.product_list

data class ProductModel(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    var quantity: Int = 1,
    val thumbnail: String,
    val title: String
){
    fun getPriceValue(): String{
        return "$ $price"+".00"
    }
    fun getQuantityValue(): String{
        return quantity.toString()
    }
}