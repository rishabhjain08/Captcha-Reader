package PostProcessing;

import PostProcessing.Job.Pockets;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class ProcessJob {

    private Job buff;
    private BufferedImage img;
//    int count=0;

        public Job  process(BufferedImage img1,Job s){
            img=img1;
            buff=s;
//            System.out.println(buff);

            this.correctProtusions();
            BufferedImage im;
            buff.setImg((im=this.correctedImage(img)));
            buff.setMainJob(buff.toString());
//            count++;
//            try {
//                ImageIO.write(im, "png", new File("C:/Users/ci/Desktop/newing boundary/" + String.valueOf(count) + ".png"));
//            } catch (IOException ex) {
//                Logger.getLogger(ProcessJob.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            System.out.println(buff);
            this.replaceWithHandV();
//            System.out.println(buff);
            this.removeProtusions();
//            System.out.println(buff);
            this.insertPoints();
//            System.out.println(buff);
            if(Character.isDigit(buff.charAt(buff.length()-1)))
                buff.append("P");
            this.removeSinglePoints();
            System.out.println(buff);
            this.replaceWithLines(false);
        //            System.out.println("buff = "+buff);
        //            new ProccesedJob().interpret(img,buff);
            this.replaceWithLines(true);

            String replaceAll = buff.getJob().toString().replaceAll("P", "");
            buff.setJob(new StringBuffer(replaceAll));
            return buff;
        }

        private BufferedImage correctedImage(BufferedImage img1){
            int i=0;
            int w=img1.getWidth();
//            int h=img1.getHeight();
            char ch;
            int start=buff.getStarting_pixel();
//            System.out.println(buff);
            while(i<buff.length()){
                img1.setRGB(buff.getPoint().get(i)%w, (int)buff.getPoint().get(i)/w, Color.RED.getRGB());
//                ch = buff.charAt(i);
//                if(ch=='0')
//                    start = start-w-1;
//                else if(ch=='1')
//                    start=start-w;
//                else if(ch=='2')
//                    start=start-w+1;
//                else if(ch=='3')
//                    start--;
//                else if(ch=='4')
//                    start++;
//                else if(ch=='5')
//                    start=start+w-1;
//                else if(ch=='6')
//                    start+=w;
//                else if(ch=='7')
//                    start+=w+1;
//                else
//                    System.out.println("Other than direction");
//                img1.setRGB(start%w, (int)start/w, Color.RED.getRGB());
                i++;
            }
            return img1;
        }


//        public String process(BufferedImage Img,String s){
//            return this.process(Img,new StringBuffer(s));
//        }

        public int min(int ... v){
            if(v.length==0)
                throw new IllegalArgumentException();
            int min=v[0];
            int i=1;
            while(i<v.length){
                if(min>v[i])
                    min=v[i];
                i++;
            }
            return min;
        }


        private void replaceWithLines(boolean all_p_allowed){

            int start,remstart,end;
            String g,p;
            int from = 0;


            from=0;
            start=min(buff.indexOf("U2U",from),buff.indexOf("P2P",from),buff.indexOf("P2U",from),buff.indexOf("U2P",from));
            while(start!=-1){
                remstart=start;
                end=start+2;
                start+=2;

                while(start<buff.length()-2&&((g=buff.substring(start, start+3)).equals("U2U")||g.equals("P2P")||g.equals("U2P")||g.equals("P2U"))){
                    start+=2;
                    end+=2;
                }
                if(!all_p_allowed&&buff.substring(remstart, end+1).indexOf('U')==-1)
                    from=end+1;
                else{
                    buff.replace(remstart, end+1, "i",true);
                    from=remstart+1;//i.e. remstart+"U".length();
                }
                if(from>=buff.length())
                    break;
            start=min(buff.indexOf("U2U",from),buff.indexOf("P2P",from),buff.indexOf("P2U",from),buff.indexOf("U2P",from));
            }

            from=0;
            start=min(buff.indexOf("U0U",from),buff.indexOf("P0P",from),buff.indexOf("P0U",from),buff.indexOf("U0P",from));
            while(start!=-1){
                remstart=start;
                end=start+2;
                start+=2;

                while(start<buff.length()-2&&((g=buff.substring(start, start+3)).equals("U0U")||g.equals("P0P")||g.equals("U0P")||g.equals("P0U"))){
                    start+=2;
                    end+=2;
                }
                if(!all_p_allowed&&buff.substring(remstart, end+1).indexOf('U')==-1)
                    from=end+1;
                else{
                    buff.replace(remstart, end+1, "y",true);
                    from=remstart+1;//i.e. remstart+"U".length();
                }
                if(from>=buff.length())
                    break;
            start=min(buff.indexOf("U0U",from),buff.indexOf("P0P",from),buff.indexOf("P0U",from),buff.indexOf("U0P",from));
            }


            from=0;
            start=min(buff.indexOf("L5L",from),buff.indexOf("P5P",from),buff.indexOf("P5L",from),buff.indexOf("L5P",from));
            while(start!=-1){
                remstart=start;
                end=start+2;
                start+=2;

                while(start<buff.length()-2&&((g=buff.substring(start, start+3)).equals("L5L")||g.equals("P5P")||g.equals("L5P")||g.equals("P5L"))){
                    start+=2;
                    end+=2;
                }
                if(!all_p_allowed&&buff.substring(remstart, end+1).indexOf('L')==-1)
                    from=end+1;
                else{
                    buff.replace(remstart, end+1, ";",true);
                    from=remstart+1;//i.e. remstart+"U".length();
                }
                if(from>=buff.length())
                    break;
                start=min(buff.indexOf("L5L",from),buff.indexOf("P5P",from),buff.indexOf("P5L",from),buff.indexOf("L5P",from));
            }


            from=0;
            start=min(buff.indexOf("L0L",from),buff.indexOf("P0P",from),buff.indexOf("P0L",from),buff.indexOf("L0P",from));
            while(start!=-1){
                remstart=start;
                end=start+2;
                start+=2;

                while(start<buff.length()-2&&((g=buff.substring(start, start+3)).equals("L0L")||g.equals("P0P")||g.equals("L0P")||g.equals("P0L"))){
                    start+=2;
                    end+=2;
                }
                if(!all_p_allowed&&buff.substring(remstart, end+1).indexOf('L')==-1)
                    from=end+1;
                else{
                    buff.replace(remstart, end+1, "k",true);
                    from=remstart+1;//i.e. remstart+"U".length();
                }
                if(from>=buff.length())
                    break;
                start=min(buff.indexOf("L0L",from),buff.indexOf("P0P",from),buff.indexOf("P0L",from),buff.indexOf("L0P",from));
            }

            //now for down and right


            from=0;
            start=min(buff.indexOf("D7D",from),buff.indexOf("P7P",from),buff.indexOf("P7D",from),buff.indexOf("D7P",from));
            while(start!=-1){
                remstart=start;
                end=start+2;
                start+=2;

                while(start<buff.length()-2&&((g=buff.substring(start, start+3)).equals("D7D")||g.equals("P7P")||g.equals("D7P")||g.equals("P7D"))){
                    start+=2;
                    end+=2;
                }
                if(!all_p_allowed&&buff.substring(remstart, end+1).indexOf('D')==-1)
                    from=end+1;
                else{
                    buff.replace(remstart, end+1, "f",true);
                    from=remstart+1;//i.e. remstart+"D".length();
                }
                if(from>=buff.length())
                    break;
                start=min(buff.indexOf("D7D",from),buff.indexOf("P7P",from),buff.indexOf("P7D",from),buff.indexOf("D7P",from));
            }

            from=0;
            start=min(buff.indexOf("D5D",from),buff.indexOf("P5P",from),buff.indexOf("P5D",from),buff.indexOf("D5P",from));
            while(start!=-1){
                remstart=start;
                end=start+2;
                start+=2;

                while(start<buff.length()-2&&((g=buff.substring(start, start+3)).equals("D5D")||g.equals("P5P")||g.equals("D5P")||g.equals("P5D"))){
                    start+=2;
                    end+=2;
                }
                if(!all_p_allowed&&buff.substring(remstart, end+1).indexOf('D')==-1)
                    from=end+1;
                else{
                    buff.replace(remstart, end+1, "s",true);
                    from=remstart+1;//i.e. remstart+"D".length();
                }
                if(from>=buff.length())
                    break;
                start=min(buff.indexOf("D5D",from),buff.indexOf("P5P",from),buff.indexOf("P5D",from),buff.indexOf("D5P",from));
            }


            from=0;
            start=min(buff.indexOf("R7R",from),buff.indexOf("P7P",from),buff.indexOf("P7R",from),buff.indexOf("R7P",from));
            while(start!=-1){
                remstart=start;
                end=start+2;
                start+=2;

                while(start<buff.length()-2&&((g=buff.substring(start, start+3)).equals("R7R")||g.equals("P7P")||g.equals("R7P")||g.equals("P7R"))){
                    start+=2;
                    end+=2;
                }
                if(!all_p_allowed&&buff.substring(remstart, end+1).indexOf('R')==-1)
                    from=end+1;
                else{
                    buff.replace(remstart, end+1, "t",true);
                    from=remstart+1;//i.e. remstart+"D".length();
                }
                if(from>=buff.length())
                    break;
                start=min(buff.indexOf("R7R",from),buff.indexOf("P7P",from),buff.indexOf("P7R",from),buff.indexOf("R7P",from));
            }


            from=0;
            start=min(buff.indexOf("R2R",from),buff.indexOf("P2P",from),buff.indexOf("P2R",from),buff.indexOf("R2P",from));
            while(start!=-1){
                remstart=start;
                end=start+2;
                start+=2;

                while(start<buff.length()-2&&((g=buff.substring(start, start+3)).equals("R2R")||g.equals("P2P")||g.equals("R2P")||g.equals("P2R"))){
                    start+=2;
                    end+=2;
                }
                if(!all_p_allowed&&buff.substring(remstart, end+1).indexOf('R')==-1)
                    from=end+1;
                else{
                    buff.replace(remstart, end+1, "e",true);
                    from=remstart+1;//i.e. remstart+"D".length();
                }
                if(from>=buff.length())
                    break;
            start=min(buff.indexOf("R2R",from),buff.indexOf("P2P",from),buff.indexOf("P2R",from),buff.indexOf("R2P",from));
            }

}

        private void replaceWithHandV(){


            int start,i;
            start=buff.indexOf("1");
            while(start!=-1){
                i=start+1;
            while(i<buff.length()){
                if(buff.charAt(i)!='1')
                    break;
                i++;
            }
            buff.replace(start, i, "U",true);
            start=buff.indexOf("1");
            }

            start=buff.indexOf("6");
            while(start!=-1){
                i=start+1;
            while(i<buff.length()){
                if(buff.charAt(i)!='6')
                    break;
                i++;
            }
            buff.replace(start, i, "D",true);
            start=buff.indexOf("6");
            }

            start=buff.indexOf("3");
            while(start!=-1){
                i=start+1;
            while(i<buff.length()){
                if(buff.charAt(i)!='3')
                    break;
                i++;
            }
            buff.replace(start, i, "L",true);
            start=buff.indexOf("3");
            }

            start=buff.indexOf("4");
            while(start!=-1){
                i=start+1;
            while(i<buff.length()){
                if(buff.charAt(i)!='4')
                    break;
                i++;
            }
            buff.replace(start, i, "R",true);
            start=buff.indexOf("4");
            }
    }

        private void removeProtusions() {
        int start=0;
        while(start<buff.length()-1){
            if(Character.isDigit(buff.charAt(start))&&Character.isDigit(buff.charAt(start+1))&&Integer.parseInt(buff.substring(start, start+2))%11!=0){
                buff.deleteCharAt(start);
                int i=0;
                while(i<buff.list.size()&&buff.list.get(i).getFromPoint()<start)
                    i++;

                if(i<buff.list.size()&&buff.list.get(i).getFromPoint()==start){
                    buff.list.remove(i);
                    System.out.println("sorry");
                    System.exit(0);
                }

                while(i<buff.list.size()){
                    Pockets get = buff.list.get(i);
                    get.setFromPoint(get.getFromPoint()-1);
                    get.setToPoint(get.getToPoint()-1);
                    i++;
                }
                start--;
            }
            start++;
        }
    }

        private void insertPoints(){
            int start=0,r;
            char c;
            while(start<buff.length()-1){
                if(Character.isDigit((c=buff.charAt(start)))&&Character.isDigit(buff.charAt(start+1))&&(Integer.parseInt(buff.substring(start, start+2))%11!=0||(r=Integer.parseInt(Character.toString(c)))==0||r==2||r==5||r==7)){
                    buff.insert(start+1, "P");
                int i=0;
                while(i<buff.list.size()&&buff.list.get(i).getFromPoint()<start+1)
                    i++;

                while(i<buff.list.size()){
                    Pockets get = buff.list.get(i);
                    get.setFromPoint(get.getFromPoint()+1);
                    get.setToPoint(get.getToPoint()+1);
                    i++;
                    }
                    start++;
                }
                start++;
            }
        }

        private int min(int a,int b,int c,int d){
            int min=a;
            if((min>b&&b!=-1)||min==-1)
                min=b;
            if((min>c&&c!=-1)||min==-1)
                min=c;
            if((min>d&&d!=-1)||min==-1)
                min=d;
            return min;
        }

    private void correctProtusions() {
        int i=0;
        int a,b,di;
        String s;
        while(i<buff.length()-1){
            a=Integer.parseInt(Character.toString(buff.charAt(i)));
            b=Integer.parseInt(Character.toString(buff.charAt(i+1)));
            i++;
            s="";
            if((a==5&&b==7)||(a==7&&b==5)){
                s="66";
                buff.getDirection().set(i-1, 6);
                buff.getDirection().set(i, 6);
                di=a<b?1:-1;
                buff.getPoint().set(i, buff.getPoint().get(i)+di);
            }
            else if((a==0&&b==2)||(a==2&&b==0)){
                s="11";
                buff.getDirection().set(i-1, 1);
                buff.getDirection().set(i, 1);
                di=a<b?1:-1;
                buff.getPoint().set(i, buff.getPoint().get(i)+di);
            }
            else if((a==2&&b==7)||(a==7&&b==2)){
                s="44";
                buff.getDirection().set(i-1, 4);
                buff.getDirection().set(i, 4);
                di=a<b?img.getWidth():-img.getWidth();
                buff.getPoint().set(i, buff.getPoint().get(i)+di);
            }
            else if((a==0&&b==5)||(a==5&&b==0)){
                s="33";
                buff.getDirection().set(i-1, 3);
                buff.getDirection().set(i, 3);
                di=a<b?img.getWidth():-img.getWidth();
                buff.getPoint().set(i, buff.getPoint().get(i)+di);
            }
            else
                i--;
            if(!s.equals("")){
                buff.replace(i-1, i+1, s);
            }
            i++;

        }
    }

    private void removeSinglePoints() {
        int i=0;
        while(i<buff.length()){
            if(i>0&&buff.charAt(i)=='P')
                buff.replace(i-1, i+2, "",true);
            i++;
        }
    }
}
