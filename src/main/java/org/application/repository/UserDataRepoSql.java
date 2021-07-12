package org.application.repository;

import org.application.model.UserData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDataRepoSql implements UserDataRepo {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDataRepoSql(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Long save(UserData user) {
        return (Long) sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    @Override
    public List<UserData> list() {
        @SuppressWarnings("unchecked")
        TypedQuery<UserData> query = sessionFactory.getCurrentSession().createQuery("from UserData");

        return query.getResultList();
    }

    @Transactional
    @Override
    public boolean delete(Long id) {

        UserData userDataFound = sessionFactory.getCurrentSession().get(UserData.class, id);

        if (userDataFound != null) {

            sessionFactory.getCurrentSession().delete(userDataFound);

            return true;

        } else {

            return false;
        }
    }

    @Transactional
    @Override
    public Optional<UserData> get(Long id) {

        UserData userDataFound = sessionFactory.getCurrentSession().get(UserData.class, id);

        return Optional.ofNullable(userDataFound);
    }

    @Transactional
    @Override
    public Optional<UserData> updateIfPresent(Long id,
                                              UserData user) {

        user.setId(id);

        UserData userDataFound = sessionFactory.getCurrentSession().get(UserData.class, id);

        if (userDataFound != null) {

            userDataFound.setEmail(user.getEmail());
            userDataFound.setName(user.getName());

            return Optional.of(user);
        }

        return Optional.empty();
    }

}

