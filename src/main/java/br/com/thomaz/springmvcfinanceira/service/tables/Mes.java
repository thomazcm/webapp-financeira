package br.com.thomaz.springmvcfinanceira.service.tables;

import java.time.Month;
import java.util.EnumMap;

public abstract class Mes {

    private static EnumMap<Month, String> meses = new EnumMap<>(Month.class);

    public static String de(int mes) {
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
        return meses.get(Month.of(mes));
    }
}
