package org.example

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.io.InputStreamReader
import java.net.URL
import java.sql.Connection
import java.sql.PreparedStatement

object JsonToDatabase {
    fun addProductsFromJson() {
        println("Agregando productos desde JSON...")

        val jsonUrl = "https://dummyjson.com/products"
        val connection: Connection? = DatabaseConnection.getConnection()

        if (connection != null) {
            try {
                connection.autoCommit = false // Habilitamos commit manual

                val reader = InputStreamReader(URL(jsonUrl).openStream())
                val products: JsonArray = JsonParser.parseReader(reader)
                    .asJsonObject.getAsJsonArray("products")

                val insertQuery = "INSERT INTO productos (nombre, descripcion, cantidad, precio) VALUES (?, ?, ?, ?)"
                val statement: PreparedStatement = connection.prepareStatement(insertQuery)

                for (product: JsonElement in products) {
                    val nombre = product.asJsonObject.get("title").asString
                    val descripcion = product.asJsonObject.get("description").asString
                    val cantidad = product.asJsonObject.get("stock").asInt
                    val precio = product.asJsonObject.get("price").asDouble

                    statement.setString(1, nombre)
                    statement.setString(2, descripcion)
                    statement.setInt(3, cantidad)
                    statement.setDouble(4, precio)
                    statement.executeUpdate()
                }

                connection.commit() // Confirmamos los cambios
                println("Productos agregados exitosamente.")
            } catch (e: Exception) {
                println("Error al agregar productos: ${e.message}")
                e.printStackTrace()
            } finally {
                connection.close()
            }
        } else {
            println("No se pudo conectar a la base de datos.")
        }
    }
}
