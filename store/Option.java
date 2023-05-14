package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Option 
{
    protected String name;
    protected long cost;
    
    public Option(String name, long cost)
    {
        this.name = name;
        this.cost = cost;
        if(cost < 0)
        {
            throw new IllegalArgumentException("Cost can't be less than zero!");
        }
    }
 
    public Option(BufferedReader br) throws IOException
    {
        name = br.readLine();
        cost = Long.parseLong(br.readLine());
    }

    public long cost()
    {
        return cost/100;
    }

    public String toString()
    {
        return name + "(" + cost + ")";
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

        Option daOption = (Option) o;
        return name.equals(daOption.name) && cost == (daOption.cost);
    }

    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + '\n');
        bw.write("" + cost + '\n');
    }
}