package com.excample.testdemo.presentation.screens.countryInfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.excample.testdemo.R
import com.excample.testdemo.databinding.ScreenAllCountriesBinding
import com.excample.testdemo.databinding.ScreenCountryInfoBinding
import com.excample.testdemo.presentation.screens.allCountries.AllCountryViewModel
import com.excample.testdemo.presentation.screens.allCountries.adapter.AllCountriesAdapter
import com.excample.testdemo.presentation.viewModels.AllCountryViewModelImpl
import com.excample.testdemo.presentation.viewModels.CountryInfoViewModelImpl
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

/**
 * Created by Madina Agzamova
 * on 16,January,2023
 **/

@AndroidEntryPoint
class CountryInfoScreen : Fragment(R.layout.screen_country_info) {
    private val viewBinding: ScreenCountryInfoBinding by viewBinding(
        ScreenCountryInfoBinding::bind
    )
    private val viewModel: CountryInfoViewModel by viewModels<CountryInfoViewModelImpl>()
    private val args: CountryInfoScreenArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.apply {
            Picasso.get()
                .load(args.data.flags?.png)
                .placeholder(R.drawable.placeholder)
                .into(ivFlag)
            tvCountryName.text =
                getString(R.string.country_name) + args.data.name
            tvCapitalCity.text =
                getString(R.string.capital_city) + args.data.capital
            tvRegion.text = getString(R.string.region) + args.data.region
            tvCurrency.text = args.data.currencies?.first().toString()
            tvTimezone.text =
                getString(R.string.timezone) + args.data.timezones?.first()

            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }
}