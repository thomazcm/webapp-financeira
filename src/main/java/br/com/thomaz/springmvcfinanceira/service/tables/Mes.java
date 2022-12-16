package br.com.thomaz.springmvcfinanceira.service.tables;

import java.time.Month;
import java.util.ArrayList;
import java.util.EnumMap;

public abstract class Mes {

    private static EnumMap<Month, String> meses = new EnumMap<>(Month.class);

    public static String de(int mes) {
        criarLista();
        return meses.get(Month.of(mes));
    }
    
    public static ArrayList<MesObject> listaDeMeses(){
        criarLista();
        var listaMeses = new ArrayList<MesObject>();
        meses.forEach((k,v) -> {
            int numeroMes = k.getValue();
            listaMeses.add(new MesObject(v, numeroMes));
        });
        return listaMeses;
    }

    private static void criarLista() {
        if (meses.isEmpty()) {
            meses.put(Month.JANUARY, "Janeiro");
            meses.put(Month.FEBRUARY, "Fevereiro");
            meses.put(Month.MARCH, "Março");
            meses.put(Month.APRIL, "Abril");
            meses.put(Month.MAY, "Maio");
            meses.put(Month.JUNE, "Junho");
            meses.put(Month.JULY, "Julho");
            meses.put(Month.AUGUST, "Agosto");
            meses.put(Month.SEPTEMBER, "Setembro");
            meses.put(Month.OCTOBER, "Outubro");
            meses.put(Month.NOVEMBER, "Novembro");
            meses.put(Month.DECEMBER, "Dezembro");
        }
    }
}

class MesObject{
    
    public MesObject(String name, int number) {
        this.name = name;
        this.number = number;
    }
    private String name;
    private int number;
    
    public String getName() {
        return name;
    }
    public int getNumber() {
        return number;
    }
    
}
