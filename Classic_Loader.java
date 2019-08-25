import java.awt.*;
import java.util.Random;

public class Classic_Loader implements Runnable
{
	int level;
	public  Random rand;
	public Classic_GameEnviron game;
	public Classic_Node snake;
	public Classic_Ball b;
	public long score=0;
	public Frame1 f;
	
	long millisec=0;
	long hour=0;
	long minute=0;
	long second=0;
		
	public Classic_Loader(Frame1 f,int level)
	{
		this.level=level;
		this.f=f;
		game=new Classic_GameEnviron(f);
		snake=new Classic_Node(200,200);
		snake.game=game;
		b=new Classic_Ball(400,400,12,game);
		rand=new Random();
	}
	
	public void run()
	{
		for(;true&&!game.game_over;)
		{
			if(!game.pause)
			{		
				Classic_Node.move(snake);
				b.drawBall();
				score();
				game.g2d.drawImage(game.img,0,0,game);
				try
				{
					if(level==1)
					{
						Thread.sleep(100);
						millisec+=100;
					}
					else
					if(level==2)
					{
						Thread.sleep(80);
						millisec+=80;
					}
					else
					if(level==3)
					{
						Thread.sleep(60);
						millisec+=60;
					}
					else
					{
						Thread.sleep(36);
						millisec+=36;
					}
					
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
					if(level==1)
						score++;
					else
					if(level==2)
						score+=2;
					else
					if(level==3)
						score+=4;
					else
						score+=8;
					
					Classic_Node.increment(snake);
					
					b.x=b.diameter+10+rand.nextInt(game.width-b.diameter-20);
					b.y=b.diameter+40+rand.nextInt(game.height-b.diameter-50);
					
				
				}
				
				if(Classic_Node.snakeCrash(snake,snake.next))
				{
					game.game_over=true;
				}
					
			}
			else
			{
				Classic_Node.drawSnake(snake);
				b.drawBall();
				score();
				game.g2d.drawImage(game.img,0,0,game);
				try
				{
					Thread.sleep(200);
				}
				catch(Exception ex){}
				
			}
		}
		snake.deleteSnake(snake);
		b=null;
		snake=null;
		
		Anony player=new Anony("",score,hour,minute,second);
		
		
		int rnk=DetRnk.addPlayer(player,"Class_Log.txt");
		
		game.point=score;
		game.game_over=true;
		game.setSize(700,700);
		game.setBackground(Color.black);
		game.setResizable(false);
		game.gameOver();
	}
	
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
	public void score()
	{
		game.gfx.setRenderingHints(game.antiAlias_TEXT_ON);
		
		String display="Score : "+score;
		int dislength=game._fontmetrics.stringWidth(display);
		
		String str_score="Score : ";
		int str_score_length=game._fontmetrics.stringWidth(str_score);
		
		int score_length=game._fontmetrics.stringWidth(Long.toString(score));
		
		int startx=game.width-dislength-50;
		
		game.gfx.setColor(Color.yellow);
		game.gfx.drawString(str_score,startx,game._fontmetrics.getHeight()+50);
		game.gfx.setColor(Color.red);
		game.gfx.drawString(Long.toString(score),startx+str_score_length+20,game._fontmetrics.getHeight()+50);
		
	}
}