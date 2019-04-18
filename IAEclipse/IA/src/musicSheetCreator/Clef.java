package musicSheetCreator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public abstract class Clef extends Simbol {
	protected BufferedImage img;

	public Clef(int x, int y) {
		super(x, y);
	}

	public static Clef generateRandomClef() {
		Random rn = new Random();
		switch(rn.nextInt(2)) {
		case 0:
			return new SolClef();
		case 1:
			return new FaClef();
		default:
			return new SolClef();
		}
	}

	public void paint(Graphics2D g2d) {
		g2d.drawImage(this.getImg(), null, this.getCenter().getX(), this.getCenter().getY());
	}
	
	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}
}

class SolClef extends Clef {
	private static final int x = 20, y = 20;
	public SolClef() {
		super(x, y);
		try {
			super.setImg(ImageIO.read(new File("claveSol.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

class FaClef extends Clef {
	private static final int x = 35, y = 56;
	public FaClef() {
		super(x, y);
		try {
			super.setImg(ImageIO.read(new File("clave-fa.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
