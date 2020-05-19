package sample.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String host = "localhost";
    private static final String user = "topicos20";
    private static final String pwd = "1234";
    private static final String db = "otso";
    public static Connection con;

    public static void crearConexion(){

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            //ABRIENDO el socket hacia el SGBD
            con = DriverManager.getConnection("jdbc:mariadb://"+host+":3306/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",user,pwd);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
