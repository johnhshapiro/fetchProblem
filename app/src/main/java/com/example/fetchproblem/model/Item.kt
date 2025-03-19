package com.example.fetchproblem.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int = -1,
    val listId: Int = -1,
    val name: String = ""
)

data class ListItem(
    val type: Int,
    val item: Item? = null,
    val header: String? = null
)