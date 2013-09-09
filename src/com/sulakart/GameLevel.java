package com.sulakart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.content.pm.ActivityInfo;


public class GameLevel extends Activity {
    private ImageView information1;
	private int level = 1;
	private int humanColor = Map.RED;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamelevel);
		ImageButton confirm=(ImageButton)findViewById(R.id.confirm);
		confirm.setBackgroundDrawable(getResources().getDrawable(R.drawable.confirm));
		ImageButton easy=(ImageButton)findViewById(R.id.easy);
		easy.setBackgroundDrawable(getResources().getDrawable(R.drawable.easy));
		ImageButton master=(ImageButton)findViewById(R.id.master);
		master.setBackgroundDrawable(getResources().getDrawable(R.drawable.master));
		//ImageButton expert=(ImageButton)findViewById(R.id.expert);
		//expert.setBackgroundDrawable(getResources().getDrawable(R.drawable.expert));
		
		ImageButton Red = (ImageButton)findViewById(R.id.red);
		Red.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
		
		ImageButton Blue = (ImageButton)findViewById(R.id.blue);
		Blue.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
		
		ImageButton back=(ImageButton)findViewById(R.id.backu);
		final EditText User1=(EditText)findViewById(R.id.username1);
		back.setBackgroundDrawable(getResources().getDrawable(R.drawable.backu));
		easy.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				level=1;
			}
		});
		master.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				level=2;
			}
		});
		/*
		expert.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				level=3;
			}
		});
		*/
		confirm.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent(GameLevel.this, AIGame.class);
				intent.putExtra("name", User1.getText().toString());
				intent.putExtra("level", level);
				intent.putExtra("color", humanColor);
				startActivity(intent);
				GameLevel.this.finish();
			}
		});
		back.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent();
				intent.setClass(GameLevel.this, MainFrame.class);
				startActivity(intent);
				GameLevel.this.finish();
			}
		});
		
		Red.setOnClickListener(
			new Button.OnClickListener() {
				public void onClick(View v) {
					humanColor = Map.RED;
				}
			}
		);
		
		Blue.setOnClickListener(
			new Button.OnClickListener() {
				public void onClick(View v) {
					humanColor = Map.BLUE;
				}
			}
		);
		
		information1 = (ImageView)findViewById(R.id.background);
        information1.setImageDrawable(getResources().getDrawable(R.drawable.mainframe));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       // mainbackground.setImageDrawable(getDrawableResous)
	}
}