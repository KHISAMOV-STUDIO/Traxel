package ru.traxel.traxel.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.traxel.traxel.models.Cart;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class CartDAO implements StandartDAO<Cart>{
    private final SessionFactory sessionFactory;

    @Autowired
    public CartDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Cart a) {
        Session session = open();
        Transaction tx = null;
        try {
            tx = getTx(session);
            session.persist(a);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
    }

    @Override
    public List<Cart> list() {
        Session session = open();
        Transaction tx = null;
        List<Cart> list = null;
        try {
            tx = getTx(session);
            TypedQuery<Cart> query = session.createQuery("SELECT e FROM Cart e", Cart.class);
            list = query.getResultList();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
        return list;
    }

    @Override
    public Optional<Cart> show(long id) {
        Transaction tx = null;
        Cart a = null;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.get(Cart.class, id);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
        return Optional.ofNullable(a);
    }

    @Override
    public void update(Cart a) {
        Transaction tx = null;
        Session session = open();
        try {
            tx = getTx(session);
            session.update(a);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
    }

    @Override
    public void delete(long id) {
        Transaction tx = null;
        Cart a;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.get(Cart.class, id);
            if(null != a){
                session.delete(a);
            }
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            close(session);
        }
    }

    private Session open() {
        return sessionFactory.openSession();
    }
    private Transaction getTx(Session session) {
        return session.beginTransaction();
    }
    private void close(Session session) {
        session.close();
    }

}
