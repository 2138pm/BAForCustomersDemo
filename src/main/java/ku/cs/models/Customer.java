package ku.cs.models;

public class Customer {
    private String id;
    private String username;
    private String password;
    private double balance;

    public Customer(String id,String name, String password){
        this.id = id;
        this.username = name;
        this.password = password;

        balance = 0;
    }

    public Customer(String id, String name, String password ,double balance){
        this.id = id;
        this.username = name;
        this.password = password;
        this.balance = balance;
    }

    public boolean check(String username, String password){
        return this.username.equals(username) || this.password.equals(password);
    }

    public boolean check(String username){
        return this.username.equals(username);
    }




    public void changeName(String name) {
        if (!name.trim().equals("")) {
            this.username = name.trim();
        }
    }

    public void addBalance(double balance) {
        if (balance > 0) {
            this.balance += balance;
        }
    }

    public void reduceBalance(double balance){
        if (balance > 0){
            this.balance -= balance;
        }
    }

    // TODO: design grading system for Student
//    public String grade()
//    {
//        return "F";
//    }

    public boolean isId(String id) {
        return this.id.equals(id);
    }

    public boolean isUsername(String username){ return this.username.equals(username);}

    public String getId() {
        return id;
    }

    public String getName() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public String toCsv(){
        return id + "," + username + "," + password + "," + balance ;
    }

    @Override
    public String toString() {
        return "{" +
                "id: '" + id + '\'' +
                ", name: '" + username + '\'' +
                ", balance: " + balance +
                '}';
    }

}
