package business.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import business.typeuser.TypeUser;
import msa.commons.commands.user.CreateUserCommand;

@Stateless
public class TypeUserServicesImpl implements TypeUserServices {

    private EntityManager entityManager;

    public TypeUserServicesImpl(){}

    @Inject
    public TypeUserServicesImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long getIdTypeUser(CreateUserCommand c) {
        List<TypeUser> typeUser = this.entityManager.createNamedQuery("TypeUser.findByName", TypeUser.class)
                                                .setParameter("name", c.getTypeUser())
                                                .setLockMode(LockModeType.OPTIMISTIC)
                                                .getResultList();
        return typeUser.isEmpty() ? 0 : typeUser.get(0).getId();
    }
    
}
