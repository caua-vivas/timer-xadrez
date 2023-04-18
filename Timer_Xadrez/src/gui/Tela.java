package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import domain.Counter;
import domain.Partida;

public class Tela extends JFrame {
	
	public Tela(Partida partida) {
		
		Container container = this.getContentPane();
		
	    setSize(500, 300);
	    setTitle("Timer");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    JPanel painel = new JPanel();

        JButton botaoStart = new JButton("Começar");
        botaoStart.setPreferredSize(new Dimension(100, 50));
        
        painel.add(botaoStart);
        this.add(painel);
        
        this.add(painel, BorderLayout.CENTER);
        
        botaoStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	JPanel painel2 = new JPanel();
            	
            	container.remove(painel);
            	
            	JButton botaoTerminar = new JButton("Terminar");
            	botaoTerminar.setPreferredSize(new Dimension(100, 50));
            	JButton botaoPassar = new JButton("Passar");
            	botaoPassar.setPreferredSize(new Dimension(100, 50));
            	
            	painel2.add(botaoTerminar);
            	painel2.add(botaoPassar);
            	
            	container.remove(painel);
            	container.add(painel2);

            	partida.getTimer1().comecar(false);
                partida.getTimer2().comecar(true);
                
                JLabel timer1 = new JLabel(String.valueOf(partida.getTimer1().getTempoAtual()));
        		JLabel timer2 = new JLabel(String.valueOf(partida.getTimer2().getTempoAtual()));
        		
        		
        
        		painel2.add(timer1);
        		painel2.add(timer2);
        		
        		
        		container.revalidate();
        		container.repaint();
        		
        		
            }
        });
        
        
	}
	
	public void mostrarVencedor(String nome) {
		JPanel painel = new JPanel();
		
		JLabel texto = new JLabel("Vencedor: " + nome);
		
		painel.add(texto);
		
		add(painel);
	}

}
