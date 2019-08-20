import java.awt.*;
import java.awt.event.*;

class Aram extends Frame implements WindowListener,MouseMotionListener,ComponentListener,MouseListener
{
	Frame1 frame1;
	LB board;
	boolean play_status=false;
	boolean lb_status=false;
	
	RenderingHints figure_Antialis_ON=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	RenderingHints text_Antialias_ON=new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	RenderingHints figure_Antialis_OFF=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
	RenderingHints text_Antialias_OFF=new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	
	
	public class Rect
	{
		int topx;
		int topy;
		
		int length;
		int height;
		
		String msg;
		
		public Rect(int topx,int topy,int length,int height,String msg)
		{
			this.topx=topx;
			this.topy=topy;
			this.length=length;
			this.height=height;
			this.msg=msg;
		}
		
		public void drawActiveRect(Graphics g)
		{
			((Graphics2D)g).setRenderingHints(text_Antialias_ON);
			g.setFont(new Font("candara",Font.PLAIN,29));
			
			g.setColor(new Color(255,242,4));
			g.fillRect(topx,topy,length,height);
			
			g.setColor(new Color(255,242,4));
			g.drawRect(topx,topy,length,height);
			
			FontMetrics fm=g.getFontMetrics();
			
			int msg_length=fm.stringWidth(msg);
			int msg_height=fm.getHeight();
			
			g.setColor(Color.black);
			g.drawString(msg,topx+length/2-msg_length/2,topy+height/2-msg_height/2+20);
			
		}
		public void drawDeactiveRect(Graphics g)
		{
			
			((Graphics2D)g).setRenderingHints(text_Antialias_ON);
			g.setFont(new Font("candara",Font.PLAIN,28));
			
			g.setColor(Color.orange);
			g.fillRect(topx,topy,length,height);
			
			g.setColor(Color.black);
			g.drawRect(topx+1,topy+1,length-1,height-1);
			g.setColor(Color.black);
			g.drawRect(topx,topy,length,height);
			
			FontMetrics fm=g.getFontMetrics();
			
			
			int msg_length=fm.stringWidth(msg);
			int msg_height=fm.getHeight();
			
			g.drawString(msg,topx+length/2-msg_length/2,topy+height/2-msg_height/2+20);
		}
		
	}
	Rect play;
	Rect lb;
	
	public  Aram()
	{
		this.setSize(600,600);
		this.setVisible(true);
		this.setBackground(Color.black);
		
		int len=200;;
		int hei=100;
		int gap=50;
		play=new Rect(getWidth()/2-len/2,getHeight()/2-hei/2-gap,len,hei,"PLAY");
		lb=new Rect(getWidth()/2-len/2,getHeight()/2-hei/2+gap,len,hei,"LEADER-BOARD");
		addWindowListener(this);
		addMouseMotionListener(this);
		addComponentListener(this);
		addMouseListener(this);
		
	}
	public void paint(Graphics g)
	{
		int len=200;
		int hei=100;
		int gap=100;
		play=new Rect(getWidth()/2-len/2,getHeight()/2-hei/2-gap,len,hei,"PLAY");
		lb=new Rect(getWidth()/2-len/2,getHeight()/2-hei/2+gap,len,hei,"LEADER-BOARD");
		
		g.setColor(Color.black);
		g.fillRect(0,0,getWidth(),getHeight());
		
		if(play_status)
		play.drawActiveRect(g);
		else
		play.drawDeactiveRect(g);
	
		if(lb_status)
		lb.drawActiveRect(g);
		else
		lb.drawDeactiveRect(g);
	}
	//////Window Listener///////
	public void windowClosed(WindowEvent we){}
	public void windowOpened(WindowEvent we){}
	public void windowIconified(WindowEvent we){}
	public void windowDeiconified(WindowEvent we){}
	public void windowActivated(WindowEvent we){}
	public void windowDeactivated(WindowEvent we){}
	public void windowClosing(WindowEvent we)
	{
		if(board!=null)
			board.free();
		board=null;
		
		this.setVisible(false);
		System.exit(0);
	}
	////////MouseListener///////
	public void mouseMoved(MouseEvent me)
	{
		if(play!=null)
		{
			int x=me.getX();
			int y=me.getY();
			
			if(x>=play.topx&&x<=play.topx+play.length&&y>=play.topy&&y<=play.topy+play.height)
			{
				if(play_status==false)
				{
					play_status=true;
					repaint();
				}
				
			}
			else
			if(!(x>=play.topx&&x<=play.topx+play.length&&y>=play.topy&&y<=play.topy+play.height))
				if(play_status==true)
				{
					play_status=false;
					repaint();
				}
			
		}
		
		
		if(lb!=null)
		{
			int x=me.getX();
			int y=me.getY();
			
			if(x>=lb.topx&&x<=lb.topx+lb.length&&y>=lb.topy&&y<=lb.topy+lb.height)
			{
				if(lb_status==false)
				{
					lb_status=true;
					repaint();
				}
				
			}
			else
			if(!(x>=lb.topx&&x<=lb.topx+lb.length&&y>=lb.topy&&y<=lb.topy+lb.height))
				if(lb_status==true)
				{
					lb_status=false;
					repaint();
				}
			
		}
	
		
	}
	public void mouseDragged(MouseEvent me){}
	/////////Mouse Listener///////////
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseClicked(MouseEvent me)
	{
		int x=me.getX();
		int y=me.getY();
		
		
		if(play!=null)
		if(x>=play.topx&&x<=play.topx+play.length&&y>=play.topy&&y<=play.topy+play.height)
		{
			frame1=new Frame1(this);
		}
		
		if(lb!=null)
		if(x>=lb.topx&&x<=lb.topx+lb.length&&y>=lb.topy&&y<=lb.topy+lb.height)
		{
			System.out.println("Detected");
			board=new LB("O:/Projects/Snake Game/Class_Log.txt",this);
		}
	}
	////////Component Listener////////
	public void componentResized(ComponentEvent ce)
	{
		repaint();
	}
	public void componentMoved(ComponentEvent ce){}
	public void componentShown(ComponentEvent ce){}
	public void componentHidden(ComponentEvent ce){}
	
	////////Main/////////
	public static void main(String $[])
	{
		Aram aram_frame =new Aram();
		aram_frame.repaint();
		
	}
	
}