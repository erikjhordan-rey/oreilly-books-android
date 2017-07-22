package oreilly_free_books.ui

import erikjhordanrey.base_components.view.BasePresenterLoader
import oreilly_free_books.domain.model.Book


interface BooksView : BasePresenterLoader.Ui {

  fun showBooks(books: List<Book>) {

  }

  fun showEmptyMessage() {

  }

  fun showErrorMessage() {

  }

  override fun showLoading() {

  }

  override fun hideLoading() {

  }
}