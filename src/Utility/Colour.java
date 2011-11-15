package Utility;

import java.awt.Color;

    public class Colour {

        private int RED,GREEN,BLUE,hue;
        private  static double GOOD_BRIGHTNESS=75;//125
        private static int GOOD_HUE=300;//500
        private double brightness;
        private Color color;

        public Colour(int r,int g,int b){
            color=new Color(RED,GREEN,BLUE);
            RED=r;GREEN=g;BLUE=b;
            brightness=(299*RED+587*GREEN+114*BLUE)/1000;
        }

        public Colour(Color color1){
           color=color1;
           RED=color.getRed();GREEN=color.getGreen();BLUE=color.getBlue();
           brightness=(299*RED+587*GREEN+114*BLUE)/1000;
        }

        public int getRed(){
            return RED;
        }

        public int getGreen(){
            return GREEN;
        }

        public int getBlue(){
            return BLUE;
        }

        public int getHueContrast(Color c){
            return (Math.abs(c.getRed()-this.RED)+Math.abs(c.getBlue()-this.BLUE)+Math.abs(c.getGreen()-this.GREEN));
        }

        public double getBrightnessContrast(Color c){
            return Math.abs(this.getBrightness()-new Colour(c).getBrightness());
        }

        public boolean isBrightnessContrastGood(Color c){
            if(this.getBrightnessContrast(c)>GOOD_BRIGHTNESS)
                return true;
            return false;
        }

        public boolean isHueContrastGood(Color c){
            if(this.getHueContrast(c)>GOOD_HUE)
                return true;
            return false;
        }

        public boolean isContrastGood(Color c){
            if(this.isBrightnessContrastGood(c)&&this.isHueContrastGood(c))
                return true;
            return false;
        }

        public double getBrightness(){
            return brightness;
        }

}
