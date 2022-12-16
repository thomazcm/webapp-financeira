package br.com.thomaz.springmvcfinanceira.service.tables;

import java.util.List;

public abstract class Categorias {
    
    public static List<String> lista(){
        return List.of("Alimentação", "Educação", "Imprevistos", "Lazer", "Moradia", 
                "Saúde", "Transporte", "Outras");
    }
}
