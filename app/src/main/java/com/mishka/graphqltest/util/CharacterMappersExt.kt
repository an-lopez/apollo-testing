package com.mishka.graphqltest.util

import com.mishka.graphqltest.apollo.CharactersAndEpisodeAndOriginQuery
import com.mishka.graphqltest.apollo.CharactersQuery
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.domain.model.EpisodeModel
import com.mishka.graphqltest.domain.model.OriginModel
import com.mishka.graphqltest.room.model.CharacterEntity

inline fun mapCharacterDto(
    query: CharactersQuery.Data?,
    mapOriginModel: (originNetwork: CharactersQuery.Origin?) -> OriginModel
): List<CharacterModel> {
    return mapNullInputList(query?.characters?.results) {
        mapCharacter(it, mapOriginModel)
    }
}

inline fun mapCharacter(
    it: CharactersQuery.Result?,
    mapOriginModel: (originNetwork: CharactersQuery.Origin?) -> OriginModel
): CharacterModel =
    CharacterModel(
        id = it?.id?.toInt() ?: 0,
        name = it?.name.orEmpty(),
        gender = it?.gender.orEmpty(),
        image = it?.image.orEmpty(),
        origin = mapOriginModel(it?.origin),
        episodes = emptyList()
    )

fun mapOrigin(it: CharactersQuery.Origin?): OriginModel {
    return OriginModel(
        id = it?.id?.toInt() ?: 0,
        name = it?.name.orEmpty(),
        dimension = it?.dimension.orEmpty(),
        type = it?.type.orEmpty(),
    )
}

inline fun mapSingleCharacter(
    it: CharactersAndEpisodeAndOriginQuery.Character?,
    mapOriginModel: (originNetwork: CharactersAndEpisodeAndOriginQuery.Origin?) -> OriginModel,
    mapEpisodes: (originNetwork: List<CharactersAndEpisodeAndOriginQuery.Episode?>) -> List<EpisodeModel>
): CharacterModel =
    CharacterModel(
        id = it?.id?.toInt() ?: 0,
        name = it?.name.orEmpty(),
        gender = it?.gender.orEmpty(),
        image = it?.image.orEmpty(),
        origin = mapOriginModel(it?.origin),
        episodes = mapEpisodes(it?.episode ?: emptyList())
    )

fun mapEpisode(episode: CharactersAndEpisodeAndOriginQuery.Episode?): EpisodeModel = with(episode) {
    EpisodeModel(
        id = this?.id?.toInt() ?: 0,
        name = this?.name.orEmpty(),
        episode = this?.episode.orEmpty(),
        airDate = this?.air_date.orEmpty()
    )
}

fun mapOriginCharactersQuery(it: CharactersAndEpisodeAndOriginQuery.Origin?): OriginModel {
    return OriginModel(
        id = it?.id?.toInt() ?: 0,
        name = it?.name.orEmpty(),
        dimension = it?.dimension.orEmpty(),
        type = it?.type.orEmpty(),
    )
}

fun mapCharacterModelToEntity(it: CharacterModel): CharacterEntity =
    CharacterEntity(it.id, it.name, it.gender, it.origin.name, it.image)

fun mapCharacterEntityToModel(it: CharacterEntity): CharacterModel =
    CharacterModel(it.id, it.name, OriginModel(0, it.origin, "", ""), it.gender, it.image, emptyList())