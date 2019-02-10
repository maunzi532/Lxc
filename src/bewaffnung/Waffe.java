package bewaffnung;

import v.*;

import java.util.*;

public abstract class Waffe
{
	public static final ArrayList<Class> wList = new ArrayList<>();
	static
	{
		wList.add(XN.class);
		wList.add(FPL.class);
		wList.add(S4.class);
		wList.add(CHY.class);
	}

	public static Waffe instanz(Class cl, V besitzer)
	{
		try{
			return (Waffe)(cl.getConstructors()[0].newInstance(besitzer));
		}catch(Exception e){return null;}
	}

	Waffe(V besitzer)
	{
		this.besitzer = besitzer;
	}

	V besitzer;

	protected double lvNachladen = 1;
	protected double lvFeuerrate = 1;
	protected double lvMunition = 1;
	protected double lvSchaden = 1;
	protected double lvSpeed = 1;
	protected double lvRadius = 1;


	public abstract void tick(boolean lcl);
	public abstract void nTick();
	//public abstract String status();
	public abstract double mLeiste();
	public abstract double cldLeiste();
	public abstract void endExpl();
	public abstract boolean tauschenGeht();
	public abstract void nachTauschen();
	public abstract String getName();
	public abstract boolean autoTausch();
}