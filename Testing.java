import java.awt.*;
import java.awt.event.*;

class Testing extends Frame implements WindowListener
{
	Graphics2D g2d;
	public Testing()
	{
		super("Testing");
		this.setSize(600,600);
		this.setVisible(true);
		this.addWindowListener(this);
		g2d=(Graphics2D)this.getGraphics();
	}
	public void draw3DBox(int _topx,int _topy,int _width,int _height,Graphics gfx)
	{
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
	public void paint(Graphics g)
	{
		draw3DBox(200,200,28,28,g);
	}
	public static void main(String $[])
	{
		Testing test=new Testing();
		test.repaint();
	}
	public void windowClosed(WindowEvent we){}
	public void windowOpened(WindowEvent we){}
	public void windowActivated(WindowEvent we){}
	public void windowDeactivated(WindowEvent we){}
	public void windowIconified(WindowEvent we){}
	public void windowDeiconified(WindowEvent we){}
	public void windowClosing(WindowEvent we)
	{
		System.exit(0);
	}
}