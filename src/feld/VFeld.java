package feld;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import pause.*;
import rahmen.*;
import v.*;

public class VFeld
{
	private static final double SWGF = 5; //Irgendwas mit Mauern
	private static final int farbEndZeit = 6000;
	public static int mZ;
	//Feldgröße (Virtuell)
	public static int weit = 1000;
	public static int hoch = 600;
	public static Color zf2 = Color.WHITE;
	public static BufferedImage bild;
	private static Graphics2D gf;

	public static void neuStart()
	{
		bild = new BufferedImage(weit, hoch, BufferedImage.TYPE_INT_ARGB_PRE);
		gf = bild.createGraphics();
	}

	public static void seheheh(boolean nexp)
	{
		mZ++;
		vorSehen();
		sehen(nexp);
	}

	private static void sehen(boolean nexp)
	{
		gf.setColor(new Color(20, 20, 20));
		//gf.drawImage(Grafiken.grafiken.get(Lang.z("Hintergrund")), 0, 0, weit, hoch, null);
		gf.fillRect(0, 0, weit, hoch);
		for(Object ex : Listen.wand.arr)
		{
			if(ex != null)
			{
				WandTeil x = (WandTeil) ex;
				int wGF = (int) ((x.xw + x.yw) / SWGF);
				int[] xp = new int[wGF];
				int[] yp = new int[wGF];
				if(x.xw > x.yw)
					for(int i = 0; i < wGF; i++)
					{
						xp[i] = (int) (weit / 2 + x.x - x.xw + Math.abs((i - wGF / 2d) / wGF) * x.xw * 4);
						yp[i] = (int) (hoch / 2 + x.y - x.yw + FL.r.nextDouble() * x.yw * 2);
					}
				else if(x.yw > x.xw)
					for(int i = 0; i < wGF; i++)
					{
						xp[i] = (int) (weit / 2 + x.x - x.xw + FL.r.nextDouble() * x.xw * 2);
						yp[i] = (int) (hoch / 2 + x.y - x.yw + Math.abs((i - wGF / 2d) / wGF) * x.yw * 4);
					}
				else
					for(int i = 0; i < wGF; i++)
					{
						xp[i] = (int) (weit / 2 + x.x - x.xw + FL.r.nextDouble() * x.xw * 2);
						yp[i] = (int) (hoch / 2 + x.y - x.yw + FL.r.nextDouble() * x.yw * 2);
					}
				gf.setColor(Color.YELLOW);
				gf.fill(new Polygon(xp, yp, wGF));
			}
		}
		for(Object ex : Listen.alle.arr)
			if(ex != null)
				((V) ex).aussehen(gf, weit, hoch);
		for(Object ex : Listen.expl.arr)
			if(ex != null)
			{
				Explosion x = (Explosion) ex;
				if(x.typ == ExplTyp.NICHT_AUF_GEGNER)
					gf.setColor(new Color(1.0f, (float) x.dauer / x.insgDauer,
							0, nexp ? 1f : ((float) x.dauer / x.insgDauer / 2)));
				if(x.typ == ExplTyp.NUR_AUF_FEINDE)
					gf.setColor(new Color(1f - ((float) x.dauer / x.insgDauer), 0,
							(float) x.dauer / x.insgDauer, nexp ? 1f : ((float) x.dauer / x.insgDauer / 2)));
				double x1 = weit / 2 + x.x;
				double y1 = hoch / 2 + x.y;
				double xy2 = x.radius * 2 * (1.0 - (double) x.dauer / x.insgDauer);
				if(!P_Menu.p.istPause)
				{
					x.pausedX = FL.r.nextInt(5) - 2;
					x.pausedY = FL.r.nextInt(5) - 2;
				}
				if(nexp)
				{
					gf.draw(new Ellipse2D.Double(x1 - xy2 / 2 + x.pausedX,
							y1 - xy2 / 2 + x.pausedY, xy2, xy2));
					gf.drawLine((int)(x1 - xy2 / 2.82 + x.pausedX),
							(int)(y1 - xy2 / 2.82 + x.pausedY),
							(int)(x1 + xy2 / 2.82 + x.pausedX),
							(int)(y1 + xy2 / 2.82 + x.pausedY));
					gf.drawLine((int)(x1 + xy2 / 2.82 + x.pausedX),
							(int)(y1 - xy2 / 2.82 + x.pausedY),
							(int)(x1 - xy2 / 2.82 + x.pausedX),
							(int)(y1 + xy2 / 2.82 + x.pausedY));
				}
				else
					gf.fill(new Ellipse2D.Double(x1 - xy2 / 2 + x.pausedX,
							y1 - xy2 / 2 + x.pausedY, xy2, xy2));
			}
		leuchten(gf, 1);
		if(!P_Menu.p.istPause)
		{
			int[] z = new int[]{(int) (FL.ich.x + weit / 2), (int) (FL.ich.y + hoch / 2),
					(int) (InV.mausx + FL.ich.x + weit / 2), (int) (InV.mausy + FL.ich.y + hoch / 2)};
			gf.fill(new Polygon(new int[]{z[0] - 10, z[0] + 10, z[2] + 10, z[2] - 10},
					new int[]{z[1], z[1], z[3], z[3]}, 4));
			gf.fill(new Polygon(new int[]{z[0], z[0], z[2], z[2]},
					new int[]{z[1] - 10, z[1] + 10, z[3] + 10, z[3] - 10}, 4));
		}
		/*if(FL.ich != null)
			for(int i = 50; i < 150; i++) //schön aber laggt
			{
				gf.setColor(new Color(305-i, i-50, i-50, 50-Math.abs(i-100)));
				gf.draw(new Ellipse2D.Double((int)(weit/2+FL.ich.x-i+0.2),
						(int)(hoch/2+FL.ich.y-i+0.2), i*2, i*2));
			}*/
		if(Listen.alle.notcontains(Analyse.analysiere))
			Analyse.analysiere = null;
		if(Analyse.analysiere != null)
			analyseZeiger(Analyse.analysiere);
	}

