package com.sulakart;

import java.util.Arrays;

//棋盘类，用于定义一个棋盘的表示

public class Map {
	public static final int MAXSIZE = 6;				//定义棋盘大小
	public static final int NOSTONE = 0;				//定义无棋子
	public static final int RED = 1;					//定义红方棋子
	public static final int BLUE = 2;					//定义蓝方棋子
	public static final int SELECT = 3;					//定义被选中棋子
	
	private int redNum;									//定义棋盘中红字个数
	private int blueNum;								//定义棋盘中黑子个数
	
	public int[][] map = new int[MAXSIZE][MAXSIZE];

	public int redx[]=new int[12];
	public int redy[]=new int [12];
	
	public int bluex[]=new int[12];
	public int bluey[]=new int[12];
	
	public void clear() {							//清空棋盘方法
		for (int i = 0; i < MAXSIZE; i++) {
			Arrays.fill(map[i], 0);
		}
	}
	
	public void init() {							//初始化棋盘方法，将棋盘初始化为棋局开始状态
		clear();
		
		Arrays.fill(map[0], RED);
		Arrays.fill(map[1], RED);
		Arrays.fill(map[4], BLUE);
		Arrays.fill(map[5], BLUE);
		
		redNum = blueNum = 12;
	}
	
	public void Currentmap()
	{
		int cntr=0,cntb=0;
		for(int i=0;i<MAXSIZE;i++)
		{
			for(int j=0;j<MAXSIZE;j++)
			{
				if(map[i][j]==RED)
				{
					redx[cntr]=j;
					redy[cntr++]=i;
				}
				else if(map[i][j]==BLUE)
				{
					bluex[cntb]=j;
					bluey[cntb++]=i;
				}
			}
		}
	}
	public boolean isInMap(int x, int y) {			//判断某个点坐标是否在棋盘中方法
		return (x >= 0) && (x < MAXSIZE) && (y >= 0) && (y < MAXSIZE);
	}
	
	public void move(Move m) {						//落子方法，在棋盘中走某个招法	
		map[m.x[1]][m.y[1]] = map[m.x[0]][m.y[0]];
		map[m.x[0]][m.y[0]] = NOSTONE;
		if (m.isAttack) {
			if (map[m.x[1]][m.y[1]] == BLUE) redNum--;
			else blueNum--;
		}
	}
	
	public void unMove(Move m) {					//撤子方法，在棋盘中撤销某个招法
		map[m.x[0]][m.y[0]] = map[m.x[1]][m.y[1]];
		if (m.isAttack) {
			map[m.x[1]][m.y[1]] = (map[m.x[0]][m.y[0]] ^ 3);
			if (map[m.x[1]][m.y[1]] == RED) redNum++;
			else blueNum++;
		}
		else map[m.x[1]][m.y[1]] = NOSTONE;
	}
	
	public int getRedNum() {						//获取当前棋盘红方子力方法
		return redNum;
	}
	
	public int getBlueNum() {						//获取当前棋盘蓝方子力方法
		return blueNum;
	}
	
	public int isGameOver() {
		if (getRedNum() == 0) return BLUE;
		if (getBlueNum() == 0) return RED;
		return 0;
	}
}
