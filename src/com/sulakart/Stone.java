package com.sulakart;

public class Stone {
	int x, y, color;
	
	Stone(int x, int y, int color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public boolean equals(Object o) {
		return x == ((Stone)o).x && y == ((Stone)o).y && color == ((Stone)o).color;
	}
}
