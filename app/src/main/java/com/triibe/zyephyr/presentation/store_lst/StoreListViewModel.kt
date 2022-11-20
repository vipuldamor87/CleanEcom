package com.triibe.zyephyr.presentation.store_lst

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triibe.zyephyr.common.Resource
import com.triibe.zyephyr.domain.usecase.StoreListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StoreListViewModel @Inject constructor(
    private  val storeListUseCase: StoreListUseCase
    ):ViewModel() {

    private val _storeList = MutableStateFlow<StoreListState>(StoreListState())
    val storeList: StateFlow<StoreListState> = _storeList


    fun getStoreList(s: String) {
        storeListUseCase(s).onEach {
            when (it) {
                is Resource.Loading -> {
                    _storeList.value = StoreListState(isLoading = true)
                }
                is Resource.Success -> {
                    _storeList.value = StoreListState(data = it.data)
                }
                is Resource.Error -> {
                    _storeList.value = StoreListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }


}