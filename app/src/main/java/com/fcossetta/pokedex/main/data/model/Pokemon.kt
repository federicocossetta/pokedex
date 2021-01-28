package com.fcossetta.pokedex.main.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable


@JsonClass(generateAdapter = true)
@Entity
data class Pokemon(
    @ColumnInfo(name = "name")
    @Json(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "order")
    @Json(name = "order")
    val order: Int? = null,
    @Json(name = "height")
    @ColumnInfo(name = "height")
    val height: Int? = null,
    @Json(name = "id")
    @PrimaryKey
    val id: Int? = null,
    @Json(name = "weight")
    @ColumnInfo(name = "weight")
    val weight: Int? = null,
    @Json(name = "stats")
    @ColumnInfo(name = "stats")
    val stats: List<StatInfo>? = null,
    @Json(name = "sprites")
    @ColumnInfo(name = "sprites")
    val sprites: Sprite? = null,
    @Json(name = "types")
    @ColumnInfo(name = "types")
    val pokemonTypes: List<PokemonType>? = null,
    @ColumnInfo(name = "url")
    var url: String? = null,

    ) : Serializable

@JsonClass(generateAdapter = true)
@Entity
data class Stat(
    @Json(name = "name")
    @ColumnInfo(name = "name")
    val name: String? = null,
    @Json(name = "url")
    @ColumnInfo(name = "url")
    val url: String? = null,
)

@JsonClass(generateAdapter = true)
@Entity
data class StatInfo(
    @Json(name = "base_stat")
    @ColumnInfo(name = "base_stat")
    val baseStats: Int? = null,
    @Json(name = "effort")
    @ColumnInfo(name = "effort")
    val effort: Int? = null,
    @Json(name = "stat")
    @ColumnInfo(name = "stat")
    val stat: Stat? = null,
)

@JsonClass(generateAdapter = true)
@Entity
data class Sprite(
    @Json(name = "front_default")
    @ColumnInfo(name = "front_default")
    val front_default: String? = null,
)


@JsonClass(generateAdapter = true)
data class PokemonType(
    @Json(name = "slot")
    @ColumnInfo(name = "slot")
    val slot: String? = null,
    @Json(name = "type")
    @ColumnInfo(name = "typeDetail")
    val typeDetail: TypeDetail? = null,
)

@JsonClass(generateAdapter = true)
data class TypeDetail(
    @Json(name = "name")
    @ColumnInfo(name = "name")
    val name: String? = null,
    @Json(name = "url")
    @ColumnInfo(name = "url")
    val url: String? = null,
)
