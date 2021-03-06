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

package io.github.erikcaffrey.oreilly_free_books.ui

import io.reactivex.Single
import io.github.erikcaffrey.oreilly_free_books.domain.model.Book
import io.github.erikcaffrey.oreilly_free_books.domain.repository.BooksRepository
import io.github.erikcaffrey.oreilly_free_books.domain.usecase.GetBooks
import io.github.erikcaffrey.oreilly_free_books.RxAndroidRule
import io.github.erikcaffrey.oreilly_free_books.ui.view.CategoryViewModel
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit

class BooksPresenterTest {

  companion object {

    private const val ANY_TITLE = "_title"
    private const val ANY_DESCRIPTION = "_description"
    private const val ANY_IMAGE = "_image"
    private const val ANY_URL = "_url"
    private const val ANY_CATEGORY = "_category"
    private const val ANY_SUBCATEGORY = "_subCategory"

    @ClassRule @JvmField
    val blockingRxAndroidTestRule = RxAndroidRule()
  }

  @Rule @JvmField
  var mockitoRule = MockitoJUnit.rule()

  @Mock lateinit var ui: BooksUi
  @Mock lateinit var repository: BooksRepository
  @Mock lateinit var getBooks: GetBooks

  private lateinit var presenter: BooksPresenter

  @Before
  fun setUp() {
    getBooks = GetBooks(repository)
    presenter = BooksPresenter(getBooks)
    presenter.ui = ui
  }

  @Test
  fun shouldShowTheBookList() {
    val bookList = givenABookListWithData()
    val categoryViewModel = CategoryViewModel.Mapper.from(bookList)

    presenter.start()

    verify(ui).showLoading()
    verify(ui).hideLoading()
    verify(ui).showBooks(categoryViewModel)
  }

  @Test
  fun shouldShowEmptyMessageIfTheBookListIsEmpty() {
    givenAnEmptyBookList()

    presenter.start()

    verify(ui).showLoading()
    verify(ui).hideLoading()
    verify(ui).showEmptyMessage()
  }

  @Test
  fun shouldShowErrorMessageIfGetBookReturnsAnSingleError() {
    givenAnErrorBookList()

    presenter.start()

    verify(ui).showLoading()
    verify(ui).hideLoading()
    verify(ui).showErrorMessage()

  }

  private fun givenABookListWithData(): List<Book> {
    val bookList = ArrayList<Book>()
    (1..10).mapTo(bookList) {
      Book(ANY_TITLE,
          ANY_DESCRIPTION, ANY_IMAGE,
          ANY_URL, ANY_CATEGORY,
          ANY_SUBCATEGORY, emptyList())
    }
    given(getBooks.execute()).willReturn(Single.just(bookList))
    return bookList
  }

  private fun givenAnEmptyBookList() =
      given(getBooks.execute()).willReturn(Single.just(emptyList<Book>()))

  private fun givenAnErrorBookList() {
    given(getBooks.execute()).willReturn(Single.error(Throwable("An error occurred")))
  }

}