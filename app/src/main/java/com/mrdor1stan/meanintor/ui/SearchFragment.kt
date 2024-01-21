package com.mrdor1stan.meanintor.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrdor1stan.meanintor.SearchFragmentViewModel
import com.mrdor1stan.meanintor.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "SearchFragment"
@AndroidEntryPoint
class SearchFragment : Fragment() {

    val searchFragmentViewModel:SearchFragmentViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!
    private val adapter = WordCardListAdapter()

    //TODO: Add Internet connection check
    //TODO: Add saving cards to the memory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(VerticalSpacingItemDecoration(8))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                        val result = query?.let { searchFragmentViewModel.searchWord(it) }
                        adapter.submitList(result)
                    }
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        lifecycleScope.launch {
            for (str in listOf("bright", "take part", "j,mfavnlakf", "introduction")) {
                val result =  searchFragmentViewModel.searchWord(str)
                adapter.submitList(result)
                Log.d(TAG, result.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}