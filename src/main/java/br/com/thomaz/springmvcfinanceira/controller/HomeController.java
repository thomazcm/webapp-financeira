package br.com.thomaz.springmvcfinanceira.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.thomaz.springmvcfinanceira.service.tables.Ano;
import br.com.thomaz.springmvcfinanceira.service.tables.Categorias;
import br.com.thomaz.springmvcfinanceira.service.tables.Mes;

@Controller
@RequestMapping("/home")
public class HomeController {
    
    @GetMapping
    public String homeMesAtual(Model model) {
        String mes = Integer.toString(LocalDate.now().getMonthValue());
        String ano = Integer.toString(LocalDate.now().getYear());
        addModelAttributes(model, ano, mes);
        return "home";
    }

    @GetMapping("/{ano}/{mes}")
    public String homeAnoMes(Model model, @PathVariable String ano, @PathVariable String mes) {
        if (addModelAttributes(model, ano, mes)) {
            return "home";
        }
        return "redirect:/home";
    }

    private boolean addModelAttributes(Model model, String anoString, String mesString) {
        try {
            Integer ano = Integer.valueOf(anoString);
            Integer mes = Integer.valueOf(mesString);
            model.addAttribute("ano", ano);
            model.addAttribute("mes", mes);
            model.addAttribute("mesNome", Mes.de(mes));
            model.addAttribute("mesAnteriorUrl", calculaMesAnterior(ano, mes));
            model.addAttribute("proximoMesUrl", calculaProximoMes(ano, mes));
            model.addAttribute("mesAnteriorNome", Mes.de(mes == 1 ? 12 : mes-1));
            model.addAttribute("proximoMesNome", Mes.de(mes == 12 ? 1 : mes+1));
            model.addAttribute("listaMeses", Mes.listaDeMeses());
            model.addAttribute("listaAnos", Ano.listaDeAnos());
            model.addAttribute("listaCategorias", Categorias.lista());
            return true;
        } catch (NumberFormatException | DateTimeException e) {
            return false;
        }
    }

    private String calculaMesAnterior(Integer ano, Integer mes) {
        return String.format("%d/%d", 
                mes == 1 ? ano-1 : ano,
                mes == 1 ? 12 : mes-1);
    }

    private String calculaProximoMes(Integer ano, Integer mes) {
        return String.format("%d/%d", 
                mes == 12 ? ano+1 : ano,
                mes == 12 ? 1 : mes+1);
    }
}
