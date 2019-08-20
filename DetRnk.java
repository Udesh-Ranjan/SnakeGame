import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class DetRnk extends Frame implements ActionListener
{
	volatile boolean ok_Flag=false;
	
	TextField text;
	Label name;
	Button enter;
	Label _time;
	Label _rank;
	TextField time;
	TextField rank;
	public DetRnk(){}
	
	Anony _player;
	
	public DetRnk(Anony gamer)
	{
		super("Player Form");
		this._player=gamer;
		
		this.setFont(new Font("dialog",Font.BOLD,20));
		this.setLayout(null);
		this.setSize(400,400);
		
		name=new Label("Name",Label.CENTER);
		name.setBounds(100,200-20-50,60,40);
		
		text=new TextField("",90);
		text.setBounds(200,200-20-50,100,40);
		
		_time=new Label("Time",Label.CENTER);
		_time.setBounds(100,200-20+25,60,40);
		time=new TextField(_player.hour+":"+_player.minute+":"+_player.second);
		time.setBounds(200,200-20+25,100,40);
		
		_rank=new Label("Rank",Label.CENTER);
		_rank.setBounds(100,200-20+100,60,40);
		rank=new TextField(_player.rank+" ");
		rank.setBounds(200,200-20+100,100,40);
		
		
		enter=new Button("Enter");
		enter.setBounds(330,330,60,30);
		
		add(name);
		add(text);
		add(_time);
		add(time);
		add(_rank);
		add(rank);
		add(enter);
		
		time.setEditable(false);
		rank.setEditable(false);
		
		this.setVisible(true);
		this.setResizable(false);
		enter.addActionListener(this);
		text.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==enter)
		{
			String str=text.getText();
			boolean f=false;
			
			for(int i=0;i<str.length();i++)
			{
				char c=str.charAt(i);
				if(c>=97&&c<=122||c>=65&&c<=90||c>=48&&c<=57)
				{
					f=true;
					break;
				}
			}
			
			if(f)
			{
				_player.name=text.getText();
				ok_Flag=true;
				this.setVisible(false);
			}
		}
		
	}
	public static int addPlayer(Anony player,String path)
	{
		try
		{
			player.rank=-1;
			
			File file=new File(path);
		
			if(!file.exists())
			{
				player.rank=1;
			
				DetRnk dr=new DetRnk(player);
			
				while(!dr.ok_Flag)
				{
					try{Thread.sleep(500);}
					catch(Exception e){}
				}
			
				FileOutputStream fout=new FileOutputStream(file);
				ObjectOutputStream out=new ObjectOutputStream(fout);
				out.writeObject(player);
				out.close();
				fout.close();
			}
		else
		{
			FileInputStream fin=new FileInputStream(file);
			ObjectInputStream oin=new ObjectInputStream(fin);
			
			ArrayList<Anony>arr=new ArrayList<Anony>();
			
			while(fin.available()>1)
			{
				arr.add((Anony)oin.readObject());
			}
			
			fin.close();
			oin.close();
			
			Collections.sort(arr);
			
			for(int i=0;i<arr.size()&&i<10;i++)
			{
				Anony ob=arr.get(i);
				if(player.score>ob.score)
				{
					player.rank=i+1;
					break;
				}
				else
					if(player.score==ob.score)
						if(player.hour*60*60+player.minute*60+player.second<ob.hour*60*60+ob.minute*60+ob.second)
						{
							player.rank=i+1;
							break;
						}
			}
			
			if(player.rank==-1&&arr.size()>=10)
				return -1;
			else
				if(player.rank==-1&&arr.size()<10)
				{
					player.rank=arr.size()+1;
				}
			
			DetRnk dr=new DetRnk(player);
			
			while(!dr.ok_Flag)
			{
				try{Thread.sleep(500);}
				catch(Exception e){}
			}
			
			arr.add(player);
			Collections.sort(arr);
			
			FileOutputStream fout=new FileOutputStream(file);
			ObjectOutputStream out=new ObjectOutputStream(fout);
			
			for(int i=0;i<10&&i<arr.size();i++)
			{
				Anony temp=arr.get(i);
				temp.rank=i+1;
				out.writeObject(temp);
				
			}
			out.close();
			fout.close();
		}
		
		}catch(Exception e){System.out.println(e);}
		
		return player.rank;
	}
}