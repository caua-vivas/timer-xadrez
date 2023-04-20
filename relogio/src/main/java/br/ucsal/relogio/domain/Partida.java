package br.ucsal.relogio.domain;

import java.util.Objects;

public class Partida extends Thread{

	public static Partida instancia;

	private Counter timer1;
	private Counter timer2;
	private Counter vencedor;
	private Counter jogando;	

	private int tempoMaximo;


	public static Partida getInstancia(int... tempoMaximo) {
		if(!Objects.isNull(instancia)) {
			return instancia;
		}else {
			instancia = new Partida(tempoMaximo[0]);
			return instancia;		
		}
	}	

	public Partida(int tempoMaximo) {
		this.tempoMaximo = tempoMaximo;
		this.timer1 = new Counter("Jogador 1", this.tempoMaximo);
		this.timer2 = new Counter("Jogador 2", this.tempoMaximo);
		this.jogando = this.timer1;
	}

	public void checarZerado(Counter timer) {
		if(timer.getTempoAtual() == 0) {
			if(timer.getNome().equals("Jogador 1")) {
				this.vencedor = timer2;
			}else {
				this.vencedor = timer1;
			}
		}
	}

	public void mudarTurno() throws InterruptedException {
		if(this.getJogando().getNome().equals("Jogador 1")){
			this.timer1.wait();
			this.timer2.notify();
		}else {
			this.timer2.wait();
			this.timer1.notify();
		}
	}

	public void stop(Counter timer) {
		try {
			timer.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Counter getTimer1() {
		return  this.timer1;
	}

	public Counter getTimer2() {
		return this.timer2;
	}

	public Counter getVencedor() {
		return this.vencedor;
	}

	public Counter getJogando() {
		return jogando;
	}

}