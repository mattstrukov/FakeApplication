package com.strukov.fake_repo.model

import com.strukov.api.data.dto.PostDto

fun List<PostDto>.mapToPost(): List<Post> {
    return map { post -> Post(post.id, post.title, post.body) }
}