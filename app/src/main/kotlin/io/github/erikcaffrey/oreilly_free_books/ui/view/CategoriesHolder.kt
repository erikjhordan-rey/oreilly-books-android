package io.github.erikcaffrey.oreilly_free_books.ui.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.erikcaffrey.oreilly_free_books.domain.model.Book
import kotlinx.android.synthetic.main.category_item.view.*

class CategoriesHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

  fun render(categoryViewModel: CategoryViewModel) {
    renderTitle(categoryViewModel.categoryTitle)
    renderRecycler(categoryViewModel.booksList)
  }

  private fun renderTitle(title: String) {
    itemView.txt_title.text = title
  }

  private fun renderRecycler(books: List<Book>) {
    itemView.recycler.layoutManager =
        LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
    //itemView.recycler.onFlingListener = null
    itemView.recycler.adapter = BooksAdapter(books)
  }

}