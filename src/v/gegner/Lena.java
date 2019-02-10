package v.gegner;

import feld.*;

import java.awt.*;

public class Lena extends Gegner
{
	boolean destroySchild = true;
	private LS schild;
	private int schildZaehler = 190;

	public Lena(double level)
	{
		super(level);
		maxspeed = mxs();
	}

	public void denken()
	{
		if(Listen.nahe(FL.ich, this) < Listen.nahe(FL.ich, x + xs, y + ys))
		{
			destroySchild = true;
			schild = null;
			maxspeed = speed * 10 * mxs();
		}
		if(schildZaehler < 200 - level)
			schildZaehler++;
		if(schildZaehler >= 200 - level && Listen.glaser.notcontains(schild))
		{
			schildZaehler = 0;
			double d = mxs() * 0.5 / Listen.nahe(FL.ich, this);
			xs = d * xdiff();
			ys = d * ydiff();
			schildAktivieren();
			speed += 0.01;
			maxspeed = speed * 15 * mxs();
		}
		if(FL.ich.x != x && FL.ich.y != y)
		{
			double d = speed * (destroySchild ? 1 : 2) / fact();
			xs += xdiff() * d;
			ys += ydiff() * d;
		}
		if(maxspeed > 100)
			maxspeed = 100;
	}

	private void schildAktivieren()
	{
		destroySchild = false;
		schild = new LS(x, y, xdiff(), ydiff(), level, this);
		Listen.alle.add(schild);
	}

	private double mxs()
	{
		return Math.sqrt(level) + 3;
	}

	public boolean isEigener()
	{
		return false;
	}

	protected double maxL()
	{
		return 3 * level;
	}

	public Color farbe()
	{
		return Color.MAGENTA;
	}
}