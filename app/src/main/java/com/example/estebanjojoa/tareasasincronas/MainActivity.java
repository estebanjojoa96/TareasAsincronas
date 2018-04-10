package com.example.estebanjojoa.tareasasincronas;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ProgressBar progressBar;
    TextView textView_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = (ProgressBar) findViewById(R.id.id_progressb_loaddata);
        textView_data = (TextView) findViewById(R.id.id_textview_data);

    }
    //Valida el estado de la red
    public Boolean isOnline() {

        // Obtener el servicio de la conectividad en android

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //Obtener la información del estado de la red
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null){
            return true;
        }else{
            return false;

        }



    }
    //Evento para el boton principal
    public void loadData(View view){
        //
        if(isOnline()){
            MyTask myTask = new MyTask();
            myTask.execute("");

        }else{
            Toast.makeText(this, "No hay conexiones disponibles", Toast.LENGTH_SHORT).show();
        }
    }
    //Metodo para procesar los datos
    public void processData(String s){

        textView_data.setText("Numero" +s);
        textView_data.setTextSize(Integer.parseInt(s)+10);
    }

    public class MyTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            for(int i=1; i<=50; i++){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(String.valueOf(i));

            }

            return "Fin";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            processData(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
        }
    }

}
