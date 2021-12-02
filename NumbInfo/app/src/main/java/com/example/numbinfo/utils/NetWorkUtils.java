package com.example.numbinfo.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

// https://www.kody.su/api/v2.1/search.json?q=79501099990&key=test
public class NetWorkUtils {
   public static final String KODY_BASE_URL = "https://www.kody.su/api/v2.1/search.json";
   public static final String NUMBER = "q";
   public static final String KEY = "key";

    public static URL generateURL(String numb){
        Uri builtUri = Uri.parse(KODY_BASE_URL)
                .buildUpon()
                .appendQueryParameter(NUMBER, numb)
                .appendQueryParameter(KEY, "test")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    try {
        InputStream in = urlConnection.getInputStream();

        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");

        boolean hasInput = scanner.hasNext();

        if (hasInput) {
            return scanner.next();
        } else {
            return null;
        }
    } finally {
        urlConnection.disconnect();
        }
    }
}
