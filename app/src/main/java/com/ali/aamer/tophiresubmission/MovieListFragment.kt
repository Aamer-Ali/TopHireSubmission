package com.ali.aamer.tophiresubmission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ali.aamer.tophiresubmission.ViewModel.MoviesViewModel
import com.ali.aamer.tophiresubmission.adapter.MoviesListAdapter
import com.ali.aamer.tophiresubmission.databinding.FragmentMovieListBinding
import com.ali.aamer.tophiresubmission.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel by viewModels<MoviesViewModel>()
    private lateinit var adapter: MoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        adapter = MoviesListAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObserver()
        moviesViewModel.getMoviesList()
        binding.rcvMoviesList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rcvMoviesList.adapter = adapter

    }

    private fun bindObserver() {
        moviesViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressCircular.isVisible = false
            binding.tvError.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    adapter.submitList(it.data)
                }
                is NetworkResult.Failure -> {
                    binding.tvError.text = it.message
                    binding.tvError.isVisible = true
                }
                is NetworkResult.Loading -> {
                    binding.progressCircular.isVisible = true
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}