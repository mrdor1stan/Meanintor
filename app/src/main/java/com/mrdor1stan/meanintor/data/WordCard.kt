package com.mrdor1stan.meanintor.data

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
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
) {
    class DiffCallback : DiffUtil.ItemCallback<WordCard>() {
        override fun areItemsTheSame(oldItem: WordCard, newItem: WordCard): Boolean =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: WordCard, newItem: WordCard): Boolean =
            oldItem == newItem
    }
}
