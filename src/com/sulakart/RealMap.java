package com.sulakart;

class Route {
	//绿线和蓝线上按顺时针方向没点坐标
	//0-5为上 6-11为右 12-17为下 18-23为左
	public static final int[] outx = {1, 1, 1, 1, 1, 1, 0, 1, 2, 3, 4, 5, 4, 4, 4 ,4, 4, 4, 5, 4, 3, 2, 1, 0};
	public static final int[] outy = {0, 1, 2, 3, 4, 5, 4, 4, 4, 4, 4, 4, 5, 4, 3, 2, 1, 0, 1, 1, 1, 1, 1, 1};
	public static final int[] inx = {2, 2, 2, 2, 2, 2, 0, 1, 2, 3, 4, 5, 3, 3, 3, 3, 3, 3, 5, 4, 3, 2, 1, 0};
	public static final int[] iny = {0, 1, 2, 3, 4, 5, 3, 3, 3, 3, 3, 3, 5, 4, 3, 2, 1, 0, 2, 2, 2, 2, 2, 2};
	public static final int LINE_LEN = 24;
	
	public static final int[][] px = {
		{ 69,  69,  69,  69,  69,  69},
		{ 93,  93,  93,  93,  93,  93},
		{117, 117, 117, 117, 117, 117},
		{141, 141, 141, 141, 141, 141},
		{165, 165, 165, 165, 165, 165},
		{189, 189, 189, 189, 189, 189}
	};
	public static final int[][] py = {
		{176, 200, 224, 248, 272, 296},
		{176, 200, 224, 248, 272, 296},
		{176, 200, 224, 248, 272, 296},
		{176, 200, 224, 248, 272, 296},
		{176, 200, 224, 248, 272, 296},
		{176, 200, 224, 248, 272, 296}
	};
	
	public static final double PI = 3.14159265358979323;
	
	public static final int NSTEP = 3;
	public static final int LEN = 24;
	public static final int MAXSTONE = 200;
	
	Stone[] stoneRoute = new Stone[MAXSTONE];
	public int tot;
	
	public void makeRoute(Map board, Move m) {
		int p;
		
		init();
		
		if (!m.isAttack) {
			getUnAttackRoute(board, m);
			return;
		}
		
		for (p = 0; p < LINE_LEN; p++) {
			if (outx[p] == m.x[0] && outy[p] == m.y[0]) {
				if (canOutAttack(board, p, 1, board.map[m.x[0]][m.y[0]], m.x[1], m.y[1])) {
					getOutAttackRoute(board, m, p, 1);
					return;
				}
				if (canOutAttack(board, p, -1, board.map[m.x[0]][m.y[0]], m.x[1], m.y[1])) {
					getOutAttackRoute(board, m, p, -1);
					return;
				}
			}
		}
		for (p = 0; p < LINE_LEN; p++) {
			if (inx[p] == m.x[0] && iny[p] == m.y[0]) {
				if (canInAttack(board, p, 1, board.map[m.x[0]][m.y[0]], m.x[1], m.y[1])) {
					getInAttackRoute(board, m, p, 1);
					return;
				}
				if (canInAttack(board, p, -1, board.map[m.x[0]][m.y[0]], m.x[1], m.y[1])) {
					getInAttackRoute(board, m, p, -1);
					return;
				}
			}
		}
	}
	
	private void init() {
		tot = 0;
	}
	
	private void insert(int x, int y, int color) {
		System.out.format("x = %d, y = %d, tot = %d\n", x, y, tot);
		stoneRoute[tot++] = new Stone(x, y, color);
	}
	
	private boolean canOutAttack(Map board, int p, int d, int color, int tx, int ty) {
		int i, end;
		
		if (d == 1) end = ((p / 6 + 1) * 6 + LINE_LEN) % LINE_LEN;
		else end = ((p / 6) * 6 - 1 + LINE_LEN) % LINE_LEN;
		for (i = (p + d + LINE_LEN) % LINE_LEN; i != end; i = (i + d + LINE_LEN) % LINE_LEN) {
			if (board.map[outx[i]][outy[i]] != Map.NOSTONE) return false;
		}
		for (; i != p; i = (i + d + LINE_LEN) % LINE_LEN) {
			if (outx[i] == outx[p] && outy[i] == outy[p]) continue;
			if (outx[i] == tx && outy[i] == ty) return true;
			if (board.map[outx[i]][outy[i]] != Map.NOSTONE) return false;
		}
		
		return false;
	}
	
