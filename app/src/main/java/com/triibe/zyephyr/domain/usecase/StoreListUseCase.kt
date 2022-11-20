package com.triibe.zyephyr.domain.usecase

import com.triibe.zyephyr.common.Resource
import com.triibe.zyephyr.data.model.store_list.toDomainMeal
import com.triibe.zyephyr.domain.model.store_list.StoreModel
import com.triibe.zyephyr.domain.repository.StoreListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class StoreListUseCase @Inject constructor(private val repository: StoreListRepository) {


    operator fun invoke(q: String): Flow<Resource<List<StoreModel>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getStoreList()
            val domainData =
                if (data != null) data.map { it -> it.toDomainMeal() } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        }
    }



}