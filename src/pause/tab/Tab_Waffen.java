package pause.tab;

import bewaffnung.*;
import rahmen.*;
import pause.*;
import umgebung.*;

import java.awt.*;

public class Tab_Waffen extends P_Tab
{
	public static int wL;
	public static int wR;

	public void aufruf()
	{
		String[] namen = new String[Waffe.wList.size()];
		for(int i = 0; i < Waffe.wList.size(); i++)
			namen[i] = Waffe.wList.get(i).getSimpleName();
		int[] ints = new int[Waffe.wList.size()];
		for(int i = 0; i < Waffe.wList.size(); i++)
			ints[i] = i;
		Rahmen.lSwitcher = new Switcher();
		Rahmen.lSwitcher.change(namen, ints);
		Rahmen.lSwitcher.no = wL;
		Rahmen.rSwitcher = new Switcher();
		Rahmen.rSwitcher.change(namen, ints);
		Rahmen.rSwitcher.no = wR;
	}

	public void sehen()
	{
		gp.setColor(Color.WHITE);
		gp.setFont(new Font(Rahmen.FONT, Font.PLAIN, 50));
		if(Rahmen.lSwitcher != null && Rahmen.lSwitcher.no >= 0)
			gp.drawString(T.tex(Waffe.wList.get(Rahmen.lSwitcher.no).getSimpleName()), 100, 100);
		if(Rahmen.rSwitcher != null && Rahmen.rSwitcher.no >= 0)
			gp.drawString(T.tex(Waffe.wList.get(Rahmen.rSwitcher.no).getSimpleName()), 600, 100);
		gp.setFont(new Font(Rahmen.FONT, Font.PLAIN, 20));
		gp.drawString(T.tex("A1"), 200, 200);
		gp.drawString(T.tex("A2"), 200, 230);
		gp.drawString(T.tex("A3"), 200, 260);
		if(Rahmen.lSwitcher != null && Rahmen.lSwitcher.no >= 0)
			gp.drawString(T.tex("A4") + T.tex("B_" + Waffe.wList.get(Rahmen.lSwitcher.no).getSimpleName()), 150, 350);
		if(Rahmen.rSwitcher != null && Rahmen.rSwitcher.no >= 0)
			gp.drawString(T.tex("B_" + Waffe.wList.get(Rahmen.rSwitcher.no).getSimpleName()) + T.tex("A5"), 150, 450);
	}

	public void tick()
	{
		if(ThaCre.ta.useKeys[0])
			P_Menu.switchTab(new Tab_Ability(), true);
		else if(ThaCre.ta.useKeys[2])
			P_Menu.switchTab(new Tab_Ability(), false);
	}

	public void weg()
	{
		wL = Rahmen.lSwitcher.no;
		wR = Rahmen.rSwitcher.no;
		Rahmen.lSwitcher = null;
		Rahmen.rSwitcher = null;
	}
}