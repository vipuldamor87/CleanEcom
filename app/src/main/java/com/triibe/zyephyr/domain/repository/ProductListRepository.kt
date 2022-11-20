package com.triibe.zyephyr.domain.repository

import com.triibe.zyephyr.data.model.product_list.ProductsListDto
import com.triibe.zyephyr.data.model.store_list.StoreListDto

interface ProductListRepository {
    suspend fun getProducts(): ProductsListDto
}