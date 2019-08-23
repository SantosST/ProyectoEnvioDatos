package com.example.proyectojulio;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;

import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    ListView  lstvListaProf;

    List<EstructuraDatos> ListaAlmacenarProfesores= new ArrayList<EstructuraDatos>();
    List<EstructuraDatos> ListaAlmacenarProfesoresVacia= new ArrayList<EstructuraDatos>();
    ArrayList<String> ListaProfesores = new ArrayList<String>();
    Spinner Materias;
    String MateriaElegida;
    Button btnTraerLista;
    Adaptador Adaptadorcito;
    String strHOST = "http://10.0.2.2:80";
    int NumeroMateria = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObtenerReferencias();
        SetearListener();
        String[] items = new String[]{"Matematica", "Lengua", "Ingles","Base de Datos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        Materias.setAdapter(adapter);
        Adaptadorcito = new Adaptador(getApplicationContext(),ListaAlmacenarProfesores);

      /*  tareaAsincronica miTarea = new tareaAsincronica();
        miTarea.execute(); */








    }

    private void SetearListener() {
        btnTraerLista.setOnClickListener(btnTraer_Click);

    }



    private void ObtenerReferencias(){
        lstvListaProf= (ListView)findViewById(R.id.lstvListaProg);
        Materias = findViewById(R.id.spnMateria);
        btnTraerLista = findViewById(R.id.btnTraerLista);

    }
    private View.OnClickListener btnTraer_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ListaAlmacenarProfesores.clear();
            String text = Materias.getSelectedItem().toString();

            MateriaElegida = text;
            JSONObject jsonObject=new JSONObject();
            try {

                // paso a Json los datos ingresados
                switch(text) {
                    case "Matematica":
                        NumeroMateria = 1;
                        break;
                    case "Lengua":
                        NumeroMateria = 2;
                        break;
                    case "Ingles":
                        NumeroMateria = 3;
                        break;
                    case "Base de Datos":
                        NumeroMateria = 4;
                        break;
                    default:
                        break;
                }




                jsonObject.put("numerito",NumeroMateria);


            } catch (JSONException e) {
                e.printStackTrace();
            }
           if (NumeroMateria!=0){
               MyHttpPostRequest myHttp = new MyHttpPostRequest();
               myHttp.execute(jsonObject);
              /* tareaAsincronica miTarea = new tareaAsincronica();
               miTarea.execute(); */
           }


            }

        };

   /*  private class tareaAsincronica extends AsyncTask<Void,Void,Void> {

        //String strHOST = "
        // ttp://profesores.com.ar";
        protected Void doInBackground(Void... voids){
            try{
                URL miRuta= new URL (strHOST);
               HttpURLConnection conn = (HttpURLConnection) miRuta.openConnection();
               conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "raw; charset=utf-8");
                conn.setRequestProperty("Charset", "UTF-8");
                conn.setDoOutput(true);

                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.write(("numerito=1").getBytes("UTF-8"));
                os.flush();
                os.close();

                Log.d("AccesoAPI","Me conecto");
                if (conn.getResponseCode()==200){
                    Log.d("AccesoAPI", "ConexionOK");
                    InputStream cuerpoRespuesta =conn.getInputStream();
                    InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta, "UTF-8");

                    procesarJSONLeido(lectorRespuesta);
                }else{
                    Log.d("AccesoApi", "ConexionOK");
                }
                conn.disconnect();
            }catch(MalformedURLException error){
                Log.d("AccesoAPI","Error 1 en la conexión"+error.getMessage());
            } catch (IOException error) {
                Log.d("AccesoAPI","Error 2 en la conexión"+error.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lstvListaProf.setAdapter(Adaptadorcito);
        }
    }

*/
    public Void procesarJSONLeido(InputStreamReader streamLeido)
    {

        JsonReader JSONLeido = new JsonReader(streamLeido);
        Log.d("LecturaJson", "El stream leido es " + JSONLeido);
        try
        {
            Log.d("LecturaJson","empezo el array 1");
            JSONLeido.beginArray();
            Log.d("LecturaJson","empezo el array 2");
            while (JSONLeido.hasNext()) {
                Log.d("LecturaJson","tiene algo");
                int ID = -1;
                String nombre = null;
                int PrecioBase = -1;
                String Localizacion_X = null;
                String NombreMateria = null;




                JSONLeido.beginObject();
                while (JSONLeido.hasNext()) {

                    //Log.d("ACCESOAPI","Estoy en: " + JSONLeido);
                    String nombreElementoActual = JSONLeido.nextName();
                    Log.d("LecturaJson", "aaaaaaaaaaaaaa: " + nombreElementoActual);

                    if (nombreElementoActual.equals("id")){
                        ID = Integer.parseInt(JSONLeido.nextString());
                        Log.d("LecturaJson","ID leido: " + ID);
                    }
                    else if (nombreElementoActual.equals("Nombre")) {
                        nombre = JSONLeido.nextString();
                        Log.d("LecturaJson","ID leido: " + nombre);
                    }

                     else if (nombreElementoActual.equals("PrecioBase")) {
                        PrecioBase = Integer.parseInt(JSONLeido.nextString());
                        Log.d("LecturaJson","ID leido: " + PrecioBase);
                    }


                     else if (nombreElementoActual.equals("Localizacion_X")) {
                        Localizacion_X = JSONLeido.nextString();
                        Log.d("LecturaJson","ID leido: " + Localizacion_X);
                    } else if (nombreElementoActual.equals("NombreMateria")) {
                        NombreMateria = JSONLeido.nextString();
                        Log.d("LecturaJson","ID leido: " + NombreMateria);
                    }
                    else {
                        JSONLeido.skipValue();
                    }

                }
                Log.d("LecturaJson", NombreMateria + "    " + MateriaElegida);
              /* if (
                    (ID != -1) &&
                    (nombre != null) &&
                    (Localizacion_X != null) &&
                    (PrecioBase != -1 ) &&
                    (NombreMateria != null) &&
                    NombreMateria.equals(MateriaElegida)
                ) { */
                    Log.d("LecturaJson", "Agregamos");
                    ListaAlmacenarProfesores.add(new EstructuraDatos(ID,nombre,NombreMateria,PrecioBase,R.drawable.profesor,Localizacion_X));
                    Log.d("adsadsad", ""+ListaAlmacenarProfesores.size());
               // }

                JSONLeido.endObject();

            }
            JSONLeido.endArray();
        } catch (IOException error)
        {
            Log.d("AccesoAPI","Error 3 en la conexión"+error.getMessage());
        }
        return null;
    }
    private class MyHttpPostRequest extends AsyncTask <JSONObject, Void, Void> {
        @Override
        protected Void doInBackground(JSONObject... parametros) {


            JSONObject data = parametros[0];
            String jsonInputString = data.toString();

            try {
                URL miRuta = new URL(strHOST);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                miConexion.setRequestMethod("POST");
                miConexion.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                miConexion.setDoOutput(true);
                miConexion.setFixedLengthStreamingMode(jsonInputString.getBytes().length);
                miConexion.connect();
                Log.d("CONEXIONAPIINSERTAR", "Conexion OK");
                OutputStream os;
                os = new BufferedOutputStream(miConexion.getOutputStream());
                os.write(jsonInputString.getBytes());
                os.flush();
                Log.d("LLego", "LLego");
                os.close();
                if (miConexion.getResponseCode()==200){
                    Log.d("AccesoAPI", "ConexionOK");
                    InputStream cuerpoRespuesta =miConexion.getInputStream();
                    InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta, "UTF-8");

                    procesarJSONLeido(lectorRespuesta);
                }else{
                    Log.d("AccesoApi", "ConexionOK");
                }
                miConexion.disconnect();
            } catch(MalformedURLException error) {
                Log.d("AccesoAPI", "Error 1 en la conexión" + error.getMessage());
            }catch (Exception e) {
                Log.d("ACCESOAPI2", "Hubo un error al conectarme: " + e.getMessage());
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lstvListaProf.setAdapter(Adaptadorcito);
        }


    }
}
