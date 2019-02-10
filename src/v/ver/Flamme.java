package v.ver;

import v.*;
import feld.*;

import java.awt.*;

public class Flamme extends R_S
{
	private static Color[] flcls = new Color[]{
			new Color(223, 255, 0),
			new Color(230, 180, 0),
			new Color(235, 120, 0),
			new Color(245, 70, 0),
			new Color(255, 0, 0)};

	private int cl;

	public Flamme(double x, double y, double xsv, double ysv, double l, double sp, double abw, V von)
	{
		super(x, y, xsv, ysv, l, sp, abw);
		size = 1.8;
		if(von != null)
		{
			xs += von.xs;
			ys += von.ys;
		}
	}

	public void denken()
	{
		if(FL.r.nextInt(2000) < Listen.nahe(this, FL.ich))
			leben -= maxL() / 10;
		if(leben > 0 && leben < maxL())
			cl = flcls.length - 1 - (int)((leben / maxL() * (flcls.length - 1)));
	}

	public Color farbe()
	{
		return flcls[cl];
	}
}