	private boolean canInAttack(Map board, int p, int d, int color, int tx, int ty) {
		int i, end;
		
		if (d == 1) end = ((p / 6 + 1) * 6 + LINE_LEN) % LINE_LEN;
		else end = ((p / 6) * 6 - 1 + LINE_LEN) % LINE_LEN;
		for (i = (p + d + LINE_LEN) % LINE_LEN; i != end; i = (i + d + LINE_LEN) % LINE_LEN) {
			if (board.map[inx[i]][iny[i]] != Map.NOSTONE) return false;
		}
		for (; i != p; i = (i + d + LINE_LEN) % LINE_LEN) {
			if (inx[i] == inx[p] && iny[i] == iny[p]) continue;
			if (inx[i] == tx && iny[i] == ty) return true;
			if (board.map[inx[i]][iny[i]] != Map.NOSTONE) return false;
		}
		
		return false;
	}
	
	private void getUnAttackRoute(Map board, Move m) {
		int sx, sy, tx, ty;
		int color = board.map[m.x[0]][m.y[0]];
		
		sx = px[m.x[0]][m.y[0]];
		sy = py[m.x[0]][m.y[0]];
		tx = px[m.x[1]][m.y[1]];
		ty = py[m.x[1]][m.y[1]];
		getLine(sx, sy, tx, ty, color);
		insert(tx, ty, color);
	}
	
	private void getOutAttackRoute(Map board, Move m, int p, int d) {
		int sx, sy, tx, ty, color = board.map[m.x[0]][m.y[0]];
		int i;
		
		for (i = p; !(outx[i] == m.x[1] && outy[i] == m.y[1]); i = (i + d + LINE_LEN) % LINE_LEN) {
			if (i == 5 && d == 1) {
				getCircle(px[0][5], py[0][5], LEN, PI / 2, -1, color);
			}
			else if (i == 6 && d == -1) {
				getCircle(px[0][5], py[0][5], LEN, PI, 1, color);
			}
			else if (i == 11 && d == 1) {
				getCircle(px[5][5], py[5][5], LEN, PI, -1, color);
			}
			else if (i == 12 && d == -1) {
				getCircle(px[5][5], py[5][5], LEN, -PI / 2, 1, color);
			}
			else if (i == 17 && d == 1) {
				getCircle(px[5][0], py[5][0], LEN, 3 * PI / 2, -1, color);
			}
			else if (i == 18 && d == -1) {
				getCircle(px[5][0], py[5][0], LEN, 0, 1, color);
			}
			else if (i == 23 && d == 1) {
				getCircle(px[0][0], py[0][0], LEN, 0, -1, color);
			}
			else if (i == 0 && d == -1) {
				getCircle(px[0][0], py[0][0], LEN, PI / 2, 1, color);
			}
			else {
				sx = px[outx[i]][outy[i]];
				sy = py[outx[i]][outy[i]];
				tx = px[outx[(i + d + LINE_LEN) % LINE_LEN]][outy[(i + d + LINE_LEN) % LINE_LEN]];
				ty = py[outx[(i + d + LINE_LEN) % LINE_LEN]][outy[(i + d + LINE_LEN) % LINE_LEN]];
				getLine(sx, sy, tx, ty, color);
			}
		}
		insert(px[m.x[1]][m.y[1]], py[m.x[1]][m.y[1]], color);
	}
	
	private void getInAttackRoute(Map board, Move m, int p, int d) {
		int sx, sy, tx, ty, color = board.map[m.x[0]][m.y[0]];
		int i;
		
		for (i = p; !(inx[i] == m.x[1] && iny[i] == m.y[1]); i = (i + d + LINE_LEN) % LINE_LEN) {
			if (i == 5 && d == 1) {
				getCircle(px[0][5], py[0][5], 2 * LEN, PI / 2, -1, color);
			}
			else if (i == 6 && d == -1) {
				getCircle(px[0][5], py[0][5], 2 * LEN, PI, 1, color);
			}
			else if (i == 11 && d == 1) {
				getCircle(px[5][5], py[5][5], 2 * LEN, PI, -1, color);
			}
			else if (i == 12 && d == -1) {
				getCircle(px[5][5], py[5][5], 2 * LEN, -PI / 2, 1, color);
			}
			else if (i == 17 && d == 1) {
				getCircle(px[5][0], py[5][0], 2 * LEN, 3 * PI / 2, -1, color);
			}
			else if (i == 18 && d == -1) {
				getCircle(px[5][0], py[5][0], 2 * LEN, 0, 1, color);
			}
			else if (i == 23 && d == 1) {
				getCircle(px[0][0], py[0][0], 2 * LEN, 0, -1, color);
			}
			else if (i == 0 && d == -1) {
				getCircle(px[0][0], py[0][0], 2 * LEN, PI / 2, 1, color);
			}
			else {
				sx = px[inx[i]][iny[i]];
				sy = py[inx[i]][iny[i]];
				tx = px[inx[(i + d + LINE_LEN) % LINE_LEN]][iny[(i + d + LINE_LEN) % LINE_LEN]];
				ty = py[inx[(i + d + LINE_LEN) % LINE_LEN]][iny[(i + d + LINE_LEN) % LINE_LEN]];
				getLine(sx, sy, tx, ty, color);
			}
		}
		insert(px[m.x[1]][m.y[1]], py[m.x[1]][m.y[1]], color);
	}
	
