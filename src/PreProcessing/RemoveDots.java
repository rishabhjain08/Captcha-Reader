
package PreProcessing;

import Utility.ArePointsConnected;
import Utility.CountPixels;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class RemoveDots {

    private BufferedImage img1;
    private int[] pix;
    private int w,h;
    private PixelGrabber grab;
    private ArePointsConnected connected;
    private CountPixels pixCount;
        public RemoveDots(BufferedImage img2) throws IOException, InterruptedException{
            img1=img2;
            w=img1.getWidth();
            h=img1.getHeight();
            pix=new int[w*h];
            pixCount=new CountPixels();
            connected=new ArePointsConnected();
            this.fillGaps();
            ImageIO.write(img1, "png", new File("C:/Users/ci/Desktop/"+"gaps filled"+".png"));
            this.removeDots();
            ImageIO.write(img1, "png", new File("C:/Users/ci/Desktop/dots removed+"+".png"));
    }

        private void fillGaps(){
            BufferedImage remimg=img1.getSubimage(0, 0, img1.getWidth(), img1.getHeight());
            grab = new PixelGrabber(img1, 0, 0, w, h, pix, 0, w);
            try {
                grab.grabPixels();
            } catch (InterruptedException ex) {
                Logger.getLogger(RemoveDots.class.getName()).log(Level.SEVERE, null, ex);
            }
            int i=0,k,l=0;
            boolean connect;
            boolean[] dir=new boolean[8];
            int[] positions=new int[8];
            boolean break1;
            int count=0;
            while(i<w*h){
                if(pix[i]!=-1){
                    i++;
                    continue;
                }
                for(k=0;k<8;k++)
                    dir[k]=true;
                connect=false;
                positions[0]=i-w-1;
                positions[1]=i-w;
                positions[2]=i-w+1;
                positions[3]=i-1;
                positions[4]=i+1;
                positions[5]=i+w-1;
                positions[6]=i+w;
                positions[7]=i+w+1;

                if(i>=w&&pix[i-w]==pix[i]){
                    dir[1]=false;
                }
                if(i + w < w * h && pix[i + w] == pix[i]){
                    dir[6]=false;
                }
                if(i > 0 && pix[i] == pix[i - 1]){
                    dir[3]=false;
                }
                if(i + 1 < w * h && pix[i + 1] == pix[i]){
                    dir[4]=false;
                }
                if(i>=w+1&&pix[i-w-1]==pix[i]){
                    dir[0]=false;
                }
                if(i>=w-1&&pix[i-w+1]==pix[i]){
                    dir[2]=false;
                }
                    if(i+w-1<w*h&&pix[i+w-1]==pix[i]){
                    dir[5]=false;
                }
                if(i+w+1<w*h&&pix[i+w+1]==pix[i]){
                    dir[7]=false;
                }
                count=0;
                for(k=0;k<8;k++)
                    if(!dir[k])
                        count++;
                //&& ((dir[0] && (dir[7]||dir[2]||dir[4]||dir[5]||dir[6])) || (dir[1] && (dir[3]||dir[4]||dir[5]||dir[6]||dir[7]))||(dir[2]&&(dir[3]||dir[5]||dir[6]||dir[7]))||(dir[3]&&(dir[4]||dir[6]||dir[7]))||(dir[4]&&(dir[5]||dir[6]))||(dir[5]&&(dir[7])))){//&& dir[1]&&dir[3]&&dir[4]&&dir[6]&&!dir[0]&&!dir[2]&&!dir[5]&&!dir[7]){
                if((int) i / w != 0 && (int) i / w != h - 1 && i % w != 0 && i % w != w - 1 && pix[i] == -1 && count<7){
                    connect=true;
                    break1=false;
                    for(k=0;k<8;k++){
                        if(pix[positions[k]]==-1)
                            continue;
                        for(l=k+1;l<8;l++){
                            if(pix[positions[l]]==-1)
                                continue;
                        try {
                            connect=connect&&connected.initialize(remimg, null, positions[k] % w, (int) positions[k] / w, positions[l] % w, (int) positions[l] / w, true, false);
                        } catch (Exception ex) {
                            Logger.getLogger(RemoveDots.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(!connect){
                            break1=true;
                            break;
                        }
                        }
                        if(break1)
                            break;
                    }
                    if(!connect){
                        img1.setRGB(i%w, (int)i/w, Color.BLACK.getRGB());
                        pix[i]=Color.BLACK.getRGB();
                    }
                }
                i++;
            }
        }

        private void removeDots() {
            grab = new PixelGrabber(img1, 0, 0, w, h, pix, 0, w);
            try {
                grab.grabPixels();
            } catch (InterruptedException ex) {
                Logger.getLogger(RemoveDots.class.getName()).log(Level.SEVERE, null, ex);
            }
            int i=0,k;
            boolean done=true;
            boolean[] dir=new boolean[8];
            int count=0;
            while(i<w*h){

                for(k=0;k<8;k++)
                    dir[k]=true;

                if(i>=w&&pix[i-w]==pix[i])
                    dir[1]=false;
                if(i + w < w * h && pix[i + w] == pix[i])
                    dir[6]=false;
                if(i > 0 && pix[i] == pix[i - 1])
                    dir[3]=false;
                if(i + 1 < w * h && pix[i + 1] == pix[i])
                    dir[4]=false;
                if(i>=w+1&&pix[i-w-1]==pix[i])
                    dir[0]=false;
                if(i>=w-1&&pix[i-w+1]==pix[i])
                    dir[2]=false;
                if(i+w-1<w*h&&pix[i+w-1]==pix[i])
                    dir[5]=false;
                if(i+w+1<w*h&&pix[i+w+1]==pix[i])
                    dir[7]=false;
                count=0;
                for(k=0;k<8;k++)
                    if(!dir[k])
                        count++;
                if(pix[i]==Color.BLACK.getRGB()&&count<2){
//                    if(count==0){
                        done=false;
                        img1.setRGB(i%w, (int)i/w, -1);
                        //pix[i]=-1;
  //                  }
//                    else if((count == 1 && pixCount.getConnectedPixels(img1, new Point(i % w, (int) i / w)) < 10))
//                    {
//                        done=false;
//                        img1.setRGB(i%w, (int)i/w, -1);
//                        pix[i]=-1;
//                    }
                }
                i++;
            }
//        try {
  //          grab.grabPixels();
    //    } catch (Exception ex) {
            //Logger.getLogger(RemoveDots.class.getName()).log(Level.SEVERE, null, ex);
      //  }
     //       return false;
//            if(done)
  //              return true;
    //        else
      //         return this.removeDots();
        }

        public Image getModifiedImage(){
            return img1;
        }

}