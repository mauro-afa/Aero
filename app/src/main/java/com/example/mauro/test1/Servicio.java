package com.example.mauro.test1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mfigueroa on 07/07/2015.
 */
public class Servicio extends Service {
    String vuelo, area, usuario;
    public static final String Pref = "Prefs" ;
    public static final String Vuelo = "Vuelo";
    public static final String Usuario = "Usuario";
    public static final String Area = "Area";
    private static final String LOGIN_URL = "http://aero.esy.es/notificaciones.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CAMPO2 = "campo2";
    private static final String TAG_CAMPO3 = "campo3";
    private static final String TAG_CAMPO4 = "campo4";
    private static final String TAG_CAMPO5 = "campo5";
    private static final String TAG_CAMPO6 = "campo6";
    private static final String TAG_CAMPO7 = "campo7";
    private static final String TAG_CAMPO8 = "campo8";
    private static final String TAG_CAMPO9 = "campo9";
    private static final String TAG_CAMPO10 = "campo10";
    private static final String TAG_CAMPO11 = "campo11";
    private static final String TAG_CAMPO12 = "campo12";
    private static final String TAG_CAMPO13 = "campo13";
    private static final String TAG_CAMPO14 = "campo14";
    private static final String TAG_CAMPO15 = "campo15";
    private static final String TAG_CAMPO16 = "campo16";
    private static final String TAG_CAMPO17 = "campo17";
    private static final String TAG_CAMPO18 = "campo18";
    private static final String TAG_CAMPO19 = "campo19";
    private static final String TAG_CAMPO20 = "campo20";
    private static final String TAG_CAMPO21 = "campo21";
    private static final String TAG_CAMPO22 = "campo22";

    private static final String TAG_VALOR2 = "valor2";
    private static final String TAG_VALOR3 = "valor3";
    private static final String TAG_VALOR4 = "valor4";
    private static final String TAG_VALOR5 = "valor5";
    private static final String TAG_VALOR6 = "valor6";
    private static final String TAG_VALOR7 = "valor7";
    private static final String TAG_VALOR8 = "valor8";
    private static final String TAG_VALOR9 = "valor9";
    private static final String TAG_VALOR10 = "valor10";
    private static final String TAG_VALOR11 = "valor11";
    private static final String TAG_VALOR12 = "valor12";
    private static final String TAG_VALOR13 = "valor13";
    private static final String TAG_VALOR14 = "valor14";
    private static final String TAG_VALOR15 = "valor15";
    private static final String TAG_VALOR16 = "valor16";
    private static final String TAG_VALOR17 = "valor17";
    private static final String TAG_VALOR18 = "valor18";
    private static final String TAG_VALOR19 = "valor19";
    private static final String TAG_VALOR20 = "valor20";
    private static final String TAG_VALOR21 = "valor21";
    private static final String TAG_VALOR22 = "valor22";


    JSONParser jsonParser = new JSONParser();


    public void onCreate() {
        Log.v("servicio", "inicado");

    }

    class verificar extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            try{
                SharedPreferences sp = getSharedPreferences(Pref, Context.MODE_PRIVATE);
                vuelo = (sp.getString(Vuelo, ""));
                area = (sp.getString(Area, ""));
                usuario = (sp.getString(Usuario,""));

                int success;
                String v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22;
                String c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22;
                List param = new ArrayList();
                param.add(new BasicNameValuePair("vuelo", vuelo));
                param.add(new BasicNameValuePair("area",area));
                param.add(new BasicNameValuePair("usuario",usuario));
                JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                        param);
                if(json == null)
                    return null;
                success = json.getInt(TAG_SUCCESS);

