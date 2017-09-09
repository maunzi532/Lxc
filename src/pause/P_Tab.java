package pause;

import rahmen.*;

import java.awt.*;
import java.awt.image.*;

public abstract class P_Tab
{
	public abstract void aufruf();
	protected abstract void sehen();
	public abstract void tick();
	public abstract void weg();

	private BufferedImage flach;
	protected Graphics2D gp;
	private static final int weit = Rahmen.tw(0.65);
	private static final int hoch = Rahmen.th(0.65);

	protected P_Tab()
	{
		flach = new BufferedImage(weit, hoch, BufferedImage.TYPE_INT_ARGB_PRE);
		gp = flach.createGraphics();
	}

	public void sehen1()
	{
		//Alles weg
		gp.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		gp.setColor(new Color(0, true));
		gp.fillRect(0, 0, weit, hoch);
		//Transparent
		gp.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		gp.setColor(new Color(0, 0, 0, 200));
		gp.fillRect(0, 0, weit, hoch);
		sehen();
	}

	public void auf(Graphics2D gd, int w, int h, double ver, Boolean nxr)
	{
		if(nxr == null)
			gd.drawImage(flach, w / 2 - weit / 2, h / 2 - hoch / 2, null);
		else
		{
			int xv = (int) (Rahmen.fw * 2 / 3 * ver);
			int kw = (int) (weit * ver / 2);
			int kh = (int) (hoch * ver / 2);
			if(nxr)
				xv = -xv;
			gd.drawImage(flach, w / 2 - weit / 2 + xv + kw,
					h / 2 - hoch / 2 + kh, weit - kw * 2, hoch - kh * 2, null);
		}
	}
}