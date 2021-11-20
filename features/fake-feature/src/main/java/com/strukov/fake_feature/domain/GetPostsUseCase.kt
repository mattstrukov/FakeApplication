package com.strukov.fake_feature.domain

import com.strukov.core.data.Result
import com.strukov.core.usecase.UseCase
import com.strukov.fake_repo.FakeRepository
import com.strukov.fake_repo.model.Post
import com.strukov.fake_repo.model.mapToPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repo: FakeRepository
) : UseCase<List<Post>> {

    override val value: Flow<Result<List<Post>>> = flow {
        emit(repo.getPosts())
    }
        .map { it.mapToPost() }
        .map { if (it.isEmpty()) Result.Empty else Result.Success(it) }
        .catch { emit(Result.Error(it)) }
}