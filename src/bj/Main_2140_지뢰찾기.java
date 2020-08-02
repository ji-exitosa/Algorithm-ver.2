package bj;

import java.io.*;

public class Main_2140_지뢰찾기 {
	static int [][] dir = new int[][] {{0,1},{1,0},{0,-1},{-1,0}}; // 우 하 좌 상
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/testcase/bj_2140.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		int answer = (size - 4)*(size - 4);	 
		if(size<3) {
			answer=0;
		}else if(size==3){
			answer=br.readLine().charAt(0)-'0';
		}else {
			char map[][] = new char[size][size];
			// 중앙에 대한 정보가 없고 최대값을 원하니까 이부분은 다 지뢰가 있다고 가정
			for(int s=0; s<size; s++)
				map[s]=br.readLine().toCharArray();
			// 시계방향 돌기
			int curx=0,cury=-1,tempx, tempy,calculator;
			// '0' = 48 '8' = 56
			for(int i=0; i<4; i++) {
				for(int j=0; j<size-(i+1)/2; j++) {
					curx+=dir[i][0];
					cury+=dir[i][1];
					tempx = curx+dir[(i+1)%4][0]+dir[(i+2)%4][0];
					tempy = cury+dir[(i+1)%4][1]+dir[(i+2)%4][1];
//					System.out.println(curx + " " + tempx + " / " + cury + " " + tempy);
//					System.out.println("curx / cury = " + curx + " / " + cury);
					calculator=map[curx][cury]-'0';
					for(int r=0; r<2; r++) {
//						System.out.println("tmpx / tmpy = " + tempx + " / " + tempy);
						if(tempx>=0&&tempy>=0&&tempx<size-1&&tempy<size-1&&(map[tempx][tempy]=='.'))
							calculator-=1;
						tempx += dir[i][0];
						tempy += dir[i][1];
					}
					if(tempx>0&&tempy>0&&tempx<size-1&&tempy<size-1&&map[tempx][tempy]!='.'&&calculator==1) {
//						System.out.println(tempx + " " + tempy);
						map[tempx][tempy]='.';
						answer++;
					}
				}
			}
		}
		System.out.println(answer);
//		for(int s=0; s<size; s++) {
//		System.out.println(Arrays.toString(map[s]));
//	}
	}
}





//if(tempx<0||tempy<0||tempx>size-1||tempy>size-1) {
////	||(map[tempx][tempy]>='0'&&map[tempx][tempy]<='8')
//	// 이거일때도 +=0이긴한데 
//	calculator+=0;
//}else {
//	if(map[tempx][tempy]=='.')
//		calculator+=1;
//}
