package com.triibe.zyephyr.data.repository

import com.triibe.zyephyr.data.model.order_list.OrderDto
import com.triibe.zyephyr.data.model.order_list.OrderDtoItem
import com.triibe.zyephyr.data.model.store_list.StoreListDto
import com.triibe.zyephyr.data.remote.EcomAPI
import com.triibe.zyephyr.domain.repository.OrderRepository
import com.triibe.zyephyr.domain.repository.StoreListRepository

class OrderRepositoryImpl(private val ecomAPI: EcomAPI) : OrderRepository {

    override suspend fun placeOrder(data: HashMap<String,Any>): OrderDtoItem {
        return ecomAPI.placeOrder(data)
    }
}