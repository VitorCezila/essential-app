package com.cezila.essential.data.repository

import com.cezila.essential.data.mapper.toDrink
import com.cezila.essential.data.remote.EssentialApi
import com.cezila.essential.domain.model.Drink
import com.cezila.essential.domain.repository.EssentialRepository
import com.cezila.essential.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EssentialRepositoryImpl @Inject constructor(
    private val api: EssentialApi,
) : EssentialRepository {
    override suspend fun getDrinkListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Drink>>> {
        return flow {
            val remoteListings = try {
                api.getDrinks()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                emit(Resource.Success(
                    data = listings.map { it.toDrink() }
                ))
            }
        }
    }
}