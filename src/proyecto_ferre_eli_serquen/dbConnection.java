
package proyecto_ferre_eli_serquen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
     static String url = "jdbc:mysql://localhost:3306/inventario_ferreteria_elec";
    static String user = "root";
    static String pass = "";
    
    public static Connection conectar () {
        
        Connection con = null;
        try {
            con = DriverManager.getConnection(url,user,pass);
                System.out.println("Conexion exitosa");
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return con;
    }
}
