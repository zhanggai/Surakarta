package com.sulakart;

public class MoveStack {
	public static final int MAXMOVE = 100;
	
	Move[] moves = new Move[MAXMOVE];
	int top = 0;
	
	public void clear() {
		top = 0;
	}
	
	public int size() {
		return top;
	}
	
	public void push(Move m) {
		moves[top++] = m;
	}
	
	public Move pop() {
		return moves[--top];
	}
	
	public Move getTop() {
		return moves[top - 1];
	}
}
