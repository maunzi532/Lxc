package pause.tab;

import bewaffnung.upgrade.*;
import pause.*;
import rahmen.*;
import umgebung.*;

import java.awt.*;
import java.util.*;

public class Tab_Upgrade extends P_Tab
{
	boolean u;

	public void aufruf()
	{
		String[] namen = new String[U.upgrades.size()];
		Iterator<String> it = U.upgrades.keySet().iterator();
		int i = 0;
		while(it.hasNext())
		{
			namen[i] = it.next();
			i++;
		}
		int[] ints = new int[U.upgrades.size()];
		for(int j = 0; j < U.upgrades.size(); j++)
			ints[j] = j;
		Rahmen.lSwitcher = new Switcher();
		Rahmen.lSwitcher.change(namen, ints);
	}

	public void sehen()
	{
		gp.setColor(Color.WHITE);
		if(Rahmen.lSwitcher != null && Rahmen.lSwitcher.no >= 0)
		{
			String n = Rahmen.lSwitcher.toSwitch.get(Rahmen.lSwitcher.no);
			gp.setFont(new Font(Rahmen.FONT, Font.PLAIN, 50));
			gp.drawString(T.tex(n), 100, 100);
			gp.setFont(new Font(Rahmen.FONT, Font.PLAIN, 25));
			gp.drawString(T.tex("U1") + U.val(n) + T.tex("U2") + U.uval(n) + T.tex("U3"), 100, 200);
			gp.drawString(T.tex("U4") + U.getLV(n) + T.tex("U5"), 100, 250);
			gp.drawString(T.tex("U6") + U.nextuk(n) + T.tex("U7"), 100, 300);
			gp.drawString(T.tex("UD_" + n), 100, 350);
		}
	}

	public void tick()
	{
		if(ThaCre.ta.useKeys[5])
		{
			if(!u)
			{
				u = true;
				U.levelup(Rahmen.lSwitcher.toSwitch.get(Rahmen.lSwitcher.no));
			}
		}
		else
			u = false;
		if(ThaCre.ta.useKeys[0])
			P_Menu.switchTab(new Tab_Analyse(), true);
		else if(ThaCre.ta.useKeys[2])
			P_Menu.switchTab(new Tab_Analyse(), false);
	}

	public void weg()
	{
		Rahmen.lSwitcher = null;
		Rahmen.rSwitcher = null;
	}
}
