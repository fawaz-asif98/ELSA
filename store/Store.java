package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Store
{
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Option> options = new ArrayList<>();
    private ArrayList<Computer> computers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public Store(String name)
    {
        this.name = name;
    }

    public String name()
    {
        return this.name;
    }

    public Store(BufferedReader br) throws IOException
    {
        name = br.readLine();
    }

    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + '\n');
    }
    
    // ///////////////////////////////////////////////////////////
    // Customers
    
    public void add(Customer customer)
    {
        if(!customers.contains(customer))
        {
            customers.add(customer);
        }
    }
    public Object[] customers()
    {
        return this.customers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Options
    
    public void add(Option option)
    {
        if(!options.contains(option))
        {
            options.add(option);
        }
    }
    public Object[] options()
    {
        return this.options.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Computers
    
    public void add(Computer computer)
    {
        if(!computers.contains(computer))
        {
            computers.add(computer);
        }
    }
    public Object[] computers() 
    {
        return this.computers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Orders
    
    public void add(Order order) 
    {
        if(!orders.contains(order))
        {
            orders.add(order);
        }
    }
    public Object[] orders()
    {
        return this.orders.toArray();
    }
}