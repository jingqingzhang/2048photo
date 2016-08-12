package com.yuzhiyun.growup2048;

import android.content.Context;
import android.media.MediaPlayer;

public class playMusic {
	
	Context c;
	static MediaPlayer m_MediaPlay;
	public playMusic(Context c) {
		this.c=c;
	}
	public  void start(){
		m_MediaPlay = MediaPlayer.create(c, R.raw.down);
		m_MediaPlay.start();
	}
	public  void release(){
		m_MediaPlay.release();
	}




}
