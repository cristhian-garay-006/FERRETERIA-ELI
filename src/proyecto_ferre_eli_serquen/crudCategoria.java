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



public class crudCategoria {
    public void insertarCategoria(String nombreCategoria, String descripcionCategoria) {
    String query = "INSERT INTO CATEGORIAS (nombre_categoria, descripcion_categoria) VALUES (?, ?)";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, nombreCategoria);
        ps.setString(2, descripcionCategoria);

        ps.executeUpdate();
        System.out.println("Categoría insertada con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL INSERTAR CATEGORÍA.");
        ex.printStackTrace();
    }
}

public void leerCategorias() {
    String query = "SELECT * FROM CATEGORIAS";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id_categoria");
            String nombre = rs.getString("nombre_categoria");
            String descripcion = rs.getString("descripcion_categoria");

            System.out.printf("ID: %d | Nombre: %-20s | Descripción: %s%n", id, nombre, descripcion);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR AL LEER CATEGORÍAS.");
        ex.printStackTrace();
    }
}

public void actualizarCategoria(int idCategoria, String nombreCategoria, String descripcionCategoria) {
    String query = "UPDATE CATEGORIAS SET nombre_categoria = ?, descripcion_categoria = ? WHERE id_categoria = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, nombreCategoria);
        ps.setString(2, descripcionCategoria);
        ps.setInt(3, idCategoria);

        ps.executeUpdate();
        System.out.println("Categoría actualizada con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ACTUALIZAR CATEGORÍA.");
        ex.printStackTrace();
    }
}

public void eliminarCategoria(int idCategoria) {
    String query = "DELETE FROM CATEGORIAS WHERE id_categoria = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, idCategoria);

        ps.executeUpdate();
        System.out.println("Categoría eliminada con éxito.");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ELIMINAR CATEGORÍA.");
        ex.printStackTrace();
    }
}

public DefaultTableModel mostrarCategorias() {
    String[] nombresColumnas = {"ID", "Nombre", "Descripción"};
    String[] registros = new String[3];
    DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas);
    String query = "SELECT * FROM CATEGORIAS";

    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            registros[0] = String.valueOf(rs.getInt("id_categoria"));
            registros[1] = rs.getString("nombre_categoria");
            registros[2] = rs.getString("descripcion_categoria");

            modelo.addRow(registros);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR AL MOSTRAR CATEGORÍAS.");
        ex.printStackTrace();
    }

    return modelo;
}

 // Método para buscar categoría por ID
    public DefaultTableModel buscarCategoriaPorId(int idCategoria) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        
        String query = "SELECT * FROM CATEGORIAS WHERE id_categoria = ?";
        try {
            Connection con = dbConnection.conectar(); // Método de conexión
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idCategoria); // Parámetro ID
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getInt("id_categoria");
                fila[1] = rs.getString("nombre_categoria");
                fila[2] = rs.getString("descripcion_categoria");
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar categoría por ID.");
            ex.printStackTrace();
        }
        return modelo;
    }

    // Método para obtener todas las categorías
    public DefaultTableModel obtenerCategorias() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        
        String query = "SELECT * FROM CATEGORIAS";
        try {
            Connection con = dbConnection.conectar(); // Método de conexión
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getInt("id_categoria");
                fila[1] = rs.getString("nombre_categoria");
                fila[2] = rs.getString("descripcion_categoria");
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener todas las categorías.");
            ex.printStackTrace();
        }
        return modelo;
    }
}
