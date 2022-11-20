package com.triibe.zyephyr.data.repository

import com.triibe.zyephyr.data.model.store_list.StoreListDto
import com.triibe.zyephyr.data.remote.EcomAPI
import com.triibe.zyephyr.domain.repository.StoreListRepository

class StoreListRepositoryImpl(private val ecomAPI: EcomAPI) : StoreListRepository {

    override suspend fun getStoreList(): StoreListDto {
        return ecomAPI.getStoreList()
    }
}