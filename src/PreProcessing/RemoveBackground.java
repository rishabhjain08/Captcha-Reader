package PreProcessing;

import Utility.BackgroundColor;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Utility.Colour;
import java.awt.Color;
public class RemoveBackground {

    private BufferedImage img1;
    private int[] pix;
    private int w,h,color;
    private PixelGrabber grab;
    private Colour Colour;
    public RemoveBackground(BufferedImage img2) throws IOException, InterruptedException{
        img1=img2;
        w=img1.getWidth();
        h=img1.getHeight();
        pix=new int[w*h];
        grab = new PixelGrabber(img1, 0, 0, w, h, pix, 0, w);
        grab.grabPixels();

        color=new BackgroundColor().getBackgroundColor(img1);
        Colour=new Colour(new Color(color));

        this.setPixelsWhite();
        ImageIO.write(img1, "png", new File("C:/Users/ci/Desktop/background removed.png"));
    }
        private void setPixelsWhite(){
        int i=0;
        while(i<w*h){
            if(this.setWhite(pix[i])){
                img1.setRGB(i%w, (int)i/w,-1);
            }
            i++;
        }
    }
        private boolean setWhite(int pixel){
            if(!Colour.isContrastGood(new Color(pixel)))
                return true;
            return false;

//            if(pixel>-6000000)
//                return true;
//            return false;
        }
    public Image getModifiedImage(){
        return img1;
    }
}