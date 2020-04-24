package ie.tudublin;

import processing.data.TableRow;

public class Task
{
    private String Task;
    private float Start;
    private float End;
    
    public Task(String Task,float Start,float End)
    {
        this.Task = Task;
        this.Start = Start;
        this.End = End;
    }

    public Task(TableRow tr)
    {
        this(tr.getString("Task"), tr.getFloat("Start"),tr.getFloat("End"));
    }

    public String getName()
    {
        return Task;
    }

    public void setName(String Task)
    {
        this.Task = Task;
    }

    public float getStart()
    {
        return Start;
    }

    public void setStart(float Start)
    {
        this.Start = Start;
    }

    public float getEnd()
    {
        return End;
    }

    public void setEnd(float End)
    {
        this.End = End;
    }

    public String toString()
    {
        return "Tasks [Task=" + Task + ", Start=" + Start + ", End=" + End + "]";
    }

}