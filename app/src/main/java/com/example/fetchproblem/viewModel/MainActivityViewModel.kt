package com.example.fetchproblem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchproblem.model.HiringItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.URL

class MainActivityViewModel(): ViewModel() {

    private var hiringData = ""
    private val json = Json {
        coerceInputValues = true
    }
    var hiringViewState: HiringViewState = HiringViewState(mutableListOf())


    init {
        getHiringData()
    }

    private fun getHiringData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                hiringData = URL("https://fetch-hiring.s3.amazonaws.com/hiring.json").readText()
                hiringViewState = HiringViewState(
                    json.decodeFromString<List<HiringItem>>(hiringData)
                        .filter{ it.name.isNotBlank() }
                        .sortedBy { it.name }
                        .sortedBy { it.listId }
                )
            }
        }
    }

    class HiringViewState(
        val hiringItems: List<HiringItem>
    )
}