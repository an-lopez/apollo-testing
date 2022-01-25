package com.mishka.graphqltest.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.google.gson.Gson
import com.mishka.graphqltest.apollo.CharactersAndEpisodeAndOriginQuery
import com.mishka.graphqltest.apollo.CharactersQuery
import com.mishka.graphqltest.domain.dashboard.CharacterDetailRepository
import com.mishka.graphqltest.domain.dashboard.CharacterDetailRepositoryImpl
import com.mishka.graphqltest.interactors.dashboard.*
import com.mishka.graphqltest.util.mapNullInputList
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        mapCharacterDto(data) {
            mapOrigin(it)
        }
    }

    @Provides
    @Singleton
    fun providesSingleCharacterMapper() = { data: CharactersAndEpisodeAndOriginQuery.Character? ->
        mapSingleCharacter(data,
            {
                mapOriginCharactersQuery(it)
            },
            { episodeList ->
                mapNullInputList(episodeList) { episode ->
                    mapEpisode(episode)
                }
            }
        )
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }


}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindDashboardRepository {
    @Binds
    abstract fun bindDashboardRepository(repositoryImpl: DashboardRepositoryImpl): DashboardRepository

}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindCharacterDetailRepository {
    @Binds
    abstract fun bindCharacterDetailRepository(repositoryImpl: CharacterDetailRepositoryImpl): CharacterDetailRepository

}