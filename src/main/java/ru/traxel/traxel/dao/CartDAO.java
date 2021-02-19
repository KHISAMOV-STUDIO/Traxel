package ru.traxel.traxel.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.traxel.traxel.models.Cart;

import java.util.List;

@Component
public class CartDAO {
    private final SessionFactory sessionFactory;

    Session session;

    @Autowired
    public CartDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Cart a) {
        open();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(a);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public List list() {
        open();
        Transaction tx = null;
        List list = null;

        try {
            tx = session.beginTransaction();
            list = session.createQuery("from Cart").list();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close();
        }
        return list;
    }

    public Cart show(int id) {
        open();
        Transaction tx = null;
        Cart a = null;
        try {
            tx = session.beginTransaction();
            a = session.load(Cart.class, id);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close();
        }
        return a;
    }

    public void update(Cart a) {
        open();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(a);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void delete(int id) {
        get();
        Transaction tx = null;
        Cart a = null;
        try {
            tx = session.beginTransaction();
            a = session.load(Cart.class, id);
            if(null != a){
                session.delete(a);
            }
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    private void open() {
        session = sessionFactory.openSession();
    }

    private void get() {
        session = sessionFactory.getCurrentSession();
    }

    private void close() {
        session.close();
    }
}
