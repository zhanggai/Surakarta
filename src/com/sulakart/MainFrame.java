package com.sulakart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.content.pm.ActivityInfo;

public class MainFrame extends Activity {
    private ImageView information1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainframe);
		ImageButton newgame=(ImageButton)findViewById(R.id.newgame);
		newgame.setBackgroundDrawable(getResources().getDrawable(R.drawable.newgame));
		ImageButton match=(ImageButton)findViewById(R.id.match);
		match.setBackgroundDrawable(getResources().getDrawable(R.drawable.match));
//		ImageButton guide=(ImageButton)findViewById(R.id.guide);
//		guide.setBackgroundDrawable(getResources().getDrawable(R.drawable.guide));
		ImageButton winner=(ImageButton)findViewById(R.id.winner);
		winner.setBackgroundDrawable(getResources().getDrawable(R.drawable.winner));
		ImageButton help=(ImageButton)findViewById(R.id.help);
		help.setBackgroundDrawable(getResources().getDrawable(R.drawable.help));
		ImageButton exit=(ImageButton)findViewById(R.id.exit);
		exit.setBackgroundDrawable(getResources().getDrawable(R.drawable.exit));
		newgame.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent();
				intent.setClass(MainFrame.this,GameLevel.class);
				startActivity(intent);
				MainFrame.this.finish();
			}
		});
		match.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent();
				intent.setClass(MainFrame.this, UserName.class);
				startActivity(intent);
				MainFrame.this.finish();
			}
		});
//		guide.setOnClickListener(new Button.OnClickListener()
//		{
//			public void onClick(View v)
//			{	
//				Intent intent=new Intent();
//				intent.setClass(MainFrame.this, GameInterface.class);
//				startActivity(intent);
//				MainFrame.this.finish();
//			}
//		});
		help.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent();
				intent.setClass(MainFrame.this, HelpInterface.class);
				startActivity(intent);
				MainFrame.this.finish();
			}
		});
		winner.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent();
				intent.setClass(MainFrame.this, WinnerList.class);
				startActivity(intent);
				MainFrame.this.finish();
			}
		});
		exit.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{
				MainFrame.this.finish();
			}
		});
        information1 = (ImageView)findViewById(R.id.background);
        information1.setImageDrawable(getResources().getDrawable(R.drawable.mainframe));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       // mainbackground.setImageDrawable(getDrawableResous)
    }
}