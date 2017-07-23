package io.github.erikcaffrey.oreilly_free_books.di

import dagger.Component
import io.github.erikcaffrey.oreilly_free_books.ui.view.BooksFragment
import javax.inject.Singleton


@Component(modules = arrayOf(ApiModule::class, AppModule::class))
@Singleton interface AppComponent {

  fun inject(booksFragment: BooksFragment)

}