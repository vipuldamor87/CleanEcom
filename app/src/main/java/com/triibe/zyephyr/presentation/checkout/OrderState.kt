package com.triibe.zyephyr.presentation.checkout

import com.triibe.zyephyr.domain.model.order.OrderModel
import com.triibe.zyephyr.domain.model.store_list.StoreModel

data class OrderState(
    val isLoading: Boolean = false,
    val data: OrderModel? = null,
    val error: String = ""
)