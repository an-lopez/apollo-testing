package com.mishka.graphqltest.di

import com.mishka.graphqltest.data.repository.CharacterRepository
import com.mishka.graphqltest.data.repository.FakeCharacterAndroidRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn


@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [AppModule::class, RepositoryModule::class])
abstract class AppTestModule {
    @Binds
    abstract fun bindCharacterDetailRepository(repositoryImpl: FakeCharacterAndroidRepository): CharacterRepository

}