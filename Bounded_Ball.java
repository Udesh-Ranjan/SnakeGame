import java.awt.*;

public class Bounded_Ball
{
	public static Bounded_GameEnviron game;
	public int x;
	public int y;
	public int radius;
	public int diameter;
	
	public Bounded_Ball()
	{
		x=y=radius=0;
	}
	
	public Bounded_Ball(int x,int y,int radius,Bounded_GameEnviron game)
	{
		this.x=x;
		this.y=y;
		this.radius=radius;
		this.diameter=radius*2;
		this.game=game;
	}
	public void drawBall()
	{
		game.gfx.setStroke(game.narrowStroke);
		game.gfx.setRenderingHints(game.antiAlias_ON);
		game.gfx.setColor(new Color(251,47,52));
		game.gfx.fillOval(x-radius,y-radius,diameter,diameter);
		game.gfx.setColor(Color.white);
		
		game.gfx.setStroke(new BasicStroke(3.2f));
		
		game.gfx.drawLine(x+4,y-4,x+4,y-4);
		game.gfx.drawLine(x+5,y-5,x+5,y-5);
		game.gfx.drawLine(x+5,y-3,x+5,y-3);
		
		game.gfx.setRenderingHints(game.antiAlias_OFF);
		game.gfx.setColor(game.front);
		
	}
	public void clearBall()
	{
		game.gfx.setStroke(game.narrowStroke);
		game.gfx.setColor(game.back);
		game.gfx.fillRect(x+radius,y+radius,diameter,diameter);
	}
}