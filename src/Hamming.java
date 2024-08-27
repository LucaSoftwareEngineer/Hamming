
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luca
 */
public class Hamming {
    public String calculateHammingCode(String data) {
        int m = data.length();
        int r = 0;

        while ((1 << r) < (m + r + 1)) {
            r++;
        }

        char[] parityBits = new char[r];

        char[] hammingCode = new char[m + r];
        int j = 0;
        for (int i = 0; i < hammingCode.length; i++) {
            if (isPowerOfTwo(i + 1)) {
                hammingCode[i] = '0';
            } else {
                hammingCode[i] = data.charAt(j);
                j++;
            }
        }

        for (int i = 0; i < r; i++) {
            int parityPosition = (1 << i);
            int count = 0;
            
            for (int k = parityPosition - 1; k < hammingCode.length; k += 2 * parityPosition) {
                for (int l = k; l < k + parityPosition && l < hammingCode.length; l++) {
                    if (hammingCode[l] == '1') {
                        count++;
                    }
                }
            }

            // Impostare il bit di parità
            if (count % 2 != 0) {
                parityBits[i] = '1';
            } else {
                parityBits[i] = '0';
            }
        }

        String bn = new String(parityBits);
        
        return reverse(bn);
    }
    
    private static boolean isPowerOfTwo(int x) {
        return (x & (x - 1)) == 0;
    }
    
    public static String reverse(String input){
        char[] in = input.toCharArray();
        int begin=0;
        int end=in.length-1;
        char temp;
        while(end>begin){
            temp = in[begin];
            in[begin]=in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }

}
