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
 * @author serquen
 */
public class crudVenta {
 public void insertarVenta(String cliente, String fechaVenta, float totalVenta) {
    String query = "INSERT INTO VENTAS (clientes, fecha_venta, total_venta) VALUES (?, ?, ?)";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, cliente);
        ps.setString(2, fechaVenta);
        ps.setFloat(3, totalVenta);

        ps.executeUpdate();
        System.out.println("Venta registrada con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL INSERTAR VENTA.");
        ex.printStackTrace();
    }
}

public void leerVentas() {
    String query = "SELECT * FROM VENTAS";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idVenta = rs.getInt("id_venta");
            String cliente = rs.getString("clientes");
            String fechaVenta = rs.getString("fecha_venta");
            float totalVenta = rs.getFloat("total_venta");

            System.out.printf("ID Venta: %d | Cliente: %-20s | Fecha: %-20s | Total: %.2f%n", idVenta, cliente, fechaVenta, totalVenta);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR AL LEER VENTAS.");
        ex.printStackTrace();
    }
}

public void actualizarVenta(int idVenta, String cliente, String fechaVenta, float totalVenta) {
    String query = "UPDATE VENTAS SET clientes = ?, fecha_venta = ?, total_venta = ? WHERE id_venta = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, cliente);
        ps.setString(2, fechaVenta);
        ps.setFloat(3, totalVenta);
        ps.setInt(4, idVenta);

        ps.executeUpdate();
        System.out.println("Venta actualizada con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ACTUALIZAR VENTA.");
        ex.printStackTrace();
    }
}

public DefaultTableModel mostrarVentas() {
    String[] nombresColumnas = {"ID Venta", "Cliente", "Fecha Venta", "Total Venta"};
    String[] registros = new String[4];
    DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);
    String query = "SELECT * FROM VENTAS";

    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            registros[0] = String.valueOf(rs.getInt("id_venta"));
            registros[1] = rs.getString("clientes");
            registros[2] = rs.getString("fecha_venta");
            registros[3] = String.valueOf(rs.getFloat("total_venta"));

            modelo.addRow(registros);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR AL MOSTRAR VENTAS.");
        ex.printStackTrace();
    }

    return modelo;
}

public void eliminarVenta(int idVenta) {
    String query = "DELETE FROM VENTAS WHERE id_venta = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, idVenta);

        ps.executeUpdate();
        System.out.println("Venta eliminada con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ELIMINAR VENTA.");
        ex.printStackTrace();
    }
}

public DefaultTableModel buscarVentaPorId(int idVenta) {
    String query = "SELECT id_venta, clientes, fecha_venta, total_venta FROM VENTAS WHERE id_venta = ?";
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Cliente");
    modelo.addColumn("Fecha");
    modelo.addColumn("Total");

    try (Connection con = dbConnection.conectar();
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setInt(1, idVenta);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("id_venta"),
                rs.getString("clientes"),
                rs.getString("fecha_venta"),
                rs.getFloat("total_venta")
            });
        }
    } catch (SQLException e) {
        System.out.println("Error al buscar venta por ID.");
        e.printStackTrace();
    }
    return modelo;
}

}
