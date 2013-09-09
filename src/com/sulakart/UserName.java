package com.sulakart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.content.pm.ActivityInfo;

public class UserName extends Activity {
    private ImageView information1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.username);
		ImageButton confirm=(ImageButton)findViewById(R.id.confirm);
		confirm.setBackgroundDrawable(getResources().getDrawable(R.drawable.confirm));
		ImageButton back=(ImageButton)findViewById(R.id.backu);
		final EditText User1=(EditText)findViewById(R.id.username1);
		final EditText User2=(EditText)findViewById(R.id.username2);
		back.setBackgroundDrawable(getResources().getDrawable(R.drawable.backu));
		confirm.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent(UserName.this, GameInterface.class);
				intent.putExtra("name1", User1.getText().toString());
				intent.putExtra("name2", User2.getText().toString());
				startActivity(intent);
				UserName.this.finish();
			}
		});
		back.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent();
				intent.setClass(UserName.this, MainFrame.class);
				startActivity(intent);
				UserName.this.finish();
			}
		});
		information1 = (ImageView)findViewById(R.id.background);
        information1.setImageDrawable(getResources().getDrawable(R.drawable.mainframe));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       // mainbackground.setImageDrawable(getDrawableResous)
	}
}