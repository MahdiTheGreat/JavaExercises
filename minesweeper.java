import java.util.Scanner;

/* In this program, we want to partially implement the minecraft game by writing a program that first takes m and n as input from the user, 
then takes the information of an m*n matrix of character type from the user. Houses marked with '0' are empty houses and houses marked with '*' indicate houses with mines.

example input:
5
10
*00000000*
000000*000
00000000**
000***00*0
000*000000

The output of the program is an m*n matrix, which contains the number of surrounding bombs for the empty houses corresponding to the input and '*' for the houses with mines.

example output:
*10001111*
110001*233 
00123323** 
002***12*3 
002*421111 */

public class Main {
    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);
        int y = scanner.nextInt();
        int x = scanner.nextInt();
        String line[]=new String[y];
        char matrix[][] = new char[y][x];
        int relfection[][] = new int [y][x];
        scanner.nextLine();
        for(int k=0;k<y;k++){
            line[k]=scanner.nextLine();
        }
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                matrix[i][j]=line[i].charAt(j);
            }
        }
        int sum=0;
        int i;
        int j;
        for( i=0;i<y;i++) {
            for ( j = 0; j < x; j++) {
                for(int k=j-1;j<=j+1;j++){
                    for(int l=x-1;x<=x+1;x++){
                        if(k>y || k<0 || l>x || l<0);
                        else if(matrix[k][l]=='*')sum++;
                    }
                }

            }
            relfection[i][j]=sum;
            sum=0;

        }
        for(int k=0;k<y;k++){
            for (int l=0;l<x;l++){
                System.out.print(relfection[k][l]);
            }
        }












    }

}
