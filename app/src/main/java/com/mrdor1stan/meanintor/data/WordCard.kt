package com.mrdor1stan.meanintor.data

import androidx.room.Entity
import com.mrdor1stan.meanintor.MeaningsResponse
import java.util.UUID

@Entity
data class WordCard(
    val id: UUID,
    val word: String,
    val phonetic: String?,
    val definition: String?,
    val partOfSpeech: String?,
    val synonyms: List<String>,
    val antonyms: List<String>,
    val example: List<String>
)

