
package Utility;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountPixels {

    public int getConnectedPixels(BufferedImage img,Point point){
        int w=img.getWidth();
        int h=img.getHeight();
//        int i=0;
        int count=0;
//        int[] pix=new int[w*h];
//        PixelGrabber grab=new PixelGrabber(img,0,0,w,h,pix,0,w);
//        try {
//            grab.grabPixels();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(CountPixels.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        int color = pix[point.x + point.y * w];
        ArePointsConnected connected=new ArePointsConnected();
        System.out.print("in");
        int x=point.x,k;
        boolean found=false;
        while(x>=0&&found){
            found=false;
            for(k=0;k<h;k++){
                if(connected.initialize(img, null, point.x, point.y, x, k, true, false)){
                    found=true;
                    count++;
                }
            }
            x--;
        }
        x=point.x+1;
        found=true;
        while(x<w&&found){
            found=false;
            for(k=0;k<h;k++){
                if(connected.initialize(img, null, point.x, point.y, x, k, true, false)){
                    found=true;
                    count++;
                }
            }
            x++;
        }
        System.out.print("out");
        return count;
    }

    public int getCount(BufferedImage img,int color){
        int w=img.getWidth();
        int h=img.getHeight();
        int i=0;
        int count=0;
        int[] pix=new int[w*h];
        PixelGrabber grab=new PixelGrabber(img,0,0,w,h,pix,0,w);
        try {
            grab.grabPixels();
        } catch (InterruptedException ex) {
            Logger.getLogger(CountPixels.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(i<w*h){
            if(color==pix[i])
                count++;
            i++;
        }
        return count;
    }

}
