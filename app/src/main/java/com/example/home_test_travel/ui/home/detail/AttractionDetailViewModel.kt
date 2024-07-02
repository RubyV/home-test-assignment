package com.example.home_test_travel.ui.home.detail

import androidx.lifecycle.ViewModel
import com.example.home_test_travel.data.network.API
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AttractionDetailViewModel @Inject constructor(
    private val api: API,
)  : ViewModel() {
    // TODO: Implement the ViewModel
}