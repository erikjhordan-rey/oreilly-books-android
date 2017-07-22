package oreilly_free_books.data

import com.google.gson.annotations.SerializedName

data class BookEntity(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val image: String,
    @SerializedName("category") val category: String,
    @SerializedName("subcategory") val subCategory: String,
    @SerializedName("url") val url: String,
    @SerializedName("mobi") val mobi: String,
    @SerializedName("pdf") val pdf: String,
    @SerializedName("epub") val epub: String
)