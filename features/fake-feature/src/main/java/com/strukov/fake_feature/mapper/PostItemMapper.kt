package com.strukov.fake_feature.mapper

import com.strukov.fake_feature.ui.adapter.PostItem
import com.strukov.fake_repo.model.Post
import com.strukov.core.data.Result

fun List<Post>.mapToPostItem(): Result<List<PostItem>> {
    return Result.Success(map { post -> PostItem(post.id, post.title, post.body) })
}