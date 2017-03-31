package com.example.mauro.test1;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Encargados extends Fragment {

    public Encargados() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_encargados,container,false);
       /* SharedPreferences sp = this.getActivity().getSharedPreferences(Pref, Context.MODE_PRIVATE);
        vuelo = (sp.getString(Vuelo, ""));
        Lista = new ArrayList<HashMap<String, String>>();
        lv = (ListView) rootView.findViewById(R.id.ListaEncargados);
        new cargar_encargados().execute();*/

        return rootView;
    }



   /* public class cargar_encargados extends AsyncTask<String, String, String> {


        protected void onPreExecute() {
            super.onPreExecute();
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
                        String area = o.getString(TAG_AREA);
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


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListAdapter adapter = new SimpleAdapter(getActivity(),
                                Lista,
                                R.layout.lista_encargados,
                                new String[]{"PA", "PN", "TA"},
                                new int[]{R.id.nombre, R.id.apellido, R.id.area
                                });
                        lv.setAdapter(adapter);
                    }
                });
            }
        }
    }
*/

}
