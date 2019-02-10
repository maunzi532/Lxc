package welle;

import feld.*;

public class SVSpawn extends Spawn
{
	private int gcld;
	private int next;
	private double nlvl;
	private static final int[] clds = new int[]{1, 10, 60, 40, 400, 600, 200, 800, 300};
	private static final int[] chancen = new int[]{
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
			4, 4, 4, 4, 6, 5, 5, 7, 7, 8,
			6, 6, 6, 6, 6, 8, 8, 8, 8, 8};
	//40, 20, 20, 4, 2, 6, 2, 6

	public void xSpawn()
	{
		int i = 0;
		do{
			gcld--;
			if(gcld <= 0)
			{
				Listen.spawnByNo(next, nlvl);
				chance(FL.level);
				gcld = clds[next];
			}
			i++;
		}while(FL.r.nextInt(FL.schwierigkeit) + i * FL.schwierigkeit < FL.zeit + FL.schwierigkeit * 3);
	}

	public void platziereMauern()
	{
		chance(1);
		gcld = clds[next];
	}

	private void chance(double level)
	{
		next = chancen[FL.r.nextInt(chancen.length)];
		if(next == 4 && FL.zeit < 900)
			next = 1;
		if(next == 5 && FL.zeit < 1200)
			next = 1;
		if(next == 6 && FL.zeit < 300)
			next = 1;
		if(next == 7 && FL.zeit < 1500)
			next = 1;
		if(next == 8 && FL.zeit < 600)
			next = 1;
		nlvl = level;
	}
}