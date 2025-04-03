package business.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import business.eventdispatcher.IJMSEventDispatcher;
import business.mapper.TypeUserMapper;
import business.typeuser.TypeUser;
import msa.commons.event.EventId;

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
    public void getTypeUser(long typeUserId) {
        TypeUser typeUser = this.entityManager.find(TypeUser.class, typeUserId, LockModeType.OPTIMISTIC);
        this.jmsEventDispatcher.publish(EventId.GET_TYPE_USER, TypeUserMapper.INSTANCE.entityToDTO(typeUser));
    }
    
}
