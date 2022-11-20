package com.triibe.zyephyr.domain.usecase

import com.triibe.zyephyr.common.Resource
import com.triibe.zyephyr.data.model.product_list.toDomain
import com.triibe.zyephyr.domain.model.order.OrderModel
import com.triibe.zyephyr.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class OrderUseCase @Inject constructor(private val repository: OrderRepository) {


    operator fun invoke(data: HashMap<String,Any>): Flow<Resource<OrderModel>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.placeOrder(data)
            val domainData =
                data.toDomain()
                if (data != null) {  data.toDomain() }
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        }
    }



}