
package proyecto_ferre_eli_serquen;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;


public class crudProductos {

public void insertarProducto(String nombre, int idCategoria, int idProveedor, float precioCompra, float precioVenta, int stock) {
    String query = "INSERT INTO PRODUCTOS (nombre_producto, id_categoria, id_proveedor, precio_compra, precio_venta, stock) VALUES (?, ?, ?, ?, ?, ?)";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, nombre); 
        ps.setInt(2, idCategoria); 
        ps.setInt(3, idProveedor); 
        ps.setFloat(4, precioCompra); 
        ps.setFloat(5, precioVenta); 
        ps.setInt(6, stock);

        ps.executeUpdate();
        System.out.println("Producto insertado con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL INSERTAR PRODUCTO.");
        ex.printStackTrace();
    }
}

public void leerProductos() {
    String query = "SELECT * FROM PRODUCTOS";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id_producto");
            String nombre = rs.getString("nombre_producto");
            int idCategoria = rs.getInt("id_categoria");
            int idProveedor = rs.getInt("id_proveedor");
            float precioCompra = rs.getFloat("precio_compra");
            float precioVenta = rs.getFloat("precio_venta");
            int stock = rs.getInt("stock");

            System.out.printf("ID: %d | Nombre: %-20s | Categoría: %d | Proveedor: %d | Precio Compra: %.2f | Precio Venta: %.2f | Stock: %d%n",
                    id, nombre, idCategoria, idProveedor, precioCompra, precioVenta, stock);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR AL LEER PRODUCTOS.");
        ex.printStackTrace();
    }
}

public void actualizarProducto(int idProducto, String nombre, int idCategoria, int idProveedor, float precioCompra, float precioVenta, int stock) {
    String query = "UPDATE PRODUCTOS SET nombre_producto = ?, id_categoria = ?, id_proveedor = ?, precio_compra = ?, precio_venta = ?, stock = ? WHERE id_producto = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, nombre);
        ps.setInt(2, idCategoria);
        ps.setInt(3, idProveedor);
        ps.setFloat(4, precioCompra);
        ps.setFloat(5, precioVenta);
        ps.setInt(6, stock);
        ps.setInt(7, idProducto);

        ps.executeUpdate();
        System.out.println("Producto actualizado con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ACTUALIZAR PRODUCTO.");
        ex.printStackTrace();
    }
}

public void eliminarProducto(int idProducto) {
    String query = "DELETE FROM PRODUCTOS WHERE id_producto = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, idProducto);

        ps.executeUpdate();
        System.out.println("Producto eliminado con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ELIMINAR PRODUCTO.");
        ex.printStackTrace();
    }
}

public DefaultTableModel mostrarProductos() {
    String[] nombresColumnas = {"ID", "Nombre", "Categoría", "Proveedor", "Precio Compra", "Precio Venta", "Stock"};
    String[] registros = new String[7];
    DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);
    String query = "SELECT * FROM PRODUCTOS";

    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            registros[0] = String.valueOf(rs.getInt("id_producto"));
            registros[1] = rs.getString("nombre_producto");
            registros[2] = String.valueOf(rs.getInt("id_categoria"));
            registros[3] = String.valueOf(rs.getInt("id_proveedor"));
            registros[4] = String.format("%.2f", rs.getFloat("precio_compra"));
            registros[5] = String.format("%.2f", rs.getFloat("precio_venta"));
            registros[6] = String.valueOf(rs.getInt("stock"));

            modelo.addRow(registros);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR AL MOSTRAR PRODUCTOS.");
        ex.printStackTrace();
    }

    return modelo;
}

public DefaultTableModel buscarProductoPorId(int idProducto) {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID Producto");
    modelo.addColumn("Nombre");
    modelo.addColumn("Categoría");
    modelo.addColumn("Proveedor");
    modelo.addColumn("Precio Compra");
    modelo.addColumn("Stock");

    try {
        String query = "SELECT * FROM PRODUCTOS WHERE id_producto = ?";
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, idProducto);
        ResultSet rs = ps.executeQuery();

        // Si hay resultados, agregar la fila a la tabla
        if (rs.next()) {
            Object[] row = new Object[6];
            row[0] = rs.getInt("id_producto");
            row[1] = rs.getString("nombre_producto");
            row[2] = rs.getInt("id_categoria");
            row[3] = rs.getInt("id_proveedor");
            row[4] = rs.getFloat("precio_compra");
            row[5] = rs.getInt("stock");
            modelo.addRow(row);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return modelo;
}

public DefaultTableModel llenarTodosLosProductos() {
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID Producto");
    modelo.addColumn("Nombre");
    modelo.addColumn("Categoría");
    modelo.addColumn("Proveedor");
    modelo.addColumn("Precio Compra");
    modelo.addColumn("Stock");

    try {
        String query = "SELECT * FROM PRODUCTOS";
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        // Recorrer los resultados y agregar cada producto a la tabla
        while (rs.next()) {
            Object[] row = new Object[6];
            row[0] = rs.getInt("id_producto");
            row[1] = rs.getString("nombre_producto");
            row[2] = rs.getInt("id_categoria");
            row[3] = rs.getInt("id_proveedor");
            row[4] = rs.getFloat("precio_compra");
            row[5] = rs.getInt("stock");
            modelo.addRow(row);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return modelo;
}

}
  
