package io.github.erikcaffrey.oreilly_free_books

import android.app.Application
import io.github.erikcaffrey.oreilly_free_books.di.ApiModule
import io.github.erikcaffrey.oreilly_free_books.di.AppComponent
import io.github.erikcaffrey.oreilly_free_books.di.AppModule
import io.github.erikcaffrey.oreilly_free_books.di.DaggerAppComponent


class BooksApplication : Application() {

  companion object {
    lateinit var appComponent: AppComponent
  }

  override fun onCreate() {
    initDagger()
  }

  fun initDagger() {
    appComponent = DaggerAppComponent.builder()
        .appModule(AppModule()).apiModule(ApiModule()).build()
  }
}