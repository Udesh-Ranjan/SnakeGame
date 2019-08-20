import java.awt.*;
import java.awt.event.*;

public class Railing_GameEnviron extends Frame implements KeyListener,WindowListener,ComponentListener
{
	public Graphics _g;
	public Graphics2D g2d;
	public boolean flush;
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public int half_stroke;
	public Color back;
	public Color front;
	public Frame _frame;
	public FontMetrics _fontmetrics;
	public RenderingHints antiAlias_ON;
	public RenderingHints antiAlias_OFF;
	public RenderingHints antiAlias_TEXT_ON;
	public BasicStroke wideStroke;
	public BasicStroke narrowStroke;
	public int width;
	public int height;
	public volatile boolean pause;
	public Color _red=Color.red;
	public Color _cyan=Color.cyan;
	public Color _orange=Color.orange;
	public Color _yellow=Color.yellow;
	public Color _green=Color.green;
	public Graphics2D gfx;  //For drawing on the image
	public Image img;		//Image will display or game.
	/////For Boundary/////
	
	/*
	(corn1_startx,corn1_starty)		  			   (corn2_startx,corn2_starty)
				 █■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■█
	
    (corn4_startx,corn4_starty)		  			   (corn3_startx,corn3_starty)
				 █■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■█
	*/
	
	public int bound_width;
	public int bound_height;
	
	public int corn1_startx;
	public int corn1_starty;
	public int corn2_startx;
	public int corn2_starty;
	public int corn3_startx;
	public int corn3_starty;
	public int corn4_startx;
	public int corn4_starty;
	
