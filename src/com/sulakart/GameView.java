package com.sulakart;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	GameInterface father;
	GameDrawThread gdt;
	Bitmap red;
	Bitmap background;
	Bitmap blue;
	Bitmap gray;
	static final int Xoffset = 176;
	static final int Yoffset = 69;
	static final int length = 24;
	private int current_x, current_y;
	boolean clicked;
	int color;
	int xclicked, yclicked;
	int xcurrent, ycurrent;
	int txclicked, tyclicked;
	
	ArrayList<Move> moveList = new ArrayList<Move>();
	
	boolean canbeupdated;
	boolean tag;
	String user1,user2;

	public GameView(GameInterface father,String name1,String name2) {
		super(father);
		this.father = father;
		getHolder().addCallback(this);
		initBitmap(father);
		gdt = new GameDrawThread(this, getHolder());
		clicked = false;
		user1=name1;
		user2=name2;	
		
	}

	public void initBitmap(Context context) {
		Resources r = context.getResources();
		background = BitmapFactory.decodeResource(r, R.drawable.gameframe);
		red = BitmapFactory.decodeResource(r, R.drawable.red);
		blue = BitmapFactory.decodeResource(r, R.drawable.blue);
		gray = BitmapFactory.decodeResource(r, R.drawable.pressdown);
	}

	public void doDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		canvas.drawBitmap(background, 0, 0, paint);
		//father.board.Currentmap();
		Paint text=new Paint();
		text.setTextSize(20);
		text.setTextAlign(Align.CENTER);
		text.setColor(Color.RED);
		canvas.drawText(user1,70,85,text);
		text.setColor(Color.BLUE);
		canvas.drawText(user2, 70, 165, text);
		
		Paint ml = new Paint();
		int k, h = 85;
		if (moveList.size() < 6) k = 0;
		else k = moveList.size() - 6;
		for (; k < moveList.size(); k++) {
			ml.setTextSize(12);
			ml.setTextAlign(Align.CENTER);
			if ((k & 1) == 0) ml.setColor(Color.RED);
			else ml.setColor(Color.BLUE);
			canvas.drawText(moveList.get(k).toString(), 427, h, ml);
			h += 17;
		}
		
		int size=father.realmap.sum;
		if(canbeupdated)
			canbeupdated=father.realmap.update(tag);
		for (int i = 0; i < size; i++)
		{
			if(father.realmap.stoneList[i].color==Map.RED)
				canvas.drawBitmap(red, father.realmap.stoneList[i].y, father.realmap.stoneList[i].x, paint);
			else
				canvas.drawBitmap(blue, father.realmap.stoneList[i].y, father.realmap.stoneList[i].x, paint);
		}
		if (clicked) {
			if (father.board.map[yclicked][xclicked] != Map.NOSTONE)
				canvas.drawBitmap(gray, Xoffset + length * xclicked, Yoffset
						+ length * yclicked, paint);
		}
	}

	private boolean inScale(int x, int y) {
		return (x <= Xoffset + length * 6) && (x >= Xoffset)
				&& (y <= Yoffset + length * 6) && (y >= Yoffset);
	}

	public boolean gvOnTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			current_x = x;
			current_y = y - 50;
			xcurrent = (current_x - Xoffset) / length;
			ycurrent = (current_y - Yoffset) / length;
			if (inScale(current_x, current_y)) {
				if (clicked == true) {
					if (father.board.map[ycurrent][xcurrent] == father.board.map[yclicked][xclicked]) {
						xclicked = xcurrent;
						yclicked = ycurrent;
					} else {
						tag = (!(father.board.map[ycurrent][xcurrent] == Map.NOSTONE)) && father.board.map[yclicked][xclicked] != father.board.map[ycurrent][xcurrent];
						//Toast.makeText(father,clicked+" "+tag+" "+father.stack.size()+" "+yclicked+" "+xclicked+" "+ycurrent+" "+xcurrent,Toast.LENGTH_SHORT).show();
						Move temp = new Move(yclicked, xclicked, ycurrent,
								xcurrent, tag);
						moveList.add(temp);
						boolean flag = false;
						for (int i = 0; i < father.stack.size(); i++) {
							if (father.stack.moves[i].equals(temp)) {
								flag = true;
								break;
							}
						}
						if (flag) {
							father.color ^= 3;
							father.realmap.init(father.board, temp);
							father.movelist.add(temp);
							father.board.move(temp);
							canbeupdated=true;
							clicked=false;
							int rslt = father.board.isGameOver();
							if (rslt == Map.RED) {
								Toast.makeText(father, "Red Winner",
										Toast.LENGTH_SHORT).show();
							} else if (rslt == Map.BLUE) {
								Toast.makeText(father, "Blue Winner",
										Toast.LENGTH_SHORT).show();
							} else {
								father.makemove.makeMoves(father.board,
										father.stack, father.color);
								if (father.stack.size() == 0) {
									if (father.color == Map.RED)
										Toast.makeText(father, "Blue Winner",
												Toast.LENGTH_SHORT).show();
									else
										Toast.makeText(father, "Red Winner",
												Toast.LENGTH_SHORT).show();
								}
							}
						}else
							Toast.makeText(father, "Not Allowed!",Toast.LENGTH_SHORT).show();
					}
				}
				else {
					txclicked = (current_x - Xoffset) / length;
					tyclicked = (current_y - Yoffset) / length;
					// Toast.makeText(father,father.color+" "+txclicked+" "+tyclicked+" "+father.board.map[tyclicked][txclicked],
					// Toast.LENGTH_SHORT).show();

					if (father.board.map[tyclicked][txclicked] == father.color) {
						//Toast.makeText(father,father.color+ " "+ txclicked+ " "+ tyclicked+ " "+ father.board.map[tyclicked][txclicked],Toast.LENGTH_SHORT).show();
						clicked = true;
						xclicked = txclicked;
						yclicked = tyclicked;
					}
				}
				// Toast.makeText(father, x+"  "+y+"  "+xclicked+"  "+yclicked,
				// Toast.LENGTH_SHORT).show();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			current_x = x;
			current_y = y - 50;
			break;
		case MotionEvent.ACTION_UP:
			current_x = x;
			current_y = y - 50;
		}
		if (current_x < 118 && current_x > 18 && current_y > 230
				&& current_y < 270 && action == MotionEvent.ACTION_UP) {
			Toast.makeText(father, "Exit", Toast.LENGTH_SHORT).show();

			return false;
		}
		// Toast.makeText(father,father.color+" "+txclicked+" "+tyclicked+" "+father.board.map[txclicked][tyclicked],
		// Toast.LENGTH_SHORT).show();

		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if (!gdt.isAlive()) { // 启动后台绘制线程
			gdt.start();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (gdt.isAlive()) { // 停止后台绘制线程
			gdt.flag = false;
			// Toast.makeText(father,"thread end", Toast.LENGTH_SHORT).show();
		}
	}
}
