package com.mrdor1stan.meanintor.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mrdor1stan.meanintor.data.WordCard
import com.mrdor1stan.meanintor.databinding.ItemListWordCardBinding

class WordCardListAdapter : ListAdapter<WordCard, WordCardListAdapter.WordCardViewHolder>(WordCard.DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListWordCardBinding.inflate(inflater, parent, false)
        return WordCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordCardViewHolder, position: Int) {
        val card = this.currentList.get(position)
        holder.bind(card)
    }

    class WordCardViewHolder(private val binding: ItemListWordCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WordCard) {
            binding.apply {
                word.setText(item.word)
                partOfSpeech.setText(item.partOfSpeech)
                definition.setText(item.definition)
            }
        }
    }
}

