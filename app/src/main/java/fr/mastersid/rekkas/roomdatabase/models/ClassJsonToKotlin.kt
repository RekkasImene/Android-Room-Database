package fr.mastersid.rekkas.roomdatabase.models

import com.squareup.moshi.Json

data class ClassJsonToKotlin(
    @field : Json(name = "items") val items: List<Item>
)
