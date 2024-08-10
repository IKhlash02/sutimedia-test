package com.example.suitmedia.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.suitmedia.data.response.DataItem
import com.example.suitmedia.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val apiService: ApiService,
) {
    fun getAllUsers(): Flow<PagingData<DataItem>>  {
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}