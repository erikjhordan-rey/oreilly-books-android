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

package oreilly_free_books

import erikjhordanrey.base_components.view.BaseFragment
import io.github.erikcaffrey.oreilly_free_books.R

class BooksFragment : BaseFragment() {

  companion object {
    fun newInstance() = BooksFragment()
  }

  override fun getLayoutResId() = R.layout.books_fragment

}
