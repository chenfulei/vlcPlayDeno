package com.mediedictionary.playerlibrary;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button bplay,bpause,bstop;
    private MediaPlayer mp = new MediaPlayer();
    
    private SurfaceView surfaceView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		
		initView();
		
	}

	private void initView() {
		
		surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
		
		bplay = (Button) findViewById(R.id.play);
		bpause = (Button) findViewById(R.id.pause);
		bstop = (Button) findViewById(R.id.stop);
		
		bplay.setOnClickListener(this);
		bpause.setOnClickListener(this);
		bstop.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			try {
                mp.setDataSource("http://img1.peiyinxiu.com/2014121211339c64b7fb09742e2c.mp4");
                mp.prepare();
                mp.start();
                mp.setDisplay(surfaceView.getHolder());
             } catch (IllegalArgumentException e) {
                e.printStackTrace();
             } catch (IllegalStateException e) {
                e.printStackTrace();
             } catch (IOException e) {
                e.printStackTrace();
             }
             mp.setOnCompletionListener(new OnCompletionListener(){
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
             });
			break;
			
		case R.id.pause:
			 if(mp != null){
                 mp.pause();
              }
			break;

		case R.id.stop:
			if(mp != null){
                mp.stop();
             }
			break;
			
		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		
		 if(mp != null)
	           mp.release();
		
		super.onDestroy();
	}
	
}
