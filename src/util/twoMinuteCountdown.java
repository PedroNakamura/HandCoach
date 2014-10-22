package util;

import Entidades.Jogador;

import com.example.handcoach.telaPartidas.Scouting.TelaScouting;

public class twoMinuteCountdown extends CountDownTimerWithPause {
	
	protected long tempo;
	protected TelaScouting tela;
	protected Jogador jogador;

	public twoMinuteCountdown(long millisOnTimer, long countDownInterval, boolean runAtStart, TelaScouting t, Jogador jog) {
		super(millisOnTimer, countDownInterval, runAtStart);
		this.tempo = millisOnTimer;
		this.tela = t;
		this.jogador = jog;
	}

	@Override
	public void onFinish() {
		
	}
	
	@Override
	public void onTick(long millisUntilFinished) {
		this.tempo = millisUntilFinished;
	}

}
