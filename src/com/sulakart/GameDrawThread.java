package com.sulakart;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class GameDrawThread extends Thread {
	GameView father;
	SurfaceHolder surfaceholder;
	int sleepSpan = 60;
	boolean flag;
	public GameDrawThread(GameView father,SurfaceHolder surfaceHolder)
	{
		this.father=father;
		this.surfaceholder=surfaceHolder;
		this.flag=true;
	}

	public void run()
	{
		Canvas canvas=null;
		while(flag)
		{
			try{
				canvas=surfaceholder.lockCanvas(null);
				synchronized(surfaceholder){
					father.doDraw(canvas);
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				if(canvas!=null)
				{
					surfaceholder.unlockCanvasAndPost(canvas);
				}
			}
			try{
				Thread.sleep(sleepSpan);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
