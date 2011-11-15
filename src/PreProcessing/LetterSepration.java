package PreProcessing;

import Utility.ArePointsConnected;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

public class LetterSepration {

	private BufferedImage img;
	private List <BufferedImage>images;
	private PixelGrabber grab;
	private int width,height;
	private int[] pix;
	private Point start_point;
	private boolean start_found=false;
	private List <Line>overall_list=new LinkedList();
        private int remcount=0,count=0;
        
        public List getLetters(BufferedImage img1){
        try {
            img = img1;
            images = new LinkedList();
            width = img.getWidth();
            height = img.getHeight();
            pix = new int[width * height];
            grab = new PixelGrabber(img, 0, 0, width, height, pix, 0, width);
            grab.grabPixels();
            this.startScanning();
        } catch (Exception ex) {
        }
            return images;
        }

	private void startScanning() throws Exception{

		int i,k,top,bottom,from=0,to=0,rem;
		Line line;
		Point mid;
		List <Line>l = null;
		boolean none;

		while(true){

			ImageIO.write(img, "png", new File("C:/Users/ci/Desktop/clearedimage/"+String.valueOf(++remcount)+".png"));

			from=0; to=0; i=0; top=height; bottom=-1;
                        start_found=false;
			line=new Line(0,0,0,height-1);

			while(i<width){
				line.setTopCoordinate(i, 0);
				line.setBottomCoordinate(i, height-1);
				l=this.breakLine(line);

				if(!start_found&&l.size()>0){
					from=i;
					start_found=true;
					start_point=l.get(0).getMidPoint();
				}

				k=0;
				none=true;
				while(k<l.size()){
					if(new ArePointsConnected().initialize(img, null, start_point.x, start_point.y, (mid=l.get(k).getMidPoint()).x, mid.y,true,false)){
						none=false;
						overall_list.add(l.get(k));
						if(top>(rem=l.get(k).getTopCoordinate().y))
							top=rem;
						if(bottom<(rem=l.get(k).getBottomCoordinate().y))
							bottom=rem;
					}
					k++;
				}
				if(start_found&&none){
					to=i-1;
					break;
				}
				i++;
			}
			if(overall_list.isEmpty())
				break;

                            BufferedImage buff=new BufferedImage(to-from+1,bottom-top+1,BufferedImage.TYPE_INT_RGB);
                            i=0;
                            int x,y;
                            while(i<overall_list.size()){
                                    y=overall_list.get(i).getTopCoordinate().y;
                                    x=overall_list.get(i).x;
                                    while(y<=overall_list.get(i).getBottomCoordinate().y){
                                            buff.setRGB(x-from, y-top,-1);
                                            img.setRGB(x, y, -1);
                                            pix[x+y*width]=-1;
                                            y++;
                                    }
                                    i++;
                            }
                        if((to-from+1)*(bottom-top+1)>10){
                            images.add(buff);
                            count++;
                            ImageIO.write(buff, "png", new File("C:/Users/ci/Desktop/letters/" + String.valueOf(count) + ".png"));
                        }
                        overall_list.clear();
		}
	}

	private List breakLine(Line l){
		List list=new LinkedList();
		int i=0,start=-1,x=l.getTopCoordinate().x,end;
		while(i<height){
			if(start==-1&&pix[x+i*width]==-16777216){
				start=i;
			}
			if(start!=-1&&(i==height-1||pix[x+i*width]!=-16777216)){
				if(i==height-1&&pix[x+i*width]==-16777216)
					end=i;
				else
					end=i-1;
				list.add(new Line(x,start,x,end));
				start=-1;
			}
			i++;
		}
		return list;
	}

	class Line {
		int x,y,x1,y1;
		public Line(int x_1,int y_1,int x_2,int y_2){
			x=x_1;y=y_1;x1=x_2;y1=y_2;
		}
		public void setTopCoordinate(int X,int Y){
			x=X;
			y=Y;
		}
		public void setBottomCoordinate(int X,int Y){
			x1=X;
			y1=Y;
		}
		public Point getTopCoordinate(){
			return new Point(x,y);
		}
		public Point getBottomCoordinate(){
			return new Point(x1,y1);
		}
		public Point getMidPoint(){
			return new Point(x,(int)(y1+y)/2);
		}
	}

}
