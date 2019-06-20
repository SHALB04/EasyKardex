//
// Created by jmarkstar on 19/06/19.
//

import Foundation
import Vapor
import FluentMySQL

final class ProductCategory: Codable {

    static let entity = "product_category"

    var id: Int?
    var name: String

    init(id: Int? = nil, name: String) {
        self.id = id
        self.name = name
    }

    enum CodingKeys: String, CodingKey {
        case id = "id_category"
        case name = "name"
    }
}

extension ProductCategory {
    
    var products: Children<ProductCategory, Product> {
        return children(\.categoryID)
    }
}

extension ProductCategory: MySQLModel {}

extension ProductCategory: Content {}

extension ProductCategory: Parameter {}