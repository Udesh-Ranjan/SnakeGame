	import java.awt.*;
	import java.awt.event.*;

	public class Classic_GameEnviron extends Frame implements KeyListener,WindowListener,ComponentListener
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
		public Image img;		//Image will be displayed on game.
		public boolean game_over;
		public Frame1 f;
		
		long point=0;
		
		public Classic_GameEnviron(Frame1 f)
		{
			super("Classic Snake Game");
			this.f=f;
			_frame=this;
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
			g2d.setFont(new Font("comic sans ms", Font.PLAIN, 32));
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
			gfx.setFont(new Font("comic sans ms", Font.PLAIN, 30));
			
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
				
				g2d.setFont(new Font("jokerman",Font.PLAIN,40));
				g2d.setRenderingHints(antiAlias_TEXT_ON);
			
				g2d.setColor(Color.black);
				g2d.fillRect(0,0,dim.width,dim.height);

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
			
				g2d.setColor(Color.green);
				g2d.drawString(over,width/2-length_over/2,height/2-height_over/2-sep);
				g2d.drawString(score,width/2-length_score/2,height/2-height_score/2+sep);
				g2d.setColor(Color.red);
				g2d.drawString(str_point,width/2-length_point/2,height/2-height_point/2+2*sep);
			
			}
		}
		public void clear()
		{
			gfx.setColor(back);
			gfx.fillRect(0,0,getSize().width,getSize().height);
			gfx.setColor(front);
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
			if(!game_over)
			pause=true;
		}
		public void windowOpened(WindowEvent we){}
		public void windowClosed(WindowEvent we){}
		public void windowIconified(WindowEvent we)
		{
			if(!game_over)
			pause=true;
		}
		public void windowDeiconified(WindowEvent we){}
		public void windowClosing(WindowEvent we)
		{
			pause=true;
			this.setVisible(false);
			f.setVisible(true);
			f.freeMem();
			
		}
		//Component Events
		public void componentHidden(ComponentEvent ce)
		{
			if(!game_over)
			pause=true;
		}
		public void componentShown(ComponentEvent ce){}
		public void componentMoved(ComponentEvent ce)
		{
			if(!game_over)
			{
				Dimension d=this.getSize();
				this.width=d.width;
				this.height=d.height;
				img=createImage(width,height);
				gfx=(Graphics2D)img.getGraphics();
				gfx.setFont(new Font("comic sans ms", Font.PLAIN, 40));
			}
		}
		public void componentResized(ComponentEvent ce)
		{
			if(!game_over)
			{
				Dimension d=this.getSize();
				System.out.println(d.width+" "+d.height);
				this.width=d.width;
				this.height=d.height;
				img=createImage(width,height);
				gfx=(Graphics2D)img.getGraphics();
				gfx.setFont(new Font("comic sans ms", Font.PLAIN, 30));
			}
			
		}
	}