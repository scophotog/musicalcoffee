package com.sco.musicalcoffee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CoffeeViewModel(private val repository: CoffeeRepository = CoffeeRepository()) : ViewModel() {

    private val _viewState = MutableLiveData<CoffeeListState>()
    val viewState: LiveData<CoffeeListState>
        get() = _viewState

    fun getCoffeeList() {
        viewModelScope.launch {
            _viewState.value = CoffeeListState.Loading
            val coffeeList = repository.getCoffee()
            if (coffeeList.isEmpty()) {
                _viewState.postValue(CoffeeListState.Error)
            } else {
                _viewState.postValue(CoffeeListState.Loaded(coffeeList))
            }
        }
    }
}

sealed class CoffeeListState {
    object Loading : CoffeeListState()
    data class Loaded(val coffeeList: List<Coffee>) : CoffeeListState()
    object Error : CoffeeListState()
}