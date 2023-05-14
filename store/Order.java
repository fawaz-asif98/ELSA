package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Order 
{
    private static long nextOrderNumber = 0;
    private long orderNumber;

    private Customer customer;
    public ArrayList<Computer>computers = new ArrayList<>();

    public Order(Customer customer)
    {
        this.customer = customer;
        orderNumber = orderNumber++;
    }

    public Order(BufferedReader br) throws IOException
    {
        customer = new Customer(br);
        int size = Integer.parseInt(br.readLine());
        while(size --> 0)
        {
            computers.add(new Computer(br));
        }
    }

    public Order(BufferedWriter bw) throws IOException
    {
        customer.save(bw);
        bw.write("" + computers.size() + '\n');
        for(Computer computer : computers)
        {
            computer.save(bw);
        }
    }

    public void addComputer(Computer computer)
    {
        computers.add(computer);
    }

    public long cost()
    {
        long total = 0;
        for(int i = 0; i < computers.size(); ++i)
        {
            total += computers.get(i).cost();
        }
        return total;
    }

    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }

        if( (o == null) || getClass() != o.getClass())
        {
            return false;
        }

        Order daOrder = (Order) o;
        return customer.equals(daOrder.customer) && computers.equals(daOrder.computers);
    }

    public String toString()
    {
        return "Order " + orderNumber + "for " + customer + " ($" + cost() + ")";
    }
}