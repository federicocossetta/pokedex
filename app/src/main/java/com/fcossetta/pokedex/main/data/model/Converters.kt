package com.fcossetta.pokedex.main.data.model

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {

    @TypeConverter
    fun pokemonTypesToJson(value: List<PokemonType>?): String {
        val listMyData = Types.newParameterizedType(MutableList::class.java, PokemonType::class.java)
        val adapter: JsonAdapter<List<PokemonType>> =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(listMyData)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun jsonToPokemonTypes(value: String): List<PokemonType>? {
        val listMyData = Types.newParameterizedType(MutableList::class.java, PokemonType::class.java)
        val adapter: JsonAdapter<List<PokemonType>> =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(listMyData)
        return adapter.fromJson(value)
    }
    @TypeConverter
    fun statsToJson(value: List<StatInfo>?): String {
        val listMyData = Types.newParameterizedType(MutableList::class.java, StatInfo::class.java)
        val adapter: JsonAdapter<List<StatInfo>> =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(listMyData)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun jsonToStats(value: String): List<StatInfo> {
        val listMyData = Types.newParameterizedType(MutableList::class.java, StatInfo::class.java)
        val adapter: JsonAdapter<List<StatInfo>> =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(listMyData)
        return adapter.fromJson(value)!!
    }

    @TypeConverter
    fun spriteToJson(value: Sprite): String {
        val adapter: JsonAdapter<Sprite> =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(Sprite::class.java)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun jsonToSprites(value: String): Sprite {
        val adapter: JsonAdapter<Sprite> =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(Sprite::class.java)
        return adapter.fromJson(value)!!
    }
}