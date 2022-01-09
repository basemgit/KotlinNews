package com.basemibrahim.redditnews.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.basemibrahim.redditnews.R
import com.basemibrahim.redditnews.databinding.ListFragmentBinding
import com.basemibrahim.redditnews.utils.Constants
import com.basemibrahim.redditnews.utils.NetworkResult
import com.basemibrahim.redditnews.utils.Utils
import com.basemibrahim.redditnews.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var _binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListFragmentBinding.inflate(inflater)
        _binding.lifecycleOwner = this
        _binding.viewModel = mainViewModel
        _binding.list.adapter = NewsAdapter()
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchResponse()
    }

    private fun fetchResponse() {
        if (Utils.isNetworkAvailable(requireContext())) {
            mainViewModel.fetchNewsResponse()
            _binding.pbDog.visibility = View.VISIBLE
            processData()
        } else {
            Snackbar.make(
                _binding.root,
                resources.getString(R.string.no_internet),
                Snackbar.LENGTH_SHORT
            )
                .show()
            _binding.pbDog.visibility = View.GONE
            mainViewModel.fetchResponseFromDb()
        }

    }

    private fun processData() {
        mainViewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        mainViewModel.saveResponseToDb(response.data)
                    }
                    _binding.pbDog.visibility = View.GONE
                }

                is NetworkResult.Error -> {
                    _binding.pbDog.visibility = View.GONE
                    Log.d(Constants.NETWORK_TAG, response.message.toString())
                }

                is NetworkResult.Loading -> {
                    _binding.pbDog.visibility = View.VISIBLE
                }
            }
        }
    }


}