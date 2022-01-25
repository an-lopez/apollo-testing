package com.mishka.graphqltest.interactors.dashboard

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.rx3.rxSingle
import com.mishka.graphqltest.apollo.CharactersAndEpisodeAndOriginQuery
import com.mishka.graphqltest.apollo.CharactersQuery
import com.mishka.graphqltest.core.UseCase
import com.mishka.graphqltest.domain.model.CharacterModel
import com.mishka.graphqltest.domain.model.EpisodeModel
import com.mishka.graphqltest.domain.model.OriginModel
import com.mishka.graphqltest.util.mapNullInputList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

data class CharacterViewState(
    val characters: List<CharacterModel>? = null,
    val loading: Boolean = false,
    val error: Throwable? = null
)

class GetCharactersUseCase @Inject constructor(private val repository: DashboardRepository) :
    UseCase<Flowable<CharacterViewState>> {

    override fun invoke(): Flowable<CharacterViewState> {
        return Flowable.create({ emitter ->
            emitter.onNext(CharacterViewState(loading = true))
            Timber.tag(GetCharactersUseCase::class.simpleName.toString()).d("Se emite loading")
            repository.getCharactersRemote().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { charactersList ->
                        emitter.onNext(CharacterViewState(characters = charactersList))
                    },
                    { throwable ->
                        emitter.onNext(CharacterViewState(error = throwable))
                    }
                )
        }, BackpressureStrategy.BUFFER)
    }
}

interface DashboardRepository {

    fun getCharactersRemote(): Single<List<CharacterModel>>

    fun persistCharacters(characters: List<CharacterModel>)

    fun getCharacter(id: String)

}


class DashboardRepositoryImpl
@Inject constructor(
    private val apollo: ApolloClient,
    private val mapper: @JvmSuppressWildcards (CharactersQuery.Data?) -> @JvmSuppressWildcards List<CharacterModel>
) :
    DashboardRepository {

    override fun getCharactersRemote(): Single<List<CharacterModel>> {
        return Single.create { emitter ->
            apollo.query(CharactersQuery())
                .rxSingle()
                .subscribe({
                    Timber.tag(GetCharactersUseCase::class.simpleName.toString())
                        .d("Retorna respuesta de Apollo")
                    emitter.onSuccess(mapper(it.data))
                }, {
                    emitter.onError(it)
                })
        }
    }

    override fun persistCharacters(characters: List<CharacterModel>) {

    }

    override fun getCharacter(id: String) {
        TODO("Not yet implemented")
    }

}

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

fun mapEpisode(episode: CharactersAndEpisodeAndOriginQuery.Episode?): EpisodeModel = with(episode){
    EpisodeModel(id = this?.id?.toInt() ?: 0,
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
