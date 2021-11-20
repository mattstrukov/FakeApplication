package com.strukov.fake_feature.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strukov.core.data.Result
import com.strukov.fake_feature.domain.GetPostsUseCase
import com.strukov.fake_feature.mapper.mapToPostItem
import com.strukov.fake_feature.ui.adapter.PostItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FakeViewModel @Inject internal constructor(
    private val postsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _posts = MutableStateFlow<Result<List<PostItem>>>(Result.Loading)
    internal val posts: StateFlow<Result<List<PostItem>>> = _posts

    init {
        viewModelScope.launch {
            postsUseCase.value
                .map { if (it is Result.Success) it.data.mapToPostItem() else it }
                .collect {
                    _posts.value = it as Result<List<PostItem>>
                }
        }
    }
}