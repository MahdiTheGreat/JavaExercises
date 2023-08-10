package com.company;
import java.util.*;

/* We write a program that receives an algebraic expression from the input and prints its integral as a string in the output. The general format of each sentence is {sign}x^{power}, 
which naturally may not contain parts of the sentence. For example, consider the following entry:
4x^2-5x^4+x-2
with the output below:
-x^5+1.33x^3+0.5x^2-2x

below things are considered:
1) The coefficients of sentences of the input string are integers and their exponents are non-negative integers.

2) Sentences with zero coefficient, power of 1, positive sign of the first term, coefficient of 1, x with zero power, etc. should not be included in the output string. (that is, the output string must be simplified according to the mathematical standard)

3) The coefficient of the output sentences is placed in the string as a decimal number up to two decimal digits (similar to the example). (useless zeros after the decimal point should not be printed)

4) Simplify the resulting algebraic expression as much as possible (for example, 5^2x should be written instead of 5^x^5+x)

5) The final expressions should be sorted in the order of degree x in the output string.

6) There is no need to add the constant C with the integral result. */

 class Ex {
    int power;
    int positive;
    int coeficient;
    public Ex(){
        coeficient=1;
        power=1;
        positive=1;
    }
}


public class Main {

    public static void main(String[] args) {
    Scanner scanner=new Scanner(System.in);
	String line=new String();
    line=scanner.nextLine();
    String number=new String();
    number="";
        int j=line.length();
        char lines[]=new char[j];
        for(int i=0;i<j;i++){
            lines[i]=line.charAt(i);
        }
        int k=0;
        for(int i=0;i<j;i++){
            if(lines[i]=='x')k++;
        }
        Ex x[]=new Ex[k];
        int coe[]=new int[k];
        int power[]=new int[k];

        int g=0;
        int c=0;
        for(int i=0;i<j;i++){
            if(lines[i]=='x' && c==0) {
                x[g].coeficient=1;
                g++;
            }
            if(lines[i]=='x' && c!=0) {
                x[g].coeficient=Integer.parseInt(number);
                g++;
                c=0;
            }
            if(lines[i]=='1' || lines[i]=='2' || lines[i]=='3' || lines[i]=='4' || lines[i]=='5' || lines[i]=='6' || lines[i]=='7' || lines[i]=='8' ||lines[i]=='9' ||lines[i]=='0' ){
                number+=lines[i];
                c++;
            }
        }
        g=0;
        int p=0;
        number="";
        for(int i=0;i<j;i++){
            if(lines[i]=='x')p++;



            }

        }





    }
}
