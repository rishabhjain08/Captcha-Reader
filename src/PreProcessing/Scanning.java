package PreProcessing;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Scanning {
    int[] pix;
    int w,h;
    static int UP=-1,DOWN=1;
    PixelGrabber grab;
    public Scanning(File f) throws IOException, InterruptedException{
        Image img=ImageIO.read(f);
    }
    public static void main(String args[]) throws IOException, InterruptedException{
        Scanning scanning = new Scanning(new File("C:/Users/ci/Desktop/images/test7.png"));
    }

}
