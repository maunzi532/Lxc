package welle;

import feld.*;

public class Welle extends Spawn
{
	public final int zeit;
	private final int weit;
	private final int hoch;
	private final double[] gegnertypen;
	private final double[] gegnerlv;
	private final double[] gegnerInsg;
	private final double[] startZ;
	private final double[] endZ;

	private final int[] gespawnt;

	private final double[][] wandDaten;
	
	private Welle(int zeit, int weit, int hoch, double[] gegnertypen, double[] gegnerlv, double[] gegnerInsg,
			double[] startZ, double[] endZ, double[][] wandDaten)
	{
		this.zeit = zeit;
		this.weit = weit;
		this.hoch = hoch;
		this.gegnertypen = gegnertypen;
		this.gegnerlv = gegnerlv;
		this.gegnerInsg = gegnerInsg;
		this.startZ = startZ;
		this.endZ = endZ;
		gespawnt = new int[gegnertypen.length];
		this.wandDaten = wandDaten;
	}
	
	private double getN(int i, int z)
	{
		return (float) (z - startZ[i]) / (endZ[i] - startZ[i]) * gegnerInsg[i];
	}

	public static Welle textZuWelle(String text)
	{
		try
		{
			@SuppressWarnings("HardcodedLineSeparator")
			String[] arr = text.split("\n");
			double[][] daten = new double[arr.length][];
			for(int i = 0; i < arr.length; i++)
			{
				String[] inArr = arr[i].split(",");
				daten[i] = new double[inArr.length];
				for(int j = 0; j < inArr.length; j++)
					daten[i][j] = Double.parseDouble(inArr[j]);
			}
			return new Welle((int)daten[0][0], (int)daten[0][1], (int)daten[0][2],
					daten[1], daten[2], daten[3], daten[4], daten[5],
					new double[][]{daten[6], daten[7], daten[8], daten[9]});
		}catch(Exception e){}
		System.exit(34);
		return null;
	}

	public void xSpawn()
	{
		if(FL.zeit < zeit)
			for(int i = 0; i < gegnertypen.length; i++)
				if(zeit >= startZ[i] && zeit <= endZ[i])
				{
					double spawnWert = getN(i, zeit) - gespawnt[i];
					while(spawnWert > 1)
					{
						Listen.spawnByNo((int) gegnertypen[i], gegnerlv[i]);
						gespawnt[i]++;
						spawnWert--;
					}
					if(spawnWert > 0 && spawnWert > FL.r.nextFloat())
					{
						Listen.spawnByNo((int)gegnertypen[i], gegnerlv[i]);
						gespawnt[i]++;
					}
				}
	}

	public void platziereMauern()
	{
		VFeld.weit = weit;
		VFeld.hoch = hoch;
		for(int i = 0; i < wandDaten[0].length; i++)
			Listen.wand.add(new WandTeil(wandDaten[0][i],
					wandDaten[1][i], wandDaten[2][i], wandDaten[3][i]));
	}
}