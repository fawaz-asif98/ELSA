package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Computer implements Saveable
{
    private String name;
    private String model;
    private ArrayList <Option> options = new ArrayList<>();

    @Override
    public int hashCode()
    {
        return Objects.hash(name, model);
    }

    public Computer(String name, String model)
    {
        this.name = name;
        this.model = model;
    }

    @Override
    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + '\n');
        bw.write("" + options.size() + '\n');
        for(Option option : options)
        {
            option.save(bw);
        }
        bw.write(model + '\n');
    }

    public Computer (BufferedReader br) throws IOException
    {
        name = br.readLine();
        model = br.readLine();
        int size = Integer.parseInt(br.readLine());
        while(size-- > 0)
        {
            options.add(new Option(br));
        }
    }

    public void addOption(Option option)
    {
        options.add(option);
    }

    public long cost()
    {
        long costSum = 0;
        for(Option addCoster : options)
        {
            costSum += addCoster.cost();
        }
        return costSum;
    }

    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }

        Computer daComputer = (Computer) o;
        return this.toString().equals(daComputer.toString());
        
    }

    @Override
    public String toString()
    {
        return name + "(" + model + ")";
    }
}
