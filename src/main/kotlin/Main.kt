package org.example

fun main() {
    println("Probando conexión a la base de datos...")
    val connection = DatabaseConnection.getConnection()

    if (connection != null) {
        println("Conexión establecida correctamente.")
        connection.close()
    } else {
        println("No se pudo establecer la conexión.")
    }
    JsonToDatabase.addProductsFromJson()

    // Paso 3: Insertar empleados y pedidos
    InsertData.insertEmployeesAndOrders()

    // Paso 4: Ejecutar consultas
    QueryData.executeQueries()

    println("Finalizando el programa.")
}

