package com.example.practica2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practica2.WebServices.Asynchtask;
import com.example.practica2.WebServices.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class actListbancos extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_act_listbancos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new
                WebService("https://api-uat.kushkipagos.com/transfer/v1/bankList",
                datos, actListbancos.this, actListbancos.this);
        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtBancos = (TextView)findViewById(R.id.txtListaBancos);
        String lstBancos="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco= JSONlista.getJSONObject(i);
            lstBancos = lstBancos + "\n" +
                    banco.getString("name").toString();
        }
        txtBancos.setText("Respuesta WS Lista de Bancos" + lstBancos);
    }
}