package br.com.williamsilva.economizze.controller.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by william on 13/02/16.
 */
public class RelogioHelper {

    private final Locale locale;
    private Date date;
    private SimpleDateFormat dateFormat;


    public RelogioHelper(Date date) {
        this.date = date;
        locale = new Locale("pt", "BR");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public String MesPorExtenso() {
        dateFormat = new SimpleDateFormat("MMMM", locale);
        String mes = dateFormat.format(date);
        return mes.substring(0, 1).toUpperCase() + mes.substring(1);

    }

    public String dataPtBr() {
        return dateFormat.format(date);
    }

    public static Date parse(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return data;
    }

    public boolean possuiMesmoMesAno(Object o) {

        Date date = (Date) o;

        /**
         * Formata a data para comparar o mês e o ano atual
         * Formato: Mês/Ano ex: 01/2016
         */
        SimpleDateFormat fmt = new SimpleDateFormat("MM/yyyy");

        if (fmt.format(this.date).equals(fmt.format(date))) {
            return true;
        }

        return false;
    }

    public Locale getLocale() {
        return this.locale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelogioHelper that = (RelogioHelper) o;

        if (!locale.equals(that.locale)) return false;
        if (!date.equals(that.date)) return false;
        return dateFormat.equals(that.dateFormat);
    }
}