	private void getCircle(int x, int y, int r, double alpha, int d, int color) {
		int tx, ty, k, i;
		double step;
		
		if (r == LEN) k = 9;
		else k = 18;
		step = PI * 3 / 2 / k;
		
		for (i = 0; i < k; i++) {
			tx = (int)(x + r * Math.sin(alpha + d * step * i));
			ty = (int)(y + r * Math.cos(alpha + d * step * i));
			insert(tx, ty, color);
		}
	}
	
	private void getLine(int sx, int sy, int tx, int ty, int color) {
		int nx, ny, stepx, stepy;
		
		
		
		stepx = (tx - sx) / NSTEP;
		stepy = (ty - sy) / NSTEP;
		
		//System.out.format("sy = %d, ty = %d, stepy = %d\n", sy, ty, stepy);
		
		nx = sx;
		ny = sy;
		while (!(nx == tx && ny == ty)) {
			insert(nx, ny, color);
			nx += stepx;
			ny += stepy;
		}
	}
}

public class RealMap {
	public static final int MAX_STONE = 25;
	
	public static final int[][] px = {
		{ 69,  69,  69,  69,  69,  69},
		{ 93,  93,  93,  93,  93,  93},
		{117, 117, 117, 117, 117, 117},
		{141, 141, 141, 141, 141, 141},
		{165, 165, 165, 165, 165, 165},
		{189, 189, 189, 189, 189, 189}
	};
	public static final int[][] py = {
		{176, 200, 224, 248, 272, 296},
		{176, 200, 224, 248, 272, 296},
		{176, 200, 224, 248, 272, 296},
		{176, 200, 224, 248, 272, 296},
		{176, 200, 224, 248, 272, 296},
		{176, 200, 224, 248, 272, 296}
	};
	
	Stone[] stoneList = new Stone[MAX_STONE];
	public int sum, pos;
	
	Route route = new Route();
	
	@SuppressWarnings("static-access")
	public void init(Map board, Move m) {
		int i, j;
		
		sum = 1;
		for (i = 0; i < board.MAXSIZE; i++) {
			for (j = 0; j < board.MAXSIZE; j++) {
				if (i == m.x[0] && j == m.y[0]) continue;
				if (i == m.x[1] && j == m.y[1]) continue;
				if (board.map[i][j] != board.NOSTONE) {
					stoneList[sum++] = new Stone(px[i][j], py[i][j], board.map[i][j]);
				}
			}
		}
		stoneList[0] = new Stone(px[m.x[0]][m.y[0]], py[m.x[0]][m.y[0]], board.map[m.x[0]][m.y[0]]);
		if (m.isAttack) stoneList[sum++] = new Stone(px[m.x[1]][m.y[1]], py[m.x[1]][m.y[1]], board.map[m.x[1]][m.y[1]]);
		
		route.makeRoute(board, m);
		pos = 0;
	}
	
	@SuppressWarnings("static-access")
	public void Make(Map board) {
		int i, j;
		
		sum = 0;
		for (i = 0; i < board.MAXSIZE; i++) {
			for (j = 0; j < board.MAXSIZE; j++) {
				if(board.map[i][j]!=Map.NOSTONE)
					stoneList[sum++] = new Stone(px[i][j], py[i][j], board.map[i][j]);
			}
		}
	}
	
	public boolean update(boolean isAttack) {
		if (pos >= route.tot) return false;
		
		stoneList[0].x = route.stoneRoute[pos].x;
		stoneList[0].y = route.stoneRoute[pos].y;
		if (pos == route.tot - 1 && isAttack) sum--;
		pos++;
		
		return true;
	}
}
