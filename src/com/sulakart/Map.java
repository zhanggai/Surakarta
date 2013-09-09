package com.sulakart;

import java.util.Arrays;

//�����࣬���ڶ���һ�����̵ı�ʾ

public class Map {
	public static final int MAXSIZE = 6;				//�������̴�С
	public static final int NOSTONE = 0;				//����������
	public static final int RED = 1;					//����췽����
	public static final int BLUE = 2;					//������������
	public static final int SELECT = 3;					//���屻ѡ������
	
	private int redNum;									//���������к��ָ���
	private int blueNum;								//���������к��Ӹ���
	
	public int[][] map = new int[MAXSIZE][MAXSIZE];

	public int redx[]=new int[12];
	public int redy[]=new int [12];
	
	public int bluex[]=new int[12];
	public int bluey[]=new int[12];
	
	public void clear() {							//������̷���
		for (int i = 0; i < MAXSIZE; i++) {
			Arrays.fill(map[i], 0);
		}
	}
	
	public void init() {							//��ʼ�����̷����������̳�ʼ��Ϊ��ֿ�ʼ״̬
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
	public boolean isInMap(int x, int y) {			//�ж�ĳ���������Ƿ��������з���
		return (x >= 0) && (x < MAXSIZE) && (y >= 0) && (y < MAXSIZE);
	}
	
	public void move(Move m) {						//���ӷ���������������ĳ���з�	
		map[m.x[1]][m.y[1]] = map[m.x[0]][m.y[0]];
		map[m.x[0]][m.y[0]] = NOSTONE;
		if (m.isAttack) {
			if (map[m.x[1]][m.y[1]] == BLUE) redNum--;
			else blueNum--;
		}
	}
	
	public void unMove(Move m) {					//���ӷ������������г���ĳ���з�
		map[m.x[0]][m.y[0]] = map[m.x[1]][m.y[1]];
		if (m.isAttack) {
			map[m.x[1]][m.y[1]] = (map[m.x[0]][m.y[0]] ^ 3);
			if (map[m.x[1]][m.y[1]] == RED) redNum++;
			else blueNum++;
		}
		else map[m.x[1]][m.y[1]] = NOSTONE;
	}
	
	public int getRedNum() {						//��ȡ��ǰ���̺췽��������
		return redNum;
	}
	
	public int getBlueNum() {						//��ȡ��ǰ����������������
		return blueNum;
	}
	
	public int isGameOver() {
		if (getRedNum() == 0) return BLUE;
		if (getBlueNum() == 0) return RED;
		return 0;
	}
}
