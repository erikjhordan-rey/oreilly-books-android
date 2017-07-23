package io.github.erikcaffrey.oreilly_free_books.di

import dagger.Module
import dagger.Provides
import io.github.erikcaffrey.oreilly_free_books.data.api.ApiBooksDataSource
import io.github.erikcaffrey.oreilly_free_books.data.api.BooksService
import io.github.erikcaffrey.oreilly_free_books.domain.repository.BooksRepository
import io.github.erikcaffrey.oreilly_free_books.domain.repository.Repository
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule() {

  @Provides @Singleton fun provideApiBooksDataSource(booksService: BooksService) =
      ApiBooksDataSource(booksService)

  @Provides @Singleton @Named("apiBooksDataSource")
  fun provideBooksRepository(apiBooksDataSource: ApiBooksDataSource): Repository =
      BooksRepository(apiBooksDataSource)
}