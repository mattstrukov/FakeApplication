package com.strukov.fake_repo

import com.strukov.api.api.FakeApi
import com.strukov.api.data.dto.PostDto
import com.strukov.core.qualifier.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FakeRepository {
    suspend fun getPosts(): List<PostDto>
}

class FakeRepositoryImpl @Inject constructor(
    private val api: FakeApi,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : FakeRepository {

    override suspend fun getPosts(): List<PostDto> {
        return withContext(ioDispatcher) {

            val response = api.getPosts()

            if (!response.isSuccessful) {
                response.body() ?: listOf()
            } else {
                throw Throwable(response.message())
            }
        }
    }
}