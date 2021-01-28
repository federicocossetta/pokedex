package com.fcossetta.pokedex.main.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class SimplePokemon(
    @Json(name = "name")
    @ColumnInfo(name = "name")
    val name: String? = null,
    @Json(name = "url")
    @ColumnInfo(name = "url")
    @PrimaryKey
    val url: String? = null,
    @ColumnInfo(name = "indexSearch")
    var indexSearch : Int? = null,
    @ColumnInfo(name = "limitSearch")
    var limitSearch : Int? = null,

    )