package com.example.ashfaq_iroid.network


import com.example.ashfaq_iroid.model.CategoryListResponseModel
import retrofit2.Response
import retrofit2.http.*

@JvmSuppressWildcards
interface ApiService {


    @GET("index.php")
    suspend fun getCategory(
        @Query("route") route: String = "api/common/getCategories"
    ): Response<CategoryListResponseModel>


}