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

import erikjhordanrey.base_components.view.BasePresenterLoader
import io.github.erikcaffrey.oreilly_free_books.domain.usecase.GetBooks
import io.github.erikcaffrey.oreilly_free_books.ui.view.CategoryViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class BooksPresenter @Inject constructor(private val getBooks: GetBooks) : BasePresenterLoader<BooksUi>() {

  private val compositeDisposable = CompositeDisposable()

  override fun start() {
    super.start()

    ui.showLoading()

    val disposable = getBooks.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ it ->
          ui.hideLoading()
          when (it.isEmpty()) {
            true -> ui.showEmptyMessage()
            else -> {
              val categoryViewModelList = CategoryViewModel.Mapper.from(it)
              ui.showBooks(categoryViewModelList)
            }
          }
        }, { ui.hideLoading(); ui.showErrorMessage() })

    compositeDisposable.add(disposable)

  }

  override fun terminate() {
    super.terminate()
    compositeDisposable.clear()
  }

}







