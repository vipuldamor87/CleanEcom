package com.triibe.zyephyr.presentation.product_lst

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triibe.zyephyr.common.Resource
import com.triibe.zyephyr.domain.model.product_list.ProductModel
import com.triibe.zyephyr.domain.usecase.ProductListUseCase
import com.triibe.zyephyr.presentation.store_lst.StoreListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private  val productListUseCase: ProductListUseCase
): ViewModel() {

    private val _productList = MutableStateFlow<ProductListState>(ProductListState())
    val productList: StateFlow<ProductListState> = _productList

    val cart: MutableLiveData<ProductListState> = MutableLiveData(ProductListState())


    fun getProductList() {
        productListUseCase("a").onEach {
            when (it) {
                is Resource.Loading -> {
                    _productList.value = ProductListState(isLoading = true)
                }
                is Resource.Success -> {
                    _productList.value = ProductListState(data = it.data as ArrayList<ProductModel>?)
                }
                is Resource.Error -> {
                    _productList.value = ProductListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
    fun addToCart(productModel: ProductModel){
        if(cart.value?.data.isNullOrEmpty()){
            cart.value = ProductListState(data = arrayListOf())
        }
        var data = cart.value?.data
        data?.add(productModel)
        cart.postValue(ProductListState(data = data))

    }


}