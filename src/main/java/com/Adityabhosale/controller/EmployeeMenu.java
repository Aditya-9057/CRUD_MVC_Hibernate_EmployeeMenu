package com.Adityabhosale.controller;

import com.Adityabhosale.dao.DAOException;
import com.Adityabhosale.dao.EmployeeDAO;
import com.Adityabhosale.dao.EmployeeDAOImpl;
import com.Adityabhosale.model.Employee;
import com.Adityabhosale.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class EmployeeMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        EmployeeDAOImpl dao = new EmployeeDAOImpl();
        EmployeeDAO dao = new EmployeeDAOImpl();

        HibernateUtil.getSessionFactory();

        int choice;
        do{
            System.out.println("\n==== Employee Menu ====");
            System.out.println("\n1️⃣ Add Employee");
            System.out.println("\n2️⃣ Delete Employee");
            System.out.println("\n3️⃣ Update Employee");
            System.out.println("\n4️⃣ Search Employee Details");
            System.out.println("\n5️⃣ View Employee Details");
            System.out.println("\n6️⃣ Exit Program");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1: {
                        System.out.println("Enter Employee ID");
                        int id = sc.nextInt();
                        System.out.println("Enter Employee Name");
                        String name = sc.next();
                        System.out.println("Enter Employee Salary");
                        float salary = sc.nextFloat();

                        Employee employee = new Employee(id, name, salary);
                        int inserted = dao.insertEmployee(employee);
                        if(inserted > 0){
                            System.out.println("✅✅✅ Employee Inserted Successfully");
                        }
                    }break;
                    case 2: {
                        System.out.println("Enter Employee ID to be deleted: ");
                        int id = sc.nextInt();
                        int deleted = dao.deleteEmployee(id);
                        if(deleted > 0){
                            System.out.println("✅✅✅ Employee Deleted Successfully");
                        }
                    }break;
                    case 3: {
                        System.out.println("Enter Employee ID to be updated: ");
                        int id = sc.nextInt();

                        Employee employee = dao.getEmployeeById(id);
                        if(employee == null){
                            System.out.println("⚠️⚠️⚠️ Employee Not Found");
                        }else {
                            System.out.println("1️⃣: Update Name: ");
                            System.out.println("2️⃣ Update SALARY: ");
                            System.out.println("Enter your Choice: ");
                            int choice2 = sc.nextInt();

                            if(choice2 == 1){
                                System.out.println("Enter New NAME: ");
                                String name = sc.next();
                                employee.setName(name);
                            }else if(choice2 == 2){
                                System.out.println("Enter New SALARY: ");
                                float salary = sc.nextFloat();
                                employee.setSalary(salary);
                            }else
                                System.out.println("⚠️⚠️⚠️ Invalid Choice:");


                        }
                            int updated = dao.updateEmployee(employee);
                            if(updated > 0){
                                System.out.println("✅✅✅ Employee Updated Successfully");
                            }
                    }break;
                    case 4: {
                        System.out.println("Enter Employee ID to be searched: ");
                        int id = sc.nextInt();
                        Employee employee = dao.getEmployeeById(id);
                        if(employee != null){
                            System.out.println(employee);

                        }else {
                            System.out.println("⚠️⚠️⚠️ Employee not found");
                        }
                    }break;
                    case 5: {
                       List<Employee> employeeList = dao.getAllEmployees();
                       if(employeeList.isEmpty()){
                           System.out.println("⚠️⚠️⚠️ No Records Found:");
                       }else{
                           System.out.println("ID\t\tNAME\t\tSALARY");
                           for(Employee e:employeeList){
                               System.out.println(e);

//                               employeeList.forEach(System.out::println);
                           }
                       }

                    }break;
                    case 6: {
                        System.out.println("🙋‍♂️🙋‍♂️🙋‍♂️ exit");
                        HibernateUtil.shutdown();

                    }break;
                    default: {
                        System.out.println("⚠️⚠️⚠️ Invalid choice");
                    }
                }

            }catch(DAOException de){
                System.err.println("⚠️⚠️⚠️ DAO Error."+de.getMessage());

            }
            catch(Exception e){
                System.err.println("❌❌❌ Unexpected Error"+e.getMessage());
            }

        }while(choice !=6);


            sc.close();
    }

}
