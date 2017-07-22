package oreilly_free_books.domain.model


data class Book(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val url: String,
    val category: String,
    val subCategory: String,
    val downloadUrls: List<String>
)