package com.strukov.fake_repo.di

import com.strukov.fake_repo.FakeRepository
import com.strukov.fake_repo.FakeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideFakeRepository(impl: FakeRepositoryImpl): FakeRepository
}
