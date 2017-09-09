package pause.tab;

import rahmen.*;
import pause.*;
import umgebung.*;
import v.*;
import feld.*;

import java.awt.*;

public class Tab_Analyse extends P_Tab
{
	public void aufruf(){}

	public void sehen()
	{
		if(Analyse.analysiere != null)
			drawAnalyse(Analyse.analysiere);
	}

	public void tick()
	{
		if(ThaCre.ta.useKeys[0])
			P_Menu.switchTab(new Tab_Upgrade(), true);
		else if(ThaCre.ta.useKeys[2])
			P_Menu.switchTab(new Tab_Upgrade(), false);
	}

	public void weg(){}

	private void drawAnalyse(V a)
	{
		String[] aD = a.desc();
		gp.setColor(Color.WHITE);
		gp.setFont(new Font(Rahmen.FONT, 0, Rahmen.th(0.052)));
		for(int i = 0; i < aD.length; i++)
		{
			gp.drawString(aD[i], Rahmen.tw(0.175),
					Rahmen.th(0.39 + 0.039 * i));
			gp.setFont(new Font(Rahmen.FONT, 0, Rahmen.th(0.02)));
		}
	}
}