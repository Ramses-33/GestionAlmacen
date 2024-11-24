package org.example

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseConnection {
    private const val URL = "jdbc:mysql://localhost:3306/almacen" // Cambia "almacen" por el nombre de tu base de datos
    private const val USER = "root" // Cambia "root" por tu usuario de MySQL
    private const val PASSWORD = "ramses" // Cambia "tu_password" por tu contraseña

    // Método para obtener la conexión a la base de datos
    fun getConnection(): Connection? {
        return try {
            DriverManager.getConnection(URL, USER, PASSWORD).also {
                println("Conexión exitosa a la base de datos.")
            }
        } catch (e: SQLException) {
            println("Error al conectar con la base de datos: ${e.message}")
            null
        }
    }
}