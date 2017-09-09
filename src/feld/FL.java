package feld;

import bewaffnung.upgrade.*;
import pause.*;
import pause.tab.*;
import rahmen.*;
import umgebung.*;
import v.ver.*;
import welle.*;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class FL
{
	public static Random r;
	public static Ich ich;
	public static final double abstand = 100;
	public static int zeit;
	public static final int schwierigkeit = 1000;
	public static double level;
	public static boolean mode = true;
	private static int wNummer = 0;
	private static Spawn spawn;
	public static HashMap<String, Integer> cheats;

	public static void start()
	{
		if(ThaCre.arg0 != null)
			mode = false;
		cheats = new HashMap<>();
		//cheats.put("Skip", 1000);
		//cheats.put("Leben", 1000000);
		if(ThaCre.arg0 != null && ThaCre.arg0.startsWith("T:"))
		{
			mode = true;
			cheats = new HashMap<>();
			fillCheatList(ThaCre.arg0.substring(2));
		}
		r = new Random();
		Listen.neuStart();
		spawn = new NoSpawn();
		spawn.platziereMauern();
		P_Menu.setStatus(P_Typ.START);
		U.init();
		VFeld.neuStart();
	}

	public static void neuStarten()
	{
		Listen.neuStart();
		ich = new Ich(Tab_Waffen.wL, Tab_Waffen.wR);
		Listen.alle.add(ich);
		if(cheats != null && cheats.containsKey("Start"))
			zeit = cheats.get("Start");
		else
			zeit = 0;
		if(mode)
			spawn = new SVSpawn();
		else
		{
			FL.wNummer++;
			try
			{
				if(ThaCre.arg1 != null)
				{
					if(wNummer == 1)
						spawn = Welle.textZuWelle(ThaCre.liesDatei(ThaCre.arg0 + File.separator +
								ThaCre.arg1 + T.set("LW2")));
					else
						throw new Exception();
				}
				else
					spawn = Welle.textZuWelle(ThaCre.liesDatei(ThaCre.arg0 + File.separator +
							T.set("LW1") + wNummer + T.set("LW2")));
			}
			catch(Exception e)
			{
				ThaCre.fl.fr.setVisible(false);
				JOptionPane.showMessageDialog(ThaCre.fl.fr,
						T.tex("Ende1") + (wNummer - 1) + T.tex("Ende2"),
						"", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
		}
		spawn.platziereMauern();
		VFeld.neuStart();
	}

	public static void bewegen()
	{
		InV.mausxy();
		if(ThaCre.ta.useKeys[7])
			Analyse.wahlAnalyse();
		Listen.alleBewegen();
		spawn.xSpawn();
		if(ich.leben > 0)
		{
			if(cheats != null && cheats.containsKey("Skip"))
				zeit += cheats.get("Skip");
			else
				zeit++;
		}
		level = 1f + (double) zeit / schwierigkeit;
		if(Listen.gegner.anz == 0 && !mode && zeit > ((Welle) spawn).zeit && ich.leben > 0)
			P_Menu.setStatus(P_Typ.ZWISCHEN);
		ich.tauschen();
	}

	private static void fillCheatList(String par)
	{
		String[] ch = par.split(",");
		for(int i = 0; i < ch.length; i++)
			if(ch[i].contains("="))
			{
				String[] ech = ch[i].split("=");
				try
				{
					cheats.put(ech[0], Integer.parseInt(ech[1]));
				}catch(Exception e){}
			}
	}
}