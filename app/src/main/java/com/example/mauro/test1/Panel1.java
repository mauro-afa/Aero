package com.example.mauro.test1;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Panel1 extends FragmentActivity implements ActionBar.TabListener {

    String vuelo, area;
    private EditText e, e1, e2, e3;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL = "http://aero.esy.es/act_maletero.php";
    private static final String DESACT_URL = "http://aero.esy.es/desact_maletero.php";
    private static final String LOGIN_URL2 = "http://aero.esy.es/act_maletero2.php";
    private static final String DESACT_URL2 = "http://aero.esy.es/desact_maletero2.php";
    private static final String LOGIN_URL3 = "http://aero.esy.es/maletero.php";
    private static final String LOGIN_URL4 = "http://aero.esy.es/cambio.php";
    private static final String LOGIN_URL5 = "http://aero.esy.es/acomodador.php";
    private static final String LOGIN_URL6 = "http://aero.esy.es/mantenimiento.php";
    private static final String LOGIN_URL7 = "http://aero.esy.es/comida.php";
    private static final String LOGIN_URL8 = "http://aero.esy.es/encargados.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_P = "proceso1";
    private static final String TAG_P2 = "proceso2";
    private static final String TAG_P3 = "proceso3";
    private static final String TAG_P4 = "proceso4";
    private static final String TAG_ACOM = "acom";
    private static final String TAG_ACOM2 = "acom2";
    private static final String TAG_ACOM3 = "acom3";
    private static final String TAG_ACOM4 = "acom4";
    private static final String TAG_MANT = "mant";
    private static final String TAG_MANT2 = "mant2";
    private static final String TAG_MANT3 = "mant3";
    private static final String TAG_MANT4 = "mant4";
    private static final String TAG_MANT5 = "mant5";
    private static final String TAG_COM = "comida";
    private static final String TAG_COM2 = "comida2";
    private static final String TAG_COM3 = "comida3";
    private static final String TAG_COM4 = "comida4";
    private static final String TAG_COM5 = "comida5";
    private static final String TAG_COM6 = "comida6";
    private static final String TAG_COM7 = "comida7";
    private static final String TAG_COM8 = "comida8";
    String mant, mant2, mant3, mant4, mant5;
    String malet, malet2, malet3, malet4;
    String acom, acom2, acom3, acom4;
    String com, com2, com3, com4, com5, com6, com7, com8;
    String proc, usuario;
    public static final String Pref = "Prefs";
    public static final String Vuelo = "Vuelo";
    public static final String Area = "Area";
    public static final String Usuario = "Usuario";
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private String[] tabs = {"Comida", "Mantenimiento", "Maletero", "Acomodadores"};
    List<DrawerItem> dataList;
    private static final String TAG_NOMBRE = "nombre";
    private static final String TAG_APELLIDO = "apellido";
    private static final String TAG_AREA = "area";
    private static final String TAG_PERSONAL = "encargados";
    ListView lv;
    ArrayList<HashMap<String, String>> Lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel1);

        SharedPreferences sp = getSharedPreferences(Pref, Context.MODE_PRIVATE);
        vuelo = (sp.getString(Vuelo, ""));
        area = (sp.getString(Area, ""));
        usuario = (sp.getString(Usuario, ""));


        dataList = new ArrayList<DrawerItem>();

        dataList.add(new DrawerItem("Principal",R.drawable.principal));
        dataList.add(new DrawerItem("Marcar procesos",R.drawable.procesos));
        dataList.add(new DrawerItem("Cambiar contraseña",R.drawable.pass));
        dataList.add(new DrawerItem("Reportar inconveniente",R.drawable.error));
        dataList.add(new DrawerItem("Personal encargado",R.drawable.personal));
        dataList.add(new DrawerItem("Cerrar sesión",R.drawable.cerrar));
        drawerListView = (ListView) findViewById(R.id.left_drawer);
        drawerListView.setAdapter(new CustomAdapter(this,
                R.layout.items, dataList));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        );
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
        if (tab.getPosition() == 0) {
            new cargar_comida().execute();
        }
        if (tab.getPosition() == 1) {
            new cargar_mantenimiento().execute();
        }
        if (tab.getPosition() == 2) {
            new cargar_maletero().execute();
        }
        if (tab.getPosition() == 3) {
            new cargar_acomodadores().execute();
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            Opcion(position);
        }
    }

    public void GetThis2(View view) {

        EditText a = (EditText) findViewById(R.id.editText5);
        EditText d = (EditText) findViewById(R.id.editText6);
        String asunto = a.getText().toString();
        String descripcion = d.getText().toString();
        if (asunto.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(Panel1.this, "Alguno de los dos campos esta vacio.", Toast.LENGTH_SHORT).show();
        } else {
            Intent mail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "mauro.afa91@gmail.com", null));
            mail.putExtra(Intent.EXTRA_SUBJECT, asunto);
            mail.putExtra(Intent.EXTRA_TEXT, descripcion);
            try {
                startActivity(Intent.createChooser(mail, "Escoge un cliente de correo:"));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Panel1.this, "No hay clientes instalados...", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void GetThis1(View view) {
        e = (EditText) findViewById(R.id.editText);
        e1 = (EditText) findViewById(R.id.editText2);
        e2 = (EditText) findViewById(R.id.editText3);
        e3 = (EditText) findViewById(R.id.editText4);
        String pass_n = e2.getText().toString();
        String pass_n2 = e3.getText().toString();


        if (pass_n.equals(pass_n2)) {
            new cambio().execute();
        } else {
            Toast.makeText(Panel1.this, "Las contrase�as nuevas no son iguales", Toast.LENGTH_LONG).show();
            e2.setText("");
            e3.setText("");
        }
    }

    class cambio extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Cambiando contrase�a...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            int success;
            String a;
            String user = e.getText().toString();
            String pass = e1.getText().toString();
            String n_pass = e2.getText().toString();
            String n_pass2 = e3.getText().toString();
            try {
                List params = new ArrayList();
                params.add(new BasicNameValuePair("user", user));
                params.add(new BasicNameValuePair("pass", pass));
                params.add(new BasicNameValuePair("n_pass", n_pass));
                params.add(new BasicNameValuePair("n_pass2", n_pass2));

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL4, "POST", params);
                if (json == null)
                    return null;
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    return json.getString(TAG_MESSAGE);
                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            pDialog.dismiss();
            Toast.makeText(Panel1.this, message, Toast.LENGTH_LONG).show();
            EditText ed = (EditText) findViewById(R.id.editText);
            EditText ed2 = (EditText) findViewById(R.id.editText2);
            EditText ed3 = (EditText) findViewById(R.id.editText3);
            EditText ed4 = (EditText) findViewById(R.id.editText4);
            ed.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
        }
    }

    private void Opcion(int position) {
        Fragment f = null;
        switch (position) {
            case 0:
                Intent i2 = new Intent(this, Panel1.class);
                startActivity(i2);
                break;
            case 1:
                f = new Procesos();
                findViewById(R.id.pager).setVisibility(View.GONE);
                actionBar.removeAllTabs();
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                new cargar_lista().execute();
                break;
            case 2:
                f = new configuracion();
                findViewById(R.id.pager).setVisibility(View.GONE);
                actionBar.removeAllTabs();
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                break;
            case 3:
                f = new Error();
                findViewById(R.id.pager).setVisibility(View.GONE);
                actionBar.removeAllTabs();
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                break;
            case 4:
                f = new Encargados();
                findViewById(R.id.pager).setVisibility(View.GONE);
                actionBar.removeAllTabs();
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
                Lista = new ArrayList<HashMap<String, String>>();
                new cargar_encargados().execute();
                break;
            case 5:
                Intent i = new Intent(this, splash.class);
                startActivity(i);
                break;
        }
        if (f != null) {
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, f).commit();

            drawerListView.setItemChecked(position, true);
            drawerListView.setSelection(position);
            drawerLayout.closeDrawer(drawerListView);
        }
    }

    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.transporte_m:
                if (checked) {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea marcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    proc = getResources().getString(R.string.transporte_m);
                                    new act_proc().execute();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.transporte_m);
                                    A.setChecked(false);
                                }
                            })
                            .show();
                }
                else
                {
                    CheckBox z = (CheckBox) findViewById(R.id.transporte_m_l);
                    if(z.isChecked())
                    {
                        Toast.makeText(this,"No se puede desmarcar un proceso que esta finalizado",Toast.LENGTH_SHORT).show();
                        CheckBox A = (CheckBox) findViewById(R.id.transporte_m);
                        A.setChecked(true);
                    }
                    else
                    {
                        new AlertDialog.Builder(this)
                                .setTitle("Confirmación")
                                .setMessage("¿Seguro desea desmarcar este proceso?")
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        proc = getResources().getString(R.string.transporte_m);
                                        new desact_proc().execute();
                                        CheckBox A = (CheckBox) findViewById(R.id.transporte_m);
                                        A.setChecked(false);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        CheckBox A = (CheckBox) findViewById(R.id.transporte_m);
                                        A.setChecked(true);
                                    }
                                })
                                .show();
                    }
                }
                break;
            case R.id.transporte_m_l:
                if (checked) {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea marcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox a = (CheckBox) findViewById(R.id.transporte_m);
                                    a.setChecked(true);
                                    proc = getResources().getString(R.string.transporte_m);
                                    new act_proc_l().execute();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.transporte_m_l);
                                    A.setChecked(false);
                                }
                            })
                            .show();
                }
                else
                {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea desmarcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    proc = getResources().getString(R.string.transporte_m);
                                    new desact_proc_l().execute();
                                    CheckBox A = (CheckBox) findViewById(R.id.transporte_m);
                                    CheckBox B = (CheckBox) findViewById(R.id.transporte_m_l);
                                    A.setChecked(false);
                                    B.setChecked(false);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.transporte_m);
                                    CheckBox B = (CheckBox) findViewById(R.id.transporte_m_l);
                                    A.setChecked(true);
                                    B.setChecked(true);
                                }
                            })
                            .show();
                }
                break;
            case R.id.cargar_m:
                if (checked) {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea marcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    proc = getResources().getString(R.string.cargar_m);
                                    new act_proc().execute();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.cargar_m);
                                    A.setChecked(false);
                                }
                            })
                            .show();
                }
                else
                {
                    CheckBox z = (CheckBox) findViewById(R.id.cargar_m);
                    if(z.isChecked())
                    {
                        Toast.makeText(this,"No se puede desmarcar un proceso que esta finalizado",Toast.LENGTH_SHORT).show();
                        CheckBox A = (CheckBox) findViewById(R.id.cargar_m);
                        A.setChecked(true);
                    }
                    else
                    {
                        new AlertDialog.Builder(this)
                                .setTitle("Confirmación")
                                .setMessage("¿Seguro desea desmarcar este proceso?")
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        proc = getResources().getString(R.string.cargar_m);
                                        new desact_proc().execute();
                                        CheckBox A = (CheckBox) findViewById(R.id.cargar_m);
                                        A.setChecked(false);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        CheckBox A = (CheckBox) findViewById(R.id.cargar_m);
                                        A.setChecked(true);
                                    }
                                })
                                .show();
                    }
                }
                break;
            case R.id.cargar_m_l:
                if (checked) {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea marcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox a = (CheckBox) findViewById(R.id.cargar_m);
                                    a.setChecked(true);
                                    proc = getResources().getString(R.string.cargar_m);
                                    new act_proc_l().execute();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.cargar_m_l);
                                    A.setChecked(false);
                                }
                            })
                            .show();
                }
                else
                {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea desmarcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    proc = getResources().getString(R.string.cargar_m);
                                    new desact_proc_l().execute();
                                    CheckBox A = (CheckBox) findViewById(R.id.cargar_m);
                                    CheckBox B = (CheckBox) findViewById(R.id.cargar_m_l);
                                    A.setChecked(false);
                                    B.setChecked(false);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.cargar_m);
                                    CheckBox B = (CheckBox) findViewById(R.id.cargar_m_l);
                                    A.setChecked(true);
                                    B.setChecked(true);
                                }
                            })
                            .show();
                }
                break;
            case R.id.cargar_art_fra:
                if (checked) {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea marcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    proc = getResources().getString(R.string.cargar_art_fra);
                                    new act_proc().execute();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.cargar_art_fra);
                                    A.setChecked(false);
                                }
                            })
                            .show();
                }
                else
                {
                    CheckBox z = (CheckBox) findViewById(R.id.cargar_art_fra_l);
                    if(z.isChecked())
                    {
                        Toast.makeText(this,"No se puede desmarcar un proceso que esta finalizado",Toast.LENGTH_SHORT).show();
                        CheckBox A = (CheckBox) findViewById(R.id.cargar_art_fra);
                        A.setChecked(true);
                    }
                    else
                    {
                        new AlertDialog.Builder(this)
                                .setTitle("Confirmación")
                                .setMessage("¿Seguro desea desmarcar este proceso?")
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        proc = getResources().getString(R.string.cargar_art_fra);
                                        new desact_proc().execute();
                                        CheckBox A = (CheckBox) findViewById(R.id.cargar_art_fra);
                                        A.setChecked(false);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        CheckBox A = (CheckBox) findViewById(R.id.cargar_art_fra);
                                        A.setChecked(true);
                                    }
                                })
                                .show();
                    }
                }
                break;
            case R.id.cargar_art_fra_l:
                if (checked) {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea marcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox a = (CheckBox) findViewById(R.id.cargar_art_fra);
                                    a.setChecked(true);
                                    proc = getResources().getString(R.string.cargar_art_fra);
                                    new act_proc_l().execute();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.cargar_art_fra_l);
                                    A.setChecked(false);
                                }
                            })
                            .show();
                }
                else
                {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea desmarcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    proc = getResources().getString(R.string.cargar_art_fra);
                                    new desact_proc_l().execute();
                                    CheckBox A = (CheckBox) findViewById(R.id.cargar_art_fra);
                                    CheckBox B = (CheckBox) findViewById(R.id.cargar_art_fra_l);
                                    A.setChecked(false);
                                    B.setChecked(false);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.cargar_art_fra);
                                    CheckBox B = (CheckBox) findViewById(R.id.cargar_art_fra_l);
                                    A.setChecked(true);
                                    B.setChecked(true);
                                }
                            })
                            .show();
                }
                break;
            case R.id.cargar_mascotas:
                if (checked) {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea marcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    proc = getResources().getString(R.string.cargar_mascotas);
                                    new act_proc().execute();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.cargar_mascotas);
                                    A.setChecked(false);
                                }
                            })
                            .show();
                }
                else
                {
                    CheckBox z = (CheckBox) findViewById(R.id.cargar_mascotas_l);
                    if(z.isChecked())
                    {
                        Toast.makeText(this,"No se puede desmarcar un proceso que esta finalizado",Toast.LENGTH_SHORT).show();
                        CheckBox A = (CheckBox) findViewById(R.id.cargar_mascotas);
                        A.setChecked(true);
                    }
                    else
                    {
                        new AlertDialog.Builder(this)
                                .setTitle("Confirmación")
                                .setMessage("¿Seguro desea desmarcar este proceso?")
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        proc = getResources().getString(R.string.cargar_mascotas);
                                        new desact_proc().execute();
                                        CheckBox A = (CheckBox) findViewById(R.id.cargar_mascotas);
                                        A.setChecked(false);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        CheckBox A = (CheckBox) findViewById(R.id.cargar_mascotas);
                                        A.setChecked(true);
                                    }
                                })
                                .show();
                    }
                }
                break;
            case R.id.cargar_mascotas_l:
                if (checked) {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea marcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox a = (CheckBox) findViewById(R.id.cargar_mascotas);
                                    a.setChecked(true);
                                    proc = getResources().getString(R.string.cargar_mascotas);
                                    new act_proc_l().execute();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.cargar_mascotas_l);
                                    A.setChecked(false);
                                }
                            })
                            .show();
                }
                else
                {
                    new AlertDialog.Builder(this)
                            .setTitle("Confirmación")
                            .setMessage("¿Seguro desea desmarcar este proceso?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    proc = getResources().getString(R.string.cargar_mascotas);
                                    new desact_proc_l().execute();
                                    CheckBox A = (CheckBox) findViewById(R.id.cargar_mascotas);
                                    CheckBox B = (CheckBox) findViewById(R.id.cargar_mascotas_l);
                                    A.setChecked(false);
                                    B.setChecked(false);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    CheckBox A = (CheckBox)findViewById(R.id.cargar_mascotas);
                                    CheckBox B = (CheckBox) findViewById(R.id.cargar_mascotas_l);
                                    A.setChecked(true);
                                    B.setChecked(true);
                                }
                            })
                            .show();
                }
                break;
        }
    }

    public class act_proc_l extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Actualizando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            int success;
            try {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                param.add(new BasicNameValuePair("proc", proc));
                param.add(new BasicNameValuePair("usuario", usuario));
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL2, "POST", param);
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {

                    //Servicio y eso.
                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            pDialog.dismiss();
            if (message != null) {
                Toast.makeText(Panel1.this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public class cargar_comida extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Cargando lista...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            int success;
            try {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL7, "POST", param);
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    com = json.getString(TAG_COM);
                    com2 = json.getString(TAG_COM2);
                    com3 = json.getString(TAG_COM3);
                    com4 = json.getString(TAG_COM4);
                    com5 = json.getString(TAG_COM5);
                    com6 = json.getString(TAG_COM6);
                    com7 = json.getString(TAG_COM7);
                    com8 = json.getString(TAG_COM8);
                    return json.getString(TAG_MESSAGE);

                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            if (message != null) {
                CheckBox c26 = (CheckBox) findViewById(R.id.desa2);
                CheckBox c27 = (CheckBox) findViewById(R.id.desa_l2);
                CheckBox c28 = (CheckBox) findViewById(R.id.alm2);
                CheckBox c29 = (CheckBox) findViewById(R.id.alm_l2);
                CheckBox c30 = (CheckBox) findViewById(R.id.cena2);
                CheckBox c31 = (CheckBox) findViewById(R.id.cena_l2);
                CheckBox c32 = (CheckBox) findViewById(R.id.ref2);
                CheckBox c33 = (CheckBox) findViewById(R.id.ref_l2);
                CheckBox c34 = (CheckBox) findViewById(R.id.bebidas2);
                CheckBox c35 = (CheckBox) findViewById(R.id.bebidas_l2);
                CheckBox c36 = (CheckBox) findViewById(R.id.bebidas_alc2);
                CheckBox c37 = (CheckBox) findViewById(R.id.bebidas_alc_l2);
                CheckBox c38 = (CheckBox) findViewById(R.id.vasos2);
                CheckBox c39 = (CheckBox) findViewById(R.id.vasos_l2);
                CheckBox c40 = (CheckBox) findViewById(R.id.cubiertos2);
                CheckBox c41 = (CheckBox) findViewById(R.id.cubiertos_l2);
                c26.setChecked(false);
                c27.setChecked(false);
                c28.setChecked(false);
                c29.setChecked(false);
                c30.setChecked(false);
                c31.setChecked(false);
                c32.setChecked(false);
                c33.setChecked(false);
                c34.setChecked(false);
                c35.setChecked(false);
                c36.setChecked(false);
                c37.setChecked(false);
                c38.setChecked(false);
                c39.setChecked(false);
                c40.setChecked(false);
                c41.setChecked(false);

                pDialog.dismiss();
                if (com.equals("1")) {
                    c26.setChecked(true);
                    c26.setClickable(false);
                }
                if (com.equals("2")) {
                    c26.setChecked(true);
                    c27.setChecked(true);
                    c26.setClickable(false);
                    c27.setClickable(false);
                }
                if (com2.equals("1")) {
                    c28.setChecked(true);
                    c28.setClickable(false);
                }
                if (com2.equals("2")) {
                    c28.setChecked(true);
                    c28.setClickable(false);
                    c29.setChecked(true);
                    c29.setClickable(false);
                }
                if (com3.equals("1")) {
                    c30.setChecked(true);
                    c30.setClickable(false);
                }
                if (com3.equals("2")) {
                    c30.setChecked(true);
                    c30.setClickable(false);
                    c31.setChecked(true);
                    c31.setClickable(false);
                }
                if (com4.equals("1")) {
                    c32.setChecked(true);
                    c32.setClickable(false);
                }
                if (com4.equals("2")) {
                    c32.setChecked(true);
                    c32.setClickable(false);
                    c33.setChecked(true);
                    c33.setClickable(false);
                }
                if (com5.equals("1")) {
                    c34.setChecked(true);
                    c34.setClickable(false);
                }
                if (com5.equals("2")) {
                    c34.setChecked(true);
                    c34.setClickable(false);
                    c35.setChecked(true);
                    c35.setClickable(false);
                }
                if (com6.equals("1")) {
                    c36.setChecked(true);
                    c36.setClickable(false);
                }
                if (com6.equals("2")) {
                    c36.setChecked(true);
                    c36.setClickable(false);
                    c37.setChecked(true);
                    c37.setClickable(false);
                }
                if (com7.equals("1")) {
                    c38.setChecked(true);
                    c38.setClickable(false);
                }
                if (com7.equals("2")) {
                    c38.setChecked(true);
                    c38.setClickable(false);
                    c39.setChecked(true);
                    c39.setClickable(false);
                }
                if (com8.equals("1")) {
                    c40.setChecked(true);
                    c40.setClickable(false);
                }
                if (com8.equals("2")) {
                    c40.setChecked(true);
                    c40.setClickable(false);
                    c41.setChecked(true);
                    c41.setClickable(false);
                }
                Toast.makeText(Panel1.this, message, Toast.LENGTH_LONG).show();
            } else {
                pDialog.dismiss();
                Toast.makeText(Panel1.this, "El telefono no posee conexión a internet", Toast.LENGTH_LONG).show();
            }
        }
    }

    public class cargar_mantenimiento extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Cargando lista...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            int success;
            try {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL6, "POST", param);
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    mant = json.getString(TAG_MANT);
                    mant2 = json.getString(TAG_MANT2);
                    mant3 = json.getString(TAG_MANT3);
                    mant4 = json.getString(TAG_MANT4);
                    mant5 = json.getString(TAG_MANT5);
                    return json.getString(TAG_MESSAGE);
                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            if (message != null) {
                CheckBox c16 = (CheckBox) findViewById(R.id.carga_c2);
                CheckBox c17 = (CheckBox) findViewById(R.id.carga_c_l2);
                CheckBox c18 = (CheckBox) findViewById(R.id.tren_a2);
                CheckBox c19 = (CheckBox) findViewById(R.id.tren_a_l2);
                CheckBox c20 = (CheckBox) findViewById(R.id.aleron2);
                CheckBox c21 = (CheckBox) findViewById(R.id.aleron_l2);
                CheckBox c22 = (CheckBox) findViewById(R.id.kits_e2);
                CheckBox c23 = (CheckBox) findViewById(R.id.kits_e_l2);
                CheckBox c24 = (CheckBox) findViewById(R.id.mascaras_o2);
                CheckBox c25 = (CheckBox) findViewById(R.id.mascaras_o_l2);

                c16.setChecked(false);
                c17.setChecked(false);
                c18.setChecked(false);
                c19.setChecked(false);
                c20.setChecked(false);
                c21.setChecked(false);
                c22.setChecked(false);
                c23.setChecked(false);
                c24.setChecked(false);
                c25.setChecked(false);

                pDialog.dismiss();
                if (mant.equals("1")) {
                    c16.setChecked(true);
                    c16.setClickable(false);
                }
                if (mant.equals("2")) {
                    c16.setChecked(true);
                    c17.setChecked(true);
                    c16.setClickable(false);
                    c17.setClickable(false);
                }
                if (mant2.equals("1")) {
                    c18.setChecked(true);
                    c18.setClickable(false);
                }
                if (mant2.equals("2")) {
                    c18.setChecked(true);
                    c18.setClickable(false);
                    c19.setChecked(true);
                    c19.setClickable(false);
                }
                if (mant3.equals("1")) {
                    c20.setChecked(true);
                    c20.setClickable(false);
                }
                if (mant3.equals("2")) {
                    c20.setChecked(true);
                    c20.setClickable(false);
                    c21.setChecked(true);
                    c21.setClickable(false);
                }
                if (mant4.equals("1")) {
                    c22.setChecked(true);
                    c22.setClickable(false);
                }
                if (mant4.equals("2")) {
                    c22.setChecked(true);
                    c22.setClickable(false);
                    c23.setChecked(true);
                    c23.setClickable(false);
                }
                if (mant5.equals("1")) {
                    c24.setChecked(true);
                    c24.setClickable(false);
                }
                if (mant5.equals("2")) {
                    c24.setChecked(true);
                    c24.setClickable(false);
                    c25.setChecked(true);
                    c25.setClickable(false);
                }
                Toast.makeText(Panel1.this, message, Toast.LENGTH_SHORT).show();
            } else {
                pDialog.dismiss();
                Toast.makeText(Panel1.this, "El telefono no posee conexión a internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class cargar_acomodadores extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Cargando lista...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            int success;
            try {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL5, "POST", param);
                if (json == null)
                    return null;
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    acom = json.getString(TAG_ACOM);
                    acom2 = json.getString(TAG_ACOM2);
                    acom3 = json.getString(TAG_ACOM3);
                    acom4 = json.getString(TAG_ACOM4);
                    return json.getString(TAG_MESSAGE);
                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            if (message != null) {
                CheckBox c8 = (CheckBox) findViewById(R.id.verif_asientos2);
                CheckBox c9 = (CheckBox) findViewById(R.id.verif_asientos_l2);
                CheckBox c10 = (CheckBox) findViewById(R.id.recep_bol2);
                CheckBox c11 = (CheckBox) findViewById(R.id.recep_bol_l2);
                CheckBox c12 = (CheckBox) findViewById(R.id.e_class2);
                CheckBox c13 = (CheckBox) findViewById(R.id.e_class_l2);
                CheckBox c14 = (CheckBox) findViewById(R.id.p_class2);
                CheckBox c15 = (CheckBox) findViewById(R.id.p_class_l2);

                c8.setChecked(false);
                c9.setChecked(false);
                c10.setChecked(false);
                c11.setChecked(false);
                c12.setChecked(false);
                c13.setChecked(false);
                c14.setChecked(false);
                c15.setChecked(false);

                pDialog.dismiss();
                if (acom.equals("1")) {
                    c8.setChecked(true);
                    c8.setClickable(false);
                }
                if (acom.equals("2")) {
                    c8.setChecked(true);
                    c9.setChecked(true);
                    c8.setClickable(false);
                    c9.setClickable(false);
                }
                if (acom2.equals("1")) {
                    c10.setChecked(true);
                    c10.setClickable(false);
                }
                if (acom2.equals("2")) {
                    c10.setChecked(true);
                    c10.setClickable(false);
                    c11.setChecked(true);
                    c11.setClickable(false);
                }
                if (acom3.equals("1")) {
                    c12.setChecked(true);
                    c12.setClickable(false);
                }
                if (acom3.equals("2")) {
                    c12.setChecked(true);
                    c12.setClickable(false);
                    c13.setChecked(true);
                    c13.setClickable(false);
                }
                if (acom4.equals("1")) {
                    c14.setChecked(true);
                    c14.setClickable(false);
                }
                if (acom4.equals("2")) {
                    c14.setChecked(true);
                    c14.setClickable(false);
                    c15.setChecked(true);
                    c15.setClickable(false);
                }
                Toast.makeText(Panel1.this, message, Toast.LENGTH_LONG).show();
            } else {
                pDialog.dismiss();
                Toast.makeText(Panel1.this, "El telefono no posee conexión a internet", Toast.LENGTH_LONG).show();
            }
        }
    }

    public class cargar_maletero extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Cargando lista...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            int success;
            try {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL3, "POST", param);
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    malet = json.getString(TAG_P);
                    malet2 = json.getString(TAG_P2);
                    malet3 = json.getString(TAG_P3);
                    malet4 = json.getString(TAG_P4);
                    return json.getString(TAG_MESSAGE);
                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            if (message != null) {
                CheckBox c = (CheckBox) findViewById(R.id.transporte_m2);
                CheckBox c1 = (CheckBox) findViewById(R.id.transporte_m_l2);
                CheckBox c2 = (CheckBox) findViewById(R.id.cargar_m2);
                CheckBox c3 = (CheckBox) findViewById(R.id.cargar_m_l2);
                CheckBox c4 = (CheckBox) findViewById(R.id.cargar_art_fra2);
                CheckBox c5 = (CheckBox) findViewById(R.id.cargar_art_fra_l2);
                CheckBox c6 = (CheckBox) findViewById(R.id.cargar_mascotas2);
                CheckBox c7 = (CheckBox) findViewById(R.id.cargar_mascotas_l2);
                c.setChecked(false);
                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                c5.setChecked(false);
                c6.setChecked(false);
                c7.setChecked(false);
                pDialog.dismiss();
                if (malet.equals("1")) {
                    c.setChecked(true);
                    c.setClickable(false);
                }
                if (malet.equals("2")) {
                    c.setChecked(true);
                    c1.setChecked(true);
                    c.setClickable(false);
                    c1.setClickable(false);
                }
                if (malet2.equals("1")) {
                    c2.setChecked(true);
                    c2.setClickable(false);
                }
                if (malet2.equals("2")) {
                    c2.setChecked(true);
                    c2.setClickable(false);
                    c3.setChecked(true);
                    c3.setClickable(false);
                }
                if (malet3.equals("1")) {
                    c4.setChecked(true);
                    c4.setClickable(false);
                }
                if (malet3.equals("2")) {
                    c4.setChecked(true);
                    c4.setClickable(false);
                    c5.setChecked(true);
                    c5.setClickable(false);
                }
                if (malet4.equals("1")) {
                    c6.setChecked(true);
                    c6.setClickable(false);
                }
                if (malet4.equals("2")) {
                    c6.setChecked(true);
                    c6.setClickable(false);
                    c7.setChecked(true);
                    c7.setClickable(false);
                }
                Toast.makeText(Panel1.this, message, Toast.LENGTH_SHORT).show();
            } else {
                pDialog.dismiss();
                Toast.makeText(Panel1.this, "El telefono no posee conexión a internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class cargar_lista extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Cargando lista...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            int success;
            try {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL3, "POST", param);
                if (json == null)
                    return null;
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    malet = json.getString(TAG_P);
                    malet2 = json.getString(TAG_P2);
                    malet3 = json.getString(TAG_P3);
                    malet4 = json.getString(TAG_P4);
                    return json.getString(TAG_MESSAGE);
                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            if (message != null) {
                CheckBox c = (CheckBox) findViewById(R.id.transporte_m);
                CheckBox c1 = (CheckBox) findViewById(R.id.transporte_m_l);
                CheckBox c2 = (CheckBox) findViewById(R.id.cargar_m);
                CheckBox c3 = (CheckBox) findViewById(R.id.cargar_m_l);
                CheckBox c4 = (CheckBox) findViewById(R.id.cargar_art_fra);
                CheckBox c5 = (CheckBox) findViewById(R.id.cargar_art_fra_l);
                CheckBox c6 = (CheckBox) findViewById(R.id.cargar_mascotas);
                CheckBox c7 = (CheckBox) findViewById(R.id.cargar_mascotas_l);
                pDialog.dismiss();
                if (malet.equals("1")) {
                    c.setChecked(true);
                }
                if (malet.equals("2")) {
                    c.setChecked(true);
                    c1.setChecked(true);
                }
                if (malet2.equals("1")) {
                    c2.setChecked(true);
                }
                if (malet2.equals("2")) {
                    c2.setChecked(true);
                    c3.setChecked(true);
                }
                if (malet3.equals("1")) {
                    c4.setChecked(true);
                }
                if (malet3.equals("2")) {
                    c4.setChecked(true);
                    c5.setChecked(true);
                }
                if (malet4.equals("1")) {
                    c6.setChecked(true);
                }
                if (malet4.equals("2")) {
                    c6.setChecked(true);
                    c7.setChecked(true);
                }
                Toast.makeText(Panel1.this, message, Toast.LENGTH_SHORT).show();
            } else {
                pDialog.dismiss();
                Toast.makeText(Panel1.this, "El telefono no posee conexión a internet", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public class  desact_proc extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Cambiando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            int success;
            try {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                param.add(new BasicNameValuePair("proc", proc));
                param.add(new BasicNameValuePair("usuario", usuario));
                JSONObject json = jsonParser.makeHttpRequest(
                        DESACT_URL, "POST", param);
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    //Servicio y eso.
                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            pDialog.dismiss();
            if (message != null) {
                Toast.makeText(Panel1.this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
    public class  desact_proc_l extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Cambiando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            int success;
            try {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                param.add(new BasicNameValuePair("proc", proc));
                param.add(new BasicNameValuePair("usuario", usuario));
                JSONObject json = jsonParser.makeHttpRequest(
                        DESACT_URL2, "POST", param);
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    //Servicio y eso.
                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            pDialog.dismiss();
            if (message != null) {
                Toast.makeText(Panel1.this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
    public class act_proc extends AsyncTask<String, String, String> {


        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Actualizando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            int success;
            try {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                param.add(new BasicNameValuePair("proc", proc));
                param.add(new BasicNameValuePair("usuario", usuario));
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", param);
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    //Servicio y eso.
                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            pDialog.dismiss();
            if (message != null) {
                Toast.makeText(Panel1.this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
    public class cargar_encargados extends AsyncTask<String, String, String> {


        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Panel1.this);
            pDialog.setMessage("Actualizando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            int success;
            JSONArray personal;
            try {
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL8, "GET", param);
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {

                    personal = json.getJSONArray(TAG_PERSONAL);

                    for (int i = 0; i < personal.length(); i++) {

                        JSONObject o = personal.getJSONObject(i);

                        String nombre = o.getString(TAG_NOMBRE);
                        String apellido = o.getString(TAG_APELLIDO);
                        String area1 = o.getString(TAG_AREA);

                        if(area1.equals("1"))
                        {
                            area="Maletero";
                        }
                        if(area1.equals("2"))
                        {
                            area="Mantenimiento";
                        }
                        if(area1.equals("3"))
                        {
                            area="Acomodadores";
                        }
                        if(area1.equals("4"))
                        {
                            area="Comida";
                        }
                        Log.v("nombre", nombre);
                        Log.v("apellido",apellido);
                        Log.v("area",area);
                        HashMap map = new HashMap();
                        map.put("PN", nombre);
                        map.put("PA", apellido);
                        map.put("TA", area);

                        Lista.add(map);

                    }
                    return json.getString(TAG_MESSAGE);

                } else {
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            if (message != null) {

                pDialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lv = (ListView) findViewById(R.id.ListaEncargados);
                        ListAdapter adapter = new SimpleAdapter(Panel1.this,
                                Lista,
                                R.layout.lista_encargados,
                                new String[]{"PN", "PA", "TA"},
                                new int[]{R.id.nombre, R.id.apellido, R.id.area
                                });
                        lv.setAdapter(adapter);
                    }
                });
            }
        }
    }
}
