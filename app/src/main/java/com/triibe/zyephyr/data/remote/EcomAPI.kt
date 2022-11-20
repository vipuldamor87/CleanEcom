package com.triibe.zyephyr.data.remote

import com.triibe.zyephyr.data.model.order_list.OrderDto
import com.triibe.zyephyr.data.model.order_list.OrderDtoItem
import com.triibe.zyephyr.data.model.product_list.ProductsListDto
import com.triibe.zyephyr.data.model.store_list.StoreListDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EcomAPI {

    @GET("store")
    suspend fun getStoreList(
    ): StoreListDto

    @GET("products")
    suspend fun getProductList(
    ): ProductsListDto

    @POST("order")
    suspend fun placeOrder(
        @Body body: HashMap<String,Any>
    ): OrderDtoItem

}