package com.example.alfinadisurya_uas_android_2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private TextView idgua;
    private TextView namagua;
    private TextView asalgua;
    private TextView kamargua;
    private RequestQueue nQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nQueue = Volley.newRequestQueue(this);
        input = findViewById(R.id.et1);
        idgua = findViewById(R.id.tvid);
        namagua = findViewById(R.id.tvnama);
        asalgua = findViewById(R.id.tvasal);
        kamargua = findViewById(R.id.tvkamar);
        Button go = findViewById(R.id.btngo);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil();
            }
        });
    }
    private void tampil(){
        String url ="http://192.168.5.31/WebServices/JSON/android/mahasantri_data.json";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject mahasantri = response.getJSONObject(1);

                    String id = mahasantri.getString("id");
                    String nama = mahasantri.getString("nama");
                    String asal = mahasantri.getString("asal_Daerah");
                    String kamar = mahasantri.getString("kamar");

                    idgua.append(id);
                    namagua.append(nama);
                    asalgua.append(asal);
                    kamargua.append(kamar);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
            }
        });
        nQueue.add(request);
    }
}
