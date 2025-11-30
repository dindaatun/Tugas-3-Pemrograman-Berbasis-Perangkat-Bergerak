package com.example.tugas3_crypto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CryptoAdapter adapter;
    private List<CryptoModel> cryptoList;
    private Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi komponen
        recyclerView = findViewById(R.id.recyclerView);
        btnRefresh = findViewById(R.id.btnRefresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cryptoList = new ArrayList<>();
        adapter = new CryptoAdapter(cryptoList);
        recyclerView.setAdapter(adapter);

        // Load data pertama kali
        getDataCrypto();

        // Aksi tombol Refresh
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataCrypto();
                Toast.makeText(MainActivity.this, "Data Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataCrypto() {
        // Alamat API sesuai soal
        String url = "https://api.coinlore.net/api/tickers/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Bersihkan list lama agar tidak duplikat saat refresh
                            cryptoList.clear();

                            // API Coinlore membungkus data dalam object "data"
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);

                                // Ambil field yang diminta soal: rank, name, symbol, price_usd
                                String rank = data.getString("rank");
                                String name = data.getString("name");
                                String symbol = data.getString("symbol");
                                String price = data.getString("price_usd");

                                // Masukkan ke model
                                CryptoModel model = new CryptoModel(rank, name, symbol, price);
                                cryptoList.add(model);
                            }

                            // Beritahu adapter bahwa data berubah
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Gagal mengambil data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Masukkan request ke antrian
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}