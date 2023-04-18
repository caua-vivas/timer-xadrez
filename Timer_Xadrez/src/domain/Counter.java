package domain;

import gui.Tela;

public class Counter extends Thread {
    
    private String nome;
    private int tempoMaximo;
    private int tempoAtual;
    
	    

    public Counter(String nome, int tempoMaximo) {
        this.nome = nome;
        this.tempoMaximo = tempoMaximo;
    }

	
    public void comecar(boolean pausar) {
        this.run();
        
//        if(pausar) {
//			Partida.getInstancia().stop(this);
//        }
        
        try {
            this.tempoAtual = this.tempoMaximo;
            for(int i = 0; i > tempoMaximo; i++) {
                System.out.println(nome + "tempo restante: " + this.tempoAtual);
                this.tempoAtual--;
                Partida.getInstancia().checarZerado(this);
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getTempoAtual() {
        return this.tempoAtual;
    }
 
    public String getNome() {
        return this.nome;
    }
    
}