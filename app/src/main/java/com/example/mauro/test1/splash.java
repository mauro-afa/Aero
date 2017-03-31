package com.example.mauro.test1;

import android.app.Activity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


public class splash extends Activity {

    private EditText user, pass;
    public static final String Pref = "Prefs" ;
    public static final String Vuelo = "Vuelo";
    public static final String Usuario = "Usuario";
    public static final String Area = "Area";
    SharedPreferences sp;
    private Button bLogin;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL = "http://aero.esy.es/Login.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_VUELO = "vuelo";
    private static final String TAG_AREA = "area";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp = getSharedPreferences(Pref, Context.MODE_PRIVATE);
        Intent serv = new Intent(splash.this, Servicio.class);
        PendingIntent pintent = PendingIntent.getService(splash.this, 0, serv, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 40000, pintent);

    }

    public void GetThis(View view) {
        user = (EditText) findViewById(R.id.usua);
        pass = (EditText) findViewById(R.id.contra);
        bLogin = (Button) findViewById(R.id.button);

        new AttemptLogin().execute();
    }

    class AttemptLogin extends AsyncTask<String, String, String> {
        boolean failure = false;
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(splash.this);
            pDialog.setMessage("Iniciando sesión");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... args) {
            int success=0;
            String a, b;
            String username = user.getText().toString();
            String password = pass.getText().toString();
            try{
                List params = new ArrayList();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);
                if(json == null)
                    return null;
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    SharedPreferences.Editor ed = sp.edit();

                    a =json.getString(TAG_VUELO);
                    b =json.getString(TAG_AREA);
                    ed.putString(Area, b);
                    ed.putString(Vuelo, a);
                    ed.putString(Usuario, username);
                    ed.apply();
                    Intent ii = new Intent(splash.this,Panel1.class);
                    ii.putExtra("a1",a);
                    finish();
                    startActivity(ii);
                    return json.getString(TAG_MESSAGE);
                }
                if (success == 2) {
                    SharedPreferences.Editor ed = sp.edit();
                    a =json.getString(TAG_VUELO);
                    b =json.getString(TAG_AREA);
                    ed.putString(Area, b);
                    ed.putString(Vuelo,a);
                    ed.putString(Usuario,username);
                    ed.apply();
                    Intent ii = new Intent(splash.this,Panel2.class);
                    ii.putExtra("a1",a);
                    finish();

                    startActivity(ii);
                    return json.getString(TAG_MESSAGE);
                }
                if(success == 3) {
                    SharedPreferences.Editor ed = sp.edit();
                    a =json.getString(TAG_VUELO);
                    b =json.getString(TAG_AREA);
                    ed.putString(Area, b);
                    ed.putString(Vuelo,a);
                    ed.putString(Usuario,username);
                    ed.apply();
                    Intent ii = new Intent(splash.this,Panel3.class);
                    ii.putExtra("a1",a);
                    finish();

                    startActivity(ii);
                    return json.getString(TAG_MESSAGE);
                }
                if(success == 4) {
                    SharedPreferences.Editor ed = sp.edit();
                    a =json.getString(TAG_VUELO);
                    b =json.getString(TAG_AREA);
                    ed.putString(Area, b);
                    ed.putString(Vuelo,a);
                    ed.putString(Usuario,username);
                    ed.apply();
                    Intent ii = new Intent(splash.this,Panel4.class);
                    ii.putExtra("a1",a);
                    finish();

                    startActivity(ii);
                    return json.getString(TAG_MESSAGE);
                }
                else
                {
                    return json.getString(TAG_MESSAGE);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String TAG_MESSAGE) {
            pDialog.dismiss();
            if (TAG_MESSAGE != null){
                Toast.makeText(splash.this, TAG_MESSAGE, Toast.LENGTH_LONG).show();
                user.setText("");
                pass.setText("");
            }
            else
            {
                Toast.makeText(splash.this, "Ocurrio un error inesperado", Toast.LENGTH_LONG).show();
                user.setText("");
                pass.setText("");
                user.requestFocus();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.accion_ayuda) {
            Intent a = new Intent(splash.this,Ayuda.class);
            startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }
}
