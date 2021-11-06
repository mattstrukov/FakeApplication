package com.strukov.core.usecase

import com.strukov.core.data.Result
import kotlinx.coroutines.flow.Flow

interface UseCase<T> {
    val value: Flow<Result<T>>
}