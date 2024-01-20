package com.mrdor1stan.meanintor

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrdor1stan.meanintor.data.WordCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SearchFragmentViewModel"

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val api: FreeDictionaryApi
) : ViewModel() {


    suspend fun searchWord(query: String): List<WordCard> {
        try {
            val query = api.query(query)
            return query.toWordCards()
        } catch (e: Exception) {
            Log.w(TAG, "Dictionary request failed: ${query}")
            return emptyList()
        }
    }
}
