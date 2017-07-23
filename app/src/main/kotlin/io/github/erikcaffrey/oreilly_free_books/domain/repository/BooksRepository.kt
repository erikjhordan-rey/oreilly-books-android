/*
 * Copyright (C) 2017 Erik Jhordan Rey.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.erikcaffrey.oreilly_free_books.domain.repository

import io.reactivex.Single
import io.github.erikcaffrey.oreilly_free_books.data.api.ApiBooksDataSource
import io.github.erikcaffrey.oreilly_free_books.domain.model.Book
import javax.inject.Inject


open class BooksRepository @Inject constructor(private val apiBooksDataSource: ApiBooksDataSource) : Repository {

  override fun getBooks(): Single<List<Book>> {
    return apiBooksDataSource.getBooks().map {
      val books = ArrayList<Book>()
      it.mapTo(books) {
        Book.Mapper.from(it)
      }
      books
    }
  }

}