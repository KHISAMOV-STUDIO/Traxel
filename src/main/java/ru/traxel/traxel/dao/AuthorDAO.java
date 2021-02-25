package ru.traxel.traxel.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.traxel.traxel.models.Author;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class AuthorDAO implements StandartDAO<Author>{
    private final SessionFactory sessionFactory;

    @Autowired
    public AuthorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Author a) {
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
    public List<Author> list() {
        Session session = open();
        Transaction tx = null;
        List<Author> list = null;
        try {
            tx = getTx(session);
            TypedQuery<Author> query = session.createQuery("SELECT e FROM Author e", Author.class);
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
    public Optional<Author> show(long id) {
        Session session = open();
        Transaction tx = null;
        boolean openSession = session == null;
        if (openSession) {
            session = open();
            tx = session.getTransaction();
        }
        Author a = null;
        try {
            if(openSession) {
                tx = getTx(session);
            }
            a = session.get(Author.class, id);
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
    public void update(Author a) {
        Session session = open();
        Transaction tx = null;
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
        Session session = open();
        Transaction tx = null;
        Author a = null;
        try {
            tx = getTx(session);
            a = session.get(Author.class, id);
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
