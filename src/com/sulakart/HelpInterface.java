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
		text.setText("\t��������������һ�����������Ϸ��Դ��ӡ��צ�۵�����������(Surakarta)��������������������������ϵ�8��Բ������ɣ���ͼ1��ʾ������Ϸ��ʼʱ��ÿ����12������(���˵�������ɫ��ͬ���Ա�����)������12�������ų�����(��ͼ1)����������Ӳ�Ҿ�����˭�ȿ�ʼ��ÿ��ֻ���ƶ�һ�����ӣ����������ߡ�\n\t������������������\n\t������\n\t1. ������ʼ˫������12�����ӣ���˫��Э��ѡ�����֡����ֺ����ӵ���ɫ��ÿ��ֻ���ƶ�һ�����ӣ�\n\t2. ÿ�����ӿ��������¡����ҡ��ضԽ���8�������ƶ�������ȥ�ķ�������������ʱ����\n\t3. ��Ҫ�Ե��Է��ӣ����뾭��ͼ�е�һ�����ߣ������ƶ�·���в����������ӣ�\n\t4. �����ӿ��ԳԵ����ӣ�ͬ����������ͬһ·�����෴����Ҳ���ԳԵ����ӣ�\n\t5. ��һ������ȫ�����Ե�ʱ��ֽ�������ʣ�����ӷ���ʤ��\n\t6. ��ÿ�ֳ���30���ӣ�˫��ֹͣ��������ʣ�����Ӷ��һ����ʤ��\n\t����Ϸ��Ŀ����Ҫ�Ե�(Ӯȡ)���ֵ����ӣ������ʱ�������ϵ�Բ�����־������ó��ˡ���һ������Ҫȥ�ԶԷ�������ʱ��������һ��ͨ�������·�ߣ�������·�߱������һ��(������)Բ�����ڡ�ֻҪ����û���������ӵ�����·���ϣ�һ�����ӿ���������Զ�ľ���ȥ�ԶԷ������ӣ����Ե������ӱ���������ȡ����\n\tҪע�����е�·����Ϊ˫��ͨ�������������ɫ���ӿ�����һ·���Ժ�ɫ���ӣ�ͬ���أ���ɫ����Ҳ������ͬ·��ȥ�԰�ɫ���ӡ�\n\tֻ���ڳԶ��ֵ�����ʱ������Բ�����塣����涨�Ǵ���Ϸ����ɫ�������������ʹ�ô���Ϸ������Ȥ��\n\t��ζ�ʤ���أ���Ϸǰ������˵�ã�˭�Ƚ��Է������ӳ�ʣ��ĳ����Ŀ�߻�ʤ������Լ������Ϸ��ʱ�䣬ʱ�䵽ʱ�����н϶����ӵ��˻�ʤ��");
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