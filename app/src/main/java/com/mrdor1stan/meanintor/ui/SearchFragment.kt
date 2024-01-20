package com.mrdor1stan.meanintor.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mrdor1stan.meanintor.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            for (str in listOf("bright", "take part", "j,mfavnlakf", "introduction")) {
                val result =  searchFragmentViewModel.searchWord(str)
                Log.d(TAG, result.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}