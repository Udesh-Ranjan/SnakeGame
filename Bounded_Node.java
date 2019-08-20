/*	  
*
*		Dev :	devparzival404@gmail.com
*		-------------------------------@~
*		Code:	Snake Game
*		03 AUG 2019
*   	5:24 PM
*		May This Command Live Long...
*
*	
*/
import java.awt.*;
import java.awt.event.*;

public class Bounded_Node
{
	
	static int length=20;
	int dir;			//0=up,1=down,2=left,3=right
	
	//Cuurent Size
	int curr_startx;
	int curr_starty;
	int curr_endx;
	int curr_endy;
	
	//Previous Size
	int prev_startx;
	int prev_starty;
	int prev_endx;
	int prev_endy;
	
	static Bounded_GameEnviron game;
	//Link to next node
	Bounded_Node next;
	
	
	public Bounded_Node()
	{
		next=null;
	}
	public Bounded_Node(int sx,int sy)
	{
		curr_startx=sx;
		curr_starty=sy;
		
		curr_endy=curr_starty;
		curr_endx=curr_startx-length;	
		dir=3;
		next=null;
		
		prev_startx=sx;
		prev_starty=sy;
		prev_endx=curr_endx;
		prev_endy=curr_endy;
	}
	public Bounded_Node(int sx,int sy,int ex,int ey)
	{
		curr_startx=prev_startx=sx;
		curr_starty=prev_starty=sy;
		curr_endx=prev_endx=ex;
		curr_endy=prev_endy=ey;
		dir=0;
		next=null;
	}
	public Bounded_Node(int sx,int sy,int ex,int ey,int dir)
	{
		curr_startx=prev_startx=sx;
		curr_starty=prev_starty=sy;
		curr_endx=prev_endx=ex;
		curr_endy=prev_endy=ey;
		this.dir=dir;
		next=null;
	}
	
