package com.sco.musicalcoffee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sco.musicalcoffee.espresso.TestIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(private val repository: ICoffeeRepository) : ViewModel() {

    private val _viewState = MutableLiveData<CoffeeListState>()
    val viewState: LiveData<CoffeeListState>
        get() = _viewState

    fun getCoffeeList() {
        TestIdlingResource.increment()
        viewModelScope.launch {
            _viewState.value = CoffeeListState.Loading
            val coffeeList = repository.getCoffee()
            if (coffeeList.isEmpty()) {
                _viewState.postValue(CoffeeListState.Error)
            } else {
                _viewState.postValue(CoffeeListState.Loaded(coffeeList))
            }
            TestIdlingResource.decrement()
        }
    }
}

sealed class CoffeeListState {
    object Loading : CoffeeListState()
    data class Loaded(val coffeeList: List<Coffee>) : CoffeeListState()
    object Error : CoffeeListState()
}