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

package io.github.erikcaffrey.oreilly_free_books.data

import com.google.gson.annotations.SerializedName

data class BookEntity(
    @SerializedName(TITLE) val title: String,
    @SerializedName(DESCRIPTION) val description: String,
    @SerializedName(THUMBNAIL) val image: String,
    @SerializedName(CATEGORY) val category: String?,
    @SerializedName(SUBCATEGORY) val subCategory: String?,
    @SerializedName(URL) val url: String,
    @SerializedName(MOBI) val mobi: String,
    @SerializedName(PDF) val pdf: String,
    @SerializedName(EPUB) val epub: String
) {
  companion object {
    private const val TITLE = "title"
    private const val DESCRIPTION = "description"
    private const val THUMBNAIL = "thumbnail"
    private const val CATEGORY = "categoryTitle"
    private const val SUBCATEGORY = "subcategory"
    private const val URL = "url"
    private const val MOBI = "mobi"
    private const val PDF = "pdf"
    private const val EPUB = "epub"
  }
}