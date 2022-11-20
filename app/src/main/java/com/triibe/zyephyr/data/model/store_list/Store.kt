package com.triibe.zyephyr.data.model.store_list

import com.triibe.zyephyr.domain.model.store_list.StoreModel

data class Store(
    val description: String,
    val id: Int,
    val thumbnail: String,
    val title: String
)

fun Store.toDomainMeal(): StoreModel {
    return StoreModel(
        description = this.description,
        id = this.id ?: 0,
        thumbnail = this.thumbnail ?: "",
        title = this.title ?: ""
    )
}