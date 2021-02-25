package ru.traxel.traxel.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.traxel.traxel.models.Music;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class MusicDAO implements StandartDAO<Music>{

    private final SessionFactory sessionFactory;

    @Autowired
    public MusicDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Music a) {
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
    public List<Music> list() {
        Session session = open();
        Transaction tx = null;
        List<Music> list = null;
        try {
            tx = getTx(session);
            TypedQuery<Music> query = session.createQuery("SELECT e FROM Music e", Music.class);
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
    public Optional<Music> show(long id) {
        Transaction tx = null;
        Music a = null;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.get(Music.class, id);
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
    public void update(Music a) {
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
        Music a;
        Session session = open();
        try {
            tx = getTx(session);
            a = session.get(Music.class, id);
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
