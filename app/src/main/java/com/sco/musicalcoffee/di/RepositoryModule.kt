package com.sco.musicalcoffee.di

import com.sco.musicalcoffee.CoffeeRepository
import com.sco.musicalcoffee.ICoffeeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(repository: CoffeeRepository): ICoffeeRepository
}