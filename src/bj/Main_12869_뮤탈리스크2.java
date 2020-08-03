package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12869_뮤탈리스크2 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/testcase/bj_12869.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int alive = Integer.parseInt(br.readLine());
		int damage[] = new int[] {9,3,1};
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int temp,min=0,answer=0;

				
				
		System.out.println(answer);
	}
}