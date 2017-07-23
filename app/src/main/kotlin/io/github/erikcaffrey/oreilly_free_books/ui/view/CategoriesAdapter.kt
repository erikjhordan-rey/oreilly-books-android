package io.github.erikcaffrey.oreilly_free_books.ui.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.erikcaffrey.oreilly_free_books.R
import io.github.erikcaffrey.oreilly_free_books.ui.ext.inflate


class CategoriesAdapter : RecyclerView.Adapter<CategoriesHolder>() {

  private var categoryList: List<CategoryViewModel>

  init {
    categoryList = ArrayList<CategoryViewModel>()
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoriesHolder {
    val itemView = parent?.inflate(R.layout.category_item)
    return CategoriesHolder(itemView)
  }

  override fun onBindViewHolder(holder: CategoriesHolder?, position: Int) {
    val categoryViewModel = categoryList[position]
    holder?.render(categoryViewModel)
  }

  override fun getItemCount() = categoryList.size

  fun addCategoryList(categoryList: List<CategoryViewModel>) {
    this.categoryList = categoryList
  }

}