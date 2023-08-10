import java.util.*;

/* In this program, a bracket expression is given as input, which contains two characters "(" and ")". 
This expression is balanced when its parentheses are done correctly. The correct parenthesis is:

- Any parentheses that have been opened should be closed.

- When a parenthesis is closed, its opening parenthesis must be present before it.

The input is a line that is the expression of the parentheses. 
The output must be the largest contiguous balance substring starting from the beginning of the string. below are some examples of some inputs and outputs.


input:

()()()

output:

()()()

input:

(()())()()(()))

output:

(()())()()(()))

input:

(()))))(


output:

(()())

input:

(()())((((()

output:

(()())

input:

()
 */
output:



class Parantes {
    public int active;
    public char paran;

    public Parantes(char par){
        active=0;
        paran=par;
    }
    public void activate(){
        this.active=1;
    }


}

public class Main {
    public static void main(String[] args) {

        String line=new String();
        Scanner scanner=new Scanner(System.in);
        line=scanner.nextLine();
        Parantes garbage;
        ArrayList<Parantes> parantes=new ArrayList<>(line.length());
        for(int i=0;i<line.length();i++){
            garbage=new Parantes(line.charAt(i));
            parantes.add(garbage);
        }
        int h=0;
        for(int i=0;i<line.length();i++){
            if(parantes.get(i).paran=='('){
                h++;
            }

        }
        for(int l=0;l<h;l++) {
            for (int i = 0; i < line.length(); i++) {
                if (parantes.get(i).paran == '(' && parantes.get(i).active == 0) {
                    Parantes balanced = parantes.get(i);
                    for (int k = i; k < line.length(); k++) {
                        int d = 0;
                        if (parantes.get(k).paran == ')' && parantes.get(k).active == 0) {
                            parantes.get(k).active = 1;
                            parantes.get(i).active = 1;
                            d++;
                        }
                        if (d == 1) break;

                        if (parantes.get(k).paran == '(' && parantes.get(k).active == 0) {
                            balanced = parantes.get(k);
                        }

                    }
                }
            }
        }
        for(int i=0;i<parantes.size();i++){
            if(parantes.get(i).active==1)System.out.print(parantes.get(i).paran);
        }

        }



    }

