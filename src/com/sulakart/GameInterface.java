package com.sulakart;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.view.MotionEvent;

public class GameInterface extends Activity {
    Map board=new Map();
	MoveStack stack=new MoveStack();
	MakeMove makemove=new MakeMove();
	RealMap realmap=new RealMap();
	int color;
	ArrayList<Move> movelist=new ArrayList<Move>();
	
    GameView gv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    board.init();
	    color=Map.RED;
		Bundle bundle=getIntent().getExtras();
		String u1=bundle.getString("name1"),u2=bundle.getString("name2");
	    makemove.makeMoves(board, stack, color);
	    realmap.Make(board);
	    gv=new GameView(this,u1,u2);
		setContentView(gv);
		
		
		//user1.layout(30, 60, 110, 110);
		//TextView user2=new TextView(this);
		//user2.setText(u2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       // mainbackground.setImageDrawable(getDrawableResous)
    }
	public boolean onTouchEvent(MotionEvent event)
	{
		if(gv.gvOnTouchEvent(event)==false)
		{
			Intent intent=new Intent();
			intent.setClass(GameInterface.this, MainFrame.class);
			startActivity(intent);
			GameInterface.this.finish();
		}
		return true;
	}
}