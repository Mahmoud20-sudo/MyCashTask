package com.plcoding.mycashtask.yjahz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.catrgories.CategoryModel
import com.plcoding.mycashtask.yjahz.data.model.sellers.PopularSeller
import com.plcoding.mycashtask.yjahz.data.model.user.User
import com.plcoding.mycashtask.yjahz.domain.use_case.CategoriesUseCases
import com.plcoding.mycashtask.yjahz.domain.use_case.SellersUseCases
import com.plcoding.mycashtask.yjahz.domain.use_case.UsersUseCases
import com.plcoding.mycashtask.yjahz.presentation.model.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sellersUseCases: SellersUseCases,
    private val userUseCases: UsersUseCases,
    private val categoriesUseCases: CategoriesUseCases
) : ViewModel() {

    private val _popularSellersFlow = MutableStateFlow<PopularSeller?>(null)
    val popularSellersFlow: StateFlow<PopularSeller?> = _popularSellersFlow

    private val _trendingSellersFlow = MutableStateFlow<PopularSeller?>(null)
    val trendingSellersFlow: StateFlow<PopularSeller?> = _trendingSellersFlow

    private val _categoriesFlow = MutableStateFlow<CategoryModel?>(null)
    val categoriesFlow: StateFlow<CategoryModel?> = _categoriesFlow

    private val _userFlow = MutableStateFlow<User?>(null)
    val userFlow: SharedFlow<User?> = _userFlow

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    init {
        getUserFromLocal()

        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + handler) {
            withTimeout(5000) {
                combine(
                    sellersUseCases.getPopular.invoke(29.1931, 30.6421),
                    sellersUseCases.getTrending.invoke(29.1931, 30.6421),
                    categoriesUseCases.invoke()
                ) { popular, trending, categories ->
                    renderSellers(true, popular)
                    renderSellers(false, trending)
                    renderCategories(categories)
                    _isLoading.value = false
                }.collect {
                    println("End of Combine")
                }
            }
        }
    }

    private fun getUserFromLocal() = viewModelScope.launch {
        userUseCases.profile.invoke().collectLatest { profile ->
            _userFlow.value = profile
        }
    }

    private fun renderSellers(isPopular: Boolean, res: Resource<PopularSeller?>) {
        when (res) {
            is Resource.Error -> {
                //FAIL
                viewModelScope.launch {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = res.error?.message ?: "Couldn't retrieve sellers"
                        )
                    )
                }
            }

            is Resource.Loading -> {
                //LOADING
            }

            is Resource.Success -> {
                res.data?.let {
                    if (isPopular) _popularSellersFlow.value = it else _trendingSellersFlow.value =
                        it
                }
            }
        }
    }

    private fun renderCategories(res: Resource<CategoryModel?>) {
        when (res) {
            is Resource.Error -> {
                //FAIL
                viewModelScope.launch {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            message = res.error?.message ?: "Couldn't retrieve categories"
                        )
                    )
                }
            }

            is Resource.Loading -> {
                //LOADING
            }

            is Resource.Success -> {
                res.data?.let {
                    _categoriesFlow.value = it
                }
            }
        }
    }
}