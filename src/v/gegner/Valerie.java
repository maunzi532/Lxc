package v.gegner;

import v.*;
import feld.*;

import java.awt.*;

public class Valerie extends Gegner
{
	private final double maxsize;
	private double vorLeben;

	public Valerie(double level)
	{
		super(level);
		size = 10;
		maxspeed = 0.5;
		maxsize = Math.sqrt(level) * 25;
		vorLeben = leben;
	}

	public void denken()
	{
		if(vorLeben > leben)
			size -= vorLeben - leben;
		if(size < 10)
			size = 10;
		vorLeben = leben;
		if(FL.ich.x != x && FL.ich.y != y)
		{
			xs += xdiff() * speed / fact();
			ys += ydiff() * speed / fact();
		}
		if(size < maxsize && size + Math.sqrt(level) / 4 > maxsize)
			size = maxsize;
		if(size < maxsize)
			size += Math.sqrt(level) / 4;
	}

	public boolean isEigener()
	{
		return false;
	}

	protected double maxL()
	{
		return 200 * level;
	}

	public Color farbe()
	{
		return Color.DARK_GRAY;
	}

	public void aussehen(Graphics2D gf, int weit, int hoch)
	{
		VFeld.leuchten(gf, 0);
		gf.fillRect((int) (weit / 2 + x  - size), (int) (hoch / 2 + y - size),
				(int) (size * 2), (int) (size * 2));
		Polygon tL = new Polygon(new int[8], new int[8], 8);
		tL.xpoints = new int[]{-5, 5, 10, 10, 5, -5, -10, -10};
		tL.ypoints = new int[]{10, 10, 5, -5, -10, -10, -5, 5};
		V.tL(weit, hoch, this, 10, tL);
		super.aussehen(gf, tL);
	}
}