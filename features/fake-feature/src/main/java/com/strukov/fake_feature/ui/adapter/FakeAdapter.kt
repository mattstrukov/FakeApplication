package com.strukov.fake_feature.ui.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.strukov.core.adapter.IAdapterItem
import com.strukov.fake_feature.databinding.ItemFakePostBinding

fun fakeAdapterDelegate() = adapterDelegateViewBinding<PostItem, IAdapterItem, ItemFakePostBinding>(
    { layoutInflater, parent -> ItemFakePostBinding.inflate(layoutInflater, parent, false) }
) {

    bind {
        binding.apply {
            title.text = item.title
            body.text = item.body
        }
    }
}

data class PostItem(
    val id: Int,
    val title: String,
    val body: String,
    override val uniqueTag: String = id.toString()
) : IAdapterItem