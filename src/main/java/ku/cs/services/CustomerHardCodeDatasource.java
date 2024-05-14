package ku.cs.services;

import ku.cs.models.CustomerList;

public class CustomerHardCodeDatasource {
    public CustomerList readData(){
        CustomerList list = new CustomerList();
        list.addNewCustomer("6410400001", "First", "1");
        list.addNewCustomer("6410400002", "Second", "2");
        list.addNewCustomer("6410400003", "Third", "3");
        list.addNewCustomer("6410400004", "Fourth", "4");
        return list;
    }
}
