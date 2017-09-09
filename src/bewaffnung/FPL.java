package bewaffnung;

import bewaffnung.upgrade.*;
import rahmen.*;
import v.*;
import feld.*;
import v.ver.*;

public class FPL extends Waffe
{
	private static final double abw = 40;
	private static final double maxFuel = 600;
	private static int tZeit = (int) U.val("Tausch"); //Level
	private int tauschCld;
	private double fuel;
	private double bereit;
	private int fuelWCld;
	private boolean ladetNach;

	public static void act()
	{
		tZeit = (int) U.val("Tausch");
	}

	public FPL(V besitzer)
	{
		super(besitzer);
		fuel = lvMunition * 600;
	}

	public void tick(boolean lcl)
	{
		if(fuelWCld > 0)
			fuelWCld--;
		else if(ladetNach)
		{
			ladetNach = false;
			fuel = lvMunition * maxFuel;
		}
		if(!ladetNach && fuel <= 0)
		{
			fuelWCld = (int)(100 / lvNachladen);
			ladetNach = true;
		}
		if(tauschCld <= 0 && !ladetNach)
		{
			if(bereit <= lvFeuerrate * 3) //Level
				bereit += lvFeuerrate * 3; //Level
			while(bereit >= 1 && fuel > 0 && lcl)
			{
				bereit--;
				fuel--;
				feuern();
			}
		}
		if(tauschCld > 0)
			tauschCld--;
	}

	private void feuern()
	{
		Listen.alle.add(new Flamme(besitzer.x, besitzer.y,
				InV.mausx, InV.mausy, lvSchaden * 3, lvSpeed, abw, FL.ich)); //Level
	}

	public void nTick()
	{
		if(fuelWCld > 0 && tauschCld <= 0)
			fuelWCld--;
		else if(ladetNach)
		{
			ladetNach = false;
			fuel = lvMunition * maxFuel;
		}
		if(!ladetNach && tauschCld <= 0 && fuel < lvMunition * maxFuel)
		{
			fuelWCld = (int)(100 / lvNachladen);
			ladetNach = true;
		}
		if(tauschCld > 0)
			tauschCld--;
	}

	public double mLeiste()
	{
		return fuel / lvMunition / maxFuel;
	}

	public double cldLeiste()
	{
		if(ladetNach)
			return fuelWCld * lvNachladen / 100;
		return (double) tauschCld / tZeit;
	}

	public void endExpl()
	{
		for(int i = 0; i < 600 + fuel; i++)
			Listen.alle.add(new Flamme(besitzer.x, besitzer.y,
					FL.r.nextDouble()*5-2.5,
					FL.r.nextDouble()*5-2.5, 7, FL.r.nextDouble()+0.2, abw, null));
	}

	public boolean tauschenGeht()
	{
		return tauschCld <= 0;
	}

	public void nachTauschen()
	{
		ladetNach = false;
		fuelWCld = 0;
		tauschCld = tZeit;
	}

	public String getName()
	{
		return "FPL-2D2";
	}

	public boolean autoTausch()
	{
		return ladetNach;
	}
}