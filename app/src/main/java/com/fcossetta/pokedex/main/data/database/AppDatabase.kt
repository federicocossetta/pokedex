package com.fcossetta.pokedex.main.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fcossetta.pokedex.main.data.dao.PokemonDao
import com.fcossetta.pokedex.main.data.model.Converters
import com.fcossetta.pokedex.main.data.model.Pokemon
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

@Database(entities = [Pokemon::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}

