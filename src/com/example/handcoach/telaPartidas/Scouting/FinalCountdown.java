package com.example.handcoach.telaPartidas.Scouting;

import java.util.concurrent.TimeUnit;
import com.example.handcoach.R;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;

public class FinalCountdown extends CountDownTimer {
	
	protected TextView text;
	protected Vibrator v;
	protected Context context;

	public FinalCountdown(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	@Override
	public void onFinish() {
		text.setText(R.string.tempoAcabou);
	}

	@Override
	public void onTick(long millisUntilFinished) {
		text.setText(""+String.format("%d min, %d sec", 
                TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - 
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
	}
	
	public void onTick(long millisUntilFinished, Context context, int minToVibrate, int secToVibrate) {
		text.setText(""+String.format("%d min, %d sec", 
                TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - 
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
		
		if((TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished) == minToVibrate) && TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)) == secToVibrate) {
			v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			v.vibrate(500);
			Toast.makeText(context, R.string.tempoAcabando, Toast.LENGTH_SHORT).show();
        }
		
	}
	
	public void setText(TextView text) {
		this.text = text;
	}
	
	public TextView getText() {
		return this.text;
	}

}
