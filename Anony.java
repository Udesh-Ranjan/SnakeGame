import java.io.*;
import java.util.*;

public class Anony implements Serializable,Comparable<Anony>
{
	public String name;
	public long score;
	public int rank;
	public long hour,minute,second;
	public int date,month,year;
	
	public Anony()
	{
		name=null;
		score=-1;
		rank=-1;
		hour=minute=second=-1;
		
		GregorianCalendar gc=new GregorianCalendar();
		date=gc.get(Calendar.DATE);
		month=gc.get(Calendar.MONTH);
		year=gc.get(Calendar.YEAR);
	}
	public Anony(String _name,long scr,long hr,long min,long sec)
	{
		name=_name;
		score=scr;
		hour=hr;
		minute=min;
		second=sec;
		rank=-1;
		
		GregorianCalendar gc=new GregorianCalendar();
		date=gc.get(Calendar.DATE);
		month=gc.get(Calendar.MONTH);
		year=gc.get(Calendar.YEAR);
	}
	public Anony(long scr,long hr,long min,long sec)
	{
		name=null;
		score=scr;
		hour=hr;
		minute=min;
		second=sec;
		rank=-1;
		
		GregorianCalendar gc=new GregorianCalendar();
		date=gc.get(Calendar.DATE);
		month=gc.get(Calendar.MONTH);
		year=gc.get(Calendar.YEAR);
	}
	public int compareTo(Anony an)
	{
		//Descending Order
		if(an.score>score)
		{
			return 1;
		}
		else
			if(an.score==score)
			if(an.hour*60*60+an.minute*60+an.second<hour*60*60+minute*60+second)
				return 1;
		
		return -1;
	}
}