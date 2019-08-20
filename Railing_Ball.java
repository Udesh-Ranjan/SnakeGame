import java.awt.*;

public class Railing_Ball
{
	public static Railing_GameEnviron game;
	public int x;
	public int y;
	public int radius;
	public int diameter;
	
	public Railing_Ball()
	{
		x=y=radius=0;
	}
	
	public Railing_Ball(int x,int y,int radius,Railing_GameEnviron game)
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
		game.gfx.setStroke(game.wideStroke);
		game.gfx.setRenderingHints(game.antiAlias_OFF);
		game.gfx.setColor(game.front);
		
	}
	public void clearBall()
	{
		game.gfx.setColor(game.back);
		game.gfx.fillRect(x+radius,y+radius,diameter,diameter);
		game.gfx.setColor(game.front);
	}
}