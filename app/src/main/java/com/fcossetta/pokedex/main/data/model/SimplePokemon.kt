package com.fcossetta.pokedex.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SimplePokemon(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "url")
    val url: String? = null
)