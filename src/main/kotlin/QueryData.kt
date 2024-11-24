package org.example

import java.sql.Connection

object QueryData {
    fun executeQueries() {
        println("Ejecutando consultas...")
        val connection: Connection? = DatabaseConnection.getConnection()

        if (connection != null) {
            try {
                queryAllEmployees(connection)
                queryAllProducts(connection)
                queryAllOrders(connection)
                queryProductsUnder600(connection)
                queryFavoriteProducts(connection)
            } catch (e: Exception) {
                println("Error al ejecutar consultas: ${e.message}")
                e.printStackTrace()
            } finally {
                connection.close()
            }
        } else {
            println("No se pudo conectar a la base de datos.")
        }
    }

    private fun queryAllEmployees(connection: Connection) {
        println("\n--- Empleados ---")
        val resultSet = connection.createStatement().executeQuery("SELECT * FROM Empleados")
        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val nombre = resultSet.getString("nombre")
            val apellidos = resultSet.getString("apellidos")
            val correo = resultSet.getString("correo")
            println("ID: $id, Nombre: $nombre $apellidos, Correo: $correo")
        }
    }

    private fun queryAllProducts(connection: Connection) {
        println("\n--- Productos ---")
        val resultSet = connection.createStatement().executeQuery("SELECT * FROM Productos")
        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val nombre = resultSet.getString("nombre")
            val descripcion = resultSet.getString("descripcion")
            val cantidad = resultSet.getInt("cantidad")
            val precio = resultSet.getDouble("precio")
            println("ID: $id, Nombre: $nombre, Descripción: $descripcion, Cantidad: $cantidad, Precio: $precio€")
        }
    }

    private fun queryAllOrders(connection: Connection) {
        println("\n--- Pedidos ---")
        val resultSet = connection.createStatement().executeQuery("SELECT * FROM Pedidos")
        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val idProducto = resultSet.getInt("id_producto")
            val descripcion = resultSet.getString("descripcion")
            val precioTotal = resultSet.getDouble("precio_total")
            println("ID: $id, Producto ID: $idProducto, Descripción: $descripcion, Precio Total: $precioTotal€")
        }
    }

    private fun queryProductsUnder600(connection: Connection) {
        println("\n--- Productos con precio menor a 600€ ---")
        val resultSet = connection.createStatement().executeQuery("SELECT * FROM Productos WHERE precio < 600")
        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val nombre = resultSet.getString("nombre")
            val precio = resultSet.getDouble("precio")
            println("ID: $id, Nombre: $nombre, Precio: $precio€")
        }
    }

    private fun queryFavoriteProducts(connection: Connection) {
        println("\n--- Productos favoritos (precio > 1000€) ---")
        val resultSet = connection.createStatement().executeQuery("SELECT * FROM Productos WHERE precio > 1000")
        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val nombre = resultSet.getString("nombre")
            val precio = resultSet.getDouble("precio")
            println("ID: $id, Nombre: $nombre, Precio: $precio€")
        }
    }
}