package jp.ac.hal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Sinkeisuijaku {

	public static void main(String[] args) throws IOException
	{
		BufferedReader br =
				new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("神経衰弱ゲームへようこそ！\nプレイヤー数を入力してください。（1～4人）");
		System.out.print("人数：");
		
		String str = br.readLine();
		int member = Integer.parseInt(str);
		
		while(member > 4 || member < 1) {
			System.out.print("人数は1～4人までです。\n人数：");
			str = br.readLine();
			member = Integer.parseInt(str);
		}
		
		int[] M = new int[member];
		int[] cap = new int[member];
		for(int i = 0; i < member; i++) {
			M[i] = 0;
			cap[i] = i + 1;
		}
		
		
		Random rand = new Random();
		int card[][] = new int[4][6];
		int hako[] = new int[24 / 2];
		
		for (int n = 0; n < 24 / 2; n++) {
			hako[n] = 0;
		}
		
		for (int i = 0; i < 4; i++) {
			for (int n = 0; n < 6; n++) {
				card[i][n] = 0;
			}
		}
		
		int flg = 0;
		int flg2 = 0;
		int in = 0;
		while(flg == 0) {
			
			int math = rand.nextInt(4 * 6 / 2) + 1;
			
			for (int n = 0; n <24 / 2; n++) {
				if (hako[n] == math) {
					flg2 = 1;
					break;
				}
			}
			
			int x1 = rand.nextInt(4);
			int y1 = rand.nextInt(6);
			int x2 = rand.nextInt(4);
			int y2 = rand.nextInt(6);
			
			if (x1 == x2 && y1 == y2) {
				flg2 = 1;
			}
			
			if (!(card[x1][y1] == 0) || !(card[x2][y2] == 0)) {
				flg2 = 1;
			}
			
			if (flg2 == 1) {
				flg2 = 0;
				continue;
			}
			
			hako[in] = math;
			in++;
			
			card[x1][y1] = math;
			card[x2][y2] = math;
			
			if (24 / 2 == in) {
				flg++;
			}
		}
		
		String[][] H = new String[4][6];
		
		for (int i = 0; i < 4; i++) {
			for (int n = 0; n < 6; n++) {
				H[i][n] = "*";
			}
		}
		
		System.out.println("ゲームスタート！");
		int i = 1;
		int fl = 0;
		while(i <= 4) {
			
			for (int N = 0; N < 4; N++) {
				for (int n = 0; n < 6; n++) {
					System.out.print(H[N][n] + "\t");
				}
				System.out.println("");
			}
			
			System.out.println(i + "番目の人のターンです。");
			System.out.println("めくるカードの位置を指定してください。");
			
			int x,y,x2,y2;
			
			
			while(fl >= 0) {
				System.out.println("1枚目");
				System.out.print("縦：");
				x = Integer.parseInt(br.readLine()) - 1;
				System.out.print("横：");
				y = Integer.parseInt(br.readLine()) - 1;
				if (card[x][y] == -1) {
					System.out.println("すでにめくられたカードです。");
					continue;
				}
				if (x >= 4 || y >=  6) {
					System.out.println("指定された場所にカードが存在しません。");
					continue;
				}
				
				System.out.println("引いたカードは..." + card[x][y]);
				H[x][y] = Integer.toString(card[x][y]);
				
				for (int N = 0; N < 4; N++) {
					for (int n = 0; n < 6; n++) {
						System.out.print(H[N][n] + "\t");
					}
					System.out.println("");
				}
				
				System.out.println("2枚目");
				System.out.print("縦：");
				x2 = Integer.parseInt(br.readLine()) - 1;
				System.out.print("横：");
				y2 = Integer.parseInt(br.readLine()) - 1;
				if (card[x2][y2] == -1) {
					System.out.println("すでにめくられたカードです。");
					continue;
				}
				if (x2 == x || y2 == y) {
					System.out.println("同じカードを選択しています。");
					continue;
				}
				if (x2 >= 4 || y2 >=  6) {
					System.out.println("指定された場所にカードが存在しません。");
					continue;
				}
				
				System.out.println("引いたカードは..." + card[x2][y2]);
				H[x2][y2] = Integer.toString(card[x2][y2]);
				
				for (int N = 0; N < 4; N++) {
					for (int n = 0; n < 6; n++) {
						System.out.print(H[N][n] + "\t");
					}
					System.out.println("");
				}
				
				if (card[x][y] == card[x2][y2]) {
					System.out.println("あたり！");
					card[x][y] = -1;
					card[x2][y2] = -1;
					M[i - 1]++;
					fl++;
					break;
				}else {
					System.out.println("はずれ...");
					H[x][y] = "*";
					H[x2][y2] = "*";
					if (i == member) {
						i = 1;
					}else {
						i++;
					}
					break;
				}
			}
			
			if (fl ==24 / 2) {
				System.out.println("終了！");
				
				for (int n = 0;n < member; n++) {
					for(int cv = n; cv < member; cv++) {
						if (M[n] < M[cv]) {
							int max = M[n];
							M[n] = M[cv];
							M[cv] = max;
							int ai = cap[n];
							cap[n] = cap[cv];
							cap[cv] = ai;
						}
					}
					System.out.println((n + 1) + "位：" + cap[n] + "\n点数：" + M[n]);
				}
				
				break;
			}
			
		}
		
		
		
	}

}
