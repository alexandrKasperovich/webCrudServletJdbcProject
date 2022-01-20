package by.alex.kasperovich.dao;

import by.alex.kasperovich.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonHibernateDao implements PersonDao {

    public static void main(String[] args) {
        PersonHibernateDao personHibernateDao = new PersonHibernateDao();
        List<Person> personList = personHibernateDao.readAllPerson();
        System.out.println("d");
        long id = personHibernateDao.createPerson(new Person("Dzmitry", 12));
        Person person = personHibernateDao.readPersonById(id);
        person.setAge(22);
        person.setName("Evgeniy");
        personHibernateDao.updatePerson(person);
        personHibernateDao.deletePersonById(id);
    }


    @Override
    public long createPerson(Person person) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Serializable generationId = session.save(person);
            transaction.commit();
            return (long) generationId;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return 0;
    }

    @Override
    public Person readPersonById(long id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Person person = null;
        try {
            person = session.get(Person.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return person;
    }

    @Override
    public List<Person> readAllPerson() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        ArrayList<Person> people = new ArrayList<>();

//        List list = session.createSQLQuery("SELECT * FROM my_datebase.person;").list();
//        for(Object object:list){
//            Object[] objectArray = (Object[]) object;
//            long id = Long.parseLong(objectArray[0].toString());
//            String name  = objectArray[1].toString();
//            int age = Integer.parseInt(objectArray[2].toString());
//            people.add(new Person(id,name,age));
//        }

        try {
            people = (ArrayList<Person>) session.createQuery("FROM Person").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return people;
    }

    @Override
    public void updatePerson(Person personUpdated) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(personUpdated);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deletePersonById(long id) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Person person = readPersonById(id);
            session.delete(person);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

    }
}
