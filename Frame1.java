	import java.awt.*;
	import java.awt.event.*;

	public class Frame1 extends Frame implements ActionListener,ComponentListener,WindowListener
	{
		Aram aram_frame;
		public Button classic_button;
		public Button bounded_button;
		public Button railing_button;
		
		public Classic_Loader cl;
		public Bounded_Loader bl;
		public Railing_Loader rl;
		
		Thread t;
		
		int width;
		int height;
	
		int button_width;
		int button_height;
	
		int sep=60;
	
		public Frame1(Aram aram_frame)
		{
			super("Snake Game -------@~");
			
			this.aram_frame=aram_frame;
			this.setSize(700,700);
			this.setLayout(null);
			this.setBackground(Color.black);
			button_width=140;
			button_height=60;
		
			classic_button=new Button("Classic");
			bounded_button=new Button("Bounded");
			railing_button=new Button("Railing");
			
			cl=null;
			bl=null;
			rl=null;
			t=null;
			
			Dimension dim=this.getSize();
		
			width=dim.width;
			height=dim.height;
		
			bounded_button.setBounds(width/2-button_width/2,height/2-button_height/2,button_width,button_height);
		
			classic_button.setBounds(width/2-button_width/2,height/2-button_height/2-sep-button_height,button_width,button_height);
		
			railing_button.setBounds(width/2-button_width/2,height/2-button_height/2+sep+button_height,button_width,button_height);
		
			add(classic_button);
			add(bounded_button);
			add(railing_button);
			addWindowListener(this);
			addComponentListener(this);
			classic_button.addActionListener(this);
			bounded_button.addActionListener(this);
			railing_button.addActionListener(this);
			aram_frame.setVisible(false);
			this.setVisible(true);
			
		}
		public void paint(Graphics g)
		{
			g.drawString("Hello",100,100);
		}
		/////////ActionPerformed///////
		public void actionPerformed(ActionEvent ae)
		{
				if(ae.getActionCommand().equals(classic_button.getLabel()))
				{
					cl=new Classic_Loader(this);
					t=new Thread(cl);
					t.start();
					this.setVisible(false);
				}
				if(ae.getActionCommand().equals(bounded_button.getLabel()))
				{
					bl=new Bounded_Loader(this);
					t=new Thread(bl);
					t.start();
					this.setVisible(false);
				}
				if(ae.getActionCommand().equals(railing_button.getLabel()))
				{
					rl=new Railing_Loader(this);
					t=new Thread(rl);
					t.start();
					this.setVisible(false);
				}
		
		}
		////////WindowEvents////////
		public void windowOpened(WindowEvent we){}
		public void windowClosed(WindowEvent we){}
		public void windowIconified(WindowEvent we){}
		public void windowDeiconified(WindowEvent we){}
		public void windowActivated(WindowEvent we){}
		public void windowDeactivated(WindowEvent we){}
		public void windowClosing(WindowEvent we)
		{
			aram_frame.setVisible(true);
			System.out.println("Closing");
			this.setVisible(false);
			aram_frame.setVisible(true);
			
		}
		///////Component Listener////////
		public void componentHidden(ComponentEvent ce){}
		public void componentMoved(ComponentEvent ce){}
		public void componentShown(ComponentEvent ce){}
		public void componentResized(ComponentEvent ce)
		{
			Dimension dim=this.getSize();
		
			width=dim.width;
			height=dim.height;
		
			bounded_button.setBounds(width/2-button_width/2,height/2-button_height/2,button_width,button_height);
		
			classic_button.setBounds(width/2-button_width/2,height/2-button_height/2-sep-button_height,button_width,button_height);
		
			railing_button.setBounds(width/2-button_width/2,height/2-button_height/2+sep+button_height,button_width,button_height);
		
		}
		public void freeMem()
		{
			cl=null;
			bl=null;
			rl=null;
			t=null;
		}
		///////Main///////
		public static void main(String $[])
		{
			Frame1 f=new Frame1(null);
		}
	
	}