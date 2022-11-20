package com.triibe.zyephyr.presentation.product_lst

import com.triibe.zyephyr.domain.model.product_list.ProductModel
import com.triibe.zyephyr.domain.model.store_list.StoreModel
import java.io.Serializable

data class ProductListState(
    val isLoading: Boolean = false,
    var data: ArrayList<ProductModel>? = null,
    val error: String = ""
):Serializable