package oreilly_free_books.ui.ext

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View

@Suppress("UNCHECKED_CAST")
fun <T : View> Activity.bind(@IdRes idRes: Int): Lazy<T> {
  return noneLazy { findViewById(idRes) as T }
}

@Suppress("UNCHECKED_CAST")
fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
  return noneLazy { findViewById(idRes) as T }
}

private fun <T> noneLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

