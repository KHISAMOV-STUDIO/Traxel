package ru.traxel.traxel.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.traxel.traxel.models.Customer;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerDAO implements StandartDAO<Customer>{
    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Customer a) {
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
    public List<Customer> list() {
        Session session = open();
        Transaction tx = null;
        List<Customer> list = null;
        try {
            tx = getTx(session);
            TypedQuery<Customer> query = session.createQuery("SELECT e FROM Customer e", Customer.class);
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
    public Optional<Customer> show(long id) {
        Transaction tx = null;
        Customer a = null;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.get(Customer.class, id);
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
    public void update(Customer a) {
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
        Customer a;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.get(Customer.class, id);
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
