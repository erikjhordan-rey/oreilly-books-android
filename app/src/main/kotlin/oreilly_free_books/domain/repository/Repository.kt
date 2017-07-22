package oreilly_free_books.domain.repository

import io.reactivex.Single
import oreilly_free_books.domain.model.Book


interface Repository {

  fun getBooks(): Single<List<Book>>
}