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

package oreilly_free_books.ui

import io.reactivex.Single
import oreilly_free_books.domain.model.Book
import oreilly_free_books.domain.repository.Repository
import oreilly_free_books.domain.usecase.GetBooks
import oreilly_free_books.utils.RxAndroidRule
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

    private val ANY_TITLE = "_title"
    private val ANY_DESCRIPTION = "_description"
    private val ANY_IMAGE = "_image"
    private val ANY_URL = "_url"
    private val ANY_CATEGORY = "_category"
    private val ANY_SUBCATEGORY = "_subCategory"

    @ClassRule @JvmField
    val blockingRxAndroidTestRule = RxAndroidRule()
  }

  @Rule @JvmField
  var mockitoRule = MockitoJUnit.rule()

  @Mock lateinit var ui: BooksView
  @Mock lateinit var repository: Repository
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

    presenter.start()

    verify(ui).showLoading()
    verify(ui).hideLoading()
    verify(ui).showBooks(bookList)
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
      Book(it.toString(), ANY_TITLE,
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