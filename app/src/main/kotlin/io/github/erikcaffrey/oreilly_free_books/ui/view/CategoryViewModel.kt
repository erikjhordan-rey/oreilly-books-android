package io.github.erikcaffrey.oreilly_free_books.ui.view

import io.github.erikcaffrey.oreilly_free_books.domain.model.Book


data class CategoryViewModel(
    val categoryTitle: String,
    val booksList: List<Book>
) {

  object Mapper {

    fun from(bookList: List<Book>): List<CategoryViewModel> {

      val categoriesMap = getCategoriesMap(bookList)

      val booksMap = HashMap<String, List<Book>>()
      val subBooksMap = HashMap<String, List<Book>>()

      for ((key, value) in categoriesMap) {

        val books = ArrayList<Book>()

        // booksMap.put(key, books)

        bookList.forEach {

          if (it.category == key) {

            if (value.isNotEmpty()) {

              for (subCategory in value) {

                val subBooks = ArrayList<Book>()

                bookList.forEach {
                  if (it.subCategory == subCategory) {
                    subBooks.add(it)
                  }
                }

                booksMap.put(subCategory, subBooks)
              }
              //   books.add(it)

            } else {
              books.add(it)
            }
          }
        }

        booksMap.put(key, books)

      }

      val categoryViewModelList = ArrayList<CategoryViewModel>()

      for (j in booksMap.size - 1 downTo 0) {

        val key = booksMap.toMutableMap().keys.toTypedArray()[j]
        val value = booksMap.toMutableMap().values.toTypedArray()[j]
        val categoryViewModel = CategoryViewModel(key, value)

        categoryViewModelList.add(categoryViewModel)
      }

      return categoryViewModelList
    }

    fun getCategoriesMap(bookList: List<Book>): HashMap<String, List<String>> {
      val categories = getCategories(bookList)
      val categoriesMap = HashMap<String, List<String>>()
      for (category in categories) {
        val subCategories = ArrayList<String>()
        for (book in bookList) {
          if (category == book.category && !book.subCategory.isNullOrEmpty() && !subCategories.contains(book.subCategory!!)) {
            subCategories.add(book.subCategory)
          }
        }
        categoriesMap.put(category, subCategories.sorted())
      }
      return categoriesMap
    }

    fun getCategories(bookList: List<Book>): List<String> {
      val categories = ArrayList<String>()
      bookList.forEach {
        if (!it.category.isNullOrEmpty() && !categories.contains(it.category!!)) {
          categories.add(it.category)
        }
      }
      return categories
    }

  }
}