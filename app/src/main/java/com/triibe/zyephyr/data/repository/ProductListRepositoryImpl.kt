package com.triibe.zyephyr.data.repository

import com.triibe.zyephyr.data.model.product_list.ProductsListDto
import com.triibe.zyephyr.data.model.store_list.StoreListDto
import com.triibe.zyephyr.data.remote.EcomAPI
import com.triibe.zyephyr.domain.repository.ProductListRepository
import com.triibe.zyephyr.domain.repository.StoreListRepository

class ProductListRepositoryImpl(private val ecomAPI: EcomAPI) : ProductListRepository {

    override suspend fun getProducts(): ProductsListDto {
        return ecomAPI.getProductList()
    }
}