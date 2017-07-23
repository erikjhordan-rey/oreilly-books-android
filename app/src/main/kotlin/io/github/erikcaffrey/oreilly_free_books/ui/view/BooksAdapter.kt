package io.github.erikcaffrey.oreilly_free_books.ui.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.erikcaffrey.oreilly_free_books.R
import io.github.erikcaffrey.oreilly_free_books.domain.model.Book
import io.github.erikcaffrey.oreilly_free_books.ui.ext.inflate


class BooksAdapter constructor(private val bookList: List<Book>) : RecyclerView.Adapter<BooksHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BooksHolder {
    val itemView = parent?.inflate(R.layout.book_item)
    return BooksHolder(itemView)
  }

  override fun onBindViewHolder(holder: BooksHolder?, position: Int) {
    val book = bookList[position]
    holder?.render(book)
  }

  override fun getItemCount() = bookList.size

}