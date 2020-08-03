package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12869_뮤탈리스크 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/testcase/bj_12869.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int alive = Integer.parseInt(br.readLine());
		int damage[] = new int[] {9,3,1};
		int damageTotal[] = new int[] {9,12,13};
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int temp,min=0,answer=0;
		int [] SCV = new int[3];
		for(int n=0; n<alive; n++)
			min+= SCV[n] = Integer.parseInt(st.nextToken());
		System.out.println("min: " + min);
		answer = min%damageTotal[alive-1]>0?min/damageTotal[alive-1]+1:min/damageTotal[alive-1];
		alive=(alive==1?0:alive);
		while(alive>0) {
			System.out.println(Arrays.toString(SCV));
			if(alive==1) {
				// 굉장히 비효율적인거같긴한데
				for(int i=0; i<3; i++) 
					if(SCV[i]>0) {
						answer+=SCV[i]%9>0?SCV[i]/9+1:SCV[i]/9;
						break;
					}
			}else if(alive==2) {
				break;
			}else if(alive==3) {
				break;
			}else {
				break;
			}
		}
		System.out.println(answer);
	}
}