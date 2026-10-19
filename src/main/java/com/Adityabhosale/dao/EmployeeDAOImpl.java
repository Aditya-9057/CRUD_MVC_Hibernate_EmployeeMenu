package com.Adityabhosale.dao;

import com.Adityabhosale.model.Employee;
import com.Adityabhosale.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public int insertEmployee(Employee employee) throws DAOException {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.persist(employee);
            tx.commit();
            return 1;
        }catch(ConstraintViolationException cve){
            if (tx != null){
                tx.rollback();

            }
//            System.err.println("⚠️⚠️⚠️ Employee Already Exists:"+cve.getMessage());
            throw new DAOException("⚠️⚠️⚠️ Employee Already Exists:"+cve.getMessage(),cve);


        }catch(HibernateException he){
            if(tx != null){
                tx.rollback();
            }
//            System.err.println("⚠️⚠️⚠️ Error in HibernateUtil in insertEmployee: " + he.getMessage());
            throw new DAOException("⚠️⚠️⚠️ Error During Insertion:"+he.getMessage(),he);
        }

    }

    @Override
    public int deleteEmployee(int id) throws DAOException {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Employee employee = session.find(Employee.class, id);
            tx = session.beginTransaction();
            if(employee != null){
                session.remove(employee);
                tx.commit();
                return 1;
            }else {
                System.out.println("⚠️⚠️⚠️ Employee Not found.");
                return 0;
            }

        }catch(HibernateException he){
//            System.err.println("⚠️⚠️⚠️ Error during delete! ");
            throw new DAOException("⚠️⚠️⚠️ Error During deletion:"+he.getMessage(),he);

        }

    }
    @Override
    public int updateEmployee(Employee employee) throws DAOException {
        Transaction tx = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            tx = session.beginTransaction();
            session.merge(employee);
            tx.commit();
            return 1;

        }catch(HibernateException he){
            if(tx != null){
                tx.rollback();
            }
//            System.err.println("⚠️⚠️⚠️ Error in HibernateUtil in UpdateEmployee: " + he.getMessage());
            throw new DAOException("⚠️⚠️⚠️ Error During Updation:"+he.getMessage(),he);
        }

    }
    @Override
    public Employee getEmployeeById(int id) throws DAOException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.find(Employee.class, id);

        }catch(HibernateException he){
//            System.err.println("⚠️⚠️⚠️ Error During Searching Employee!");
            throw new DAOException("⚠️⚠️⚠️ Error During searching:"+he.getMessage(),he);
        }

    }

    @Override
    public List<Employee> getAllEmployees() throws DAOException {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Employee",Employee.class).getResultList();

        }catch (HibernateException he){
            System.err.println("⚠️⚠️⚠️ Error During View Employee!");
            throw new DAOException("⚠️⚠️⚠️ Error During Viewing:"+he.getMessage(),he);
        }

    }
}
