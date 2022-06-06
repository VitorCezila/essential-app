package com.cezila.essential.domain.repository

import com.cezila.essential.domain.model.Drink
import com.cezila.essential.util.Resource
import kotlinx.coroutines.flow.Flow

interface EssentialRepository {

    suspend fun getDrinkListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Drink>>>

}