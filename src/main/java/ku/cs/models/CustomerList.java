package ku.cs.models;

import ku.cs.services.exception.AuthenticationFailedException;

import java.util.ArrayList;

public class CustomerList {
    private ArrayList<Customer> customers;

    public CustomerList(){
        customers = new ArrayList<>();
    }

    public void addNewCustomer(String id, String name, String password){
        id = id.trim();
        name = name.trim();
        if(!id.equals("") && !name.equals("")){
            Customer exist = findCustomerById(id);
            if(exist == null){
                customers.add(new Customer(id.trim(), name.trim(), password.trim()));
            }
        }
    }

    public void addNewCustomer(String id, String name, String password,double balance){
        id = id.trim();
        name = name.trim();
        if(!id.equals("") && !name.equals("")){
            Customer exist = findCustomerById(id);
            if(exist == null){
                customers.add(new Customer(id, name, password, balance));
            }
        }
    }

    public Customer authenForLogin(String username, String password){
        for(Customer customer: customers){
            if(customer.check(username, password)){
                return customer;
            }
        }
        throw new AuthenticationFailedException("Invalid username or password!");
    }

    public Customer authen(String username){
        for(Customer customer: customers){
            if(customer.check(username)){
                return customer;
            }
        }
        throw new AuthenticationFailedException("Invalid username or password!");
    }

    public Customer findCustomerById(String id){
        for(Customer customer: customers){
            if(customer.isId(id)){
                return customer;
            }
        }
        return null;
    }

    public void giveBalanceToId(String id, double balance) {
        Customer exist = findCustomerById(id);
        if (exist != null) {
            exist.addBalance(balance);
        }
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean isExists(String username, String password){
        for(Customer customer: customers){
            if(customer.check(username,password)){
                return true;
            }
        }
        return false;
    }


    public void reduceBalanceFromUsername(String username, double amount) {
        Customer exist = findCustomerByUsername(username); // ค้นหาลูกค้าจาก username
        if (exist != null) {
            double currentBalance = exist.getBalance();
            if (currentBalance >= amount) { // ตรวจสอบว่ามียอดเงินเพียงพอสำหรับการลดหรือไม่
                exist.reduceBalance(amount); // ลดจำนวนเงินออกจากบัญชี
            } else {
                // หากยอดเงินไม่เพียงพอ
                throw new RuntimeException("Insufficient balance for this transaction.");
            }
        }
    }


    public Customer findCustomerByUsername(String username){
        for(Customer customer: customers){
            if(customer.isUsername(username)){
                return customer;
            }
        }
        return null;
    }

}
