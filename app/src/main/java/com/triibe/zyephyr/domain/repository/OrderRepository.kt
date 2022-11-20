package com.triibe.zyephyr.domain.repository

import com.triibe.zyephyr.data.model.order_list.OrderDto
import com.triibe.zyephyr.data.model.order_list.OrderDtoItem
import com.triibe.zyephyr.data.model.product_list.ProductsListDto

interface OrderRepository {
    suspend fun placeOrder(data: HashMap<String,Any>): OrderDtoItem
}