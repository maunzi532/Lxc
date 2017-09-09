package feld;

import v.*;
import v.gegner.*;
import umgebung.*;
import v.ver.*;

public class Listen
{
	public static NonMoveList<V> alle;
	public static NonMoveList<Explosion> expl;
	public static NonMoveList<WandTeil> wand;
	public static NonMoveList<Gegner> gegner;
	public static NonMoveList<R_S> laser;
	public static NonMoveList<GLaser> glaser;

	public static void neuStart()
	{
		alle = new NonMoveList<>();
		expl = new NonMoveList<>();
		wand = new NonMoveList<>();
		gegner = new NonMoveList<>();
		laser = new NonMoveList<>();
		glaser = new NonMoveList<>();
	}

	public static void spawnByNo(int no, double level)
	{
		switch(no)
		{
			case 1: alle.add(new Tom(level)); break;
			case 2: alle.add(new Lena(level)); break;
			case 3: alle.add(new Nex(level)); break;
			case 4: alle.add(new Anne(level)); break;
			case 5: alle.add(new Valerie(level)); break;
			case 6: alle.add(new IVIL(level)); break;
			case 7: alle.add(new Nick(level)); break;
			case 8: alle.add(new Udo(level)); break;
		}
	}

	public static void alleBewegen()
	{
		for(int i = 0; i < expl.ende; i++)
			if(expl.get(i) != null && expl.get(i).damage())
				expl.remove(i);
		for(int i = 0; i < wand.ende; i++)
			if(wand.get(i) != null)
				wand.get(i).damage();
		for(int i = 0; i < alle.ende; i++)
			if(alle.arr[i] != null && alle.get(i).bewegen())
			{
				if(alle.get(i) instanceof Gegner)
					gegner.remove((Gegner) alle.get(i));
				if(alle.get(i) instanceof R_S)
					laser.remove((R_S) alle.get(i));
				if(alle.get(i) instanceof GLaser)
					glaser.remove((GLaser) alle.get(i));
				alle.remove(i);
				//i--;
			}
		alle.sammeln();
		expl.sammeln();
		wand.sammeln();
		gegner.sammeln();
		laser.sammeln();
		glaser.sammeln();
	}

	public static void explode(double x, double y, double power, double radius,
			int dauer, ExplTyp typ)
	{
		expl.add(new Explosion(x, y, power, radius, dauer, typ));
	}

	public static double nahe(V a, V b)
	{
		if(a == null || b == null)
			return Double.POSITIVE_INFINITY;
		return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}

	public static double nahe(V a, double x, double y)
	{
		if(a == null)
			return Double.POSITIVE_INFINITY;
		return Math.sqrt((a.x - x) * (a.x - x) + (a.y - y) * (a.y - y));
	}
}