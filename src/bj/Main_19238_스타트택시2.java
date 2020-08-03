package bj;

import java.io.*;
import java.util.*;
	
public class Main_19238_스타트택시2 {
	static int [][] map, dir = new int[][] {{-1,0},{0,-1},{0,1},{1,0}};
	static int N, customer, fuel;
	static final int TAXI = 9, CUSTOMER = 8, VISITED=7, BLOCKED=1, EMPTY=0;
	static Map<Integer, Integer> storage;
	public static void main(String[] args) throws Exception {
		// 입력 start
		System.setIn(new FileInputStream("res/testcase/bj_19238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		customer = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		// map 입력
		// 이게 빠를까 아니면 String 그대로 가져와서 ' ' 를 ''로 바꾸고 toCharArray를 하는게 빠를까 아니면 별 차이 없을까
		map = new int[N][N];
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int m=0; m<N; m++) 
				map[n][m] = Integer.parseInt(st.nextToken());
		}

		// 택시 좌표 입력
		st = new StringTokenizer(br.readLine()," ");
		int curX = Integer.parseInt(st.nextToken())-1, curY  = Integer.parseInt(st.nextToken())-1;
		map[curX][curY]=TAXI;
		
		// 고객 좌표 입력
		storage= new HashMap<Integer, Integer>();
		// map 해서 key-value로 담아놓자
		// key, value ==> x*100 + y
		int tx, ty;
		for(int c=0; c<customer; c++) {
			st = new StringTokenizer(br.readLine()," ");
			tx = Integer.parseInt(st.nextToken())-1;
			ty = Integer.parseInt(st.nextToken())-1;
			map[tx][ty]= CUSTOMER;
			storage.put(tx*100+ty,(Integer.parseInt(st.nextToken())-1)*100+(Integer.parseInt(st.nextToken())-1));
		}		
		// 입력 end
		
		// 택시 위치로부터 가장 가까운 customer 찾기
		FindCustomer(curX,curY);
		System.out.println(fuel);
	}
	static void FindCustomer(int curX, int curY) {
//		System.out.println("손님찾기 시작할때 본 현재위치 : curx/cury : " + curX+ " " + curY);
		LinkedList<int[]> queue = new LinkedList<int[]>();
		LinkedList<int[]> visited = new LinkedList<int[]>();
		int orgx, orgy, tmpx, tmpy, usedFuel=0;
		queue.offer(new int[] {curX,curY,usedFuel});
		visited.offer(new int[] {curX,curY});
		if(map[curX][curY]==CUSTOMER) {
			orgx = queue.peek()[0];
			orgy = queue.peek()[1];
			usedFuel = 0;
//			System.out.println("손님찾기 usedfuel:"+ (usedFuel) + " fuel: " + fuel +"---------------1-----------------");
			while(!visited.isEmpty())
				map[visited.peek()[0]][visited.poll()[1]]=EMPTY;
//			System.out.println("orgx, orgy in 손님찾기 끝나고 탐색시작전 위치 : " + orgx + " " + orgy);
			MoveToDestination(orgx,orgy);
			return;
		}
		map[curX][curY] = VISITED;
		while(!queue.isEmpty()) {
			orgx = queue.peek()[0];
			orgy = queue.peek()[1];
			usedFuel = queue.poll()[2];
			for(int i=0; i<4; i++) {
				tmpx = orgx + dir[i][0];
				tmpy = orgy + dir[i][1];
				if(tmpx<0 || tmpy<0 || tmpx>=N || tmpy>=N || map[tmpx][tmpy]==BLOCKED || map[tmpx][tmpy]==VISITED) continue;
				if(map[tmpx][tmpy]==CUSTOMER) {
//					System.out.println("tmpx, tmpy in 손님찾기 끝나고 탐색시작전 위치 : " + tmpx + " " + tmpy);
					fuel -= (usedFuel+1);
//					for(int n=0; n<N; n++)
//						System.out.println(Arrays.toString(map[n]));
//					System.out.println("손님찾기 usedfuel:"+ (usedFuel+1) + " fuel: " + fuel +"---------------1-----------------");
					if(fuel<0) {
						fuel=-1;
						return;
					}
					while(!visited.isEmpty())
						map[visited.peek()[0]][visited.poll()[1]]=EMPTY;
//					System.out.println("원상복구 시작ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//					for(int n=0; n<N; n++)
//						System.out.println(Arrays.toString(map[n]));
//					System.out.println("원상복구 끝ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//					System.out.println("tmpx, tmpy in 손님찾기 끝나고 탐색시작전 위치 : " + tmpx + " " + tmpy);
//					System.out.println("손님찾기  usedfuel:"+ (usedFuel+1) + " fuel: " + fuel +"---------------1-----------------");
					MoveToDestination(tmpx,tmpy);
//					System.out.println("moveTodestination끝남");
					return;
				}
				queue.offer(new int[] {tmpx, tmpy,usedFuel+1});
				visited.offer(new int[] {tmpx,tmpy});
				map[tmpx][tmpy] = VISITED;
			}
		}
		fuel = -1;
//		return;
	}
	static void MoveToDestination(int curX, int curY) {
//		System.out.println("길찾기 시작할때 본 현재위치 : curx/cury : " + curX+ " " + curY);
		LinkedList<int[]> queue = new LinkedList<int[]>();
		LinkedList<int[]> visited = new LinkedList<int[]>();
		int orgx, orgy, tmpx, tmpy, usedFuel=0;
//		System.out.println(storage.toString());
//		System.out.println(curX + " " + curY);
		int destx=storage.get(curX*100+curY)/100, desty=storage.get(curX*100+curY)%100;
		queue.offer(new int[] {curX,curY,usedFuel});
		visited.offer(new int[] {curX,curY,EMPTY});
		map[curX][curY] = VISITED;
		while(!queue.isEmpty()) {
			orgx = queue.peek()[0];
			orgy = queue.peek()[1];
			usedFuel = queue.poll()[2];
			for(int i=0; i<4; i++) {
				tmpx = orgx + dir[i][0];
				tmpy = orgy + dir[i][1];
				if(tmpx<0 || tmpy<0 || tmpx>=N || tmpy>=N || map[tmpx][tmpy]==BLOCKED || map[tmpx][tmpy]==VISITED) continue;
				if(tmpx == destx && tmpy == desty) {
					// 목적지 도착
					fuel-=(usedFuel+1);
					if(fuel<0) {
						fuel=-1;
						return;
					}else {
						storage.remove(curX*100+curY);
						if(storage.size()>0) {
//							for(int n=0; n<N; n++)
//								System.out.println(Arrays.toString(map[n]));
//							System.out.println("길찾기 / usedfuel:"+ (usedFuel+1) + " fuel: " + fuel +"---------------2-----------------");
							while(!visited.isEmpty()) 
								map[visited.peek()[0]][visited.peek()[1]]=visited.poll()[2];
//							System.out.println("원상복구 시작ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//							for(int n=0; n<N; n++)
//								System.out.println(Arrays.toString(map[n]));
//							System.out.println("원상복구 끝ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
							fuel+=(usedFuel+1)*2;
//							System.out.println("길찾기 / usedfuel:"+ (usedFuel+1) + " 충전된 fuel: " + fuel +"---------------2-----------------");
//							System.out.println("길찾기 끝나고 본 현재위치 : tmpx/tmpy" + tmpx + " " + tmpy);
//							System.out.println(fuel);
							FindCustomer(tmpx,tmpy);
//							System.out.println("find customer 끝남");
							return;
						}
						else {
							fuel+=(usedFuel+1)*2;
							return;
						}
					}
				}
//				System.out.println("tmpx/tmpy/usedFuel+1 : " + tmpx + " " +tmpy+ " " + (usedFuel+1));
				queue.offer(new int[] {tmpx, tmpy,usedFuel+1});
				visited.offer(new int[] {tmpx,tmpy,map[tmpx][tmpy]});
				map[tmpx][tmpy] = VISITED;
			}
		}
		return;
	}
}
