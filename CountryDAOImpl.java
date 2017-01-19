package com.company.dao.impl;

import com.company.model.Country;
import com.company.dao.CountryDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository(value = "countryDAO")
public class CountryDAOImpl implements CountryDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public CountryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Country> listOfCountry() {
        List<Country> list = null;
        Transaction tx = null;
        try {
            tx = sessionFactory.getCurrentSession().beginTransaction();
            list = sessionFactory.getCurrentSession().createQuery("from Country").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return list;
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Session getCurrentSession() {
        Session session = getSessionFactory().getCurrentSession();
        if (session == null) {
            session = getSessionFactory().openSession();
        }
        return session;
    }
}
