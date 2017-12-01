
package es.cifpcm.sakilajsf.web.bean;

import es.cifpcm.sakilajsf.web.data.ActorDao;
import es.cifpcm.sakilajsf.web.data.ActorDaoImpl;
import es.cifpcm.sakilajsf.web.model.Actor;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * ActorBean... Rowan Atkinson?
 * Inma, quítame puntos.
 * 
 * @author mothcrown
 */
@Named(value = "actorBean")
@RequestScoped
public class ActorBean extends Actor {
    private ActorDao actorDao = new ActorDaoImpl();
    /**
     * Constructor vacío
     */
    public ActorBean() {
    }
    
    /**
     * "Que extraña suerte, tener que sufrir tanto miedo y tantas dudas por una 
     * cosa tan pequeña."
     * @return 
     */
    public List<Actor> getActorList() {
        List<Actor> actors = actorDao.selectAll();
        return actors;
    }
    
    
    
}
