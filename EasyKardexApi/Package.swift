// swift-tools-version:4.0
import PackageDescription

let package = Package(
    name: "EasyKardexApi",
    products: [
        .library(name: "EasyKardexApi", targets: ["App"]),
    ],
    dependencies: [
        // 💧 A server-side Swift web framework.
        .package(url: "https://github.com/vapor/vapor.git", from: "3.0.0"),

        .package(url: "https://github.com/vapor/fluent-mysql.git", from: "3.0.0"),
        .package(url: "https://github.com/vapor/database-kit.git", from: "1.0.0"),
        .package(url: "https://github.com/vapor/auth.git", from: "2.0.4"), 
        .package(url: "https://github.com/vapor/crypto.git", from: "3.0.0")
    ],
    targets: [
        .target(name: "App", dependencies: ["Vapor", "FluentMySQL", "DatabaseKit", "Authentication", "Crypto", "Random"]),
        .target(name: "Run", dependencies: ["App"]),
        .testTarget(name: "AppTests", dependencies: ["App"])
    ]
)

