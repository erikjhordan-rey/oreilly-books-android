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

package oreilly_free_books.data

import com.google.gson.annotations.SerializedName

data class BookEntity(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val image: String,
    @SerializedName("category") val category: String,
    @SerializedName("subcategory") val subCategory: String,
    @SerializedName("url") val url: String,
    @SerializedName("mobi") val mobi: String,
    @SerializedName("pdf") val pdf: String,
    @SerializedName("epub") val epub: String
)