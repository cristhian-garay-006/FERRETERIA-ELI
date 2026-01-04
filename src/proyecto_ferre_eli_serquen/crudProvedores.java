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


public class crudProvedores {
    public void insertarProveedor(String nombreProveedor, String direccionProveedor, String telefonoProveedor, String emailProveedor) {
    String query = "INSERT INTO PROVEEDORES (nombre_proveedor, direccion_proveedor, telefono_proveedor, email_proveedor) VALUES (?, ?, ?, ?)";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, nombreProveedor);
        ps.setString(2, direccionProveedor);
        ps.setString(3, telefonoProveedor);
        ps.setString(4, emailProveedor);

        ps.executeUpdate();
        System.out.println("Proveedor insertado con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL INSERTAR PROVEEDOR.");
        ex.printStackTrace();
    }
}

public void leerProveedores() {
    String query = "SELECT * FROM PROVEEDORES";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id_proveedor");
            String nombre = rs.getString("nombre_proveedor");
            String direccion = rs.getString("direccion_proveedor");
            String telefono = rs.getString("telefono_proveedor");
            String email = rs.getString("email_proveedor");

            System.out.printf("ID: %d | Nombre: %-30s | Dirección: %-50s | Teléfono: %-15s | Email: %-30s%n", id, nombre, direccion, telefono, email);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR AL LEER PROVEEDORES.");
        ex.printStackTrace();
    }
}

public void actualizarProveedor(int idProveedor, String nombreProveedor, String direccionProveedor, String telefonoProveedor, String emailProveedor) {
    String query = "UPDATE PROVEEDORES SET nombre_proveedor = ?, direccion_proveedor = ?, telefono_proveedor = ?, email_proveedor = ? WHERE id_proveedor = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, nombreProveedor);
        ps.setString(2, direccionProveedor);
        ps.setString(3, telefonoProveedor);
        ps.setString(4, emailProveedor);
        ps.setInt(5, idProveedor);

        ps.executeUpdate();
        System.out.println("Proveedor actualizado con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ACTUALIZAR PROVEEDOR.");
        ex.printStackTrace();
    }
}

public void eliminarProveedor(int idProveedor) {
    String query = "DELETE FROM PROVEEDORES WHERE id_proveedor = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, idProveedor);

        ps.executeUpdate();
        System.out.println("Proveedor eliminado con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ELIMINAR PROVEEDOR.");
        ex.printStackTrace();
    }
}

public DefaultTableModel mostrarProveedores() {
    String[] nombresColumnas = {"ID", "Nombre", "Dirección", "Teléfono", "Email"};
    String[] registros = new String[5];
    DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);
    String query = "SELECT * FROM PROVEEDORES";

    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            registros[0] = String.valueOf(rs.getInt("id_proveedor"));
            registros[1] = rs.getString("nombre_proveedor");
            registros[2] = rs.getString("direccion_proveedor");
            registros[3] = rs.getString("telefono_proveedor");
            registros[4] = rs.getString("email_proveedor");

            modelo.addRow(registros);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR AL MOSTRAR PROVEEDORES.");
        ex.printStackTrace();
    }

    return modelo;
}

// Buscar proveedor por ID
public DefaultTableModel buscarProveedorPorId(int idProveedor) {
    String query = "SELECT * FROM PROVEEDORES WHERE id_proveedor = ?";
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Dirección");
    modelo.addColumn("Teléfono");
    modelo.addColumn("Email");

    try (Connection con = dbConnection.conectar();
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setInt(1, idProveedor);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("id_proveedor"),
                rs.getString("nombre_proveedor"),
                rs.getString("direccion_proveedor"),
                rs.getString("telefono_proveedor"),
                rs.getString("email_proveedor")
            });
        }
    } catch (SQLException e) {
        System.out.println("Error al buscar proveedor por ID.");
        e.printStackTrace();
    }
    return modelo;
}

// Obtener todos los proveedores
public DefaultTableModel obtenerProveedores() {
    String query = "SELECT * FROM PROVEEDORES";
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Dirección");
    modelo.addColumn("Teléfono");
    modelo.addColumn("Email");

    try (Connection con = dbConnection.conectar();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(query)) {

        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("id_proveedor"),
                rs.getString("nombre_proveedor"),
                rs.getString("direccion_proveedor"),
                rs.getString("telefono_proveedor"),
                rs.getString("email_proveedor")
            });
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener proveedores.");
        e.printStackTrace();
    }
    return modelo;
}

}
