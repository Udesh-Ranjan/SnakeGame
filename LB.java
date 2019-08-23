import java.io.*;
import java.awt.*;
import java.awt.event.*;

class LB extends Frame implements ActionListener,WindowListener
{
	Aram frame_aram;
	
	Button mode1;
	Button mode2;
	Button mode3;
	
	Panel _panel;
		
	Label score;
	Label name;
	Label time;
	Label date;
	
	int score_length=80;
	int name_length=100;
	int time_length=70;
	int date_length=100;
	
	
	int score_x=10;
	int name_x=100;
	int time_x=230;
	int date_x=350;
	
	int y=40;
	int height=40;
	int gap=20;
	
	TextField _score[]=new TextField[10];
	TextField _name[]=new TextField[10];
	TextField _time[]=new TextField[10];
	TextField _date[]=new TextField[10];
	
	String path;
	
	File file=null;
	FileInputStream fin=null;
	ObjectInputStream oin=null;
		
	LB(String path,Aram frame_aram)
	{
		super("LeaderBoard");
		this.frame_aram=frame_aram;
		this.path=path;
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		this.setVisible(false);
		this.setSize(620,780);
		this.setLayout(null);
		this.setBackground(Color.green);
		this.setForeground(Color.black);
		this.setFont(new Font("courier",Font.PLAIN,20));
		
		_panel=new Panel();
		_panel.setBounds(10,30+140,600,600);
		_panel.setLayout(new GridLayout(11,4,10,5));
		
		
		mode1=new Button("CLASSIC");
		mode2=new Button("BOUNDED");
		mode3=new Button("RAILING");
		
		mode1.setBounds(10,30,200,140);
		mode2.setBounds(200+10,30,200,140);
		mode3.setBounds(400+10,30,200,140);
		
		add(mode1);
		add(mode2);
		add(mode3);
		
		mode1.addActionListener(this);
		mode2.addActionListener(this);
		mode3.addActionListener(this);
		
		score=new Label("SCORE",Label.CENTER);
		//score.setBounds(score_x,height,score_length,height);
		name=new Label("NAME",Label.CENTER);
		//name.setBounds(name_x,height,name_length,height);
		time=new Label("DURATION",Label.CENTER);
		//time.setBounds(time_x,height,time_length,height);
		date=new Label("DATE",Label.CENTER);
		//date.setBounds(date_x,height,date_length,height);
		
		_panel.add(score);
		_panel.add(name);
		_panel.add(time);
		_panel.add(date);
		
		
		boolean flag=true;
		try
		{
			//file=new File("O:/Projects/Snake Game/Bound_Log.txt");
			file=new File(path);
			
			fin=new FileInputStream(file);
			oin=new ObjectInputStream(fin);
		}
		catch(Exception ex)
		{
			flag=false;
		}
		
		for(int i=0;i<10;i++)
		{
			_score[i]=new TextField();
			_name[i]=new TextField();
			_time[i]=new TextField();
			_date[i]=new TextField();
			/*
			_score[i].setBounds(score_x,y,score_length,height);
			_name[i].setBounds(name_x,y,name_length,height);
			_time[i].setBounds(time_x,y,name_length,height);
			_date[i].setBounds(date_x,y,date_length,height);
			*/
			_date[i].setEditable(false);
			_time[i].setEditable(false);
			_name[i].setEditable(false);
			_score[i].setEditable(false);
				
			try{
			if(flag)
				if(fin.available()>1)
				{
					Anony an=(Anony)oin.readObject();
					_score[i].setText(an.score+"");
					_name[i].setText(an.name);
					_time[i].setText(an.hour+":"+an.minute+":"+an.second);
					_date[i].setText(an.date+"/"+an.month+"/"+an.year);
				}
			}catch(Exception ex){}
			y+=height+gap;
			if(i==0||i==1||i==2)
			{
				Panel p1=new Panel();
				p1.setForeground(Color.red);
				p1.setLayout(new GridLayout(1,1));
				p1.setVisible(true);
				p1.setFont(new Font("courier",Font.BOLD,20));
		
				p1.add(_score[i]);
				
				Panel p2=new Panel();
				p2.setForeground(Color.red);
				p2.setLayout(new GridLayout(1,1));
				p2.setVisible(true);
				p2.setFont(new Font("courier",Font.BOLD,20));
				
				p2.add(_name[i]);
				
				Panel p3=new Panel();
				p3.setForeground(Color.red);
				p3.setLayout(new GridLayout(1,1));
				p3.setVisible(true);
				p3.setFont(new Font("courier",Font.BOLD,20));
				
				p3.add(_time[i]);
				
				Panel p4=new Panel();
				p4.setForeground(Color.red);
				p4.setLayout(new GridLayout(1,1));
				p4.setVisible(true);
				p4.setFont(new Font("courier",Font.BOLD,20));
				
				p4.add(_date[i]);
				
				_panel.add(p1);
				_panel.add(p2);
				_panel.add(p3);
				_panel.add(p4);
			}
			else
			{
				_panel.add(_score[i]);
				_panel.add(_name[i]);
				_panel.add(_time[i]);
				_panel.add(_date[i]);
			}
		}
		
		add(_panel);
		this.addWindowListener(this);
		this.setVisible(true);
		frame_aram.setVisible(false);
		this.setResizable(false);
	}
	public void initializeMode(String path)
	{
		this.path=path;
		boolean flag=true;
		try
		{
			//file=new File("O:/Projects/Snake Game/Bound_Log.txt");
			file=new File(path);
			
			fin=new FileInputStream(file);
			oin=new ObjectInputStream(fin);
		}
		catch(Exception ex)
		{
			flag=false;
		}
		
		if(flag)
		for(int i=0;i<10;i++)
		{
			_score[i].setText("");
			_name[i].setText("");
			_time[i].setText("");
			_date[i].setText("");
			
			try{
			if(flag)
				if(fin.available()>1)
				{
					Anony an=(Anony)oin.readObject();
					_score[i].setText(an.score+"");
					_name[i].setText(an.name);
					_time[i].setText(an.hour+":"+an.minute+":"+an.second);
					_date[i].setText(an.date+"/"+an.month+"/"+an.year);
				}
			}catch(Exception ex){}
			
		}
		
		
	}
	void free()
	{
		this.setVisible(false);
		this.setLayout(null);
		for(int i=0;i<_score.length;i++)
			_score[i]=null;
		_score=null;
		
		for(int i=0;i<_name.length;i++)
			_name[i]=null;
		_name=null;
		
		for(int i=0;i<_time.length;i++)
			_time[i]=null;
		_time=null;
		
		for(int i=0;i<_date.length;i++)
			_date[i]=null;
		_date=null;
		
	}
	//////////ActionListener////////////
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==mode1)
		{
			initializeMode("Class_Log.txt");
		}
		if(ae.getSource()==mode2)
		{
			initializeMode("Bound_Log.txt");
		}
		if(ae.getSource()==mode3)
		{
			initializeMode("Rail_Log.txt");
		}
	}
	////////WindowListener////////
	public void windowClosing(WindowEvent we)
	{
		free();
		frame_aram.setVisible(true);
		this.setVisible(false);
		
	}
	public void windowActivated(WindowEvent we){}
	public void windowDeactivated(WindowEvent we){}
	public void windowOpened(WindowEvent we){}
	public void windowClosed(WindowEvent we){}
	public void windowIconified(WindowEvent we){}
	public void windowDeiconified(WindowEvent we){}
	
	public static void main(String $[])
	{
		LB leaderboard=new LB("Class_Log.txt",null);
		
	}
}