	/////////////////
	public Frame1 f;
	public boolean game_over;
	public long point=0;
	public Railing_GameEnviron(Frame1 f)
	{
		super("Ralling");
		_frame=this;
		this.f=f;
		game_over=false;
		this.setSize(700,700);
		width=700;
		height=700;
		this.setVisible(true);
		this.addKeyListener(this);
		this.requestFocus();
		up=down=left=false;
		right=true;
		_g=this.getGraphics();
		g2d=(Graphics2D)_g;
		
		g2d.setStroke(new BasicStroke(8F));
		half_stroke=4;
		antiAlias_ON = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		antiAlias_OFF=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
		antiAlias_TEXT_ON=new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g2d.setRenderingHints(rh);
		g2d.setFont(new Font("comic sans ms", Font.PLAIN, 30));
		_fontmetrics=g2d.getFontMetrics();
		this.setFont(new Font("comic sans ms", Font.PLAIN, 30));
		
		wideStroke=new BasicStroke(10f);
		narrowStroke=new BasicStroke(1f);
		back=Color.black;
		front=Color.cyan;
		this.addWindowListener(this);
		this.addComponentListener(this);
		//System.out.println(_g);
		
		img=createImage(width,height);
		gfx=(Graphics2D)img.getGraphics();
		
		bound_width=28;
		bound_height=28;
		
		flush=true;
		pause=false;
	}
	public void gameOver()
	{
		repaint();
	}
	public void paint(Graphics g)
	{
		if(game_over)
		{
			
			Dimension dim=new Dimension(700,700);
			width=700;
			height=700;
			
			gfx.setFont(new Font("Ocr a extended",Font.PLAIN,50));
			gfx.setRenderingHints(antiAlias_TEXT_ON);
			
			gfx.setColor(Color.black);
			
			int startx=0;
			int starty=0;
			
			int hei=80,wei=80;
			
			_fontmetrics=gfx.getFontMetrics();
			gfx.fillRect(0,0,dim.width,dim.height);
			
			while(starty<height)
			{
				for(startx=0;startx<width;startx+=wei)
				draw3DBox(startx+=10,starty,hei,wei);
				
				
				starty+=hei+10;
			}
			
			String over="Game Over";
			String score="Score";
			String str_point=Long.toString(point);
			
			int length_over=_fontmetrics.stringWidth(over);
			int height_over=_fontmetrics.getHeight();
		
			int length_score=_fontmetrics.stringWidth("Score");
			int height_score=_fontmetrics.getHeight();
			
			int length_point=_fontmetrics.stringWidth(str_point);
			int height_point=_fontmetrics.getHeight();
			
			int sep=100;
			
			gfx.setColor(Color.black);
			gfx.drawString(over,width/2-length_over/2,height/2-height_over/2-sep);
			gfx.drawString(score,width/2-length_score/2,height/2-height_score/2+sep);
			gfx.setColor(Color.red);
			gfx.drawString(str_point,width/2-length_point/2,height/2-height_point/2+2*sep);
			
			g2d.drawImage(img,0,0,this);
		}
	}
	public void clear()
	{
		
		if(gfx==null)
			return;
		gfx.setColor(back);
		gfx.fillRect(0,0,getSize().width,getSize().height);
		gfx.setColor(front);
	}
	//3D Box
	public void draw3DBox(int _topx,int _topy,int _width,int _height)
	{
		if(gfx==null)
			return;
		gfx.setColor(new Color(153,217,234));
		gfx.fillRect(_topx,_topy,_width,_height);
		
		int _unitwidth=_width/4;
		int _unitheight=_height/4;
		
		int innertopx=_topx+_unitwidth;
		int innertopy=_topy+_unitheight;
		
		gfx.setColor(new Color(79,155,155));
		gfx.fillRect(innertopx,innertopy,_unitwidth+_unitwidth,_unitheight+_unitheight);
		
		int x[]={innertopx,innertopx+_unitwidth*2,innertopx+_unitwidth*2,_topx+_width,_topx+_width,_topx};
		int y[]={innertopy+_unitheight*2,innertopy+_unitheight*2,innertopy,_topy,_topy+_height,_topy+_height};
		
		
		gfx.setColor(new Color(41,84,84));
		gfx.fillPolygon(x,y,x.length);
			
	}
	//Boundary
	public void makeBoundary(int bound_width,int bound_height)
	{
		int startx=100;
		int limit=width-100;
		
		corn1_startx=startx;//100 here
		corn4_startx=startx;
		
		int sep=100;
		
		int midy=height/2;
		corn1_starty=midy-sep-bound_height;
		corn2_starty=corn1_starty;;
		
		corn3_starty=midy+sep;
		corn4_starty=midy+sep;
		
		while(startx+bound_width<limit)
		{
			draw3DBox(startx,midy-sep-bound_height,bound_width,bound_height);
			draw3DBox(startx,midy+sep,bound_width,bound_height);
			
			startx+=bound_width;
			
			corn2_startx=startx;
			corn3_startx=startx;	
		}
	}
	//Key Events
	public void keyReleased(KeyEvent key){}
	public void keyTyped(KeyEvent key){}
	public void keyPressed(KeyEvent key)
	{
		int _key=key.getKeyCode();
		
		if(KeyEvent.VK_UP==_key&&!up&&!down)
			left=right=down=!(up=true);
		
		if(KeyEvent.VK_DOWN==_key&&!up&&!down)
			left=right=up=!(down=true);
		
		if(KeyEvent.VK_RIGHT==_key&&!left&&!right)
			left=up=down=!(right=true);
		
		if(KeyEvent.VK_LEFT==_key&&!left&&!right)
			right=up=down=!(left=true);
		
		if(_key==KeyEvent.VK_SPACE)
		{
			if(pause==true)
				pause=false;
			else
				pause=true;
			
			try
			{
				Thread.sleep(200);
			}
			catch(InterruptedException ie){}
		}
	}
	//Window Events
	public void windowActivated(WindowEvent we){}
	public void windowDeactivated(WindowEvent we)
	{
		pause=true;
	}
	public void windowOpened(WindowEvent we){}
	public void windowClosed(WindowEvent we){}
	public void windowIconified(WindowEvent we){}
	public void windowDeiconified(WindowEvent we)
	{
		pause=true;
	}
	public void windowClosing(WindowEvent we)
	{
		pause=true;
		game_over=true;
		this.setVisible(false);
		f.setVisible(true);
		f.freeMem();
	}
	//Component Events
	public void componentHidden(ComponentEvent ce)
	{
		pause=true;
	}
	public void componentShown(ComponentEvent ce){}
	public void componentMoved(ComponentEvent ce){}
	public void componentResized(ComponentEvent ce)
	{
		if(!game_over)
		{
			Dimension d=this.getSize();
			this.width=d.width;
			this.height=d.height;
			img=createImage(width,height);
			gfx=(Graphics2D)img.getGraphics();
			makeBoundary(bound_width,bound_height);

		}
	}
}