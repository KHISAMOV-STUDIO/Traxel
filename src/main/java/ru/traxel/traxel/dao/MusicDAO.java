package ru.traxel.traxel.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.traxel.traxel.models.Customer;
import ru.traxel.traxel.models.Music;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class MusicDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public MusicDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Music a) {
        Session session = get();
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

    }

    public List<Music> list() {
        Session session = get();
        Transaction tx = null;
        List<Music> list = null;
        try {
            tx = session.beginTransaction();
            TypedQuery<Music> query = session.createQuery("SELECT e FROM Music e", Music.class);
            list = query.getResultList();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return list;
    }

    public Music show(int id) {
        Transaction tx = null;
        Music a = null;
        Session session = get();
        try {
            tx = session.beginTransaction();
            a = session.load(Music.class, id);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return a;
    }

    public void update(Music a) {
        Transaction tx = null;
        Session session = get();
        try {
            tx = session.beginTransaction();
            session.update(a);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Transaction tx = null;
        Music a = null;
        Session session = get();
        try {
            tx = session.beginTransaction();
            a = session.load(Music.class, id);
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

    private Session open() {
        return sessionFactory.openSession();
    }

    private Session get() {
        return sessionFactory.getCurrentSession();
    }

    private void close(Session session) {
        session.close();
    }
}
