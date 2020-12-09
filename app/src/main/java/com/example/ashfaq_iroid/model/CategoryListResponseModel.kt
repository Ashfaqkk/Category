package com.example.ashfaq_iroid.model

data class CategoryListResponseModel(
    var categories: ArrayList<Category?> = arrayListOf(),
    var status: String? = ""
) {
    data class Category(
        var category_id: String? = "",
        var image: String? = "",
        var name: String? = "",
        var parent_id: String? = "",
        var subcategory: List<Any?>? = listOf(),
        var top: String? = ""
    )
}