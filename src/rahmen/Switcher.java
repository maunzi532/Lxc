package rahmen;

import umgebung.*;

import java.awt.*;
import java.util.*;

public class Switcher
{
	public ArrayList<String> toSwitch;
	private ArrayList<Integer> values;
	public int no;
	private double tch = 0.5;
	private double tcb;
	// --Commented out by Inspection (04.12.14 12:11):private boolean switched;

	public Switcher()
	{
		toSwitch = new ArrayList<>();
		values = new ArrayList<>();
		no = -1;
	}

	public void change(String[] t, int[] val)
	{
		ArrayList<String> t1 = new ArrayList<>();
		Collections.addAll(t1, t);
		ArrayList<Integer> va2 = new ArrayList<>();
		for(int va : val)
			va2.add(va);
		change(t1, va2);
	}

	private void change(ArrayList<String> t, ArrayList<Integer> val)
	{
		int va = -1;
		if(no != -1)
			va = values.get(no);
		toSwitch = t;
		values = val;
		if(no != -1)
		{
			no = -1;
			for(int i = 0; i < values.size(); i++)
				if(values.get(i) == va)
				{
					no = i;
					break;
				}
		}
	}

	public void next()
	{
		no++;
		if(no >= values.size())
			no = -1;
		tch += 1;
		//switched = true;
	}

	private int wno(int wno)
	{
		int wno1 = wno;
		while(wno1 < -1)
			wno1 += values.size() + 1;
		while(wno1 >= values.size())
			wno1 -= values.size() + 1;
		return wno1;
	}

	private String none(int wno)
	{
		int wno1 = wno(wno);
		if(wno1 == -1)
			return T.tex("Kein");
		if(toSwitch.get(wno1) == null)
			return "";
		return toSwitch.get(wno1);
	}

	public void adjTCH()
	{
		//switched = false;
		if(tch > 0.5)
		{
			tch -= tcb;
			tcb += tch / 100;
		}
		else
		{
			tcb = 0;
			tch -= tch / 10;
		}
	}

	public void draw(Graphics2D gd, int x, int y, int xw, int yh, double scale)
	{
		gd.setColor(Color.BLACK);
		gd.fillRect(x, y, xw, yh);
		gd.setColor(Color.WHITE);
		gd.setFont(new Font(Rahmen.FONT, 0, (int)(20*scale)));
		for(int i = -4-(int)tch; i <= -3+(int)(8.1-tch); i++)
			gd.drawString(none(no+i), x + (int)(20*scale),
					y + yh / 2 + (int)(40*scale*i) + (int)(40*tch*scale) - (int)(20*scale));
		gd.drawRect(x, y + yh / 2 - (int)(40*scale) + (int)(40*tch*scale), xw - 1, (int)(30*scale));
		gd.setColor(Color.BLACK);
		gd.fillRect(x, y, xw, (int)(40*scale));
		gd.fillRect(x, y + yh - (int)(40*scale), xw, (int)(40*scale));
	}
}