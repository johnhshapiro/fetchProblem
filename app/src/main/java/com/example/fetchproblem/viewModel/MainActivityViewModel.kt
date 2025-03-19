package com.example.fetchproblem.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchproblem.adapter.HiringItemRecyclerViewAdapter
import com.example.fetchproblem.model.Item
import com.example.fetchproblem.model.ListItem
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
                val items = json.decodeFromString<List<Item>>(hiringData)
                    .filter{ it.name.isNotBlank() }
                    .sortedBy { it.name }
                    .sortedBy { it.listId }
                val groupedItems = items.groupBy { it.listId }
                val listItems = mutableListOf<ListItem>()
                groupedItems.forEach { (listId, items) ->
                    listItems.add(ListItem(HiringItemRecyclerViewAdapter.TYPE_HEADER, header = listId.toString()))
                    items.forEach { item ->
                        listItems.add(ListItem(HiringItemRecyclerViewAdapter.TYPE_ITEM, item = item))
                    }
                }
                hiringViewState = HiringViewState(listItems)
            }
        }
    }

    class HiringViewState(
        val hiringItems: List<ListItem>
    )
}