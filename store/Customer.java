package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Customer
{
    private String name;
    private String email;

    public Customer(String name, String email)
    {
        this.name = name;
        this.email= email;
        //check for @ and .
        int atIndex = email.indexOf('@', 0);
        int atChecker = (atIndex >= 0) ? email.indexOf('.', atIndex) : -1;
        if(atChecker == -1)
            throw new IllegalArgumentException("Invalid email address: " + email);
    }
 
    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + '\n');
        bw.write(email + '\n');
    }

    public Customer(BufferedReader br) throws IOException
    {
        name = br.readLine();
        email = br.readLine();
    }

    public String toString()
    {
        return name + "(" + email + ")";
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

        Customer daCustomer = (Customer) o;
        return email.equals(daCustomer.email);
    }
}