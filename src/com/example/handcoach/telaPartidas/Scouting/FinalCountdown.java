package com.example.handcoach.telaPartidas.Scouting;

import java.util.concurrent.TimeUnit;
import com.example.handcoach.R;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//http://example.javamonday.com/Open-Source/Android/Timer/multitimer-android/com/cycleindex/multitimer/CountDownTimerWithPause.java.htm

public class FinalCountdown extends CountDownTimerWithPause {
	
	protected TextView text;
	protected Vibrator v;
	protected Context context;
	protected long tempoJogo;
	protected boolean onFinished;
	protected int tipoCronos;
	protected TelaScouting tela;

	public FinalCountdown(long millisInFuture, long countDownInterval, boolean mRunAtStart, int tipoCronos, TelaScouting tela) {
		super(millisInFuture, countDownInterval, mRunAtStart);
		this.tempoJogo = millisInFuture;
		this.onFinished = false;
		this.tipoCronos = tipoCronos;
		this.tela = tela;
	}

	@Override
	public void onFinish() {
		Toast.makeText(tela, R.string.tempoAcabou, Toast.LENGTH_SHORT).show();
		if(tipoCronos == 1) {
			tela.cronometroJogo.setFinished(true);
			tela.cronometroTempo.setFinished(false);
		} else if(tipoCronos == 2) {
			tela.habilitaPlayPause(true);
			tela.cronosTempo.setVisibility(View.INVISIBLE);
			tela.cronometroTempo.setFinished(true);
			Toast.makeText(tela, R.string.PlayPauseClick, Toast.LENGTH_LONG).show();
		} else if(tipoCronos == 3) {
			
		}
	}

	@Override
	public void onTick(long millisUntilFinished) {
		text.setText(""+String.format("%d:%d", 
                TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - 
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
		
		tempoJogo = millisUntilFinished;
		text.refreshDrawableState();
		
		/*if((TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished) == 0) && TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) == 5) {
			v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			v.vibrate(500);
			Toast.makeText(context, "ACABOOOOU", Toast.LENGTH_SHORT).show();
        }*/
		
	}
	
	public void setText(TextView text) {
		this.text = text;
	}	
	public TextView getText() {
		return this.text;
	}
	
	public void setTempoJogo(long tempoJogo) {
		this.tempoJogo = tempoJogo;
	}
	public long getTempoJogo() {
		return tempoJogo;
	}
	
	public boolean isFinished() {
		return onFinished;
	}
	public void setFinished(boolean bool) {
		this.onFinished = bool;
	}

}
