package bewaffnung;

import bewaffnung.upgrade.*;
import feld.*;
import rahmen.*;
import v.*;
import v.ver.*;

public class XN extends Waffe
{
	private static double abw = 30;
	private static final double maxStrom = 200;
	private static int tZeit = (int) U.val("Tausch"); //Level
	private double bereit;
	private int tauschCld;
	private double strom;

	public static void act()
	{
		tZeit = (int) U.val("Tausch");
	}

	public XN(V besitzer)
	{
		super(besitzer);
		strom = maxStrom * lvMunition;
	}

	public void tick(boolean lcl)
	{
		strom += lvMunition * lvNachladen * 0.3; //Level
		if(strom > maxStrom * lvMunition)
			strom = maxStrom * lvMunition;
		if(tauschCld <= 0)
		{
			if(bereit < lvFeuerrate * 2) //Level
				bereit += lvFeuerrate; //Level
			while(bereit >= 1 && strom > 0 && lcl)
			{
				bereit--;
				strom--;
				feuern();
			}
		}
		if(tauschCld > 0)
			tauschCld--;
	}

	private void feuern()
	{
		Listen.alle.add(new XNL(besitzer.x, besitzer.y,
				InV.mausx, InV.mausy, lvSchaden * 5, lvSpeed * 5, abw)); //Levelx2
	}

	public void nTick()
	{
		strom += lvMunition * lvNachladen * 0.3; //Level
		if(strom > maxStrom * lvMunition)
			strom = maxStrom * lvMunition;
		if(tauschCld > 0)
			tauschCld--;
	}

	public double mLeiste()
	{
		return strom / maxStrom / lvMunition;
	}

	public double cldLeiste()
	{
		return (double) tauschCld / tZeit;
	}

	public void endExpl()
	{
		for(int i = 0; i < 400 + strom * 2; i++)
			Listen.alle.add(new XNL(besitzer.x, besitzer.y,
					FL.r.nextDouble()*5-2.5,
					FL.r.nextDouble()*5-2.5, 5, FL.r.nextDouble()*4+3, abw));
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
		return "XN-417";
	}

	public boolean autoTausch()
	{
		return strom <= 0;
	}
}