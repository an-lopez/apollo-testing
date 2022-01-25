package com.mishka.graphqltest.domain.dashboard

import com.apollographql.apollo3.ApolloClient
import com.mishka.graphqltest.apollo.CharactersAndEpisodeAndOriginQuery
import com.mishka.graphqltest.apollo.CharactersQuery
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.domain.model.OriginModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val apollo: ApolloClient,
    private val mapper: (@JvmSuppressWildcards CharactersAndEpisodeAndOriginQuery.Character?) -> @JvmSuppressWildcards  CharacterModel
) : CharacterDetailRepository {

    override suspend fun getCharacterSingle(characterId: Int): CharacterModel {
        val data = apollo.query(CharactersAndEpisodeAndOriginQuery(characterId.toString())).execute()
        return mapper(data.data?.character)
    }

    override suspend fun saveCharacterSingle(model: CharacterModel): Int {
        return 0
    }

    override suspend fun getCacheCharacterSingle(characterId: Int): CharacterModel {
        return CharacterModel(0, "", OriginModel(0, "", "", "") ,"0" ,"0", emptyList())
    }


}