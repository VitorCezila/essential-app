package com.cezila.essential.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DrinkEntity::class],
    version = 1
)
abstract class EssentialDatabase: RoomDatabase() {
    abstract val dao: EssentialDao
}