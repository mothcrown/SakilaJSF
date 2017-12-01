
package es.cifpcm.sakilajsf.web.data;

import es.cifpcm.sakilajsf.web.model.Actor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ActorDaoImpl es el músculo de la aplicación!
 * La idea es que podamos cambiar el ActorDaoImpl entre nosotros y que todo
 * funcione igual, no?
 * 
 * @author mothcrown
 */
public class ActorDaoImpl implements ActorDao {
    private DataBase db;
    private List<Actor> actors;
    // Loggers
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");
    private final Logger debugLogger = LoggerFactory.getLogger("debugLogger");
    
    /**
     * Constructor vacío.
     * Lo contrario es kosher con los pojitos? PREGUNTA A INMA!
     */
    public ActorDaoImpl () {}
    
    /**
     * selectAll() ataca la BD, saca la Lista, la manda a la interfaz 
     * (actorDao) y de ahí va a la managed bean (ActorBean). Luego se vomita
     * todo en la web.
     * 
     * @return List<Actor>
     */
    @Override
    public List<Actor> selectAll() {
        actors = new ArrayList<>();
        ResourceBundle rb = ResourceBundle.getBundle("sakila");
        db = new DataBase(rb);
        
        Connection connection = null;
        
        try {
            debugLogger.info("Abriendo conexión a BD.");
            db.open();
            connection = db.getConnection();
            String query = "select * from sakila.actor";

            try (PreparedStatement pstmt = connection.prepareStatement(query)){

                try (ResultSet rs = pstmt.executeQuery()){
                    debugLogger.info("Ejecutando consulta.");
                    while (rs.next()) {
                        Actor newActor = new Actor();
                        newActor.setId(rs.getInt("actor_id"));
                        newActor.setFirstName(rs.getString("first_name"));
                        newActor.setLastName(rs.getString("last_name"));
                        newActor.setLastUpdate(rs.getTimestamp("last_update"));
                        actors.add(newActor);
                    }
                    
                } catch (SQLException e) {
                    errorLogger.error("Fallo ejecutando consulta: {}", e);
                }
                
               pstmt.close(); 
               
            } catch (SQLException e) {
                errorLogger.error("Fallo realizando conexión a la base de datos: {}", e);
            }
        } finally {
            try {
                debugLogger.info("Cerrando conexiones a la BD.");
                if (connection != null) {
                    connection.close();
                    db.close();
                }
            } catch (SQLException e) {
                errorLogger.error("Fallo cerrando conexión a la BD: {}", e);
            }
        }
        return actors;
    }
}
