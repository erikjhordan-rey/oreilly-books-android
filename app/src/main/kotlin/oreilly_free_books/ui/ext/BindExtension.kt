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

