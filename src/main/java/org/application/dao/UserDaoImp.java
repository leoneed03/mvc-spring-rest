package org.application.dao;


import org.application.exceptions.UserException;
import org.application.model.UserData;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

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
    public void delete(Long id) {
        UserData userData = new UserData();
        userData.setId(id);
        sessionFactory.getCurrentSession().delete(userData);
    }

    @Transactional
    @Override
    public Optional<UserData> get(Long id) {
        UserData userDataFound = sessionFactory.getCurrentSession().get(UserData.class, id);

        return Optional.ofNullable(userDataFound);
    }

}

