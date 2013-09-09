package com.sulakart;

public class Evalue {
	private static int[][] redPosValue =
	{
		{ 50, 100, 100, 100, 100, 50 },
		{100, 120, 150, 150, 120, 100},
		{100, 150, 120, 120, 150, 100},
		{110, 160, 130, 130, 160, 110},
		{110, 130, 160, 160, 130, 110},
		{ 50, 100, 100, 100, 100, 50 }
	};

	private static int[][] bluePosValue = 
	{
		{ 50, 100, 100, 100, 100, 50 },
		{110, 130, 160, 160, 130, 110},
		{110, 160, 130, 130, 160, 110},
		{100, 150, 120, 120, 150, 100},
		{100, 120, 150, 150, 120, 100},
		{ 50, 100, 100, 100, 100, 50 }
	};
	
	private static final int EACHSTONE = 300;
	
	
	@SuppressWarnings("static-access")
	public int evaluef(Map board, int color) {
		int redScore, blueScore;
		
		redScore = board.getRedNum() * EACHSTONE;
		blueScore = board.getBlueNum() * EACHSTONE;
		redScore += getPosValue(board, board.RED);
		blueScore += getPosValue(board, board.BLUE);
		
		if (color == board.RED) return redScore - blueScore;
		return blueScore - redScore;
	}
	
	@SuppressWarnings("static-access")
	private int getPosValue(Map board, int color) {
		int i, j, ret = 0;
		
		for (i = 0; i < board.MAXSIZE; i++) {
			for (j = 0; j < board.MAXSIZE; j++) {
				if (board.map[i][j] == color) {
					if (color == board.RED) ret += redPosValue[i][j];
					else ret += bluePosValue[i][j];
				}
			}
		}
		
		return ret;
	}
}
