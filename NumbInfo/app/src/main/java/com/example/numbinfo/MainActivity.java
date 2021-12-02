package com.example.numbinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.example.numbinfo.utils.NetWorkUtils.generateURL;
import static com.example.numbinfo.utils.NetWorkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
private EditText searchField;
private Button searchButton;
private TextView result;

    class NumQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            String operator = null;
            String region = null;
            String number = null;
            String changeOperator = null;
            if (response != null) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("numbers");
                    JSONObject numbInfo = jsonArray.getJSONObject(0);
                    operator = numbInfo.getString("operator");
                    region = numbInfo.getString("region");
                    number = numbInfo.getString("number_current");
                    changeOperator = numbInfo.getString("bdpn_operator");


                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("подожди до завтра");
                }
                String resultingString = "Проверяемый номер: " + number + "\n" + "Оператор: " + operator + "\n" + "Поменял оператор на: " + changeOperator + "\n" + "Регион: " + region + "\n";
                result.setText(resultingString);
            } else {
                String resultingString =  "превышен лимит запросов на сегодня";
                result.setText(resultingString);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field);
        searchButton = findViewById(R.id.b_search_number);
        result = findViewById(R.id.tv_result);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generateURL = generateURL(searchField.getText().toString());

                new NumQueryTask().execute(generateURL);
            }
        };
        searchButton.setOnClickListener(onClickListener);
    }
}
