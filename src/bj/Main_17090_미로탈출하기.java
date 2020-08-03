package bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_17090_미로탈출하기 {
	static char map[][];
	static HashMap<Character,int[]> easy = new HashMap<Character,int[]>();
	static int M,N,answer=0;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/testcase/bj_17090.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		map = new char[M][N];
		for(int m=0; m<M; m++)
			map[m] = br.readLine().toCharArray();
		easy.put('U', new int[] {-1,0});
		easy.put('R', new int[] {0,1});
		easy.put('D', new int[] {1,0});
		easy.put('L', new int[] {0,-1});
		for(int m=0;m<M;m++) {
			for(int n=0;n<N;n++) {
				if(map[m][n]>'B')
					escape(m,n);
				//System.out.println("map[m][n] : " + map[m][n] + "/ dir[map[m][n]][0] : " + dir[map[m][n]][0]);
			}
		}
		System.out.println(answer);
		
//		for(int m=0; m<M; m++)
//			System.out.println(Arrays.toString(map[m]));
	}
	private static void escape(int m, int n) {
		int tm,tn;
		char result = 'B'; // 일단 실패라고 가정하고 성공할 경우에만 A로 바꿔줌
		// 이거 지울거 테스트용임
		int counter=M*N;
		LinkedList <int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {m,n});
		
		while(counter>0) {
//			System.out.println(m + " " + n);
//			for(int x=0; x<M; x++)
//				System.out.println(Arrays.toString(map[x]));
//			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			counter--;
			// m+=easy.get(map[m][n])[0]; 
			// n+=easy.get(map[m][n])[1]; 이렇게하면  m 값이 boundary 넘어갈 경우 n에서 array out of boundary exception 
			tm = easy.get(map[m][n])[0];
			tn = easy.get(map[m][n])[1];

			map[m][n]=result;
			
			m+= tm;
			n+= tn;
			
			// 탈출하면 그대로 'A'
			if(m<0 || n<0 || m>=M || n>=N) {
				result = 'A';
				break;
			}
			
			if(!easy.containsKey(map[m][n])) {
				result = map[m][n];
//				System.out.println("반복 or 이미 있는 결과");
				break;
			}
			
			
			// hash map에 해당하는 키가 없다는 것은 과거에 방문했거나 이전에 while문 돌면서 방문한 지점이라는 뜻. 
			// --> 실패or성공 해당 칸 결과를 따름
			// 일단 실패라고 가정하고 지나온 길들을 다 B로 설정했기때문에 왔던길을 되돌아가는 상황(즉 -> <- 이렇게 핑퐁되는 상황)이면 실패로 인식하고 break;
			
			queue.offer(new int[] {m,n});
		}
		if(result=='A')
			answer += queue.size();
		while(!queue.isEmpty()) 
			map[queue.peek()[0]][queue.poll()[1]]=result; // 성공표시 A
		
//		System.out.println("결과ㅡㅡㅡㅡㅡㅡㅡㅡ");
//		for(int x=0; x<M; x++)
//			System.out.println(Arrays.toString(map[x]));
//		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
	}

}
