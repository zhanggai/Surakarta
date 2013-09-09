package com.sulakart;

import android.app.Activity;
import android.os.Bundle;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.view.MotionEvent;
import android.widget.Toast;

public class AIGame extends Activity {
    Map board=new Map();
	MoveStack stack=new MoveStack();
	MakeMove makemove=new MakeMove();
	RealMap realmap=new RealMap();
	int color, hard;
	int AIcolor=Map.RED;
	SearchEngine search=new SearchEngine();
	
    AIGameView gv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    board.init();
	    color=Map.BLUE;
	    //makemove.makeMoves(board, stack, color);
	    //realmap.Make(board);
	    String lev;
		Bundle bundle=getIntent().getExtras();
		String u1=bundle.getString("name");
		hard = bundle.getInt("level");
		search.MAX_DEEP = hard;
		color = bundle.getInt("color");
		AIcolor = color ^ 3;
		makemove.makeMoves(board, stack, color);
		realmap.Make(board);
		if (hard == 1) lev = new String("Easy");
		else if (hard == 2) lev = new String("Master");
		else lev = new String("Expert");
		if (lev.equals("Expert")) search.MAX_DEEP--;
		
	    gv=new AIGameView(this,u1, lev, color);
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
			intent.setClass(AIGame.this, MainFrame.class);
			startActivity(intent);
			AIGame.this.finish();
		}
		return true;
	}
}