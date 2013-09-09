package com.sulakart;

public class Move {
	int[] x = new int[2];
	int[] y = new int[2];
	boolean isAttack;
	
	Move(int x0, int y0, int x1, int y1, boolean tag) {
		x[0] = x0;
		y[0] = y0;
		x[1] = x1;
		y[1] = y1;
		isAttack = tag;
	}
	
	public boolean equals(Object o) {
		return x[0] == ((Move)o).x[0] && y[0] == ((Move)o).y[0] && x[1] == ((Move)o).x[1] && y[1] == ((Move)o).y[1] && isAttack == ((Move)o).isAttack;
	}
	
	public boolean equals(int x0, int y0, int x1, int y1, boolean tag) {
		return x[0] == x0 && y[0] == y0 && x[1] == x1 && y[1] == y1 && isAttack == tag;
	}
	
	public String toString() {
		return "(" + x[0] + "," + y[0] + ")->(" + x[1] + "," + y[1] + ")";
	}
	
}
