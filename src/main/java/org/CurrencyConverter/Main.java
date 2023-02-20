package org.CurrencyConverter;

import java.util.Currency;
import java.util.Scanner;
import java.util.Set;
import java.io.*;

import okhttp3.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {


        //System.out.println("Hello world!");
        Set<Currency> currencySet = Currency.getAvailableCurrencies();
/*        for (Currency c:currencySet){
            System.out.println(*//*"Currency name: " + c.getDisplayName() + *//*" Currency symbol: " + c);
        }*/
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the currency you want to exchange from: ");
        String userCurrency = input.next();
        Currency currency = Currency.getInstance(userCurrency);
        System.out.println("Please enter the currency you want exchange to: ");
        String convertTo = input.next();
        System.out.println("Please enter the amount you want to exchange: ");
        int amount = input.nextInt();


        //API_KEY=VsxbRegKE0XfLamdoLWoZhCH8DgiB0bo
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String url = "https://api.apilayer.com/exchangerates_data/convert?to=" + userCurrency + "&from=" + convertTo + "&amount=" + amount;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey","VsxbRegKE0XfLamdoLWoZhCH8DgiB0bo")
                .method("GET",null)
                .build();
        Response response = client.newCall(request).execute();
        String resultFromAPI = response.body().string();
        //System.out.println(resultFromAPI);
        //System.out.println(JSONString);

        JSONParser JSONparser = new JSONParser();

        Object obj = JSONparser.parse(resultFromAPI);

        JSONObject currencyJSON = (JSONObject) obj;

        Double result = (Double) currencyJSON.get("result");

        System.out.println(result);
    }
}
