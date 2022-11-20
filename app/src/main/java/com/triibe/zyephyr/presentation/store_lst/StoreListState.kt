package com.triibe.zyephyr.presentation.store_lst

import com.triibe.zyephyr.domain.model.store_list.StoreModel

data class StoreListState(
    val isLoading: Boolean = false,
    val data: List<StoreModel>? = null,
    val error: String = ""
)