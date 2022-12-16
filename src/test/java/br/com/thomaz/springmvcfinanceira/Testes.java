package br.com.thomaz.springmvcfinanceira;


import java.util.List;

public class Testes {

    private String descricao;
    private Integer valor;
    private int dia;
    private int mes;
    private int ano;
    private String categoria;
    
    public Testes(String descricao, Integer valor, int dia, int mes, int ano, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.categoria = categoria;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getValor() {
        return valor;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public static List<Testes> criarRegistros() {
        return List.of(
                new Testes("iFood", 1200, 3, 10, 2022, "alimentacao"),
                new Testes("Aluguel", 2300, 3, 10, 2022, "moradia"),
                new Testes("Gasolina", 200, 3, 10, 2022, "transporte"),
                new Testes("Bombeiro", 140, 3, 10, 2022, "imprevistos"),
                new Testes("Restaurantes", 500, 3, 10, 2022, "alimentacao"),
                new Testes("Cinema", 1200, 3, 10, 2022, "lazer"),
                new Testes("Unimed", 1200, 3, 10, 2022, "saude")
                );
    }
    
    
}

