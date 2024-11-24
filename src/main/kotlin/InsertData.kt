package org.example

import java.sql.Connection

object InsertData {
    fun insertEmployeesAndOrders() {
        println("Insertando empleados y pedidos...")
        val connection: Connection? = DatabaseConnection.getConnection()

        if (connection != null) {
            try {
                val statement = connection.createStatement()

                // Insertar empleados
                statement.executeUpdate(
                    "INSERT INTO Empleados (nombre, apellidos, correo) VALUES " +
                            "('Juan', 'Pérez', 'juan.perez@example.com')," +
                            "('Ana', 'López', 'ana.lopez@example.com')"
                )

                // Insertar pedidos
                statement.executeUpdate(
                    "INSERT INTO Pedidos (id_producto, descripcion, precio_total) VALUES " +
                            "(1, 'Pedido 1', 150.00)," +
                            "(2, 'Pedido 2', 300.00)"
                )

                println("Empleados y pedidos insertados correctamente.")
            } catch (e: Exception) {
                println("Error al insertar empleados y pedidos: ${e.message}")
                e.printStackTrace()
            } finally {
                connection.close()
            }
        } else {
            println("No se pudo conectar a la base de datos.")
        }
    }
}