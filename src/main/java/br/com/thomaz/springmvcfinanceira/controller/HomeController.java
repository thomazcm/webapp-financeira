package br.com.thomaz.springmvcfinanceira.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.thomaz.springmvcfinanceira.service.TokenService;
import br.com.thomaz.springmvcfinanceira.service.tables.Mes;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired TokenService tokenService;

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
            model.addAttribute("titulo", String.format("%s %d", Mes.de(mes), ano));
            model.addAttribute("token", tokenService.getToken());
            model.addAttribute("ano", ano);
            model.addAttribute("mes", mes);
            return true;
        } catch (NumberFormatException | DateTimeException e) {
            return false;
        }
    }
}
