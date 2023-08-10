package com.company;
import java.util.*;

We write a program that first takes n integers and then n strings from the input. The output of the program will be the largest string like S, each string having S or its inverse as a substring. If there is no shared substring, print nothing. The sub-string that is printed in the output should be in the form that is in the first string.
For example, in the following example, 'CDEF' should be printed, and not 'FEDC'
example input:
3
ABCDEF
FEDCAB
GHCDEFJK
example output:
CDEF
 

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int j = 0;
        String exam = new String();
        exam = "";
        scanner.nextLine();
        ArrayList<String> lin = new ArrayList<>();
        char lines[][] = new char[n][];
        String line[] = new String[n];
        for (int i = 0; i < n; i++) {
            line[i] = scanner.nextLine();

        }
        for (int i = 0; i < n; i++) {
            j = line[n].length();
            lines[n] = new char[j];
            for (int k = 0; k < j; k++) {
                lines[n][k] = line[n].charAt(k);

            }
        }
        for (int i = 1; i < n; i++) {
            for (int k = 0; k < j; k++) {
                int h = 0;
                int g = 0;
                if (lines[0][g] == lines[n][j]) {
                    h++;
                    g++;
                    exam += lines[n][j];
                }
                if (h > 1) lin.add(exam);
                else exam = "";

            }
            for (int k = j - 1; k >= 0; k++) {
                int h = 0;
                int g = 0;
                if (lines[0][g] == lines[n][j]) {
                    h++;
                    g++;
                    exam += lines[n][j];
                }
                if (h > 1) lin.add(exam);
                else exam = "";
            }


        }
        int u = lin.size();
        String max = new String();
        


    }
}
