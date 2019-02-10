package v;

import feld.*;
import umgebung.*;

import java.awt.*;

public abstract class V
{
	protected double level;
	public double x;
	public double y;
	public double xs;
	public double ys;
	public double leben = 1;
	protected double speed = 0.1;
	protected double maxspeed = 1;
	public double size = 5;
	public int stun;

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
		if(x > VFeld.weit / 2)
		{
			x = VFeld.weit / 2;
			xs = 0;
		}
		if(x < - VFeld.weit / 2)
		{
			x = - VFeld.weit / 2;
			xs = 0;
		}
		if(y > VFeld.hoch / 2)
		{
			y = VFeld.hoch / 2;
			ys = 0;
		}
		if(y < - VFeld.hoch / 2)
		{
			y = - VFeld.hoch / 2;
			ys = 0;
		}
		if(leben <= 0)
		{
			destroy(false);
			return true;
		}
		return false;
	}

	public void aussehen(Graphics2D gf, int weit, int hoch)
	{
		gf.setColor(farbe());
		gf.fillRect((int)(weit/2+x-size), (int)(hoch/2+y-size), (int)(size*2), (int)(size*2));
		if(stun > 0)
		{
			VFeld.leuchten(gf, 2);
			gf.fillRect((int)(weit/2+x-size), (int)(hoch/2+y-size), (int)(size*2), (int)(size*2));
		}
	}

	protected void aussehen(Graphics2D gf, Shape form)
	{
		gf.setColor(farbe());
		gf.fill(form);
		if(stun > 0)
		{
			VFeld.leuchten(gf, 2);
			gf.fill(form);
		}
	}

	protected abstract Color farbe();

	protected abstract void denken();

	protected abstract void xDenken();

	public abstract boolean isEigener();

	protected void destroy(boolean laserOut){}

	protected <U extends V> void checkCollisions(Class<U> typ, NonMoveList<U> liste, boolean notInvi)
	{
		for(Object ex : liste.arr)
			if(ex != null)
			{
				U a = (U) ex;
				if(typ.isInstance(a) && a.leben > 0 && leben > 0 && Listen.nahe(this, a) < size + a.size)
				{
					double b = a.leben;
					a.leben -= leben;
					if(notInvi)
						leben -= b;
				}
			}
	}

	protected abstract double maxL();

	protected double xdiff()
	{
		return FL.ich.x - x;
	}

	protected double ydiff()
	{
		return FL.ich.y - y;
	}

	protected double factq()
	{
		return xdiff() * xdiff() + ydiff() * ydiff();
	}

	protected double fact()
	{
		return Math.sqrt(xdiff() * xdiff() + ydiff() * ydiff());
	}

	public String[] desc()
	{
		return new String[]{T.tex(getClass().getSimpleName()),
				bauen(T.tex("Desc01"), leben, "  ", T.tex("Desc02"), level, "  ",
						T.tex("Desc03"), x, "  ", T.tex("Desc04"), y),
				bauen(T.tex("Desc05"), speed, "  ", T.tex("Desc06"), maxspeed),
				bauen(T.tex("Desc07"), xs, "  ", T.tex("Desc08"), ys),
				bauen(T.tex("Desc09"), size, "  ", T.tex("Desc10"), maxL()),
				bauen(isEigener() ? T.tex("Desc11") : T.tex("Desc12"), "  ",
						T.tex("Desc13"), farbe().toString().substring(14))};
	}

	public String analyseDesc()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(T.tex(getClass().getSimpleName())).append(" ");
		if(level > 0)
			sb.append("[").append(T.tex("ALv")).append(kDouble(level)).append("] ");
		sb.append(kDouble(leben)).append(" / ").append(kDouble(maxL())).append(" ").append(T.tex("ALe"));
		return sb.toString();
	}

	private static String bauen(Object... werte)
	{
		StringBuilder sb = new StringBuilder();
		if(werte.length > 0)
		{
			for(Object a : werte)
				if(a instanceof Double)
					sb.append(kDouble((Double) a)).append(" ");
				else
					sb.append(a.toString()).append(" ");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}

	private static String kDouble(double d)
	{
		String k = String.valueOf(d);
		int k2 = k.length() - k.indexOf(".");
		if(k2 > 3)
			k2 = 3;
		k = k.substring(0, k.indexOf(".") + k2);
		return k;
	}

	protected static void tL(int weit, int hoch, V t, double nSize, Polygon tL)
	{
		for(int i = 0; i < tL.npoints; i++)
		{
			if(t.size != nSize)
			{
				tL.xpoints[i] = (int) (tL.xpoints[i] / nSize * t.size);
				tL.ypoints[i] = (int) (tL.ypoints[i] / nSize * t.size);
			}
			tL.xpoints[i] += weit / 2 + t.x;
			tL.ypoints[i] += hoch / 2 + t.y;
		}
	}
}