	//_-_-_-Head Reference Should Be Passed-_-_-@~//
	public static void move(Bounded_Node n)
	{
		n.prev_startx=n.curr_startx;
		n.prev_starty=n.curr_starty;
		n.prev_endx=n.curr_endx;
		n.prev_endy=n.curr_endy;
		int prev_dir=n.dir;
		if(n.dir==0)
		{
			if(game.left)
			{
				n.curr_endx=n.curr_startx;
				n.curr_endy=n.curr_starty;
				
				n.curr_startx-=length;
				n.dir=2;
			}
			else
			if(game.right)
			{
				n.curr_endx=n.curr_startx;
				n.curr_endy=n.curr_starty;
				
				n.curr_startx+=length;
				n.dir=3;
			}
			else
			{
				n.curr_starty-=length;
				n.curr_endy-=length;
			}
		}
		else
		if(n.dir==1)
		{
			if(game.left)
			{
				n.curr_endx=n.curr_startx;
				n.curr_endy=n.curr_starty;
				
				n.curr_startx-=length;
				n.dir=2;
			}
			else
			if(game.right)
			{
				n.curr_endx=n.curr_startx;
				n.curr_endy=n.curr_starty;
				
				n.curr_startx+=length;
				n.dir=3;
			}
			else
			{
				n.curr_starty+=length;
				n.curr_endy+=length;
			}
		}
		else
		if(n.dir==2)
		{
			if(game.up)
			{
				n.curr_endx=n.curr_startx;
				n.curr_endy=n.curr_starty;
				
				n.curr_starty-=length;
				n.dir=0;
			}
			else
			if(game.down)
			{
				n.curr_endx=n.curr_startx;
				n.curr_endy=n.curr_starty;
				
				n.curr_starty+=length;
				n.dir=1;
			}
			else
			{
				n.curr_endx=n.curr_startx;
				n.curr_endy=n.curr_starty;
				
				n.curr_startx-=length;
			}
			
		}
		else
		if(n.dir==3)
		{
			if(game.up)
			{
				n.curr_endx=n.curr_startx;
				n.curr_endy=n.curr_starty;
				
				n.curr_starty-=length;
				n.dir=0;
			}
			else
			if(game.down)
			{
				n.curr_endx=n.curr_startx;
				n.curr_endy=n.curr_starty;
				
				n.curr_starty+=length;
				n.dir=1;
			}
			else
			{
				n.curr_endx=n.curr_startx;
				n.curr_endy=n.curr_starty;
				
				n.curr_startx+=length;
			}

		}
		
		game.gfx.setRenderingHints(game.antiAlias_OFF);
		game.gfx.setStroke(game.wideStroke);
		game.gfx.setColor(Color.green);
		game.gfx.drawLine(n.curr_startx,n.curr_starty,n.curr_endx,n.curr_endy);
		game.gfx.setColor(game.front);
		drawNext(n.next,n.prev_startx,n.prev_starty,n.prev_endx,n.prev_endy,prev_dir);
	}
	private static void drawNext(Bounded_Node n,int startx,int starty,int endx,int endy,int dir)
	{
		if(n==null)
		{
			return;
		}
		
		int sx=n.prev_startx;
		int sy=n.prev_starty;
		int ex=n.prev_endx;
		int ey=n.prev_endy;
		
		int prev_dir=n.dir;
		n.dir=dir;
		
		n.prev_startx=n.curr_startx;
		n.prev_starty=n.curr_starty;
		n.prev_endx=n.curr_endx;
		n.prev_endy=n.curr_endy;
		
		n.curr_startx=startx;
		n.curr_starty=starty;
		n.curr_endx=endx;
		n.curr_endy=endy;
		
		game.gfx.drawLine(n.curr_startx,n.curr_starty,n.curr_endx,n.curr_endy);
		
		drawNext(n.next,n.prev_startx,n.prev_starty,n.prev_endx,n.prev_endy,prev_dir);
	}
	////////For Tail///////
	public static void increment(Bounded_Node n)
	{
		if(n.next==null)
		{
			Bounded_Node link=new Bounded_Node(n.prev_startx,n.prev_starty,n.prev_endx,n.prev_endy,n.dir);
			n.next=link;
			return;
		}
		increment(n.next);
	}
	public static void drawSnake(Bounded_Node n)
	{
		if(n==null)
			return;
		
		game.gfx.setStroke(game.wideStroke);
		game.gfx.setRenderingHints(game.antiAlias_OFF);
		game.gfx.setColor(Color.green);
		game.gfx.drawLine(n.curr_startx,n.curr_starty,n.curr_endx,n.curr_endy);
		game.gfx.setColor(game.front);
		drawBody(n.next);
	}
	
