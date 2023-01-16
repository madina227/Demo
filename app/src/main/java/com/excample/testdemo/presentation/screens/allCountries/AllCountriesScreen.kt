package com.excample.testdemo.presentation.screens.allCountries

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.excample.testdemo.R
import com.excample.testdemo.databinding.ScreenAllCountriesBinding
import com.excample.testdemo.presentation.screens.allCountries.adapter.AllCountriesAdapter
import com.excample.testdemo.presentation.viewModels.AllCountryViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Created by Madina Agzamova
 * on 16,January,2023
 **/

@AndroidEntryPoint
class AllCountriesScreen : Fragment(R.layout.screen_all_countries) {
    private val viewBinding: ScreenAllCountriesBinding by viewBinding(
        ScreenAllCountriesBinding::bind
    )
    private val viewModel: AllCountryViewModel by viewModels<AllCountryViewModelImpl>()
    private val adapter: AllCountriesAdapter = AllCountriesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.rv.adapter = adapter
        viewModel.countries.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.ivUpdate.setOnClickListener {

            viewModel.getAllCountries()
        }

        viewModel.loading.onEach {
            if (it.fullScreen) {
                viewBinding.progress.visibility = View.VISIBLE
                viewBinding.loadingView.visibility = View.VISIBLE
                delay(5000)
                viewBinding.progress.hide()
                viewBinding.loadingView.visibility = View.GONE
            } else {
                viewBinding.progress.visibility = View.GONE
                viewBinding.loadingView.visibility = View.GONE
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.hasConnection.onEach {
            if (it) Toast.makeText(requireContext(), "Yes Internet", Toast.LENGTH_SHORT).show()
            else Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.failureFlow.onEach {
            Toast.makeText(requireContext(), "$it - oops", Toast.LENGTH_SHORT).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            findNavController().navigate(
                AllCountriesScreenDirections.actionAllCountriesScreenToCountryInfoScreen(
                    it
                )
            )
        }


    }


}