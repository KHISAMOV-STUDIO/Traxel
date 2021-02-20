package ru.traxel.traxel.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.traxel.traxel.models.Author;
import ru.traxel.traxel.models.Music;

import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class AuthorDAO {
    private final SessionFactory sessionFactory;

    Session session;

    @Autowired
    public AuthorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Author a) {
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

    public List<Author> list() {
        open();
        Transaction tx = null;
        List<Author> list = null;

        try {
            tx = session.beginTransaction();
            TypedQuery<Author> query = session.createQuery("SELECT e FROM Author e", Author.class);
            list = query.getResultList();
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

    public Author show(int id) {
        open();
        Transaction tx = null;
        Author a = null;
        try {
            tx = session.beginTransaction();
            a = session.load(Author.class, id);
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

    public void update(Author a) {
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
        Author a = null;
        try {
            tx = session.beginTransaction();
            a = session.load(Author.class, id);
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
