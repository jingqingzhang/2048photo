package com.yuzhiyun.growup2048;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.TextView;

import com.yuzhiyun.growup2048.view.Game2048Layout;


public class MainActivity extends Activity implements Game2048Layout.OnGame2048Listener
{
	
	public static final String SP_KEY_BEST_SCORE = "bestScore";
	
	private Game2048Layout mGame2048Layout;
	private TextView mScore;
	private TextView tvBestScore;
//	private TextView mProgress;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mScore = (TextView) findViewById(R.id.id_score);
		tvBestScore=(TextView) findViewById(R.id.tvBestScore);
//		mProgress = (TextView) findViewById(R.id.id_progress);
		mGame2048Layout = (Game2048Layout) findViewById(R.id.id_game2048);
		mGame2048Layout.setOnGame2048Listener(this);
		showBestScore(getBestScore());
	}

	@Override
	public void onScoreChange(int score)
	{
		mScore.setText("SCORE: " + score);
		int maxScore = Math.max(score, getBestScore());
		saveBestScore(maxScore);
		showBestScore(maxScore);
	}
	public void saveBestScore(int s){
		Editor e = getPreferences(MODE_PRIVATE).edit();
		e.putInt(SP_KEY_BEST_SCORE, s);
		e.commit();
	}

	public int getBestScore(){
		return getPreferences(MODE_PRIVATE).getInt(SP_KEY_BEST_SCORE, 0);
	}

	public void showBestScore(int s){
		tvBestScore.setText("    BestScore: "+s);
	}

	@Override
	public void onGameOver()
	{
		new AlertDialog.Builder(this).setTitle("GAME OVER")
				.setMessage("YOU HAVE GOT " + mScore.getText())
				.setPositiveButton("RESTART", new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						mGame2048Layout.restart();
					}
				}).setNegativeButton("EXIT", new OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						finish();
					}
				}).show();
	}

}
