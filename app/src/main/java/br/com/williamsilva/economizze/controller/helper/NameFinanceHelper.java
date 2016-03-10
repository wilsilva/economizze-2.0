package br.com.williamsilva.economizze.controller.helper;

/**
 * Created by william on 09/03/16.
 */
public class NameFinanceHelper {
    private static NameFinanceHelper instance;

    private NameFinanceHelper(){}

    public static synchronized NameFinanceHelper getInstance(){

        if(NameFinanceHelper.instance == null){
            NameFinanceHelper.instance = new NameFinanceHelper();
        }

        return NameFinanceHelper.instance;
    }

    public String gerarIniciais(String fullName){
        return fullName.substring(0,2).toUpperCase();
    }
}