	private static void analyseZeiger(V zeige)
	{
		int a = 20 + (int)zeige.size;
		int b = 20 + (int)(zeige.size/4);
		int c = 5;
		gf.setColor(Color.WHITE);
		int[][] set = new int[][]{
				{-a, -a, b, c},
				{-a, -a, c, b},
				{a-b, -a, b, c},
				{a-c, -a, c, b},
				{-a, a-c, b, c},
				{-a, a-b, c, b},
				{a-b, a-c, b, c},
				{a-c, a-b, c, b}};
		for(int[] teil : set)
			gf.fillRect((int)zeige.x + weit/2 + teil[0],
					(int)zeige.y + hoch/2 + teil[1], teil[2], teil[3]);
		gf.setFont(new Font(Rahmen.FONT, 0, 10));
		gf.drawString(zeige.analyseDesc(),
				(int)zeige.x + weit/2 - 30 - (int)zeige.size,
				(int)zeige.y + hoch/2 + 40 + (int)zeige.size);
	}

	public static void leuchten(Graphics2D gf, int typ)
	{
		switch(typ)
		{
			case 0:
			case 1:
				gf.setColor(new Color(255, 255, 255, Math.abs(mZ * 3 % 256 - 128) / 4 + 16));
				break;
			case 2:
				gf.setColor(new Color(255, 255, 255, Math.abs(mZ * 12 % 256 - 128) + 64));
		}
	}

	private static void vorSehen()
	{
		if(FL.mode)
			if(FL.zeit < farbEndZeit)
				zf2 = new Color(Color.HSBtoRGB(((150 - 200 * FL.zeit /
								farbEndZeit + 256 * 10) % 256) / 256f, 1,
						((-10 - 125 * FL.zeit / farbEndZeit + 256 * 10) % 256) / 256f));
			else
				zf2 = new Color(Color.HSBtoRGB(206f / 256 + Math.abs((FL.zeit * FL.zeit /
						(farbEndZeit * 3 / 5)) % 256 - 128) / 1024f, 1, 100f / 256 +
						Math.abs((FL.zeit * FL.zeit / (farbEndZeit * 3 / 5)) % 256 - 128) / 1280f));
	}
}