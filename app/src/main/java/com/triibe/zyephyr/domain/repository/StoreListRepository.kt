package com.triibe.zyephyr.domain.repository

import com.triibe.zyephyr.common.Resource
import com.triibe.zyephyr.data.model.store_list.StoreListDto
import com.triibe.zyephyr.domain.model.store_list.StoreModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface StoreListRepository {
    suspend fun getStoreList(): StoreListDto
}