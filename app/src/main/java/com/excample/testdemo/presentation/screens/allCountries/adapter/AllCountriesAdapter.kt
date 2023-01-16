package com.excample.testdemo.presentation.screens.allCountries.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.excample.testdemo.R
import com.excample.testdemo.data.source.remote.dto.response.GetAllResponse
import com.excample.testdemo.data.source.remote.dto.response.GetAllResponseItem
import com.excample.testdemo.databinding.ItemCountryBinding
import com.mocklets.pluto.core.extensions.inflate
import com.squareup.picasso.Picasso

/**
 * Created by Madina Agzamova
 * on 16,January,2023
 **/

class AllCountriesAdapter :
    ListAdapter<GetAllResponseItem, AllCountriesAdapter.ViewHolder>(CallBack) {
    private var itemClickListener: ((GetAllResponseItem) -> Unit)? = null

    fun setItemClickListener(block: (GetAllResponseItem) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val viewBinding: ItemCountryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            val data = getItem(absoluteAdapterPosition)

            viewBinding.apply {
                Picasso.get()
                    .load(data.flags?.png)
                    .placeholder(R.drawable.placeholder)
                    .into(ivFlag)
                tvCountryName.text = data.name
            }
        }
    }

    object CallBack : DiffUtil.ItemCallback<GetAllResponseItem>() {
        override fun areItemsTheSame(
            oldItem: GetAllResponseItem,
            newItem: GetAllResponseItem
        ): Boolean {
            return oldItem.nativeName == newItem.nativeName
        }

        override fun areContentsTheSame(
            oldItem: GetAllResponseItem,
            newItem: GetAllResponseItem
        ): Boolean {
            return oldItem.nativeName == newItem.nativeName &&
                    oldItem.region == newItem.region &&
                    oldItem.capital == newItem.capital &&
                    oldItem.independent == newItem.independent &&
                    oldItem.timezones == newItem.timezones &&
                    oldItem.currencies == newItem.currencies &&
                    oldItem.callingCodes == newItem.callingCodes
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            ItemCountryBinding.bind(parent.inflate(R.layout.item_country))
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind()


}