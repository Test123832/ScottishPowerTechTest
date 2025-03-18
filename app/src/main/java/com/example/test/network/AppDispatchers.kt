package com.example.test.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val AppDispatchers: AppDispatchers)

enum class AppDispatchers {
  IO,
}
