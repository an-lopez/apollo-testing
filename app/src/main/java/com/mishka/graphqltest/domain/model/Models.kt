package com.mishka.graphqltest.domain.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val origin: OriginModel,
    val gender: String,
    val image: String,
    val episodes: List<EpisodeModel>
)

data class OriginModel(
    val id: Int,
    val name: String,
    val dimension: String,
    val type: String
)

data class EpisodeModel(
    val id: Int,
    val name: String,
    val episode: String,
    val airDate: String
)
