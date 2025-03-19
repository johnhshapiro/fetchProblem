package com.example.fetchproblem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchproblem.adapter.HiringItemRecyclerViewAdapter
import com.example.fetchproblem.model.Item
import com.example.fetchproblem.model.ListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.URL

class MainActivityViewModel() : ViewModel() {

    private var hiringData = ""
    private val json = Json {
        coerceInputValues = true
    }
    private val _hiringViewState = MutableStateFlow(HiringViewState(mutableListOf(), true))
    val hiringViewState: StateFlow<HiringViewState> = _hiringViewState


    init {
        getHiringData()
    }

    private fun getHiringData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                hiringData = URL("https://fetch-hiring.s3.amazonaws.com/hiring.json").readText()
                val items = json.decodeFromString<List<Item>>(hiringData)
                    .filter { it.name.isNotBlank() }
                    .sortedBy { it.name }
                    .sortedBy { it.listId }
                val groupedItems = items.groupBy { it.listId }
                val listItems = mutableListOf<ListItem>()
                groupedItems.forEach { (listId, items) ->
                    listItems.add(
                        ListItem(
                            HiringItemRecyclerViewAdapter.TYPE_HEADER,
                            header = listId.toString()
                        )
                    )
                    items.forEach { item ->
                        listItems.add(
                            ListItem(
                                HiringItemRecyclerViewAdapter.TYPE_ITEM,
                                item = item
                            )
                        )
                    }
                }
                _hiringViewState.value = HiringViewState(listItems, false)
            }
        }
    }

    class HiringViewState(
        val hiringItems: List<ListItem>,
        val loading: Boolean
    )
}