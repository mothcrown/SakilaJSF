
package es.cifpcm.sakilajsf.web.model;

import java.sql.Timestamp;

/**
 * Clase Actor con vitaminas, proteínas y todo lo que necesita el cuerpo!
 * 
 * @author mothcrown
 */
public class Actor {
    private int id;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;
    
    /**
     * To be continued...
     * @return 
     */
    public String save() {
        return "ok";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
