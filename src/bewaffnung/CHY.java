package bewaffnung;

import bewaffnung.upgrade.*;
import rahmen.*;
import v.*;
import feld.*;
import v.gegner.*;
import v.ver.*;

public class CHY extends Waffe
{
	private static int tZeit = (int) U.val("Tausch"); //Level
	private int bereit;
	private int tauschCld;
	private boolean rakete;
	private int hitze;

	public static void act()
	{
		tZeit = (int) U.val("Tausch");
	}

	public CHY(V besitzer)
	{
		super(besitzer);
		rakete = true;
	}

	public void tick(boolean lcl)
	{
		if(tauschCld <= 0)
		{
			if(rakete)
			{
				if(hitze <= lvMunition * 800 - 100 && lcl)
				{
					hitze += 100;
					feuern();
					rakete = false;
					bereit = 0;
				}
				else
				{
					hitze -= lvNachladen * lvMunition * 4;
					if(hitze < 0)
						hitze = 0;
				}
			}
			else
			{
				if(bereit < 20 / lvFeuerrate) //Level
					bereit++;
				if(bereit >= 20 / lvFeuerrate)
				{
					rakete = true;
					bereit = 0;
				}
			}
		}
		else
		{
			hitze -= lvNachladen * lvMunition * 4;
			if(hitze < 0)
				hitze = 0;
		}
		if(tauschCld > 0)
			tauschCld--;
	}

	private void feuern()
	{
		V nahe = null;
		double abstand = 100;
		for(Object v : Listen.gegner.arr)
			if(!(v instanceof Nex))
			{
				double neu = Listen.nahe((V) v, InV.mausx + FL.ich.x, InV.mausy + FL.ich.y);
				if(neu < abstand)
				{
					nahe = (V) v;
					abstand = neu;
				}
			}
		Listen.alle.add(new Rakete(besitzer.x, besitzer.y,
				InV.mausx, InV.mausy, lvSchaden * 40, lvSpeed * 5, 0, nahe)); //Levelx2
	}

	public void nTick()
	{
		if(tauschCld <= 0)
		{
			if(rakete)
			{
				hitze -= lvNachladen * lvMunition * 4;
				if(hitze < 0)
					hitze = 0;
			}
			else
			{
				if(bereit < 20 / lvFeuerrate) //Level
					bereit++;
				if(bereit >= 20 / lvFeuerrate)
				{
					rakete = true;
					bereit = 0;
				}
			}
		}
		else
		{
			hitze -= lvNachladen * lvMunition * 4;
			if(hitze < 0)
				hitze = 0;
		}
		if(tauschCld > 0)
			tauschCld--;
	}

	public double mLeiste()
	{
		return hitze / (lvMunition * 800);
	}

	public double cldLeiste()
	{
		return (double) tauschCld / tZeit;
	}

	public void endExpl()
	{
		Listen.explode(FL.ich.x, FL.ich.y, 1, 200, 300, ExplTyp.NUR_AUF_FEINDE);//Level*3
	}

	public boolean tauschenGeht()
	{
		return tauschCld <= 0;
	}

	public void nachTauschen()
	{
		tauschCld = tZeit;
		bereit = 0;
	}

	public String getName()
	{
		return "CHY-22";
	}

	public boolean autoTausch()
	{
		return hitze > lvMunition * 800 - 100;
	}
}