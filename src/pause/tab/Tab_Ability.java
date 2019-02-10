package pause.tab;

import ability.*;
import rahmen.*;
import pause.*;
import umgebung.*;

import java.awt.*;

public class Tab_Ability extends P_Tab
{
	public void aufruf()
	{
		String[] namen = new String[Ability.all.size()];
		for(int i = 0; i < Ability.all.size(); i++)
			namen[i] = Ability.all.get(i).getClass().getSimpleName();
		int[] ints = new int[Ability.all.size()];
		for(int i = 0; i < Ability.all.size(); i++)
			ints[i] = i;
		Rahmen.lSwitcher = new Switcher();
		Rahmen.lSwitcher.change(namen, ints);
		Rahmen.lSwitcher.next();
		Rahmen.rSwitcher = new Switcher();
		Rahmen.rSwitcher.change(namen, ints);
		Rahmen.rSwitcher.next();
	}

	public void sehen()
	{
		gp.setColor(Color.RED);
		gp.setFont(new Font(Rahmen.FONT, Font.PLAIN, 50));
		if(Rahmen.lSwitcher != null && Rahmen.lSwitcher.no >= 0)
			gp.drawString(T.tex(Ability.all.get(Rahmen.lSwitcher.no).getClass().getSimpleName()), 100, 100);
		/*if(Rahmen.rSwitcher != null && Rahmen.rSwitcher.no >= 0)
			gp.drawString(Lang.z(WTyp.values()[Rahmen.rSwitcher.no].name()), 600, 100);*/
	}

	public void tick()
	{
		if(ThaCre.ta.useKeys[0])
			P_Menu.switchTab(new Tab_Waffen(), true);
		else if(ThaCre.ta.useKeys[2])
			P_Menu.switchTab(new Tab_Waffen(), false);
	}

	public void weg()
	{
		Rahmen.lSwitcher = null;
		Rahmen.rSwitcher = null;
	}
}