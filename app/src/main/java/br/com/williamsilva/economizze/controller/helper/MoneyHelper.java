package br.com.williamsilva.economizze.controller.helper;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by william on 09/03/16.
 */
public class MoneyHelper {

    private static MoneyHelper instance;
    private static Locale locale;


    private MoneyHelper() {

    }

    public static synchronized MoneyHelper getInstance() {

        if (MoneyHelper.instance == null) {
            MoneyHelper.instance = new MoneyHelper();
        }

        if (MoneyHelper.locale == null)
            MoneyHelper.locale = new RelogioHelper(new Date()).getLocale();

        return MoneyHelper.instance;
    }

    public String convert(Number value) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        numberFormat.setMinimumFractionDigits(2);
        return "R$ " + numberFormat.format(value);
    }
}
