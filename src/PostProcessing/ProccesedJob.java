package PostProcessing;

import PostProcessing.Job.Pockets;
import Utility.Chars;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ProccesedJob {

    Job buff;
    int max_v=0;
    int c_count=0,h_count=0,v_count=0,u_count=0,A_cap_count=0;
    int count_main_vertical_lines=0;
    Chars chars;
    BufferedImage img;
    private int max_y,max_i,max_s,max_f,max_D,max_U;

    private boolean checkForA() throws PixelCountNotAvailableException {
            if(c_count!=0)
                return false;
            int i=0;
            List <Integer>list=new LinkedList();
            char ch;
            while(i<buff.length()){
                if((ch=buff.charAt(i))=='s'||ch=='f'||ch=='y'||ch=='i'||ch=='U'||ch=='D'&&buff.getPixelCount(i, i)>(int)max_v*.8){
                        list.add(i);
                        count_main_vertical_lines++;
                }
                    i++;
                }
                if(count_main_vertical_lines!=2)
                    return false;
                int start=buff.indexOf("i2R7f");
                while(start!=-1){
                    if(list.contains(start)&&list.contains(start+4))
                        A_cap_count++;
                    start=buff.indexOf("i2R7F",start+5);
                }
                if(A_cap_count==0)
                    return false;
                
            
            return true;
        }

    public char interpret(BufferedImage img1,Job s) {
        try {
            img=img1;
            chars=new Chars();
            buff = s;
            System.out.println(buff);
            //h_count=this.countOccurrences('h');
            //v_count=this.countOccurrences('v');
            //u_count=this.countOccurrences("v7H2v")+this.countOccurrences("v5H0v")+this.countOccurrences("v7h2v")+this.countOccurrences("v5h0v");
            c_count =this.countOccurrences("y0i")+this.countOccurrences("y2i");
            if(c_count!=0){//C,G,O,Q,S,U,0,6,8,9,@,$,%,&
                chars.removeChar('A');
                chars.removeChar('B');
                chars.removeChar('D');
                chars.removeChar('E');
                chars.removeChar('F');
                chars.removeChar('H');
                chars.removeChar('I');
                chars.removeChar('J');
                chars.removeChar('K');
                chars.removeChar('L');
                chars.removeChar('M');
                chars.removeChar('N');
                chars.removeChar('P');
                chars.removeChar('R');
                chars.removeChar('T');
                chars.removeChar('V');
                chars.removeChar('W');
                chars.removeChar('X');
                chars.removeChar('Y');
                chars.removeChar('Z');
                chars.removeChar('1');
                chars.removeChar('2');
                chars.removeChar('3');
                chars.removeChar('4');
                chars.removeChar('5');
                chars.removeChar('7');
                chars.removeChar('#');
                chars.removeChar('*');
            }

            //System.out.println(v_count+","+h_count+","+u_count);
            max_v = Math.max(Math.max(Math.max(Math.max(Math.max((max_y=this.getMaxCharCount('y')),(max_i=this.getMaxCharCount('i'))),(max_s=this.getMaxCharCount('s'))),(max_f=this.getMaxCharCount('f'))),(max_D=this.getMaxCharCount('D'))),(max_U=this.getMaxCharCount('U')));
            if (c_count != 0) {
                System.out.println("true count = "+c_count);
            } else {
                System.out.println("false");
            }
        } catch (PixelCountNotAvailableException ex) {
            Logger.getLogger(ProccesedJob.class.getName()).log(Level.SEVERE, null, ex);
        }
            return 'a';
    }

    private int getMaxCharCount(char c){
        int max=0;
        int i=0,k=0;
        Pockets pock;
        while(i<buff.length()){
            if(buff.charAt(i)!=c){
                i++;
                continue;
            }
            while(k<buff.list.size()&&buff.list.get(k).getFromPoint()<i)
                k++;
            if(k<buff.list.size()&&(pock=buff.list.get(k)).getFromPoint()==i&&max<pock.getCountPixels()){
                max=pock.getCountPixels();
                k++;
            }
            i++;
        }
        return max;
    }

    private int countOccurrences(String s,int min1,int min2) throws PixelCountNotAvailableException{
        int count=0;
        int start=buff.indexOf(s);
        while(start!=-1){
            if(buff.getPixelCount(start, start)>(int)max_v*.5&&buff.getPixelCount(start+s.length()-1, start+s.length()-1)>(int)max_v*0.5)
                count++;
            if(start+s.length()<buff.length())
                start=buff.indexOf(s, start+s.length());
            else
                start=-1;
        }
        return count;
    }

    private int countOccurrences(String s) throws PixelCountNotAvailableException{
        return this.countOccurrences(s,0,0);
    }

    private int countOccurrences(char character) throws PixelCountNotAvailableException{
        return this.countOccurrences(String.valueOf(character));
    }

    public static void main(String args[]) throws Exception{
        ReadLetter readLetter = new ReadLetter();
////        readLetter.readLetter(ImageIO.read(new File("C:/Users/ci/Desktop/letters/50.png")));
////      return;
        File[] f=new File("C:/Users/ci/Desktop/letters").listFiles();
        int i=0;
        int[] c;
        FileWriter write=new FileWriter("C:/Users/ci/Desktop/about letters.txt");
        while(i<f.length){
            f[i]=new File("C:/Users/ci/Desktop/letters/"+String.valueOf(i+1)+".png");
            System.out.println(f[i].getPath());
            readLetter.readLetter(ImageIO.read(f[i]));
            c = readLetter.getCounter();
            write.write(f[i].getName()+"\r\n"+c[0]+","+c[1]+","+c[2]+","+c[3]+","+c[4]+","+c[5]+","+c[6]+","+c[7]+"\r\n");
            i++;
        }
        write.close();

//	int i=0;
//	File[] f=new File("C:/Users/ci/Desktop/images").listFiles();
//	LetterBlackening letterBlackening = null;
//	RemoveBackground refine = null;
//	LetterSepration letterSepration=new LetterSepration();
//	while(i<f.length){
//		if(f[i].getName().length()>2&&f[i].getName().substring(0,3).equals("cap")){
//			try {
//				refine = new RemoveBackground(ImageIO.read(f[i]));
//			} catch (InterruptedException ex) {
//				Logger.getLogger(LetterBlackening.class.getName()).log(Level.SEVERE, null, ex);
//			}
//			letterBlackening = new LetterBlackening(refine.getModifiedImage());
//			Image modifiedImage = new RemoveDots((BufferedImage) letterBlackening.getModifiedImage()).getModifiedImage();
//			letterSepration.getLetters((BufferedImage) modifiedImage);
//		}
//		i++;
//	}


	//
	//        File f=new File("C:/Users/ci/Desktop/images/cap17.png");
	//        LetterBlackening letterBlackening;
	//        RemoveBackground refine = null;
	//        refine = new RemoveBackground(ImageIO.read(f));
	//        letterBlackening = new LetterBlackening(refine.getModifiedImage());
	//        Image modifiedImage = new RemoveDots((BufferedImage) letterBlackening.getModifiedImage(),"gaps filled").getModifiedImage();
	//        LetterSepration letterSepration = new LetterSepration((BufferedImage) modifiedImage);

}
    

}
