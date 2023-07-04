import java.util.ArrayList;

record Customer(String name, ArrayList<Double> transaction){

    public Customer(String name, double initialDeposit){
        this(name.toUpperCase(), new ArrayList<Double>(500));
        transaction.add(initialDeposit);
    }
}
public class Main {

    public static void main(String[] args) {

        Customer bob = new Customer("Bob S", 1000.0);
        System.out.println(bob);

        Bank bank = new Bank("Bank of America");
     
        bank.addNewCustomer("Hayate S", 500.0);

        bank.addTransaction("Hayate S", -10.25);
        bank.addTransaction("Hayate S", -75.01);
        bank.addTransaction("Hayate S", 100.0);
        bank.printStatement("Hayate S");
    }
}

class Bank {

    private String name;
    private ArrayList<Customer> customers = new ArrayList<>(5000);

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }

    private Customer getCustomer(String name) {
        for (var c : customers) {
            if (c.name().equalsIgnoreCase(name)) {
                return c;
            }
        }
        System.out.printf("Customer (%s) wasn't found %n", name);
        return null;
    }

    public void addNewCustomer(String name, double initialDeposit){

        if(getCustomer(name) == null){
            Customer customer = new Customer(name, initialDeposit);
            customers.add(customer);
            System.out.println("new Customer added " + customer);
        }
    }

    public void addTransaction(String name, double transactionAmount){

        Customer customer = getCustomer(name);
        if(customer != null){
            customer.transaction().add(transactionAmount);
        }
    }

    public void printStatement(String name){
        Customer customer = getCustomer(name);
        if(customer == null){
            return;
        }

        System.out.println("-".repeat(30));
        System.out.println("Customer Name: " + customer.name());
        System.out.println("Transactions");
        for(double d: customer.transaction()){ //unboxing part
            System.out.printf("$%10.2f (%s)%n", d, d < 0 ? "debit": "credit");

        }
    }
}