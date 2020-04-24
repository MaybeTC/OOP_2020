package ie.tudublin;

import processing.core.PApplet;
import java.util.ArrayList;
import processing.data.Table;
import processing.data.TableRow;


public class Gantt extends PApplet
{	
	ArrayList<Task> tasks = new ArrayList<Task>();
	float x;
	float y;
	float z;
	public void settings()
	{
		size(800, 600);
		x=width;
		y=height;
		z=height/30;
		//z = 20, the distance between each line is z
	}

	public void loadTasks()
	{
		//load data from tasks.csv
		Table table = loadTable("tasks.csv","header");

		for(TableRow row : table.rows())
		{
			Task t = new Task(row);
			tasks.add(t);
		}
	}

	public void printTasks()
	{
		for(Task t:tasks)
        {
			//Prints the elements of the ArrayList
			System.out.println(t);
        }
	}

	float xline;
	float yline;
	float xrect;
	float yrect;
	float hrect;
	float wrect;
	float[] rectx = new float[10];
	float[] recty = new float[10];
	float[] rectw = new float[10];
	float[] recth = new float[10];
	/*int c = 0;
	int color[][] = new int[30][10];*/

	void display()
	{

		/*//Draw rectangle with changeable color
		for(int i=0; i<30; i++)
		{
			for(int j=0; j<tasks.size(); j++)
			{
				noStroke();
				color[i][j]=c;
				fill(color[i][j],0,0);
				rect(xline+i*z, yline-15+j*45, z, 45);
			}
		}*/
		

		//Draw the chart
		for(int i=0; i < 30; i++)
		{
			//Draw the lines
			fill(0, 0, 100);
			stroke(0, 0, 100);
			xline=x*.2f;
			yline=y*.1f;
			line(xline+z*i, 30, xline+z*i, 570);
			//Display top text 1 to 30
			textAlign(CENTER,CENTER);
			fill(0, 0, 100);
			text(i+1,xline+z*i, 15);
		}

		


		//Display right text and rectangles		
		for(int j = 0; j < tasks.size(); j++)
		{
			Task t = tasks.get(j);
			//Diplay right text
			textAlign(CENTER,CENTER);
			fill(0, 0, 100);
			text(t.getName(), xline/2, yline + j*45);
			//Draw colorific rectangles
			noStroke();
			colorMode(HSB,100);
			fill(j*10,100,100);
			xrect=map((t.getStart()-1),0,30,xline, xline+30*z);
			yrect=yline-15+j*45;
			hrect=30;
			wrect=map(t.getEnd()-1,0,30,xline,xline+30*z)-xrect;
			//stores values of colorific rectangles
			rectx[j]=xrect;
			recty[j]=yrect;
			rectw[j]=wrect;
			recth[j]=hrect;
			rect(rectx[j], recty[j], rectw[j], recth[j]);

			

			//mousePressed
			//if loop to set all rectangles
			if((mouseY > recty[j] && mouseY < recty[j] + recth[j]))
			{
				if((mouseX > rectx[j] - z*2/3 && mouseX < rectx[j] + rectw[j]))
				{
					//mouseDragged
					if((mouseX > rectx[j] && mouseX <= rectx[j] + rectw[j] - z/3))
					{
						println("Mouse pressed"+j);
						
					}
					else if((mouseX > rectx[j] + rectw[j] - z/3))
					{	
						if(mouseX < xline + 29*z)//longering cannot exceed the rightmost line.
						{
							println("Mouse Dragged "+j+" up");
							/*Rectangle becomes longer
							rectw[j]=rectw[j]+z;*/
							
						}
					}
					else if((mouseX < rectx[j] + rectw[j] - z*2/3))
					{
						if(mouseX > xline + z)//Shortening cannot exceed the leftmost line.
						{
							println("Mouse Dragged "+j+" down");
							/*Rectangle becomes shorter
							rectx[j]=rectx[j]-z;*/
							
						}
					}
				}//mouseX
			}//mouseY
		}
	}
	

	public void mousePressed()
	{
		
	}

	public void mouseDragged()
	{
		
	}

	
	
	public void setup() 
	{
		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		background(0);
		display();//Call the fuction to display the chart and text
	}
}
