
package es.cifpcm.sakilajsf.web.data;

import es.cifpcm.sakilajsf.web.model.Actor;
import java.util.List;

/**
 * Esto es una interfaz. Hace cosas de interfaz.
 * O eso espero!
 * 
 * @author mothcrown
 */
public interface ActorDao {
    public List<Actor> selectAll();
}
