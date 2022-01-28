package com.mishka.graphqltest.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.google.gson.Gson
import com.mishka.graphqltest.apollo.CharactersQuery
import com.mishka.graphqltest.data.repository.CharacterRepository
import com.mishka.graphqltest.data.repository.CharacterRepositoryImpl
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.room.ApplicationDatabase
import com.mishka.graphqltest.room.dao.CharacterDao
import com.mishka.graphqltest.room.model.CharacterEntity
import com.mishka.graphqltest.util.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApolloClient(client: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql")
            .okHttpClient(client)
            .build()
    }


    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
            })
            .build()
    }

    @Provides
    @Singleton
    fun providesCharacterMapper() = { data: CharactersQuery.Data? ->
        val mapCharacterDto = mapCharacterDto(data) {
            mapOrigin(it)
        }
        mapCharacterDto
    }

    @Provides
    @Singleton
    fun providesSingleCharacterMapper() = { data: CharacterEntity ->
        mapCharacterEntityToModel(data)
    }

    @Provides
    @Singleton
    fun providesCacheCharacterMapper() = { data: CharacterModel ->
        mapCharacterModelToEntity(data)
    }

    @Provides
    @Singleton
    fun providesEntityCharacterListMapper() = { data: List<CharacterEntity> ->
        mapList(data){
            mapCharacterEntityToModel(it)
        }
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): ApplicationDatabase =
        Room.databaseBuilder(context, ApplicationDatabase::class.java, "APP_DATABASE").build()

    @Provides
    fun providesCharacterDao(applicationDatabase: ApplicationDatabase): CharacterDao =
        applicationDatabase.characterDao()


}

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindCharacterDetailRepository {
    @Binds
    abstract fun bindCharacterDetailRepository(repositoryImpl: CharacterRepositoryImpl): CharacterRepository

}