package umgebung;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class LFrame
{
	public final JFrame fr;
	private final BufferedImage img;
	public final Graphics2D gd;
	public static Dimension scs = Toolkit.getDefaultToolkit().getScreenSize();

	public LFrame()
	{
		fr = new JFrame();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLayout(null);
		fr.setUndecorated(true);
		fr.setSize(scs);
		img = new BufferedImage(scs.width,
				scs.height, BufferedImage.TYPE_INT_ARGB_PRE);
		gd = img.createGraphics();
	}

	public void init()
	{
		fr.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE),
				new Point(), ""));
		fr.setVisible(true);
		fr.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
	
	public void imgAufPl()
	{
		fr.getGraphics().drawImage(img, 0, 0, null);
	}
}