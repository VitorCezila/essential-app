package com.cezila.essential.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EssentialDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDinks(
        drinksEntities: List<DrinkEntity>
    )

    @Query("DELETE FROM drinkentity")
    suspend fun clearDrinks()

    @Query(
        """
            SELECT * 
            FROM drinkentity
            WHERE 
            LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
            LOWER(ingredients) LIKE '%' || LOWER(:query) || '%' OR 
            LOWER(author) LIKE '%' || LOWER(:query) || '%'
        """
    )
    suspend fun searchDrink(query: String): List<DrinkEntity>

}