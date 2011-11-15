package Utility;

import GUI.ImagePanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArePointsConnected{

    static int UP=-1,DOWN=1;
    private int x1,y1,x2,y2;
    private int color;
    public int[] pix;
    public int w,h;
    public VectorLinesTravelled trav;
    private ImagePanel panel;
    private boolean CONSIDER_CONTRAST;
    private boolean CONSIDER_DIAGONAL_CONNECTED;
    private Colour Colour;

    public boolean initialize(Image img, ImagePanel panel1,int xc1,int yc1,int xc2,int yc2,boolean consider_diagonal_connected,boolean consider_contrast) {

            CONSIDER_DIAGONAL_CONNECTED=consider_diagonal_connected;
            CONSIDER_CONTRAST=consider_contrast;
            panel=panel1;
            BufferedImage img1=(BufferedImage) img;
            w=img1.getWidth();
            h=img1.getHeight();
            pix=new int[w*h];
            PixelGrabber grab = new PixelGrabber(img1, 0, 0, w, h, pix, 0, w);
            try {
                    grab.grabPixels();
            } catch (InterruptedException ex) {
                    Logger.getLogger(ArePointsConnected.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(yc1>yc2){
                    y1=yc1; x1=xc1; y2=yc2; x2=xc2;
            }
            else{
                    y2=yc1; x2=xc1; y1=yc2; x1=xc2;
            }
            color=pix[x1+y1*w];
            Colour=new Colour(new Color(color));
            if(!this.areColorsSame(pix[x2+y2*w]))
                    return false;
            trav=new VectorLinesTravelled();
            Line hLine;
            this.strechMax(hLine=new Line(x1,y1,x1,y1));
            if(!this.traverse(hLine, UP, new Point(x1,y1))){
                    this.strechMax(hLine=new Line(x1,y1,x1,y1));
                    boolean b=this.traverse(hLine, DOWN, new Point(x1,y1));
                    if(panel!=null)
                        panel.eraseLine();
                    return b;
            }
            else{
                    if(panel!=null)
                        panel.eraseLine();
                    return true;
            }
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setPix(int[] pix) {
        this.pix = pix;
    }

    public void setTrav(VectorLinesTravelled trav) {
        this.trav = trav;
    }

    public void setW(int w) {
        this.w = w;
    }


    private boolean areColorsSame(int Color1){
        if(!CONSIDER_CONTRAST){
                    if(Color1==color)
                            return true;
                    return false;
            }
            else{
                    if(!Colour.isContrastGood(new Color(Color1)))
                            return true;
                    return false;
            }    }

    public void strechMax(Line l){
            int y=l.getLeftCoordinate().y;
            int i=l.getLeftCoordinate().x;
            boolean entered=false;
            while(i>=0&&this.areColorsSame(pix[i+y*w])){
                    entered=true;
                    i--;
            }
            if(entered)
                    l.setLeftCoordinate(i+1, y);
            entered=false;
            i=l.getRightCoordinate().x;
            while(i<w&&this.areColorsSame(pix[i+y*w])){
                    entered=true;
                    i++;
            }
            if(entered)
                    l.setRightCoordinate(i-1, y);
    }

    public class VectorLinesTravelled{
            List[] arr;

        public VectorLinesTravelled() {
                arr=new LinkedList[2*h];
        }

        public void addLine(VectorLine l){
                int y=l.l.getLeftCoordinate().y;
                int dir=l.dir;
                int ind=(dir==DOWN)?y:(y+h);
                if(arr[ind]==null)
                        arr[ind]=new LinkedList();
                arr[ind].add(l.l);
        }
        public void clear(){
                arr=null;
                arr=new LinkedList[2*h];
        }
        public boolean isLinePresent(VectorLine l){
                int y=l.l.getLeftCoordinate().y;
                int dir=l.dir;
                int ind=(dir==DOWN)?y:(y+h);
                if(arr[ind]==null)
                        return false;
                int i=0;
                while(i<arr[ind].size()){
                        if(((Line)arr[ind].get(i)).isSameAs(l.l))
                                return true;
                        i++;
                }
                return false;
        }
    }

    public class VectorLine{
            Line l;int dir;
            public VectorLine(Line l1,int d){
                    l=new Line(l1.getLeftCoordinate().x,l1.getLeftCoordinate().y,l1.getRightCoordinate().x,l1.getRightCoordinate().y);
                    dir=d;
            }
            public boolean isEqualTo(VectorLine l){
                    if(this.l.isSameAs(l.l)&&this.dir==l.dir)
                            return true;
                    return false;
            }
    }

    public void strechInX(Line line){
            line.strechInXdirection();
            ////
            int diffx=0,diffy=0;
            if(line.getRightCoordinate().x>w-1)
                diffx=line.getRightCoordinate().x-w+1;
            if(line.getRightCoordinate().y>h-1)
                diffy=line.getRightCoordinate().y-h+1;
            line.setRightCoordinate(line.getRightCoordinate().x-diffx, line.getRightCoordinate().y-diffy);

            diffx=0;diffy=0;
            if(line.getLeftCoordinate().x>w-1)
                diffx=line.getLeftCoordinate().x-w+1;
            if(line.getLeftCoordinate().y>h-1)
                diffy=line.getLeftCoordinate().y-h+1;
            line.setLeftCoordinate(line.getLeftCoordinate().x-diffx, line.getLeftCoordinate().y-diffy);
        }

    public boolean traverse(Line line,int dir,Point from){
            if(panel!=null)
                panel.drawLineCoordinates(line.getLeftCoordinate().x, line.getLeftCoordinate().y, line.getRightCoordinate().x, line.getRightCoordinate().y);
            Line remLine=new Line(line.getLeftCoordinate().x,line.getLeftCoordinate().y,line.getRightCoordinate().x,line.getRightCoordinate().y);
            line.setLeftCoordinate(line.getLeftCoordinate().x, line.getRightCoordinate().y+dir);
            line.setRightCoordinate(line.getRightCoordinate().x, line.getRightCoordinate().y+dir);
            if(line.getLeftCoordinate().y<0||line.getLeftCoordinate().y>=h){
                    line.setLeftCoordinate(line.getLeftCoordinate().x, line.getRightCoordinate().y-dir);
                    line.setRightCoordinate(line.getRightCoordinate().x, line.getRightCoordinate().y-dir);
                    return this.traverse(line, -dir, from);
            }
            Line unstreched=new Line(line.getLeftCoordinate().x,line.getLeftCoordinate().y,line.getRightCoordinate().x,line.getRightCoordinate().y);
            if(CONSIDER_DIAGONAL_CONNECTED)
                this.strechInX(line);
            //this.strechMax(line);
            List <Line>list=this.getValidLines(line);
            int i=0;
            boolean conn=false,traverse;
            VectorLine vline;
            if(list.size()>1&&!trav.isLinePresent(vline=new VectorLine(remLine,-dir))){
                    conn=conn||this.traverse(new Line(unstreched.getLeftCoordinate().x,unstreched.getLeftCoordinate().y,unstreched.getRightCoordinate().x,unstreched.getRightCoordinate().y), -dir, from);
                    if(conn)
                            return true;
            }
            while(i<list.size()){
                    this.strechMax(list.get(i));
                    if(list.get(i).isPointOnLine(x2, y2)){
                            return true;
                    }
                    if(list.get(i).isPointOnLine(x1, y1)){
                            i++;
                            continue;
                    }
                    if(!trav.isLinePresent(vline=new VectorLine(list.get(i),dir))){
                            trav.addLine(vline);
                            traverse = this.traverse(list.get(i), dir, list.size() > 1 ? list.get(i).getMidPoint() : from);
                            conn=conn||traverse;
                            if(conn)
                                    return true;
                    }
                    i++;
            }
            if(i==0){
                    //System.out.println("("+unstreched.getLeftCoordinate().x+","+unstreched.getLeftCoordinate().y+"),("+unstreched.getRightCoordinate().x+","+unstreched.getRightCoordinate().y+")");
                    return this.traverse(unstreched, -dir, from);
            }
            return conn;
    }


    public List getValidLines(Line valline){
            int start=-1;
            List <Line>list=new LinkedList();
            list.clear();
            int y=valline.getLeftCoordinate().y;
            int i=valline.getLeftCoordinate().x;
            int remx=valline.getRightCoordinate().x;
            while(i<=remx){
                    if(start==-1&&this.areColorsSame(pix[i+y*w]))
                            start=i;
                    if(start!=-1&&(i==valline.getRightCoordinate().x||!this.areColorsSame(pix[i+y*w]))){
                            if(i==valline.getRightCoordinate().x&&this.areColorsSame(pix[i+y*w]))
                                    list.add(new Line(start,y,i,y));
                            else
                                    list.add(new Line(start,y,i-1,y));
                            start=-1;
                    }

                    i++;

            }
            return list;
    }

    public class Line{
		List <Point>list=new LinkedList();
		int a1,b1,a2,b2;
		public void setLeftCoordinate(int x,int y){
			a1=x;
			b1=y;
		}
		public void setRightCoordinate(int x,int y){
			a2=x;
			b2=y;
		}
		public boolean isSameAs(Line l){
			if(l.getLeftCoordinate().x==this.getLeftCoordinate().x&&l.getLeftCoordinate().y==this.getLeftCoordinate().y&&this.getRightCoordinate().x==l.getRightCoordinate().x&&this.getRightCoordinate().y==l.getRightCoordinate().y)
				return true;
			return false;
		}
		public Line(int x1,int y1,int x2,int y2){
			a1=x1<x2?x1:x2;
			a2=x1<x2?x2:x1;
			b1=y1<y2?y1:y2;
			b2=y1<y2?y2:y1;
		}
		public Point getLeftCoordinate(){
			return new Point(a1,b1);
		}
		public Point getRightCoordinate(){
			return new Point(a2,b2);
		}
		public void strechInXdirection(){//streches by 1 pixel towards left and 1 pixel toward right
			a1--;
		if(a1<0)
			a1++;
		a2++;
		}
		public void strechInYdirection(){//streches by 1 pixel towards left and 1 pixel toward right
			b1--;
		if(b1<0)
			b1++;
		b2++;
		}
		public void shrinkInXdirection(){//shrink by 1 pixel towards left and 1 pixel toward right
			a1++;
			a2--;
			if(a2<0)
				a2++;
		}
		public void shrinkInYdirection(){//shrink by 1 pixel towards left and 1 pixel toward right
			b1++;
			b2--;
			if(b2<0)
				b2++;
		}
		public boolean isPointOnLine(int x,int y){
			if((b1-y)*(a2-x)!=(b2-y)*(a1-x))
				return false;
			if(x<=a2&&x>=a1&&y<=b2&&y>=b1)
				return true;
			return false;
		}
		public Point getMidPoint(){
			return new Point((a1+a2)/2,(b1+b2)/2);
		}
		public void addFromPoint(Point p){
			int i=0;
			while(i<list.size()){
				if(p.equals(list.get(i)))
					return;
				i++;
			}
			list.add(p);
		}
		public boolean isFromPointOnLine(){
			int i=0;
			while(i<list.size()){
				if(this.isPointOnLine(list.get(i).x, list.get(i).y))
					return true;
				i++;
			}
			return false;
		}
		public List getFromPoints(){
			return list;
		}
		public void setFromPointList(List l){
			list=l;
		}
		public void printFromPoints(){
			int i=0;
			while(i<list.size()){
				System.out.print("("+list.get(i).x+","+list.get(i).y+"),");
				i++;
			}
		}
	}

}

class DifferentColorsBeingConnectedException extends Exception{
    public DifferentColorsBeingConnectedException(String s){
		super(s);
	}
}