package by.alex.kasperovich.dao;

import by.alex.kasperovich.entity.Address;
import by.alex.kasperovich.entity.Person;
import by.alex.kasperovich.entity.Phone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneHibernateDao implements PhoneDao{

    public static void main(String[] args) {
        PhoneHibernateDao phoneHibernateDao = new PhoneHibernateDao();
        phoneHibernateDao.createPhone(new Phone("MTS",33,3197501));

    }

    @Override
    public int createPhone(Phone phone) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Serializable generationId = session.save(phone);
            transaction.commit();
            return (int) generationId;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return 0;
    }

    @Override
    public Phone readPhoneById(int id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Phone phone = null;
        try {
            phone = session.get(Phone.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return phone;
    }

    @Override
    public List<Phone> readAllPhones() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        ArrayList<Phone> phones = new ArrayList<>();

        phones = (ArrayList<Phone>) session.createQuery("FROM Person").list();


        return phones;
    }

    @Override
    public void updatePhone(Phone phoneUpdated) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(phoneUpdated);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deletePhoneById(int id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Phone phone = readPhoneById(id);
            session.delete(phone);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

    }
}
