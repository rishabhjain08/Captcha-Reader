package PostProcessing;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Job {

    class Pockets{

        int fromPoint,toPoint,countPixels;

        public Pockets(int fromPoint, int toPoint, int countPixels) {
            this.fromPoint = fromPoint;
            this.toPoint = toPoint;
            this.countPixels = countPixels;
        }

        public int getCountPixels() {
            return countPixels;
        }

        public void setCountPixels(int countPixels) {
            this.countPixels = countPixels;
        }

        public int getFromPoint() {
            return fromPoint;
        }

        public void setFromPoint(int fromPoint) {
            this.fromPoint = fromPoint;
        }

        public int getToPoint() {
            return toPoint;
        }

        public void setToPoint(int toPoint) {
            this.toPoint = toPoint;
        }
    }

    private StringBuffer job;
    List <Pockets>list=new LinkedList();
    private List <Integer>point=new LinkedList();
    private List <Integer>direction=new LinkedList();
    private List <Integer>listing=new LinkedList();
    private int starting_pixel;
    private StringBuffer main_job;
    private BufferedImage img;
    private String wrap_length,wrap_starting,wrap_ending;
    private String wrap=null;

    Job(StringBuffer Job) {
        this.job=Job;
    }

    public StringBuffer getJob() {
        return job;
    }

    public void setJob(StringBuffer job) {
        this.job = job;
    }

    public BufferedImage getImg() {
        return this.img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void setMainJob(String buff){
        this.main_job=new StringBuffer(buff);
    }

    public List<Integer> getDirection() {
        return direction;
    }

    public List<Integer> getPoint() {
        return point;
    }

    public void setDirection(List<Integer> direction) {
        this.direction = direction;
    }

    public void setPoint(List<Integer> point) {
        this.point = point;
    }

    public int getStarting_pixel() {
        return starting_pixel;
    }

    public void setStartingPixel(int i){
        this.starting_pixel=i;
    }

    public void addPointandDirection(int Point,int Direction){
        point.add(Point);
        direction.add(Direction);
    }

    public double getLength(int o){
        if(Character.isDigit(this.job.charAt(o)))
            return 1;
        int i=0;
        int count=0;
        while(i<this.job.length()&&i<o){
            if(Character.isDigit(this.job.charAt(i)))
                count+=1;
            else if(this.job.charAt(i)=='P')
            {
                System.out.println("Sorry! p's are remaining in job = "+this.job+"in getLength() in Job class");
                System.exit(0);
                //count += 1;
            }
            else try {
                count += this.getPixelCount(i, i);
            } catch (PixelCountNotAvailableException ex) {
                Logger.getLogger(Job.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
        int start=count,end=0;
        try {
            System.out.println("this.job = "+this.job+" this.job.charAt("+o+")"+this.job.charAt(o)+" o = "+o);
            end = count + this.getPixelCount(o, o)-1;
        } catch (PixelCountNotAvailableException ex) {
            System.out.println("Error Message : " +ex.getMessage());
            Logger.getLogger(Job.class.getName()).log(Level.SEVERE, null, ex);
        }
        i=0;
        int  a = 0,b = 0;
        while(i<=end){
            if(i==start)
                a=point.get(i);
            if(i==end)
                b=point.get(i);
            i++;
        }
        int w=img.getWidth();
        return Math.sqrt((a%w-b%w)*(a%w-b%w)+((int)a/w-(int)b/w)*((int)a/w-(int)b/w));
    }

    public int getPixelCount(int from,int to) throws PixelCountNotAvailableException{
        int i=0;
        while(i<this.list.size()){
            Pockets get = this.list.get(i);
            if(get.getFromPoint()==from&&get.getToPoint()==to)
                return get.getCountPixels();
            i++;
        }
        if(this.job.subSequence(from, to+1).equals("P"))
            return 1;
//        try{
//            String.valueOf(this.job.substring(from, to+1));
//        }catch(Exception e){
            throw new PixelCountNotAvailableException();
//        }
//        return (to+1-from);
    }

    public List[] getStrings(List l){
        wrap=null;
        wrap_starting=null;
        wrap_ending=null;
        wrap_length=null;
        wrap_starting=null;
        wrap_ending=null;
        List <Integer>pos=new LinkedList();
        List <String>strings=new LinkedList();
        List <String>strings_length=new LinkedList();
        int i=0,k=0,m=0,count=0,s=0;
        if(l.isEmpty())
                return new List[]{l,null};
        while(i<l.size()){
            k=0;
            while(k<point.size()){
                if(point.get(k).equals(l.get(i))){
                    m=0;
                    count=0;
                    while(m<this.job.length()){
                        try{
                            count+=this.getPixelCount(m, m);
                        }catch(PixelCountNotAvailableException e){
                            count++;
                        }
                        if(count>=k+1){
                            s=0;
                            while(s<pos.size()&&pos.get(s)<m)
                                s++;
                            if(s<=pos.size()&&!(s<pos.size()&&pos.get(s)==m)){
                                pos.add(s,m);
                            }
                            break;
                        }
                        m++;
                    }
                }
                k++;
            }
            i++;
        }

        int start=-1;
        int counter=0;
        String g,starting=null,ending=null;
        String h = "",starting_length = "",ending_length = "";
        i=0;
        k=0;
        String length;
        while(i<=pos.size()){
            if(i==pos.size()&&start!=-1){
                g=this.job.substring(start, start+counter);
                h="";
                k=0;
                while(k<counter){
                    length=String.valueOf(this.getLength(k+start));
                    h+=length.substring(0, length.indexOf('.')+2)+(k==counter-1?"":",");
                    k++;
                }
                if(start+counter==this.job.length()){
                     ending=g;
                     ending_length=h;
                }
                else if(start==0){
                    starting=g;
                    starting_length=h;
                }
                else if(!g.equals("")){
                    strings.add(g);
                    strings_length.add(h);
                }
                break;
            }
            if(start==-1){
                start=pos.get(i);
                counter=0;
            }
            if(start!=-1&&pos.get(i).equals(counter+start)){
                counter++;
            }
            else if(start!=-1){
                g=this.job.substring(start,start+counter);
                h="";
                k=0;
                while(k<counter){
                    length=String.valueOf(this.getLength(k+start));
                    h+=length.substring(0, length.indexOf('.')+2)+(k==counter-1?"":",");
                    k++;
                }
                if(start+counter==this.job.length()){
                     ending=g;
                     ending_length=h;
                }
                else if(start==0){
                    starting=g;
                    starting_length=h;
                }
                else if(!g.equals("")){
                    strings.add(g);
                    strings_length.add(h);
                }
                start=-1;
                i--;
            }
            i++;
        }
        g=(ending==null?"":ending)+(starting==null?"":starting);
        if(!g.equals("")&&ending!=null&starting!=null)
            strings.add(ending+":"+starting);
        else if(!g.equals(""))
            strings.add(g);
        if(!ending_length.equals("")&&!starting_length.equals("")){
            strings_length.add(ending_length+":"+starting_length);
        }
        else if(!ending_length.equals(""))
            strings_length.add(ending_length);
        else if(!starting_length.equals(""))
            strings_length.add(starting_length);

/*        i=0;
        System.out.println("here");
        while(i<strings.size()){
            System.out.println(strings.get(i));
            i++;
        }
 * 
 */
        return new List[]{strings,strings_length};
    }

    private boolean isPresent(int o,List l){
        int i=0;
        while(i<l.size()){
            if(((Integer)l.get(i))==o)
                return true;
            i++;
        }
        return false;
    }

    public void replace(int a,int b,String s){
        this.replace(a, b, s,false);
    }

    public void replace(int a,int b,String s,boolean con){
//        String g=s.substring(a,b);
//        boolean isDigit=true;
//        try{
//            Integer.parseInt(g);
//        }catch(Exception e){
//            isDigit=false;
//        }
//        if(isDigit){
//            int i=0;
//            while(i<a){
//
//            }
//        }
       
        if(con){
            this.addToList(new Pockets(a,s.length()+a-1,this.replacing(a, b, s)));
////                    this.printList();
        }
        this.job.replace(a, b, s);
////                System.out.println(this.job);
////                System.out.println("*****************");
    }

        private int replacing(int a,int b,String s){
            String g=this.job.substring(a, b);
            boolean isDigit=true;
            try{
                Integer.parseInt(g);
            }catch(Exception e){
                isDigit=false;
            }
            if(isDigit){
                int i=0;
                while(i<list.size()&&list.get(i).getFromPoint()<b)
                    i++;
                Pockets  get;
                while(i<list.size()){
                    get = list.get(i);
                    get.setFromPoint(get.getFromPoint()-(b-a-s.length()));
                    get.setToPoint(get.getToPoint()-(b-a-s.length()));
                    i++;
                }
                return g.length();
            }
            int i=0,count=0,k;
            Pockets get;
            while(i<list.size()){
             get = list.get(i);
             if((k=get.getFromPoint())>=a&&k<b&&(k=get.getToPoint())>=a&&k<b){
                 count+=get.getCountPixels();
                 list.remove(i);
                 i--;
             }
             i++;
            }
            i=0;
        //System.out.println("incrementing 1 : "+count);
            while(i<g.length()){
                if(Character.isDigit(g.charAt(i)))
                    count++;
                i++;
            }
        //System.out.println("incrementing 2 : "+count);
            i=0;
            while(i<list.size()&&list.get(i).getFromPoint()<b)
                    i++;
                while(i<list.size()){
                    get = list.get(i);
                    get.setFromPoint(get.getFromPoint()-(b-a-s.length()));
                    get.setToPoint(get.getToPoint()-(b-a-s.length()));
                    i++;
                }
            return count;
        }

        private void printList(){
            int i=0;
            System.out.println("*****************");
            System.out.println(this.job);
            while(i<list.size()){
                Pockets get = list.get(i);
                System.out.println(get.getFromPoint()+","+get.getToPoint()+" = "+get.getCountPixels());
                i++;
            }
        }

    private void addToList(Pockets pock){
        int i=0;
        int start=pock.getFromPoint();
        while(i<list.size()&&list.get(i).getFromPoint()<start)
            i++;
        list.add(i, pock);
    }

    @Override
    public String toString(){
        return this.job.toString();
    }

    public int indexOf(String s,int from){
        return this.job.indexOf(s, from);
    }

    public int indexOf(String s){
        return this.job.indexOf(s);
    }

    public String substring(int a,int b){
        return this.job.substring(a, b);
    }
    public int length(){
        return this.job.length();
    }
    public void deleteCharAt(int a){
        this.job.deleteCharAt(a);
    }
    public char charAt(int i){
        return this.job.charAt(i);
    }
    public void insert(int i,String s){
        this.job.insert(i, s);
    }
    public void append(String s){
        this.job.append(s);
    }
        }
