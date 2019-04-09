import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Main {
    public static void main(String[] args) {
        BufferedImage[] img = new BufferedImage[5];
        try {
            img[0] = ImageIO.read(new File("src/img/partitura.jpg"));
        } catch (IOException e) {
        	e.printStackTrace();
        }
        new MusicSheet(img);
    }
}