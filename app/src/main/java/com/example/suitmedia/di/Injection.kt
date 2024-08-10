package com.example.suitmedia.di

import com.example.suitmedia.data.UserRepository
import com.example.suitmedia.data.retrofit.ApiConfig

object Injection {
    fun provideUserRepository(): UserRepository {
        val apiService= ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }

}