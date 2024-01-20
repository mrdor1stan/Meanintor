package com.mrdor1stan.meanintor

import android.util.Log
import com.mrdor1stan.meanintor.data.WordCard
import com.squareup.moshi.JsonClass
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID


private const val TAG = "DictionaryRepository"

interface FreeDictionaryApi {
    @GET("/api/v2/entries/en/{word}")
    suspend fun query(@Path(value = "word", encoded = true) word: String): List<DictionaryResponse>
}

@JsonClass(generateAdapter = true)
data class DictionaryResponse(
    val word: String,
    val phonetic: String?,
    val meanings: List<MeaningsResponse>
)

fun List<DictionaryResponse>.toWordCards():List<WordCard> {
    val result: MutableList<WordCard> = mutableListOf()
    this.forEach {response ->
        response.meanings.forEach { meaning ->
            meaning.definitions.forEach { definition ->
                val example = definition.example?.let { listOf(it) } ?: emptyList()
                result.add(
                    WordCard(
                        id = UUID.randomUUID(),
                        word = response.word,
                        phonetic = response.phonetic,
                        definition = definition.definition,
                        example = example,
                        antonyms = definition.antonyms,
                        synonyms = definition.synonyms,
                        partOfSpeech = meaning.partOfSpeech
                    )
                )
            }
        }
    }
    return result
}

@JsonClass(generateAdapter = true)
data class MeaningsResponse(
    val partOfSpeech: String,
    val definitions: List<DefinitionsResponse>
)

@JsonClass(generateAdapter = true)
data class DefinitionsResponse(
    val definition: String?,
    val synonyms: List<String>,
    val antonyms: List<String>,
    val example: String?
)