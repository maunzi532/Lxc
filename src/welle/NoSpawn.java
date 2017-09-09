package welle;

import feld.*;

public class NoSpawn extends Spawn
{
	private static int[][] wandDaten = new int[][]{
			{-240, -200, 0, -70, -70, 70, 70, -40, -40, 40, 40, 160, 200, 200},
			{0, 90, 0, -70, 70, -70, 70, -40, 40, -40, 40, 0, -90, 90},
			{20, 50, 40, 30, 30, 30, 30, 30, 30, 30, 30, 20, 50, 50},
			{100, 20, 40, 30, 30, 30, 30, 30, 30, 30, 30, 100, 20, 20}};

	public void xSpawn(){}

	public void platziereMauern()
	{
		double zFaktor = 600;
		for(int i = 0; i < wandDaten[0].length; i++)
			Listen.wand.add(new WandTeil(
					wandDaten[0][i] * VFeld.weit / zFaktor,
					wandDaten[1][i] * VFeld.weit / zFaktor,
					wandDaten[2][i] * VFeld.weit / zFaktor,
					wandDaten[3][i] * VFeld.weit / zFaktor));
	}
}