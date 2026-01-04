
package proyecto_ferre_eli_serquen;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class crud_usuario {
   public void insertarDato(String Usuario, String cont, String car) {
    String query = "INSERT INTO usuarios(Usuario, Contraseña, rol) VALUES (?, ?, ?)";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        
        ps.setString(1, Usuario); // Este es Correo
        ps.setString(2, cont); // Este debería ser Contraseña
        ps.setString(3, car);  // Este debería ser Rol

        ps.executeUpdate();
        System.out.println("Dato insertado con éxito");
    } catch (SQLException ex) {
        System.out.println("ERROR AL INSERTAR DATOS");
        ex.printStackTrace();
    }
}

    
      public void leerDatos(){
        String query="select * from usuarios";
        try {
            Connection con= dbConnection.conectar();
            PreparedStatement ps=con.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
               int id=rs.getInt("id_usuario");
               String Usuario=rs.getString("Usuario");
               String cont=rs.getString("Contraseña");
               String car=rs.getString("Rol");

                System.out.printf("ID_Producto " + id+ "  | NOMBRE : %-15s | Usuario : %-20s | CLAVE : %-10s | CARGO : %-15s |\n",  Usuario, cont, car);
            
            }
        }
        

        catch (SQLException e) {
            {
            System.out.println("Error al insertar");
            e.printStackTrace(); 
        }
        }
         
    }
    
    public void actualizarDato(int id_usuario,  String Usuario, String cont, String car) {
    String query = "UPDATE usuarios SET  Usuario = ?, Contraseña = ?, Rol = ? WHERE id_usuario = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);

        // Asignar los parámetros en el orden correcto
        ps.setString(1, Usuario); // Correo
        ps.setString(2, cont); // Contraseña
        ps.setString(3, car);  // Rol
        ps.setInt(4, id_usuario); // ID_Usuario (para el WHERE)

        ps.executeUpdate();
        System.out.println("Dato actualizado con éxito");
    } catch (SQLException ex) {
        System.out.println("ERROR AL ACTUALIZAR DATOS");
        ex.printStackTrace();
    }
}


      public void eliminarDato(int id_Usuario)    { 
        String query="delete from usuarios where ID_Usuario = ?";
            try {
                Connection con= dbConnection.conectar();
                PreparedStatement ps=con.prepareStatement(query);
                ps.setInt(1, id_Usuario);

                ps.executeUpdate();
                System.out.println("Dato eliminado con exito");
                }
                catch (SQLException ex)
                {
                    System.out.println("ERROR AL ELIMINAR DATOS");
                    ex.printStackTrace();
                }
    }
       
      public DefaultTableModel mostrarUsuario(){
         String [] nombresColumnas = {"id_usuario","usuario","Contraseña","Rol"};
         String [] registros = new String[4];
         DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
         String query = "SELECT * FROM usuarios"; 
         try{
            Connection con=dbConnection.conectar();
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
               
            {
            registros[0]=rs.getString("id_usuario");
            registros[1]=rs.getString("Usuario");
            registros[2]=rs.getString("Contraseña");
            registros[3]=rs.getString("Rol");

            modelo.addRow(registros);
                }
            }
            catch(SQLException e){
                    System.out.println("Error al insertar");
                    e.printStackTrace(); 
                  }
      return modelo;
     }
      public boolean verificarLogin(String usuario, String contrasena, String rol) {
    String query = "SELECT * FROM usuarios WHERE usuario = ? AND Contraseña = ? AND Rol = ?";
    try {
        Connection con = dbConnection.conectar();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, usuario);
        ps.setString(2, contrasena);
        ps.setString(3, rol);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            // Si se encuentra el usuario y la contraseña
            return true; // Login exitoso
        } else {
            // Si no se encuentra
            return false; // Login fallido
        }
    } catch (SQLException ex) {
        System.out.println("Error al verificar login.");
        ex.printStackTrace();
        return false;
    }
}
 
}