	private static void drawBody(Bounded_Node n)
	{
		if(n==null)
			return;
		game.gfx.drawLine(n.curr_startx,n.curr_starty,n.curr_endx,n.curr_endy);
		drawBody(n.next);
	}
	public static void deleteSnake(Bounded_Node n)
	{
		if(n==null)
			return;
		
		deleteSnake(n.next);
		
		n.next=null;
	}
	//SnakeCrash
	public static boolean snakeCrash(Bounded_Node head,Bounded_Node body)
	{
		if(head==null||body==null)
			return false;
		
		if(head.dir==0)
		{
			if(body.dir==0)
			{
				if(head.curr_startx>=body.curr_startx-game.half_stroke&&head.curr_startx<=body.curr_startx+game.half_stroke)
					if(head.curr_starty<=body.curr_endy&&head.curr_starty>=body.curr_starty)
						return true;
			}
			if(body.dir==1)
			{
				if(head.curr_startx>=body.curr_startx-game.half_stroke&&head.curr_startx<=body.curr_startx+game.half_stroke)
				if(head.curr_starty<=body.curr_starty&&head.curr_starty>=body.curr_endy)
					return true;
			}
			if(body.dir==2)
			{
				if(head.curr_startx>=body.curr_startx&&head.curr_startx<=body.curr_endx)
					if(head.curr_starty<=body.curr_starty+game.half_stroke&&head.curr_starty>=body.curr_starty-game.half_stroke)
						return true;
			}
			if(body.dir==3)
			{
				if(head.curr_startx<=body.curr_startx&&head.curr_startx>=body.curr_endx)
					if(head.curr_starty<=body.curr_starty+game.half_stroke&&head.curr_starty>=body.curr_starty-game.half_stroke)
						return true;
			}
		}
		if(head.dir==1)
		{
			
			if(body.dir==0)
			{
				if(head.curr_starty>=body.curr_starty&&head.curr_starty<=body.curr_starty)
					if(head.curr_startx>=body.curr_startx-game.half_stroke&&head.curr_startx<=body.curr_startx+game.half_stroke)
				return true;
			}
			if(body.dir==1)
			{
				if(head.curr_starty>=body.curr_endy&&head.curr_starty<=body.curr_starty)
					if(head.curr_startx>=body.curr_startx-game.half_stroke&&head.curr_startx<=body.curr_startx+game.half_stroke)
				return true;
			}
			if(body.dir==2)
			{
				if(head.curr_startx>=body.curr_startx&&head.curr_startx<=body.curr_endx)
					if(head.curr_starty>=body.curr_starty-game.half_stroke&&head.curr_starty<=body.curr_starty+game.half_stroke)
						return true;
				
			}
			if(body.dir==3)
			{
				if(head.curr_startx<=body.curr_startx&&head.curr_startx>=body.curr_endx)
					if(head.curr_starty>=body.curr_starty-game.half_stroke&&head.curr_starty<=body.curr_starty+game.half_stroke)
						return true;
			}
			
		}
		if(head.dir==2)
		{
			if(body.dir==0)
			{
				if(head.curr_starty>=body.curr_starty&&head.curr_starty<=body.curr_endy)
					if(head.curr_startx>=body.curr_startx-game.half_stroke&&head.curr_startx<=body.curr_startx+game.half_stroke)
						return true;
			}
			if(body.dir==1)
			{
				if(head.curr_starty<=body.curr_starty&&head.curr_starty>=body.curr_endy)
					if(head.curr_startx>=body.curr_startx-game.half_stroke&&head.curr_startx<=body.curr_startx+game.half_stroke)
						return true;
			}
			
		
			if(body.dir==2)
			{
				if(head.curr_startx<=body.curr_endx&&head.curr_startx>=body.curr_startx)
					if(head.curr_starty>=body.curr_starty-game.half_stroke&&head.curr_starty<=body.curr_starty+game.half_stroke)
						return true;
			}
			if(body.dir==3)
			{
				if(head.curr_startx>=body.curr_endx&&head.curr_startx<=body.curr_startx)
					if(head.curr_starty>=body.curr_starty-game.half_stroke&&head.curr_starty<=body.curr_starty+game.half_stroke)
						return true;
			}
		}
		if(head.dir==3)
		{
			if(body.dir==0)
			{
				if(head.curr_starty>=body.curr_starty&&head.curr_starty<=body.curr_endy)
					if(head.curr_startx>=body.curr_startx-game.half_stroke&&head.curr_startx<=body.curr_startx+game.half_stroke)
						return true;
				
			}
			if(body.dir==1)
			{
				if(head.curr_starty<=body.curr_starty&&head.curr_starty>=body.curr_endy)
					if(head.curr_startx>=body.curr_startx-game.half_stroke&&head.curr_startx<=body.curr_startx+game.half_stroke)
						return true;
			}
			if(body.dir==2)
			{
				if(head.curr_startx<=body.curr_endx&&head.curr_startx>=body.curr_startx)
					if(head.curr_starty>=body.curr_starty-game.half_stroke&&head.curr_starty<=body.curr_starty+game.half_stroke)
						return true;
			}
			if(body.dir==3)
			{
				if(head.curr_startx>=body.curr_endx&&head.curr_startx<=body.curr_startx)
					if(head.curr_starty>=body.curr_starty-game.half_stroke&&head.curr_starty<=body.curr_starty+game.half_stroke)
						return true;
			}
		}
		return snakeCrash(head,body.next);
	}
	public static boolean boundaryCrash(Bounded_Node head)
	{
		if(head.curr_startx<=game.bound_width)
			return true;
		
		if(head.curr_startx>=game.width-game.bound_width)
			return true;
		
		if(head.curr_starty<=game.bound_height+30)
			return true;
		
		if(head.curr_starty>=game.height-game.bound_height)
			return true;
		
		return false;
	}
	public static void clear()
	{
		game.clear();
	}
}