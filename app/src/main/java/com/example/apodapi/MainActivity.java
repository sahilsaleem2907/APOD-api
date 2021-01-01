package com.example.apodapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Typewriter tw ;
    TextView tv,tv2 ;
    String todaysHeading,todaysDate ;

    public void getDetails(View view)
    {
        Toast.makeText(this,"Loading ...",Toast.LENGTH_LONG).show();
        DownloadTask task = new DownloadTask();
        task.execute("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");

    }
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);

                String todaysImage = jsonObject.getString("hdurl");
                String todaysInfo = jsonObject.getString("explanation");
                 todaysHeading = jsonObject.getString("title");
                  todaysDate = jsonObject.getString("date");
                tv.setText(todaysDate);
                tv2.setText(todaysHeading);


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                        intent.putExtra("img",todaysImage);
                        intent.putExtra("inf",todaysInfo);
                        startActivity(intent);

                    }
                }, 5000);









            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tw = findViewById(R.id.tv);
        tv = findViewById(R.id.textView4);
        tv2 = findViewById(R.id.textView5);
        tw.setText("");
        tw.setCharacterDelay(150);
        tw.animateText("Image of the day is\n");



        DownloadTask task = new DownloadTask();


    }
}