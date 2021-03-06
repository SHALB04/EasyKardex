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


@Entity(tableName = "product_unit",
    indices = [Index("id")],
    primaryKeys = ["id"])


@JsonClass(generateAdapter = true)
data class UnitEntity(val id: Long? = null,
                      @Json(name = "n") var name: String,
                      @Json(name = "lud") var lastUpdateDate: LocalDateTime? = null,
                      @Json(name = "s") var status: EntityStatus = EntityStatus.ACTIVE) {

    @Ignore // Room will ignore this variable
    @Transient // Moshi will ignore this variable
    var products: List<ProductEntity>? = null
}

fun UnitEntity(productProperty: ProductProperty): UnitEntity = UnitEntity(productProperty.id, productProperty.name)

fun UnitEntity.mapToDomain(): ProductProperty = ProductProperty(id, ProductPropertyType.UNIT, name)

fun List<UnitEntity>.mapToDomain(): List<ProductProperty> = map { it.mapToDomain() }