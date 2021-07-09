package org.application.dao;


import org.application.model.UserData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void save(UserData user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    @Override
    public List<UserData> list() {
        @SuppressWarnings("unchecked")
        TypedQuery<UserData> query = sessionFactory.getCurrentSession().createQuery("from UserData");
        return query.getResultList();
    }

}

