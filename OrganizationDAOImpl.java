package com.company.dao.impl;

import com.company.dao.OrganizationDAO;
import com.company.model.Organization;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository(value = "organizationDAO")
public class OrganizationDAOImpl implements OrganizationDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public OrganizationDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addOrganization(Organization organization) {
        Transaction tx = null;
        try{
            tx = sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().save(organization);
            tx.commit();
        }catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void editOrganization(Organization organization) {
        Organization organizationForUpdate= getOrganization(organization.getId());
        organizationForUpdate.setId(organization.getId());
        organizationForUpdate.setName(organization.getName());
        organizationForUpdate.setCountry(organization.getCountry());
        organizationForUpdate.setAddress(organization.getAddress());
        organizationForUpdate.setPhone(organization.getPhone());
        organizationForUpdate.setMarketCap(organization.getMarket_cap());
        Transaction tx = null;
        try{
            tx = sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().update(organizationForUpdate);
            tx.commit();
        }catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Organization getOrganization(int id) {
        Transaction tx = null;
        Organization organization = null;
        try{
            tx = sessionFactory.getCurrentSession().beginTransaction();
            organization = (Organization) sessionFactory.getCurrentSession().get(Organization.class, id);
            tx.commit();
        }catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return organization;
    }

    public void removeOrganization(int id) {
        Transaction tx = null;
        Organization organization = getOrganization(id);
        try{
            tx = sessionFactory.getCurrentSession().beginTransaction();
            sessionFactory.getCurrentSession().delete(organization);
            tx.commit();
        }catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public List<Organization> listOfOrganizations() {
        List<Organization> list = null;
        Transaction tx = null;
        try {
            tx = sessionFactory.getCurrentSession().beginTransaction();
            list = sessionFactory.getCurrentSession().createQuery("from Organization").list();
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
