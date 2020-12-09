package com.example.ashfaq_iroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.ashfaq_iroid.model.CategoryListResponseModel
import com.example.ashfaq_iroid.model.ResultData
import com.example.ashfaq_iroid.network.APIClient
import com.example.ashfaq_iroid.network.ApiService


class CategoryViewModel :
    ViewModel() {

     fun categories(): LiveData<ResultData<CategoryListResponseModel>> {
         return liveData {
             emit(ResultData.Loading())
             emit(fetchCategory())
         }
     }

    private suspend fun fetchCategory() : ResultData<CategoryListResponseModel> {

        val data = APIClient().client.create(ApiService::class.java).getCategory()

        return if (data.isSuccessful) {
            if (data.body()!!.status == "success") {
                ResultData.Success(data.body()!!)

            } else {
                ResultData.Failed("")
            }
        } else {
            ResultData.Exception(data.errorBody().toString())
        }
    }
}