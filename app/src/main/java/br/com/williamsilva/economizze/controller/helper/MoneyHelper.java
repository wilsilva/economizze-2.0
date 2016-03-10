package br.com.williamsilva.economizze.controller.helper;

/**
 * Created by william on 09/03/16.
 */
public class MoneyHelper {

    private static MoneyHelper instance;

    private MoneyHelper(){

    }

    public static synchronized MoneyHelper getInstance(){

        if(MoneyHelper.instance == null){
            MoneyHelper.instance = new MoneyHelper();
        }

        return MoneyHelper.instance;
    }

    public String convert(Number value){
        return "R$ " + value.toString().replace(".", ",");
    }
}
