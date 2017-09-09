package bewaffnung;

import bewaffnung.upgrade.*;
import rahmen.*;
import v.*;
import feld.*;
import v.ver.*;

public class S4 extends Waffe
{
	private static double maxSGL = 6;
	private static int auf = 20;
	private static int rein = 10;
	private static int zu = 10;
	private static int tZeit = (int) U.val("Tausch"); //Level
	private int tauschCld;
	private double munition;
	private int bereit;
	private int zustand;
	private int specialCld;

	public static void act()
	{
		tZeit = (int) U.val("Tausch");
	}

	public S4(V besitzer)
	{
		super(besitzer);
		munition = maxSGL * lvMunition;
	}

	public void tick(boolean lcl)
	{
		if(tauschCld <= 0)
		{
			klappen();
			/*if(nlc && munition < maxSGL)
			{
				switch(zustand)
				{
					case 0:
						zustand = 1;
						specialCld = auf;
						break;
					case 1:
						zustand = 3;
						specialCld = zu;
						break;
				}
			}*/
			if(zustand == 0)
			{
				if(bereit < 20 / lvFeuerrate) //Level
					bereit++;
				if(bereit >= 20 / lvFeuerrate && munition > 0 && lcl)
				{
					bereit = 0;
					munition--;
					feuern();
				}
			}
		}
		else
			tauschCld--;
	}

	private void klappen()
	{
		if(zustand == 0 && munition <= 0)
		{
			zustand = 1;
			specialCld = (int)(auf / lvNachladen);
		}
		if(specialCld > 0)
		{
			specialCld--;
			if(specialCld == 0)
			{
				switch(zustand)
				{
					case 1:
						zustand = 2;
						specialCld = (int)(rein / lvNachladen);
						break;
					case 2:
						munition++;
						if(munition >= maxSGL * lvMunition)
						{
							zustand = 3;
							specialCld = (int)(zu / lvNachladen);
						}
						else
							specialCld = (int)(rein / lvNachladen);
						break;
					case 3:
						zustand = 0;
						break;
				}
			}
		}
	}

	private void feuern()
	{
		Listen.alle.add(new SGL(besitzer.x, besitzer.y,
				InV.mausx, InV.mausy, 60 * lvSchaden, 10 * lvSpeed, 0, null)); //Level
	}

	public void nTick()
	{
		if(tauschCld > 0)
			tauschCld--;
		else if(zustand != 0)
			klappen();
		else if(munition < maxSGL * lvMunition)
		{
			zustand = 1;
			specialCld = (int)(auf / lvNachladen);
		}

	}

	public double mLeiste()
	{
		return munition / lvMunition / maxSGL;
	}

	public double cldLeiste()
	{
		if(tauschCld <= 0)
			switch(zustand)
			{
				case 0:
					return 0;
				case 1:
					return 1 - specialCld * lvNachladen / auf;
				case 2:
					return 1;
				case 3:
					return specialCld * lvNachladen / zu;
			}
		return (double) tauschCld / tZeit;
	}

	public void endExpl()
	{
		for(int i = 0; i < 50; i++)
			Listen.alle.add(new SGL(besitzer.x, besitzer.y, FL.r.nextDouble()*5-2.5,
					FL.r.nextDouble()*5-2.5, 80, FL.r.nextDouble()*8+5, 0, null));
	}

	public boolean tauschenGeht()
	{
		return tauschCld <= 0;
	}

	public void nachTauschen()
	{
		tauschCld = tZeit;
	}

	public String getName()
	{
		return "S4-FSG";
	}

	public boolean autoTausch()
	{
		return zustand > 0;
	}
}