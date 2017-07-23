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

package io.github.erikcaffrey.oreilly_free_books.ui.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import erikjhordanrey.base_components.view.BaseFragment
import io.github.erikcaffrey.oreilly_free_books.R
import io.github.erikcaffrey.oreilly_free_books.di.AppInitProvider
import io.github.erikcaffrey.oreilly_free_books.ui.BooksPresenter
import io.github.erikcaffrey.oreilly_free_books.ui.BooksUi
import kotlinx.android.synthetic.main.books_fragment.*

import javax.inject.Inject

open class BooksFragment : BaseFragment(), BooksUi {

  companion object {
    fun newInstance() = BooksFragment()
  }

  override fun getLayoutResId() = R.layout.books_fragment

  @Inject lateinit var presenter: BooksPresenter

  lateinit var adapter: CategoriesAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initDagger()
  }

  override fun initPresenter() {
    super.initPresenter()
    presenter.ui = this
    presenter.start()
  }

  override fun initFragment(view: View) {
    super.initFragment(view)
    initToolbar()
    initAdapter()
    initRecycler()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    presenter.terminate()
  }

  override fun showBooks(categoryViewModelList: List<CategoryViewModel>) {
    adapter.addCategoryList(categoryViewModelList)
    adapter.notifyDataSetChanged()
  }

  override fun showEmptyMessage() {
    Log.d(BooksFragment::class.simpleName, "Emty Message")
  }

  override fun showErrorMessage() {
    Log.d(BooksFragment::class.simpleName, "An Error Occurred")
  }

  override fun showLoading() {
    Log.d(BooksFragment::class.simpleName, "Show Loading")
  }

  override fun hideLoading() {
    Log.d(BooksFragment::class.simpleName, "Hide Loading")
  }

  fun initDagger() {
    AppInitProvider.appComponent.inject(this)
  }

  fun initToolbar() {
    setSupportActionBarOnFragment(toolbar)
    supportActionBar?.title = getString(R.string.books_title)
  }

  fun initAdapter() {
    adapter = CategoriesAdapter()
  }

  fun initRecycler() {
    list_books.layoutManager = LinearLayoutManager(activity)
    list_books.adapter = adapter
  }

}
