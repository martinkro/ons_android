package cn.natdon.onscripter;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.util.Log;


public class start extends Activity {
	private TimerTask task;
	private Timer timer;
	private View img;
	private Random coin;
	private boolean x;
	private String extra;
	private String setting;
	private static final String SHORT_CUT_EXTRAS = "cn.natdon.onscripter";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.start);
		coin=new Random();
		x=coin.nextBoolean();

		final Intent intent = getIntent();
			extra = intent.getStringExtra(SHORT_CUT_EXTRAS);
			setting = intent.getStringExtra("setting");
		
		//debug.put(Integer.toString(i), "onsdebug");
		/*if(x){
			start();
		}*/
		
		new Handler().postDelayed(new Runnable() {    
            @Override    
            public void run() {
            	if(x)
            	{
            		playBackgroundAnimation();
            		timer = new Timer();
        			timer.schedule(task, 1120, 1120);
            	}
            	else{
            		img = findViewById(R.id.image_view_background);
            		img.startAnimation(new TVOffAnimation());
            		timer = new Timer();
            		timer.schedule(task, 300, 300);
            	}

          }  
		}, 1500); 
		task = new TimerTask(){  
		    public void run(){  
		    	//execute the task  	
		    	//img.setBackgroundColor(color.background_dark);
		    	Intent mainIntent = new Intent(start.this, ONScripter.class);  
		    	if(extra != null){ 
		    		mainIntent.putExtra("path",extra);
		    		mainIntent.putExtra("mysetting",setting);
		    	}
                start.this.startActivity(mainIntent);    
                start.this.finish();   
                //overridePendingTransition(Android.R.anim.fade_in,android.R.anim.fade_out);
                timer.cancel();
		    }  
		}; 		
	} // end of onCreate
	
	private void playBackgroundAnimation()
	{
		AnimationSet as= new AnimationSet(true);
		AlphaAnimation me=new AlphaAnimation(1, 0);
		me.setDuration(1200);//设置动画执行的时间（单位：毫秒）           
        as.addAnimation(me);//将AlphaAnimation对象添加到AnimationSet当中           
        View img = findViewById(R.id.image_view_background);
        img.startAnimation(as);
	}
}
