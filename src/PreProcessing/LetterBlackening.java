//After Noise has been reduced
package PreProcessing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class LetterBlackening {

    BufferedImage img;
    private int width,height;
    private PixelGrabber grab;
    private int[] pix;
    public LetterBlackening(Image img1) throws IOException{
        img=(BufferedImage)img1;
        width=img.getWidth();
        height=img.getHeight();
        pix=new int[width*height];
        grab=new PixelGrabber(img,0,0,width,height,pix,0,width);
        try {
            grab.grabPixels();
        } catch (InterruptedException ex) {
            Logger.getLogger(LetterBlackening.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.blacken();
        ImageIO.write(img, "png", new File("C:/Users/ci/Desktop/capimages/letter blackened.png"));
    }

    private void blacken(){
        int i=0;
        while(i<width*height){
            if(pix[i]!=-1)
                img.setRGB(i%width, (int)i/width,-16777216);
            i++;
        }
    }
    public Image getModifiedImage(){
        return img;
    }

}
