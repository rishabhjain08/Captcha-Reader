package Utility;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


    public class BackgroundColor{
        Image img;
        int[] pix;
        List <Ocurrences>l;
        public int getBackgroundColor(Image img1){//on the basis of the max same color on the boundary
            img=img1;
            int w=img.getWidth(null);
            int h=img.getHeight(null);
            pix=new int[w*h];
            PixelGrabber grab = new PixelGrabber(img1, 0, 0, w, h, pix, 0, w);
        try {
            grab.grabPixels();
        } catch (InterruptedException ex) {
            Logger.getLogger(BackgroundColor.class.getName()).log(Level.SEVERE, null, ex);
        }
            l=new LinkedList();
            int i=0,k=0;
            boolean present=false;

            while(i<=h){

                if(i!=h){
                    k=0;
                present=false;
                    while(k<l.size()){
                        if(pix[i*w]==l.get(k).getRGB()){
                            l.get(k).setRepetition(l.get(k).getRepetition()+1);
                            present=true;
                            break;
                        }
                        k++;
                    }
                if(!present)
                    l.add(new Ocurrences(pix[i*w],1));
                }
////
                if(i!=0){
                    k=0;
                    present=false;
                    while(k<l.size()){
                        if(pix[i*w-1]==l.get(k).getRGB()){
                            l.get(k).setRepetition(l.get(k).getRepetition()+1);
                            present=true;
                            break;
                        }
                        k++;
                    }
                if(!present)
                    l.add(new Ocurrences(pix[i*w-1],1));
                }
                                i++;
            }
            return this.post();
        }
        public int post(){
            int y=0,k=0,rem=-1;
            while(y<l.size()){
                if(k<l.get(y).getRepetition()){
                    rem=y;
                    k=l.get(y).getRepetition();
                }
                y++;
            }
            return l.get(rem).getRGB();
        }

        class Ocurrences{
            private int a=0,b=0;
            Ocurrences(int rgbvalue,int repetition){
                a=rgbvalue;
                b=repetition;
            }
            public int getRGB(){
                return a;
            }
            public int getRepetition(){
                return b;
            }
            public void setRGB(int rgbvalue){
                a=rgbvalue;
            }
            public void setRepetition(int times){
                b=times;
            }
        }

    }
