package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
    Point start,end;
    Image img=null;
    int[] pix;
    int scansize=-1;
    PixelGrabber grab;
    private boolean scanLines=true;
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(img!=null){
            g.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), 0, 0, scansize=img.getWidth(null), img.getHeight(null), null);
            grab=new PixelGrabber(img,0,0,img.getWidth(null),img.getHeight(null),pix,0,img.getWidth(null));
            try {
                grab.grabPixels();
            } catch (InterruptedException ex) {
                Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            g.setColor(Color.BLUE);
            if(scanLines)
                g.drawLine(x1, y1, x2, y2);
            //System.out.println("Drawline : ("+x1+","+y1+"),("+x2+","+y2+")");
            if(start!=null&&end!=null){
                g.setColor(Color.red);
                g.drawLine(start.x, start.y,end.x, end.y);
            }
        }
    }
    int x1,y1,x2,y2;
    public void drawLineCoordinates(int a1,int b1,int a2,int b2){
        if(!scanLines){
            this.eraseLine();
            return;
        }
        try {
            Thread.sleep(10);
    } catch (InterruptedException ex) {
    }
        x1=a1;y1=b1;x2=a2;y2=b2;
        this.repaint();
    }
    public void eraseLine(){
        x1=-1;y1=-1;x2=-1;y2=-1;
        this.repaint();
    }
    public void setStart(Point p){
        start=p;
    }
    public void setEnd(Point p){
        end=p;
    }

    public int getPixels(Point p){
        int ind=p.x+p.y*img.getWidth(null);
        if(ind>=pix.length||ind<0)
            return 1;
        return pix[p.x+p.y*img.getWidth(null)];
    }
    public void setImage(File f){
        try {
            img = ImageIO.read(f);
            scansize=img.getWidth(null);
            pix=new int[img.getWidth(null)*img.getHeight(null)];
            this.repaint();
        } catch (IOException ex) {
            scansize=-1;
            pix=null;
            img=null;
            this.add(new JLabel("Not An Image File."));
            this.revalidate();
        }
    }

    void setScanLinesVisible(boolean b) {
        scanLines=b;
    }
}