package com.triibe.zyephyr.domain.model.order

data class OrderModel(
    val Products: List<ProductsModel>,
    val address: String,
    val id: Int,
    val total: Int
)