                if(success==1){

                    c2 = json.getString(TAG_CAMPO2);
                    c3 = json.getString(TAG_CAMPO3);
                    c4 = json.getString(TAG_CAMPO4);
                    c5 = json.getString(TAG_CAMPO5);
                    c6 = json.getString(TAG_CAMPO6);
                    c7 = json.getString(TAG_CAMPO7);
                    c8 = json.getString(TAG_CAMPO8);
                    c9 = json.getString(TAG_CAMPO9);
                    c10 = json.getString(TAG_CAMPO10);
                    c11 = json.getString(TAG_CAMPO11);
                    c12 = json.getString(TAG_CAMPO12);
                    c13 = json.getString(TAG_CAMPO13);
                    c14 = json.getString(TAG_CAMPO14);
                    c15 = json.getString(TAG_CAMPO15);
                    c16 = json.getString(TAG_CAMPO16);
                    c17 = json.getString(TAG_CAMPO17);
                    c18 = json.getString(TAG_CAMPO18);
                    c19 = json.getString(TAG_CAMPO19);
                    c20 = json.getString(TAG_CAMPO20);
                    c21 = json.getString(TAG_CAMPO21);
                    c22 = json.getString(TAG_CAMPO22);

                    v2 = json.getString(TAG_VALOR2);
                    v3 = json.getString(TAG_VALOR3);
                    v4 = json.getString(TAG_VALOR4);
                    v5 = json.getString(TAG_VALOR5);
                    v6 = json.getString(TAG_VALOR6);
                    v7 = json.getString(TAG_VALOR7);
                    v8 = json.getString(TAG_VALOR8);
                    v9 = json.getString(TAG_VALOR9);
                    v10 = json.getString(TAG_VALOR10);
                    v11 = json.getString(TAG_VALOR11);
                    v12 = json.getString(TAG_VALOR12);
                    v13 = json.getString(TAG_VALOR13);
                    v14 = json.getString(TAG_VALOR14);
                    v15 = json.getString(TAG_VALOR15);
                    v16 = json.getString(TAG_VALOR16);
                    v17 = json.getString(TAG_VALOR17);
                    v18 = json.getString(TAG_VALOR18);
                    v19 = json.getString(TAG_VALOR19);
                    v20 = json.getString(TAG_VALOR20);
                    v21 = json.getString(TAG_VALOR21);
                    v22 = json.getString(TAG_VALOR22);

                    if(area.equals("1")){
                        v14 = "0";
                        v15 = "0";
                        v16 = "0";
                        v17 = "0";
                    }
                    if(area.equals("2")){
                        v18 = "0";
                        v19 = "0";
                        v20 = "0";
                        v21 = "0";
                        v22 = "0";
                    }
                    if(area.equals("3")){
                        v2 = "0";
                        v3 = "0";
                        v4 = "0";
                        v5 = "0";
                    }
                    if(area.equals("4")){
                        v6 = "0";
                        v7 = "0";
                        v8 = "0";
                        v9 = "0";
                        v10 = "0";
                        v11 = "0";
                        v12 = "0";
                        v13 = "0";
                    }
                    if(v2.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c2 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(2, nt);
                    }
                    if(v2.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c2 + " esta 'listo'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(2, nt);
                    }
                    if(v3.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c3 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(3, nt);
                    }
                    if(v3.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c3 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(3, nt);
                    }
                    if(v4.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c4 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(4, nt);
                    }
                    if(v4.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c4 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(4, nt);
                    }
                    if(v5.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c5 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(5, nt);
                    }
                    if(v5.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c5 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(5, nt);
                    }
                    if(v6.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c6 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(6, nt);
                    }
                    if(v6.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c6 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(6, nt);
                    }
                    if(v7.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c7 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(7, nt);
                    }
                    if(v7.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c7 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(7, nt);
                    }
                    if(v8.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c8 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(8, nt);
                    }
                    if(v8.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c8 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(8, nt);
                    }
                    if(v9.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c9 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(9, nt);
                    }
                    if(v9.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c9 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(9, nt);
                    }
                    if(v10.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c10 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(10, nt);
                    }
                    if(v10.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c10 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(10, nt);
                    }
                    if(v11.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c10 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(11, nt);
                    }
                    if(v11.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c11 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(11, nt);
                    }
                    if(v12.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c12 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(12, nt);
                    }
                    if(v12.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c12 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(12, nt);
                    }
                    if(v13.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c13 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(13, nt);
                    }
                    if(v13.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c13 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(13, nt);
                    }
                    if(v14.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c14 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(14, nt);
                    }
                    if(v14.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c14 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(14, nt);
                    }
                    if(v15.equals("1")){

                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c15 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(15, nt);
                    }
                    if(v15.equals("2")){
                        Log.v("v15","Entro aqui");
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c15 + " esta 'listo'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(15, nt);
                    }
                    if(v16.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i3 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p3 = PendingIntent.getActivity(getApplicationContext(), 0, i3, 0);
                        Notification nt2 = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c16 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p3)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(16, nt2);
                    }
                    if(v16.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i3 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p3 = PendingIntent.getActivity(getApplicationContext(), 0, i3, 0);
                        Notification nt2 = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c16 + " esta 'listo'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p3)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(16, nt2);
                    }
                    if(v17.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c17 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(17, nt);
                    }
                    if(v17.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c17 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(17, nt);
                    }
                    if(v18.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c18 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(18, nt);
                    }
                    if(v18.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c18 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(18, nt);
                    }
                    if(v19.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c19 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(19, nt);
                    }
                    if(v19.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c19 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(19, nt);
                    }
                    if(v20.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c20 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(20, nt);
                    }
                    if(v20.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c20 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(20, nt);
                    }
                    if(v21.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c21 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(21, nt);
                    }
                    if(v21.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c21 + " esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(21, nt);
                    }
                    if(v22.equals("1")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c22 + " esta 'en proceso'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(22, nt);
                    }
                    if(v22.equals("2")){
                        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Intent i2 = new Intent(getApplicationContext(), splash.class);
                        PendingIntent p2 = PendingIntent.getActivity(getApplicationContext(), 0, i2, 0);
                        Notification nt = new Notification.Builder(getApplicationContext())
                                .setContentTitle("Aero")
                                .setContentText(c22+" esta 'lista'")
                                .setSmallIcon(R.drawable.ic_action)
                                .setContentIntent(p2)
                                .setSound(soundUri)
                                .setAutoCancel(true)
                                .build();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(22, nt);
                    }
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            new verificar().execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return START_NOT_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onDestroy() {
        super.onDestroy();
    }
}
