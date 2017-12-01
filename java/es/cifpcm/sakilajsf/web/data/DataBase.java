
package es.cifpcm.sakilajsf.web.data;

import java.sql.*;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase DataBase. 
 * Ahora se limita a gestionar la conexión, sospecho que para la próxima 
 * práctica le cambiaré el nombre:(
 * 
 * @author mothcrown
 */
public class DataBase {
    private Connection connection;
    private String path;
    private String user;
    private String password;
    private int pageSize;
    private int defaultPageSize = 10;
    private String driverClassName;
    
    // Now with extra delicious loggers that happen to work!
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    
    public DataBase(ResourceBundle rb) {
        debugLogger.info("Creando conexión a BD.");
        this.path = rb.getString("database.url");
        this.user = rb.getString("database.user");
        this.password = rb.getString("database.password");
        this.pageSize = rb.getString("database.pageSize") == null ? defaultPageSize : Integer.parseInt(rb.getString("database.pageSize"));
        this.driverClassName = rb.getString("database.driver");
    }
    
    public DataBase(String path, String usuario, String password) {
        connection = null;
        this.path = path;
        this.user = usuario;
        this.password = password;
    }
    
    public void open() {
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(path, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            errorLogger.error("Fallo realizando conexión a la base de datos: {}", e);
        }
    }
    
    public void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            errorLogger.error("Fallo cerrando conexión a la BD: {}", e);
        }
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getPageSize(){
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getDefaultPageSize(){
        return defaultPageSize;
    }
    
    public void setDefaultPageSize(int defaultPageSize){
        this.defaultPageSize = defaultPageSize;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
}
