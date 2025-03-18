package com.example.test.data.di

import com.example.test.data.repository.DetailsRepository
import com.example.test.data.repository.DetailsRepositoryImpl
import com.example.test.data.repository.HomeRepository
import com.example.test.data.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

  @Binds
  fun bindsMainRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

  @Binds
  fun bindsDetailRepository(detailsRepositoryImpl: DetailsRepositoryImpl): DetailsRepository
}
