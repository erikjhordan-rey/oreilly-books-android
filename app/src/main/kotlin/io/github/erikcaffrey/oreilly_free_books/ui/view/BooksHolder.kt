package io.github.erikcaffrey.oreilly_free_books.ui.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import io.github.erikcaffrey.oreilly_free_books.domain.model.Book
import kotlinx.android.synthetic.main.book_item.view.*

class BooksHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

  fun render(book: Book) {
    renderPicture(book.image)
    renderTitle(book.title)

    itemView.setOnClickListener {

    }

  }

  private fun renderPicture(imageUrl: String) {
    Picasso.with(itemView.context).load(imageUrl).into(itemView.image_view_picture)
  }

  private fun renderTitle(title: String) {
    itemView.txt_title.text = title
  }

}