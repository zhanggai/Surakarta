package com.sulakart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.text.method.ScrollingMovementMethod;
import android.view.*;
import android.content.*;
import android.content.pm.ActivityInfo;
import java.util.*;
import java.io.*;

public class WinnerList extends Activity {
    private ImageView information2;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winnerlist);
		TextView text=(TextView)findViewById(R.id.winner);
		text.setMovementMethod(ScrollingMovementMethod.getInstance());
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("winner.dat")));
			out.println("ABC");
			out.close();
			Scanner input=new Scanner(new FileReader("winner.dat"));
			text.setText("fuck");
			String str;
			while(input.hasNext())
			{
				str=input.next();
				text.setText(str+'\n');
				text.setText("fuck");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Fail!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageButton back=(ImageButton)findViewById(R.id.back);
		back.setBackgroundDrawable(getResources().getDrawable(R.drawable.back));
		back.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent();
				intent.setClass(WinnerList.this, MainFrame.class);
				startActivity(intent);
				WinnerList.this.finish();
			}
		});
        information2 = (ImageView)findViewById(R.id.background);
        information2.setImageDrawable(getResources().getDrawable(R.drawable.mainframe));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       // mainbackground.setImageDrawable(getDrawableResous)
    }
}