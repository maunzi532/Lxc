package umgebung;

import java.io.*;

public class LSound
{
	private static File f;

	public static void lV()
	{
		f = new File("Space 1990-B.wav");
	}

	public static void lS()
	{
		/*try
		{
			AudioInputStream stream;
			AudioFormat format;
			DataLine.Info info;
			Clip clip;

			stream = AudioSystem.getAudioInputStream(f);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		}
		catch (Exception e){}*/
	}
}