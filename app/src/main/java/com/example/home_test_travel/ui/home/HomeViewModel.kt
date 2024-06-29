package com.example.home_test_travel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_test_travel.data.network.API
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: API,
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text



    fun callTestApi() {
        viewModelScope.launch {
            try {
                val response = api.getAllAttractions("en",1)
                Timber.d("attraction list ${response}")
            }
            catch (e: Exception){
                Timber.d("attraction list e ${e}")
            }

        }
    }
}