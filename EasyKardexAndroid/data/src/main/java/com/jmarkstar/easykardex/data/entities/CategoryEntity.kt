/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Marco Antonio Estrella Cardenas
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Created by jmarkstar on 7/12/19 6:01 PM
 *
 */

package com.jmarkstar.easykardex.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import com.jmarkstar.easykardex.domain.models.ProductProperty
import com.jmarkstar.easykardex.domain.models.ProductPropertyType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDateTime


@Entity(tableName = "product_category",
    indices = [Index("id")],
    primaryKeys = ["id"])


@JsonClass(generateAdapter = true)
data class CategoryEntity(val id: Long? = null,
                          @Json(name = "n") var name: String,
                          @Json(name = "lud") var lastUpdateDate: LocalDateTime? = null,
                          @Json(name = "s") var status: EntityStatus = EntityStatus.ACTIVE) {

    @Ignore var products: List<ProductEntity>? = null
}

fun CategoryEntity(productProperty: ProductProperty): CategoryEntity = CategoryEntity(productProperty.id, productProperty.name)

fun CategoryEntity.mapToDomain(): ProductProperty = ProductProperty(id, ProductPropertyType.CATEGORY, name)

fun List<CategoryEntity>.mapToDomain(): List<ProductProperty> = map { it.mapToDomain() }