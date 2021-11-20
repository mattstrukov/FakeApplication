package com.strukov.core.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DiffAdapter : AsyncListDifferDelegationAdapter<IAdapterItem> {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<IAdapterItem>() {
            override fun areItemsTheSame(oldItem: IAdapterItem, newItem: IAdapterItem): Boolean =
                oldItem.uniqueTag == newItem.uniqueTag


            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: IAdapterItem, newItem: IAdapterItem): Boolean =
                oldItem == newItem
        }
    }

    constructor() : super(DIFF_CALLBACK)

    constructor(vararg delegates: AdapterDelegate<List<IAdapterItem>>) : super(
        DIFF_CALLBACK,
        *delegates
    )

    constructor(manager: AdapterDelegatesManager<List<IAdapterItem>>) : super(
        DIFF_CALLBACK,
        manager
    )
}