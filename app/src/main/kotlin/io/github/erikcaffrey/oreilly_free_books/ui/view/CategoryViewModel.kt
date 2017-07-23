package io.github.erikcaffrey.oreilly_free_books.ui.view

import io.github.erikcaffrey.oreilly_free_books.domain.model.Book


data class CategoryViewModel(
    val categoryTitle: String,
    val booksList: List<Book>
) {

  object Mapper {

    fun from(bookList: List<Book>): List<CategoryViewModel> {
      val categories = ArrayList<CategoryViewModel>()
      val category = CategoryViewModel("Demo 1", bookList)
      categories.add(category)
      return categories
    }
  }

}