package umgebung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TastenAnalyse
{
	private final boolean[] keys;
	private int[][] moves;
	public boolean[] useKeys;
	public Point maus = new Point();

	public TastenAnalyse()
	{
		keys = new boolean[1024];
		moves = new int[][]
		{
				{37, 65, 74},//Links, A, J = 0 (Bewegung)
				{38, 87, 73},//Oben, W, I = 1
				{39, 68, 76},//Rechts, D, L = 2
				{40, 83, 75},//Unten, S, 75 = 3
				{701},//Kilck L = 4 (Schießen)
				{32},//Leertaste = 5 (Upgraden)
				{8},//Loeschen = 6 (Beenden)
				{69},//E = 7 (Analyse)
				{},//8 (unbenutzt)
				{81, 703},//Q, Klick R = 9
				{88, 45},//X, - = 10 (Rechts scrollen)
				{80, 27},//P, Esc = 11 (Pause)
				{89, 46},//Y, . = 12 (Links scrollen)
				{49, 97, 35},//1 = 13 (XN-Welle)
				{50, 98, 225},//2 = 14 (Teamie)
				{51, 99, 34},//3 = 15 (Warp)
				{800},//MR Vor = 16 (Scrollen)
				{801}//MR Rück = 17
		};
		useKeys = new boolean[moves.length];
	}

	public void addToFrame(JFrame f)
	{
		f.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e){}
			public void keyReleased(KeyEvent e)
			{
				keys[e.getKeyCode()] = false;
				//System.out.println(e.getKeyCode()+"+");
			}
			public void keyPressed(KeyEvent e)
			{
				keys[e.getKeyCode()] = true;
				//System.out.println(e.getKeyCode()+"-");
			}
		});
		f.addMouseListener(new MouseListener()
		{
			public void mouseReleased(MouseEvent e)
			{
				keys[700+e.getButton()] = false;
			}
			public void mousePressed(MouseEvent e)
			{
				keys[700+e.getButton()] = true;
			}
			public void mouseExited(MouseEvent e){}
			public void mouseEntered(MouseEvent e){}
			public void mouseClicked(MouseEvent e){}
		});
		f.addMouseWheelListener(new MouseWheelListener()
		{
			public void mouseWheelMoved(MouseWheelEvent e)
			{
				if(e.getWheelRotation() > 0)
					keys[800] = true;
				else if(e.getWheelRotation() < 0)
					keys[801] = true;
			}
		});
	}

	public void move()
	{
		boolean use;
		for(int b = 0; b < moves.length; b++)
		{
			use = false;
			for(int i : moves[b])
				if(keys[i])
					use = true;
			useKeys[b] = use;
		}
		keys[800] = false;
		keys[801] = false;
		maus = MouseInfo.getPointerInfo().getLocation();
		maus.translate(-ThaCre.fl.fr.getX(), -ThaCre.fl.fr.getY());
	}

	public void feedMoves(String feed)
	{
		String[] na = feed.split("\n");
		moves = new int[na.length][];
		for(int i = 0; i < na.length; i++)
		{
			if(na[i].isEmpty())
			{
				moves[i] = new int[0];
				continue;
			}
			String[] nb = na[i].split(" ");
			moves[i] = new int[nb.length];
			for(int j = 0; j < nb.length; j++)
				moves[i][j] = Integer.parseInt(nb[j]);
		}
		useKeys = new boolean[moves.length];
	}
}