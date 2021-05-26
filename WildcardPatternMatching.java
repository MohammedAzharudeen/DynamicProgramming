package DynamicProgramming;

import java.util.*;

public class WildcardPatternMatching {
	
	static boolean patternMatch(String p,String s){
        char[] pattern = p.toCharArray();
        char[] str = s.toCharArray();
        
        int writeIndex = 0;
        boolean isFirst = true;
        for(int i=0;i<pattern.length;i++){
            if(pattern[i]=='*'){
                if(isFirst){
                    pattern[writeIndex++] = pattern[i];
                    isFirst = false;
                }
            }else{
                pattern[writeIndex++] = pattern[i];
            }
        }
        
        boolean[][] T = new boolean[str.length+1][writeIndex+1];
        
        if(writeIndex>0 && pattern[0] == '*'){
            T[0][1] = true;
        }
        T[0][0] = true;
        
        for(int i=1;i<T.length;i++){
            for(int j=1;j<T[0].length;j++){
                if(pattern[j-1]=='?' || str[i-1] == pattern[j-1]){
                    T[i][j] = T[i-1][j-1];
                }else if(pattern[j-1] == '*'){
                    T[i][j] = T[i][j-1] || T[i-1][j];
                }else{
                    T[i][j] = false;
                }
            }
        }
        return T[str.length][writeIndex];
    }

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.println("Pattern :");
        String pattern = sc.next();
        System.out.println("No of matchers:");
        int num = sc.nextInt();
        String[] word = new String[num];
        for(int i=0;i<num;i++){
            word[i] = sc.next();
        }
        sc.close();
        for(int i=0;i<num;i++){
            System.out.println(patternMatch(pattern,word[i]));
        }

	}

}
