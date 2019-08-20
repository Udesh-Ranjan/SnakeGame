import java.awt.*;
import java.util.Random;

public class Railing_Loader implements Runnable
{
	public  Random rand;
	public Railing_GameEnviron game;
	public Railing_Node snake;
	public Railing_Ball b;
	private int incr=10;
	public int score=0;
	public Frame1 f;
	
	long millisec=0;
	long hour=0;
	long minute=0;
	long second=0;
	
	
	public Railing_Loader(Frame1 f)
	{
		this.f=f;
		snake=new Railing_Node(200,200);
		game=new Railing_GameEnviron(f);
		snake.game=game;
		b=new Railing_Ball(400,400,12,game);
		rand=new Random();
	}
	
	public void run()
	{
		for(;true&&!game.game_over;)
		{
			if(!game.pause)
			{
				
				Railing_Node.move(snake);
				b.drawBall();
				score();
				game.makeBoundary(game.bound_width,game.bound_height);
				game.g2d.drawImage(game.img,0,0,game);
				try
				{
					Thread.sleep(90);
					millisec+=90;
					if(millisec>=999)
					{
						second++;
						millisec=millisec%999;
						
						if(second>=59)
						{
							minute++;
							second=second%59;
							if(minute>=59)
							{
								hour++;
								minute=minute%59;
							}
						}
					}
				}
				catch(Exception ex){}
				
				//Snake hit the ball
				if(ballHit())
				{
					score=score+incr;
					incr+=10;
					Railing_Node.increment(snake);
					boolean flag=false;
					do
					{
						flag=false;
						b.x=rand.nextInt(game.width-b.radius);
					
						if(b.x<b.radius)
							b.x+=b.diameter;
					
						b.y=rand.nextInt(game.height-b.radius);
					
						if(b.y<b.radius+40)
							b.y+=b.diameter+40;
						
						//Ball is Inside Boundary
						if(b.x+b.radius>=game.corn1_startx&&b.x-b.radius<=game.corn2_startx+game.bound_width)
							if(b.y+b.radius>=game.corn1_starty-b.diameter&&b.y-b.radius<=game.corn1_starty+game.bound_height+b.diameter)
								flag=true;
							
						if(b.x+b.radius>=game.corn4_startx&&b.x-b.radius<=game.corn3_startx+game.bound_width)
							if(b.y+b.radius>=game.corn4_starty-b.diameter&&b.y-b.radius<=game.corn4_starty+game.bound_height+b.diameter)
								flag=true;
							
							if(flag)
								System.out.println("Detected");
					}
					while(flag);
					
					System.out.println(b.x+" "+b.y);
				}
				
				if(Railing_Node.snakeCrash(snake,snake.next)||boundaryCrash())
				{
					game.game_over=true;
				}
				game.clear();	
			}
			else
			{
				Railing_Node.drawSnake(snake);
				b.drawBall();
				score();
				game.makeBoundary(game.bound_width,game.bound_height);
				game.g2d.drawImage(game.img,0,0,game);
				try
				{
					Thread.sleep(200);
				}
				catch(Exception ex){}
				game.clear();
			}
		}
		snake.deleteSnake(snake);
		b=null;
		snake=null;
		game.game_over=true;
		
		
		Anony player=new Anony("",score,hour,minute,second);
		
		int rnk=DetRnk.addPlayer(player,"Rail_Log.txt");
		
		
		game.point=score;
		game.setSize(700,700);
		game.setResizable(false);
		game.repaint();
	}
	//Ball Hit
	public boolean ballHit()
	{
		/*
		System.out.println("snake.curr_startx : "+snake.curr_startx +" snake.curr_starty : "+snake.curr_starty);
		System.out.println("b.x : "+b.x+" b.y : "+b.y);
		System.out.println("b.radius "+b.radius);
		System.out.println("b.diameter "+b.diameter);
		*/
		
		//Approacing from Left
		if(snake.curr_startx>=b.x-b.radius&&snake.curr_startx<=b.x+b.radius)
			if(snake.curr_starty>=b.y-b.radius&&snake.curr_starty<=b.y+b.radius)
				return true;
		
		//Approaching from Right
		if(snake.curr_startx<=b.x+b.radius&&snake.curr_startx>=b.x-b.radius)
			if(snake.curr_starty>=b.y-b.radius&&snake.curr_starty<=b.y+b.radius)
				return true;
		
		//Approaching from Top
		if(snake.curr_starty>=b.y-b.radius&&snake.curr_starty<=b.y+b.radius)
			if(snake.curr_startx>=b.x-b.radius&&snake.curr_startx<=b.x+b.radius)
				return true;
		
		//Approaching from bottom
		if(snake.curr_starty<=b.y+b.radius&&snake.curr_starty>=b.y-b.radius)
			if(snake.curr_startx>=b.x-b.radius&&snake.curr_startx<=b.x+b.radius)
				return true;
		
		return false;
	}
	//Boundary Hit
	public boolean boundaryCrash()
	{
		//For upper
		if(snake.dir==0)
		{
			if(snake.curr_starty-5<=game.corn1_starty+game.bound_height&&snake.curr_starty+5>=game.corn1_starty)
				if(snake.curr_startx>=game.corn1_startx-5&&snake.curr_startx<=game.corn2_startx+5)
					return true;
		}
		if(snake.dir==1)
		{
			if(snake.curr_starty+5>=game.corn1_starty&&snake.curr_starty<=game.corn1_starty+game.bound_height)
				if(snake.curr_startx>=game.corn1_startx-5&&snake.curr_startx<=game.corn2_startx+5)
					return true;
		}
		if(snake.dir==2)
		{
			if(snake.curr_starty>=game.corn1_starty-5&&snake.curr_starty<=game.corn1_starty+game.bound_height+5)
				if(snake.curr_startx>=game.corn1_startx-5&&snake.curr_startx<=game.corn2_startx+5)
					return true;
			
		}
		if(snake.dir==3)
		{
			if(snake.curr_starty>=game.corn1_starty-5&&snake.curr_starty<=game.corn1_starty+game.bound_height+5)
				if(snake.curr_startx>=game.corn1_startx-5&&snake.curr_startx<=game.corn2_startx+5)
					return true;
			
		}
		//for Lower
		if(snake.dir==0)
		{
			if(snake.curr_starty-5<=game.corn4_starty+game.bound_height&&snake.curr_starty+5>=game.corn4_starty)
				if(snake.curr_startx>=game.corn4_startx-5&&snake.curr_startx<=game.corn3_startx+5)
					return true;
		}
		if(snake.dir==1)
		{
			if(snake.curr_starty+5>=game.corn4_starty&&snake.curr_starty<=game.corn4_starty+game.bound_height)
				if(snake.curr_startx>=game.corn4_startx-5&&snake.curr_startx<=game.corn3_startx+5)
					return true;
		}
		if(snake.dir==2)
		{
			if(snake.curr_starty>=game.corn4_starty-5&&snake.curr_starty<=game.corn4_starty+game.bound_height+5)
				if(snake.curr_startx>=game.corn4_startx-5&&snake.curr_startx<=game.corn3_startx+5)
					return true;	
		}
		if(snake.dir==3)
		{
			if(snake.curr_starty>=game.corn4_starty-5&&snake.curr_starty<=game.corn4_starty+game.bound_height+5)
				if(snake.curr_startx>=game.corn4_startx-5&&snake.curr_startx<=game.corn3_startx+5)
					return true;
			
		}
		
		return false;
	}
	public void score()
	{
		game.gfx.setRenderingHints(game.antiAlias_TEXT_ON);
		String _strscore="Score : ";
		
		int _strlength=game._fontmetrics.stringWidth(_strscore);
		int _intlength=game._fontmetrics.stringWidth(Integer.toString(score));
		int height=game._fontmetrics.getHeight();
		
		game.gfx.setColor(game._orange);
		game.gfx.drawString(_strscore,game.width-_strlength-_intlength-20,30+height);
		
		game.gfx.setColor(game._red);
		game.gfx.drawString(Integer.toString(score),game.width-_intlength-20,30+height);
	}
}