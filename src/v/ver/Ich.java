package v.ver;

import ability.*;
import bewaffnung.*;
import feld.*;
import umgebung.*;
import v.*;
import v.gegner.*;

import java.awt.*;

public class Ich extends V
{
	private static final int randgrenze = 50;
	private boolean tauschTaste;
	private int nolw;
	private int norw;
	public Waffe lw;
	public Waffe rw;
	public double llv;
	public AZeit az;
	private static int llvt = 500;

	public Ich(int nolw, int norw)
	{
		super();
		if(FL.cheats != null && FL.cheats.containsKey("Leben"))
			llvt = FL.cheats.get("Leben");
		llv = llvt;
		leben = llv;
		this.nolw = nolw;
		this.norw = norw;
		try
		{
			lw = Waffe.instanz(Waffe.wList.get(nolw), this);
			rw = Waffe.instanz(Waffe.wList.get(norw), this);
		}catch(Exception e){}
		az = new AZeit(new Class[]{XNWelle.class, Teamie_Spawn.class, Warp.class});
	}

	protected void denken()
	{
		if(FL.mode)
			llv = (int)(llvt * Math.sqrt(FL.level));
		speed = 1 - leben/llv/2;
		maxspeed = 10 - leben/llv*5;
		if(ThaCre.ta.useKeys[0])
			xs -= speed;
		if(ThaCre.ta.useKeys[1])
			ys -= speed;
		if(ThaCre.ta.useKeys[2])
			xs += speed;
		if(ThaCre.ta.useKeys[3])
			ys += speed;
		xs /= 1.1;
		ys /= 1.1;
		if(leben <= llv)
			leben += FL.level/10000*llvt;
		if(lw != null)
			lw.tick(ThaCre.ta.useKeys[4]);
		if(rw != null)
			rw.nTick();
		if(FL.mode)
		{
			if(ThaCre.ta.useKeys[13])
				az.aktivier(0);
			if(ThaCre.ta.useKeys[14])
				az.aktivier(1);
		}
		if(ThaCre.ta.useKeys[15])
			az.aktivier(2);
		az.cld();
	}

	public void xDenken()
	{
		checkCollisions(Gegner.class, Listen.gegner, true);
		checkCollisions(GLaser.class, Listen.glaser, true);
	}

	public boolean bewegen()
	{
		if(stun <= 0)
			denken();
		else
		{
			xs = 0;
			ys = 0;
			stun--;
		}
		xDenken();
		double a = Math.sqrt(xs * xs + ys * ys) / maxspeed;
		if(a > 1)
		{
			xs /= a;
			ys /= a;
		}
		x += xs;
		y += ys;
		if(x > VFeld.weit / 2 - randgrenze)
		{
			x = VFeld.weit / 2 - randgrenze;
			xs = 0;
		}
		if(x < randgrenze - VFeld.weit / 2)
		{
			x = randgrenze - VFeld.weit / 2;
			xs = 0;
		}
		if(y > VFeld.hoch / 2 - randgrenze)
		{
			y = VFeld.hoch / 2 - randgrenze;
			ys = 0;
		}
		if(y < randgrenze - VFeld.hoch / 2)
		{
			y = randgrenze - VFeld.hoch / 2;
			ys = 0;
		}
		if(leben <= 0)
		{
			destroy(false);
			return true;
		}
		return false;
	}

	public boolean isEigener()
	{
		return true;
	}

	protected void destroy(boolean laserOut)
	{
		if(lw != null)
			lw.endExpl();
		if(rw != null)
			rw.endExpl();
	}

	protected double maxL()
	{
		return llv;
	}

	public void tauschen()
	{
		if((ThaCre.ta.useKeys[9] && !tauschTaste) || lw.autoTausch() &&
				(lw == null || lw.tauschenGeht()) && (rw == null || rw.tauschenGeht()))
		{
			Waffe tausch = lw;
			int tn = nolw;
			lw = rw;
			nolw = norw;
			rw = tausch;
			norw = tn;
			if(lw != null)
				lw.nachTauschen();
			if(rw != null)
				rw.nachTauschen();
			tauschTaste = true;
		}
		if(!ThaCre.ta.useKeys[9])
			tauschTaste = false;
	}

	public void aussehen(Graphics2D gf, int weit, int hoch)
	{
		Polygon tL = new Polygon(new int[8], new int[8], 8);
		tL.xpoints = new int[]{-7, -4, -7, 0, 7, 4, 7, 0};
		tL.ypoints = new int[]{-7, 0, 7, 4, 7, 0, -7, -4};
		V.tL(weit, hoch, this, 5, tL);
		super.aussehen(gf, tL);
	}

	public Color farbe()
	{
		return Color.BLUE;
	}
}