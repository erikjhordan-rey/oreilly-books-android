package oreilly_free_books.domain.usecase

import io.reactivex.Single
import oreilly_free_books.domain.model.Book
import oreilly_free_books.domain.repository.Repository
import javax.inject.Inject


open class GetBooks @Inject constructor(val repository: Repository) {

  fun execute(): Single<List<Book>> {
    return repository.getBooks()
  }

}