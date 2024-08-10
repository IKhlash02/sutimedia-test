package com.example.suitmedia.ui.screen.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmedia.data.UserRepository
import com.example.suitmedia.data.response.DataItem
import kotlinx.coroutines.flow.Flow

class ThirdViewModel(userRepository: UserRepository) : ViewModel() {


    val userData: Flow<PagingData<DataItem>> =
        userRepository.getAllUsers().cachedIn(viewModelScope)


}