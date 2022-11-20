package com.triibe.zyephyr.presentation.checkout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triibe.zyephyr.common.Resource
import com.triibe.zyephyr.domain.model.order.OrderModel
import com.triibe.zyephyr.domain.model.product_list.ProductModel
import com.triibe.zyephyr.domain.usecase.OrderUseCase
import com.triibe.zyephyr.domain.usecase.ProductListUseCase
import com.triibe.zyephyr.presentation.product_lst.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private  val orderUseCase: OrderUseCase
): ViewModel() {


    private val _orderState = MutableStateFlow<OrderState>(OrderState())
    val orderState: StateFlow<OrderState> = _orderState

    val cart: MutableLiveData<ProductListState> = MutableLiveData(ProductListState())



    fun placeOrder(address:String,total:Int) {
        var data = HashMap<String,Any>()
        data.put("id", (1..1000).random())
        data.put("address",address)
        data.put("total",total)
        cart.value?.data?.let { data.put("Products", it) }
        orderUseCase(data).onEach {
            when (it) {
                is Resource.Loading -> {
                    _orderState.value = OrderState(isLoading = true)
                }
                is Resource.Success -> {
                    _orderState.value = OrderState(data = it.data)
                }
                is Resource.Error -> {
                    _orderState.value = OrderState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setCart(productListState: ProductListState){
        cart.postValue(productListState)
    }


}