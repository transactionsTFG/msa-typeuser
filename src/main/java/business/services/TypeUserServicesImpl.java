package business.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import business.typeuser.TypeUser;
import domainevent.publisher.IJMSEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.user.commandevent.CreateUserCommand;

@Stateless
public class TypeUserServicesImpl implements TypeUserServices {

    private EntityManager entityManager;
    private IJMSEventPublisher jmsEventDispatcher;

    public TypeUserServicesImpl(){}

    @Inject
    public TypeUserServicesImpl(EntityManager entityManager, IJMSEventPublisher jmsEventDispatcher) {
        this.entityManager = entityManager;
        this.jmsEventDispatcher = jmsEventDispatcher;
    }

    @Override
    public void getTypeUser(CreateUserCommand c) {
        List<TypeUser> typeUser = this.entityManager.createNamedQuery("TypeUser.findByName", TypeUser.class)
                                              .setParameter("name", c.getTypeUser()).getResultList();
        if (typeUser.isEmpty()) 
            this.jmsEventDispatcher.publish(EventId.FAILED_USER, c.getIdUser());
        else
            this.jmsEventDispatcher.publish(EventId.CREATE_USER, c.getIdUser());
    }
    
}
