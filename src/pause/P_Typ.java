package pause;

public enum P_Typ
{
	NICHT(false, false),
	ING_PAUSE(true, false),
	ZWISCHEN(true, true),
	START(true, true);

	public boolean istPause;
	public boolean upgrades;

	P_Typ(boolean istPause, boolean upgrades)
	{
		this.istPause = istPause;
		this.upgrades = upgrades;
	}
}