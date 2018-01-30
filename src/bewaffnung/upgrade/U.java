package bewaffnung.upgrade;

import java.util.*;

public class U
{
	public static HashMap<String, Integer> levels;
	public static HashMap<String, Upgrade> upgrades;

	public static void init()
	{
		levels = new HashMap<>();
		upgrades = new HashMap<>();

		put("XN-Schaden", new QLUpgrade(100, 50, 3, 1));
		put("XN-Speed", new EndUpgrade(100, 100, 10, 20, 5));
		put("XN-Akku", new QLUpgrade(100, 40, 200, 10));
		put("XN-Bereit", new QLUpgrade(50, 20, 2, 1));
		put("XN-Feuerrate", new QLUpgrade(100, 100, 2, 0.1));
		put("XN-Nachladen", new QLUpgrade(100, 100, 1, 0.05));

		put("FPL-Schaden", new QLUpgrade(100, 50, 5, 1));
		put("FPL-Speed", new EndUpgrade(100, 100, 2, 8, 5));
		put("FPL-Fuel", new QLUpgrade(100, 40, 600, 50));
		put("FPL-Bereit", new QLUpgrade(50, 20, 7, 1));
		put("FPL-Feuerrate", new QLUpgrade(100, 100, 3, 0.5));
		put("FPL-FuelZeit", new EndUpgrade(100, 100, 50, 1, 5));

		put("S4-Schaden", new QLUpgrade(100, 50, 60, 5));
		put("S4-Speed", new EndUpgrade(100, 100, 15, 40, 5));
		put("S4-Munition", new QLUpgrade(100, 100, 6, 1));
		put("S4-Feuerrate", new EndUpgrade(100, 200, 20, 1, 5));
		put("S4-Auf", new EndUpgrade(100, 100, 10, 1, 5));
		put("S4-Rein", new EndUpgrade(100, 100, 5, 1, 5));
		put("S4-Zu", new EndUpgrade(100, 100, 5, 1, 5));

		put("CHY-Schaden", new QLUpgrade(100, 50, 40, 5));
		put("CHY-Speed", new EndUpgrade(100, 50, 5, 15, 5));
		put("CHY-Cooldown", new EndUpgrade(100, 100, 20, 1, 5));

		put("Tausch", new EndUpgrade(100, 100, 10, 0, 5));
	}

	public static void put(String s, Upgrade u)
	{
		levels.put(s, 0);
		upgrades.put(s, u);
	}

	public static double val(String s)
	{
		return upgrades.get(s).wal(levels.get(s));
	}

	public static double uval(String s)
	{
		return upgrades.get(s).wal(levels.get(s) + 1) - upgrades.get(s).wal(levels.get(s));
	}

	public static int nextuk(String s)
	{
		return upgrades.get(s).upgradek(levels.get(s) + 1);
	}

	public static void levelup(String s)
	{
		levels.put(s, levels.get(s) + 1);
	}

	public static int getLV(String s)
	{
		return levels.get(s);
	}
}