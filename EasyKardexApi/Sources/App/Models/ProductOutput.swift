// MIT License
//
// Copyright (c) 2019 Marco Antonio Estrella Cardenas
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
//  ProductOutput.swift
//  App
//
//  Created by jmarkstar on 6/19/19.
//

import Foundation
import Vapor
import FluentMySQL

struct ProductOutput: Codable {
    
    static let entity = "product_output"
    
    var id: Int?
    var prodInputID: ProductInput.ID
    var quantity: Int
    var creationDate: Date?
    var creatorID: User.ID?
    
    init(id: Int? = nil,
         prodInputID: ProductInput.ID,
         quantity: Int,
         creationDate: Date? = nil,
         creatorID: User.ID? = nil) {
        self.id = id
        self.prodInputID = prodInputID
        self.quantity = quantity
        self.creationDate = creationDate
        self.creatorID = creatorID
    }
    
    enum CodingKeys: String, CodingKey {
        case id = "id_output"
        case prodInputID = "id_product_input"
        case quantity = "quantity"
        case creationDate = "creation_date"
        case creatorID = "creation_user_id"
    }
}

extension ProductOutput {
    
    var productInput: Parent<ProductOutput, ProductInput> {
        return parent(\.prodInputID)
    }
    
    var creator: Parent<ProductOutput, User>? {
        return parent(\.creatorID)
    }
}

extension ProductOutput: MySQLModel {}
