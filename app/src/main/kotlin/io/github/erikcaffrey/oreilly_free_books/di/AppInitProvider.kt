package io.github.erikcaffrey.oreilly_free_books.di

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class AppInitProvider : ContentProvider() {

  companion object {
//    lateinit var appComponent: AppComponent
  }

  override fun onCreate(): Boolean {
    //initDagger()
    return false
  }

  fun initDagger() {
     //appComponent = DaggerAppComponent.create()
  }

  override fun insert(uri: Uri?, values: ContentValues?) = null!!

  override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?,
      sortOrder: String?): Cursor = null!!

  override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int = null!!

  override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int = null!!

  override fun getType(uri: Uri?): String = null!!
}