package br.ucsal.relogio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ucsal.relogio.domain.Partida;

@Controller
public class MainController {
	
	@GetMapping("/")
	public ModelAndView home() {
		Partida ptd = Partida.getInstancia(60);
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("tempo1", ptd.getTimer1().getTempoAtual());
		mv.addObject("tempo2", ptd.getTimer2().getTempoAtual());
		return mv;
	}
	
	@GetMapping("/passar")
	public void passar() throws InterruptedException {
		Partida.getInstancia().mudarTurno();
	}
	
	@GetMapping("/terminar")
	public void terminar() throws InterruptedException {
		Partida.getInstancia().mudarTurno();
	}

}
