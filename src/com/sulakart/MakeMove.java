package com.sulakart;

import java.util.Arrays;

public class MakeMove {
	//绿线和蓝线上按顺时针方向没点坐标
	//0-5为上 6-11为右 12-17为下 18-23为左
	public static final int[] outx = {1, 1, 1, 1, 1, 1, 0, 1, 2, 3, 4, 5, 4, 4, 4 ,4, 4, 4, 5, 4, 3, 2, 1, 0};
	public static final int[] outy = {0, 1, 2, 3, 4, 5, 4, 4, 4, 4, 4, 4, 5, 4, 3, 2, 1, 0, 1, 1, 1, 1, 1, 1};
	public static final int[] inx = {2, 2, 2, 2, 2, 2, 0, 1, 2, 3, 4, 5, 3, 3, 3, 3, 3, 3, 5, 4, 3, 2, 1, 0};
	public static final int[] iny = {0, 1, 2, 3, 4, 5, 3, 3, 3, 3, 3, 3, 5, 4, 3, 2, 1, 0, 2, 2, 2, 2, 2, 2};
	public static final int LINE_LEN = 24;
	
	public final int[] stepx = {-1, -1, -1, 0, 0, 1, 1, 1};
	public final int[] stepy = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	@SuppressWarnings("static-access")
	public void makeMoves(Map board, MoveStack stack, int color) {
		stack.clear();
		
		int i, j;
		
		for (i = 0; i < board.MAXSIZE; i++) {
			for (j = 0; j < board.MAXSIZE; j++) {
				if (board.map[i][j] == color) {
					getAttackMoves(board, i, j, color, stack);
				}
			}
		}
		for (i = 0; i < board.MAXSIZE; i++) {
			for (j = 0; j < board.MAXSIZE; j++) {
				if (board.map[i][j] == color) {
					getUnAttackMoves(board, i, j, stack);
				}
			}
		}
		
	}
	
	private void getAttackMoves(Map board, int x, int y, int color, MoveStack stack) {
		int i, p;
		boolean[] vis = new boolean[LINE_LEN];
		
		Arrays.fill(vis, false);
		for (i = 0; i < LINE_LEN; i++) {
			if (x == outx[i] && y == outy[i]) {
				p = getOutTarget(board, i, 1, color);
				if (p >= 0 && !vis[p]) {
					stack.push(new Move(x, y, outx[p], outy[p], true));
					vis[p] = true;
				}
				p = getOutTarget(board, i, -1, color);
				if (p >= 0 && !vis[p]) {
					stack.push(new Move(x, y, outx[p], outy[p], true));
					vis[p] = true;
				}
			}
			if (x == inx[i] && y == iny[i]) {
				p = getInTarget(board, i, 1, color);
				if (p >= 0 && !vis[p]) {
					stack.push(new Move(x, y, inx[p], iny[p], true));
					vis[p] = true;
				}
				p = getInTarget(board, i, -1, color);
				if (p >= 0 && !vis[p]) {
					stack.push(new Move(x, y, inx[p], iny[p], true));
					vis[p] = true;
				}
			}
		}
	}
	
	@SuppressWarnings("static-access")
	private int getOutTarget(Map board, int p, int d, int color) {
		int i, end;
		
		if (d == 1) end = ((p / 6 + 1) * 6 + LINE_LEN) % LINE_LEN;
		else end = ((p / 6) * 6 - 1 + LINE_LEN) % LINE_LEN;
		for (i = (p + d + LINE_LEN) % LINE_LEN; i != end; i = (i + d + LINE_LEN) % LINE_LEN) {
			if (board.map[outx[i]][outy[i]] != board.NOSTONE) return -1;
		}
		for (; i != p; i = (i + d + LINE_LEN) % LINE_LEN) {
			if (outx[i] == outx[p] && outy[i] == outy[p]) continue;
			if (board.map[outx[i]][outy[i]] == color) return -1;
			if (board.map[outx[i]][outy[i]] == (color ^ 3)) return i;
		}
		
		return -1;
	}
	
	@SuppressWarnings("static-access")
	private int getInTarget(Map board, int p, int d, int color) {
		int i, end;
		
		if (d == 1) end = ((p / 6 + 1) * 6 + LINE_LEN) % LINE_LEN;
		else end = ((p / 6) * 6 - 1 + LINE_LEN) % LINE_LEN;
		for (i = (p + d + LINE_LEN) % LINE_LEN; i != end; i = (i + d + LINE_LEN) % LINE_LEN) {
			if (board.map[inx[i]][iny[i]] != board.NOSTONE) return -1;
		}
		for (; i != p; i = (i + d + LINE_LEN) % LINE_LEN) {
			if (inx[i] == inx[p] && iny[i] == iny[p]) continue;
			if (board.map[inx[i]][iny[i]] == color) return -1;
			if (board.map[inx[i]][iny[i]] == (color ^ 3)) return i;
		}
		
		return -1;
	}
	
	@SuppressWarnings("static-access")
	private void getUnAttackMoves(Map board, int x, int y, MoveStack stack) {
		int i, tx, ty;
		
		for (i = 0; i < 8; i++) {
			tx = x + stepx[i];
			ty = y + stepy[i];
			if (board.isInMap(tx, ty) && board.map[tx][ty] == board.NOSTONE) {
				stack.push(new Move(x, y, tx, ty, false));
			}
		}
	}
}
