package com.example.home_test_travel.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home_test_travel.R
import com.example.home_test_travel.common.vo.Resource
import com.example.home_test_travel.data.attractions.AttractionsData
import com.example.home_test_travel.data.attractions.ImagesData
import com.example.home_test_travel.data.category.Category
import com.example.home_test_travel.data.network.API
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: API,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = context.getString(R.string.attraction_home_title)
    }
    val text: LiveData<String> = _text

    private val _attractionListLiveData = MutableLiveData<List<AttractionsData>?>()
    val attractionListLiveData: LiveData<List<AttractionsData>?> = _attractionListLiveData

    private val _categoryListLiveData = MutableLiveData<List<Category>?>()
    val categoryListLiveData: LiveData<List<Category>?> = _categoryListLiveData


    init {
        getAllAttractions("en")
        getAllCategory()
    }

     fun getAllAttractions(lang: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //TODO: Remove hard code language value
                //TODO: Add pagination for this
                val response = api.getAllAttractions(lang,1)
                _attractionListLiveData.postValue(response.data)
            }
            catch (e: Exception){
                Timber.d("attraction list e ${e}")
            }

        }
    }
    private fun getAllCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //TODO: Remove hard code language value
                //TODO: Add pagination for this
                var sampleTestData : MutableList<Category>? = mutableListOf()
                sampleTestData?.add(Category(imageUrl = R.drawable.ic_category_beach, categoryTitle = "Beach"))
                sampleTestData?.add(Category(imageUrl = R.drawable.ic_beach, categoryTitle = "Natural"))
                sampleTestData?.add(Category(imageUrl = R.drawable.ic_mountain, categoryTitle = "Mountain"))
                sampleTestData?.add(Category(imageUrl = R.drawable.ic_forest, categoryTitle = "Forest"))

                _categoryListLiveData.postValue(sampleTestData)
            }
            catch (e: Exception){
                Timber.d("attraction list e ${e}")
            }

        }
    }
}