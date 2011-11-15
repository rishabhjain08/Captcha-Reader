package Utility;

import java.util.LinkedList;
import java.util.List;

    public class Chars {
        private List <Character>list=new LinkedList();
        char[] chars=new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','@','#','$','%','&','*','-'};
        void Chars(){
            list.add('A');
            list.add('B');
            list.add('C');
            list.add('D');
            list.add('E');
            list.add('F');
            list.add('G');
            list.add('H');
            list.add('I');
            list.add('J');
            list.add('K');
            list.add('L');
            list.add('M');
            list.add('N');
            list.add('O');
            list.add('P');
            list.add('Q');
            list.add('R');
            list.add('S');
            list.add('T');
            list.add('U');
            list.add('V');
            list.add('W');
            list.add('X');
            list.add('Y');
            list.add('Z');
            list.add('0');
            list.add('1');
            list.add('2');
            list.add('3');
            list.add('4');
            list.add('5');
            list.add('6');
            list.add('7');
            list.add('8');
            list.add('9');
            list.add('@');
            list.add('#');
            list.add('$');
            list.add('%');
            list.add('&');
            list.add('*');
            list.add('-');
        }
        public boolean removeChar(char c){
            int i=0;
            while(i<list.size()){
                if(list.get(i)=='c'){
                    list.remove(i);
                    return true;
                }
                i++;
            }
            return false;
        }
        public void addChar(char c){
            this.list.add(c);
        }
    }
