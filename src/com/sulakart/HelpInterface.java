package com.sulakart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.text.method.ScrollingMovementMethod;
import android.view.*;
import android.content.*;
import android.content.pm.ActivityInfo;

public class HelpInterface extends Activity {
    private ImageView information2;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		ImageButton back=(ImageButton)findViewById(R.id.back);
		TextView text=(TextView)findViewById(R.id.helpfile);
		text.setMovementMethod(ScrollingMovementMethod.getInstance());
		text.setText("\t苏拉卡尔塔棋是一种两人玩的游戏，源自印尼爪哇岛的苏拉卡塔(Surakarta)。棋盘是由正方形网络与角落上的8个圆弧所组成，如图1所示。在游戏开始时，每人有12个棋子(两人的棋子颜色不同，以便区别)，并将12个棋子排成两行(见图1)。参赛者掷硬币决定由谁先开始，每次只能移动一个棋子，两人轮流走。\n\t苏拉卡尔塔棋规则介绍\n\t规则简介\n\t1. 比赛开始双方各有12个棋子，由双方协商选择先手、后手和棋子的颜色，每次只能移动一个棋子；\n\t2. 每个棋子可以向上下、左右、沿对角线8个方向移动（当所去的方向方向向无棋子时）；\n\t3. 若要吃掉对方子，必须经过图中的一条弧线，并且移动路径中不可以有棋子；\n\t4. 若黑子可以吃掉白子，同样，白子沿同一路径的相反方向也可以吃掉黑子；\n\t5. 当一方棋子全部被吃掉时棋局结束，有剩余棋子方获胜；\n\t6. 若每局超过30分钟，双方停止比赛，以剩余棋子多的一方获胜。\n\t本游戏的目的是要吃掉(赢取)对手的棋子，而这个时候棋盘上的圆弧部分就派上用场了。当一个棋子要去吃对方的棋子时，必须有一条通行无阻的路线，且这条路线必须包含一个(或数个)圆弧在内。只要是在没有其他棋子挡道的路线上，一个棋子可以走任意远的距离去吃对方的棋子，被吃掉的棋子便自棋盘中取出。\n\t要注意所有的路径皆为双向通道，所以如果白色棋子可以沿一路径吃黑色棋子，同样地，黑色棋子也能沿相同路径去吃白色棋子。\n\t只有在吃对手的棋子时才能绕圆弧走棋。这项规定是此游戏的特色，有了这个限制使得此游戏更加有趣。\n\t如何定胜负呢？游戏前可以先说好，谁先将对方的棋子吃剩下某个数目者获胜。或是约定好游戏的时间，时间到时还留有较多棋子的人获胜。");
		back.setBackgroundDrawable(getResources().getDrawable(R.drawable.back));
		back.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
			{	
				Intent intent=new Intent();
				intent.setClass(HelpInterface.this, MainFrame.class);
				startActivity(intent);
				HelpInterface.this.finish();
			}
		});
        information2 = (ImageView)findViewById(R.id.background);
        information2.setImageDrawable(getResources().getDrawable(R.drawable.mainframe));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       // mainbackground.setImageDrawable(getDrawableResous)
    }
}