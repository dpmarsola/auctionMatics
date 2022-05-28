import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestHibernate {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //Add new Employee object
        Employee emp = new Employee();
        emp.setEmail("demo-user@mail.com");
        emp.setFirstName("demo");
        emp.setLastName("user");

        session.save(emp);
        session.getTransaction().commit();

/*
        EntityManager em = session.getEntityManagerFactory().createEntityManager();
        System.out.println(em.createQuery("SELECT * FROM Employee").getResultList());
*/

        Query q = session.createQuery("from Employee");
        List<Employee> list = q.getResultList();

        for (Employee e : list ) {
            System.out.println("First Name: " + e.getFirstName());
            System.out.println("Last Name: " + e.getLastName());
            System.out.println("Email: " + e.getEmail());
        }

        HibernateUtil.shutdown();
    }
}