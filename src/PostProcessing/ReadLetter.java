package PostProcessing;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadLetter {

    private BufferedImage img;
    private int w,h;
    private PixelGrabber grab;
    private int[] pix;
    private Job job;
    private int[] counter;
    private ProcessJob processJob;
    //int count=0;
    public ReadLetter(){
        processJob=new ProcessJob();
    }
    public Job readLetter(BufferedImage img1){
        try {
            counter=new int[8];
            int w1=img1.getWidth();
            int h1=img1.getHeight();
            img=new BufferedImage(w1+4,h1+4,BufferedImage.TYPE_INT_RGB);
            w = w1+4;
            h = h1+4;
            pix = new int[w1 * h1];
            grab = new PixelGrabber(img1, 0, 0, w1, h1, pix, 0, w1);
            grab.grabPixels();
            int i=0;
            while(i<w1*h1){
                img.setRGB(i%w1+2, (int)i/w1+2, img1.getRGB(i%w1, (int)i/w1));
                i++;
            }
            pix = new int[w* h];
            grab = new PixelGrabber(img, 0, 0, w, h, pix, 0, w);
            grab.grabPixels();
            job=new Job(new StringBuffer(""));
            this.move();
            //      count++;
            //            ImageIO.write(img, "png", new File("C:/Users/ci/Desktop/boundary/"+count+".png"));
            //            System.out.println(job);
            //          System.out.("boundary no. : "+count);
            Job process = processJob.process(img, job);
            return process;
        } catch (Exception ex) {
            Logger.getLogger(ReadLetter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }




    
    private int[] getBox(int i,int connected_to){//clockwise
        int[] pos=new int[]{i-w-1,i-w,i-w+1,i+1,i+w+1,i+w,i+w-1,i-1};
        int k=0;
        int beginning=-1;
        for(k=0;k<8;k++){
            if(connected_to==pos[k]){
                beginning=k;
                break;
            }
        }
        if(beginning==-1)
            return null;
        k=0;
        int[] posi=new int[8];
        for(k=0;k<8;k++)
            posi[k]=pos[(k+beginning+1)%8];
        return posi;
    }

    private void move(){
        int start = this.getStartingPixel();
        this.job.setStartingPixel(start);
//        System.out.println("start : "+start%w+","+(int)start/w);
        boolean entered=false;
        int req_point=start+1;
        int pointer=start,k=0;
        int rem_pointer=pointer;
        int rem_from_point=-1,get_point;
        do{
//                        here you have the required pointer and req_point
//            img.setRGB(pointer%w, (int)pointer/w, Color.RED.getRGB());
            get_point=rem_from_point;
            rem_from_point=req_point;
            int[] box = this.getBox(pointer, req_point);
            for(k=0;k<8;k++){
                if(!entered&&box[k]<w*h&&box[k]>=0&&pix[box[k]]!=-1)
                    entered=true;
                if(entered&&box[k]<w*h&&box[k]>=0&&pix[box[k]]==-1){
                    entered=false;
                    req_point=box[k];
                    pointer=box[k-1];
                    if(get_point!=req_point)
                        break;
                }
            }
            String y;
            y=this.getDirection(rem_pointer,pointer);
            this.job.addPointandDirection(rem_pointer, Integer.parseInt(y));
/////////            System.out.println("whole : "+(rem_pointer%img.getWidth())+","+(int)rem_pointer/img.getWidth());
            job.append(y);
            counter[Integer.parseInt(y)]++;
//            System.out.println(pointer%w+","+(int)pointer/w);
            rem_pointer=pointer;
        }while(pointer!=start);
    }

    public int[] getCounter(){
        return counter;
    }
    
    private String getDirection(int from,int to){
        if(to==from-w-1)
            return "0";
        else if(to==from-w)
            return "1";
        else if(to==from-w+1)
            return "2";
        else if(to==from-1)
            return "3";
        else if(to==from+1)
            return "4";
        else if(to==from+w-1)
            return "5";
        else if(to==from+w)
            return "6";
        else if(to==from+w+1)
            return "7";
        else
            return "E";
    }

    private int getStartingPixel(){
        int min=w-1;
        int x=0,y=0;
        int learn=-1;
        boolean start=false;
        for(y=h-1;y>=0;y--){
            for(x=0;x<w;x++){
                if(pix[y*w+x]==-1){
                    start=true;
                }
                else if(start){
                    learn=y*w+x;
                    start=false;
                }
            }
            if(learn!=-1)
                return learn;
        }
        return min-1;
    }
}
