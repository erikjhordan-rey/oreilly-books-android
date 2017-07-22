package oreilly_free_books.ui

import erikjhordanrey.base_components.view.BasePresenterLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import oreilly_free_books.domain.usecase.GetBooks
import javax.inject.Inject


class BooksPresenter @Inject constructor(val getBooks: GetBooks) : BasePresenterLoader<BooksView>() {

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
            else -> ui.showBooks(it)
          }
        }, { ui.hideLoading() ; ui.showErrorMessage() })

    compositeDisposable.add(disposable)

  }

  override fun terminate() {
    super.terminate()
    compositeDisposable.clear()
  }

}







