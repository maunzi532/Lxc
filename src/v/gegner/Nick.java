package v.gegner;

import v.*;
import feld.*;

import java.awt.*;

public class Nick extends Gegner
{
	private double nex;

	public Nick(double level)
	{
		super(level);
		size = 30;
		maxspeed = 0;
		speed = 0;
	}

	public void denken()
	{
		nex += (Math.sqrt(level) + 3) / 100;
		while(nex >= 1)
		{
			nex--;
			spawne();
		}
	}

	private void spawne()
	{
		Listen.alle.add(new Tom(level, x, y, 50));
	}

	public boolean isEigener()
	{
		return false;
	}

	protected double maxL()
	{
		return 100 * level;
	}

	public Color farbe()
	{
		return Color.CYAN;
	}

	public void aussehen(Graphics2D gf, int weit, int hoch)
	{
		VFeld.leuchten(gf, 0);
		gf.fillOval((int)(weit / 2 + x - 50), (int)(hoch / 2 + y - 50), 100, 100);
		int d = VFeld.mZ % 30;
		int e = d > 15 ? 15 : d;
		int f = d < 15 ? 15 : 30 - d;
		Polygon tL = new Polygon(new int[8], new int[8], 8);
		tL.xpoints = new int[]{-5, -e, 5 - d / 3, f, 5, e, -5 + d / 3, -f};
		tL.ypoints = new int[]{-5 + d / 3, -f, -5, -e, 5 - d / 3, f, 5, e};
		//tL.xpoints = new int[]{-5 + d, -5 + d, 5 - d, 15 - d, 5 - d, 5 - d, -5 + d, -15 + d};
		//tL.ypoints = new int[]{-5 + d, -15 + d, -5 + d, -5 + d, 5 - d, 15 - d, 5 - d, 5 - d};
		V.tL(weit, hoch, this, 10, tL);
		super.aussehen(gf, tL);
	}
}