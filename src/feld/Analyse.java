package feld;

import rahmen.*;
import v.*;

public class Analyse
{
	public static V analysiere;

	static void wahlAnalyse()
	{
		V nahe = null;
		double abstand = 100;
		for(Object v : Listen.alle.arr)
		{
			double neu = Listen.nahe((V) v, InV.mausx + FL.ich.x, InV.mausy + FL.ich.y);
			if(neu < abstand)
			{
				nahe = (V) v;
				abstand = neu;
			}
		}
		analysiere = nahe;
	}
}