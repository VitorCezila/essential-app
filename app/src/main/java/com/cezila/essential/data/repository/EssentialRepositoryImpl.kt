package com.cezila.essential.data.repository

import com.cezila.essential.data.local.EssentialDatabase
import com.cezila.essential.data.mapper.toDrink
import com.cezila.essential.data.mapper.toDrinkEntity
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
    private val db: EssentialDatabase
) : EssentialRepository {

    private val dao = db.dao

    override suspend fun getDrinkListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Drink>>> {
        return flow {
            emit(Resource.Loading(true))
            val localDrinks = dao.searchDrink(query)
            emit(Resource.Success(
                data = localDrinks.map { it.toDrink() }
            ))

            val isDbEmpty = localDrinks.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
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
                dao.clearDrinks()
                dao.insertDinks(
                    listings.map { it.toDrink().toDrinkEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchDrink("")
                        .map { it.toDrink() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}