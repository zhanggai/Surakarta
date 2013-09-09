package com.sulakart;

public class SearchEngine {
	public static final int MAX_VAL = 999999;
	public static final int MIN_VAL = -999999;
	
	public static int MAX_DEEP = 1;
	public Move bestMove;
	
	
	Evalue evalue = new Evalue();
	
	public boolean searchAGoodMove(Map board, int color) {
		
		bestMove = null;
		abSearch(board, 0, MIN_VAL - 1, MAX_VAL + 1, color);
		
		if (bestMove == null) return false;
		return true;
	}
	
	private int abSearch(Map board, int deep, int alpha, int beta, int color) {
		boolean foundPv = false;
		int val, tmp, i, sum;
		MoveStack stack = new MoveStack();
		MakeMove makeMove = new MakeMove();
		
		tmp = board.isGameOver();
		if (tmp != 0) {
			if (tmp == color) return MAX_VAL + 10;
			return MIN_VAL - 10;
		}
		
		makeMove.makeMoves(board, stack, color);
		if (stack.size() == 0) return MIN_VAL - 10;
		
		if (deep == MAX_DEEP) {
			return evalue.evaluef(board, color);
		}
		
		sum = stack.size();
		for (i = 0; i < sum; i++) {
			board.move(stack.moves[i]);
			if (foundPv) {
				val = -abSearch(board, deep + 1, -alpha - 1, alpha, color ^ 3);
				if (val > alpha && val < beta) {
					val = -abSearch(board, deep + 1, -beta, -alpha, color ^ 3);
				}
			}
			else {
				val = -abSearch(board, deep + 1, -beta, -alpha, color ^ 3);
			}
			board.unMove(stack.moves[i]);
			
			if (val >= beta) {
				if (deep == 0) {
					bestMove = new Move(stack.moves[i].x[0], stack.moves[i].y[0], stack.moves[i].x[1], stack.moves[i].y[1], stack.moves[i].isAttack);
				}
				return beta;
			}
			if (val > alpha) {
				alpha = val;
				if (deep == 0) {
					bestMove = new Move(stack.moves[i].x[0], stack.moves[i].y[0], stack.moves[i].x[1], stack.moves[i].y[1], stack.moves[i].isAttack);
				}
				foundPv = true;
			}
		}
		
		return alpha;
	}
}
