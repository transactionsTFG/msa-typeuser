package business.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import business.eventdispatcher.IJMSEventDispatcher;
import business.typeuser.TypeUser;
import msa.commons.event.EventId;
import msa.commons.microservices.user.commandevent.CreateUserCommand;

@Stateless
public class TypeUserServicesImpl implements TypeUserServices {

    private EntityManager entityManager;
    private IJMSEventDispatcher jmsEventDispatcher;

    public TypeUserServicesImpl(){}

    @Inject
    public TypeUserServicesImpl(EntityManager entityManager, IJMSEventDispatcher jmsEventDispatcher) {
        this.entityManager = entityManager;
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public void getTypeUser(CreateUserCommand c) {
        TypeUser typeUser = this.entityManager.createNamedQuery("TypeUser.findByName", TypeUser.class)
                                              .setParameter("name", c.getTypeUser()).getSingleResult();
        if (typeUser == null) 
            this.jmsEventDispatcher.publish(EventId.FAILED_USER, c.getIdUser());
        else
            this.jmsEventDispatcher.publish(EventId.CREATE_USER, c.getIdUser());
    }
    
}
