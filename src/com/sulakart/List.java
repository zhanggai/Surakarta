package com.sulakart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.content.pm.ActivityInfo;

public class List extends Activity {
    private ImageView information1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		ImageButton goback=(ImageButton)findViewById(R.id.menu);
		goback.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent();
				intent.setClass(List.this,MainFrame.class);
				startActivity(intent);
				List.this.finish();
			}
		});
        information1 = (ImageView)findViewById(R.id.background);
        information1.setImageDrawable(getResources().getDrawable(R.drawable.mainframe));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       // mainbackground.setImageDrawable(getDrawableResous)
    }
}