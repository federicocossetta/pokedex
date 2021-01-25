package com.fcossetta.pokedex.main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Pokemon(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "order")
    val order: Int? = null,
    @Json(name = "height")
    val height: Int? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "weight")
    val weight: Int? = null,
    @Json(name = "stats")
    val stats: List<StatInfo>? = null,
)

@JsonClass(generateAdapter = true)
data class Stat(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "url")
    val url: String? = null,
)

@JsonClass(generateAdapter = true)
data class StatInfo(
    @Json(name = "base_stat")
    val baseStats: Int? = null,
    @Json(name = "effort")
    val effort: Int? = null,
    @Json(name = "stat")
    val stat: Stat? = null
)

@JsonClass(generateAdapter = true)
data class Type(
    @Json(name = "slot")
    val slot: String? = null,
    @Json(name = "type")
    val typeDetail: List<TypeDetail>? = null
)

@JsonClass(generateAdapter = true)
data class TypeDetail(val name: String? = null, val url: String? = null)
