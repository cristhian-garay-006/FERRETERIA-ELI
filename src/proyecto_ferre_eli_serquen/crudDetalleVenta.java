/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_ferre_eli_serquen;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author crist
 */
public class crudDetalleVenta {
    public void insertarDetalleVenta(int idVenta, int idProducto, int cantidad, float precioUnitario, float subtotal) {
    String query = "INSERT INTO DETALLES_VENTA (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, idVenta);
        ps.setInt(2, idProducto);
        ps.setInt(3, cantidad);
        ps.setFloat(4, precioUnitario);
        ps.setFloat(5, subtotal);

        ps.executeUpdate();
        System.out.println("Detalle de venta registrado con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL INSERTAR DETALLE DE VENTA.");
        ex.printStackTrace();
    }
}

public void leerDetallesVenta() {
    String query = "SELECT * FROM DETALLES_VENTA";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idDetalle = rs.getInt("id_detalle");
            int idVenta = rs.getInt("id_venta");
            int idProducto = rs.getInt("id_producto");
            int cantidad = rs.getInt("cantidad");
            float precioUnitario = rs.getFloat("precio_unitario");
            float subtotal = rs.getFloat("subtotal");

            System.out.printf(
                "ID Detalle: %d | ID Venta: %d | ID Producto: %d | Cantidad: %d | Precio Unitario: %.2f | Subtotal: %.2f%n",
                idDetalle, idVenta, idProducto, cantidad, precioUnitario, subtotal
            );
        }
    } catch (SQLException ex) {
        System.out.println("ERROR AL LEER DETALLES DE VENTA.");
        ex.printStackTrace();
    }
}

public void actualizarDetalleVenta(int idDetalle, int idVenta, int idProducto, int cantidad, float precioUnitario, float subtotal) {
    String query = "UPDATE DETALLES_VENTA SET id_venta = ?, id_producto = ?, cantidad = ?, precio_unitario = ?, subtotal = ? WHERE id_detalle = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, idVenta);
        ps.setInt(2, idProducto);
        ps.setInt(3, cantidad);
        ps.setFloat(4, precioUnitario);
        ps.setFloat(5, subtotal);
        ps.setInt(6, idDetalle);

        ps.executeUpdate();
        System.out.println("Detalle de venta actualizado con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ACTUALIZAR DETALLE DE VENTA.");
        ex.printStackTrace();
    }
}

public void eliminarDetalleVenta(int idDetalle) {
    String query = "DELETE FROM DETALLES_VENTA WHERE id_detalle = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, idDetalle);

        ps.executeUpdate();
        System.out.println("Detalle de venta eliminado con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ELIMINAR DETALLE DE VENTA.");
        ex.printStackTrace();
    }
}

public DefaultTableModel mostrarDetallesVenta() {
    String[] nombresColumnas = {"ID Detalle", "ID Venta", "ID Producto", "Cantidad", "Precio Unitario", "Subtotal"};
    String[] registros = new String[6];
    DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);
    String query = "SELECT * FROM DETALLES_VENTA";

    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            registros[0] = String.valueOf(rs.getInt("id_detalle"));
            registros[1] = String.valueOf(rs.getInt("id_venta"));
            registros[2] = String.valueOf(rs.getInt("id_producto"));
            registros[3] = String.valueOf(rs.getInt("cantidad"));
            registros[4] = String.valueOf(rs.getFloat("precio_unitario"));
            registros[5] = String.valueOf(rs.getFloat("subtotal"));

            modelo.addRow(registros);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR AL MOSTRAR DETALLES DE VENTA.");
        ex.printStackTrace();
    }

    return modelo;
}

public DefaultTableModel buscarDetallePorId(int idDetalle) {
    String query = "SELECT id_detalle, id_venta, id_producto, cantidad, precio_unitario, subtotal FROM DETALLES_VENTA WHERE id_detalle = ?";
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID Detalle");
    modelo.addColumn("ID Venta");
    modelo.addColumn("ID Producto");
    modelo.addColumn("Cantidad");
    modelo.addColumn("Precio Unitario");
    modelo.addColumn("Subtotal");

    try (Connection con = dbConnection.conectar();
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setInt(1, idDetalle);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("id_detalle"),
                rs.getInt("id_venta"),
                rs.getInt("id_producto"),
                rs.getInt("cantidad"),
                rs.getFloat("precio_unitario"),
                rs.getFloat("subtotal")
            });
        }
    } catch (SQLException e) {
        System.out.println("Error al buscar detalle por ID.");
        e.printStackTrace();
    }
    return modelo;
}


public DefaultTableModel obtenerDetalles() {
    String query = "SELECT id_detalle, id_venta, id_producto, cantidad, precio_unitario, subtotal FROM DETALLES_VENTA";
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID Detalle");
    modelo.addColumn("ID Venta");
    modelo.addColumn("ID Producto");
    modelo.addColumn("Cantidad");
    modelo.addColumn("Precio Unitario");
    modelo.addColumn("Subtotal");

    try (Connection con = dbConnection.conectar();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(query)) {

        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("id_detalle"),
                rs.getInt("id_venta"),
                rs.getInt("id_producto"),
                rs.getInt("cantidad"),
                rs.getFloat("precio_unitario"),
                rs.getFloat("subtotal")
            });
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener detalles.");
        e.printStackTrace();
    }
    return modelo;
}

}
