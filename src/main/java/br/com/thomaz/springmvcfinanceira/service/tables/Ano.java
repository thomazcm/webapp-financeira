package br.com.thomaz.springmvcfinanceira.service.tables;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Ano {

    public static List<Integer> listaDeAnos(){
        var anos = new ArrayList<Integer>();
        var i = Integer.valueOf(LocalDate.now().getYear());
        while (i > 2019) {
            anos.add(i);
            i--;
        }
        return anos;
    }

    public static void main(String[] args) {
        listaDeAnos().forEach(System.out::println);
    }
}
