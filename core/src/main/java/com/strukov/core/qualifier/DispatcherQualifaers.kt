package com.strukov.core.qualifier

import javax.inject.Qualifier

@Qualifier
annotation class IODispatcher

@Qualifier
annotation class MainDispatcher

@Qualifier
annotation class DefaultDispatcher

@Qualifier
annotation class UnconfinedDispatcher