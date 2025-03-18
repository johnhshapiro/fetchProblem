package com.example.fetchproblem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchproblem.network.HiringApi
import kotlinx.coroutines.launch

class MainActivityViewModel(): ViewModel() {

    init {
        getHiringData()
    }

    fun getHiringData() {
        viewModelScope.launch {
            HiringApi.retrofitService.getHiring()
        }
    }

}