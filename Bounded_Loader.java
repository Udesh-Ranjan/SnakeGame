import java.awt.*;
import java.util.Random;
public class Bounded_Loader implements Runnable
{
	public  Random rand;
	public Bounded_GameEnviron game;
	public Bounded_Node snake;
	public Bounded_Ball b;
	public int score=0;
	public int incr=10;
	Frame1 f;
	
	long millisec=0;
	long hour=0;
	long minute=0;
	long second=0;
	
	
	public Bounded_Loader(Frame1 f)
	{
		this.f=f;
		snake=new Bounded_Node(200,200);
		game=new Bounded_GameEnviron(this.f);
		snake.game=game;
		b=new Bounded_Ball(400,400,12,game);
		rand=new Random();
	}
	
	public void run()
	{
		for(;true&&!game.game_over;)
		{
			if(!game.pause)
			{
				game.clear();
				score();
				Bounded_Node.move(snake);
				b.drawBall();
				game.makeBoundary(game.bound_width,game.bound_height);
				score++;
				
				game.g2d.drawImage(game.img,0,0,game);
				try
				{
					Thread.sleep(80);
					
					millisec+=80;
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
					score+=incr;
					incr+=10;
					Bounded_Node.increment(snake);
					
					b.clearBall();
					b.x=rand.nextInt(game.width-b.radius-game.bound_width);
					
					if(b.x<b.radius+game.bound_width)
						b.x+=b.diameter+game.bound_width;
					
					b.y=rand.nextInt(game.height-b.radius-game.bound_height);
					
					if(b.y<b.radius+40+game.bound_height)
						b.y+=b.diameter+40+game.bound_height;
					
					
					System.out.println(b.x+" "+b.y);
				}
				
				if(Bounded_Node.snakeCrash(snake,snake.next)||Bounded_Node.boundaryCrash(snake))
				{
					game.game_over=true;
				}
					
			}
			else
			{
				game.clear();
				score();
				game.gfx.setStroke(game.wideStroke);
				Bounded_Node.drawSnake(snake);
				b.drawBall();
				game.makeBoundary(game.bound_width,game.bound_height);
				//score();
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
		game.game_over=true;
		
		Anony player=new Anony("",score,hour,minute,second);
		
		int rnk=DetRnk.addPlayer(player,"Bound_Log.txt");
		
		game.point=score;
		game.setSize(700,700);
		game.setResizable(false);
		game.repaint();
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
		String _strscore="Score : ";
		
		int _strlength=game._fontmetrics.stringWidth(_strscore);
		int _intlength=game._fontmetrics.stringWidth(Integer.toString(score));
		int height=game._fontmetrics.getHeight();
		
		game.gfx.setColor(game._orange);
		game.gfx.drawString(_strscore,game.width-_strlength-_intlength-game.bound_width,30+height+game.bound_height);
		
		
		game.gfx.setColor(game._red);
		game.gfx.drawString(Integer.toString(score),game.width-_intlength-game.bound_width,30+height+game.bound_height);
		
	}
}