package com.example.practica2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.practica2.WebServices.Asynchtask;
import com.example.practica2.WebServices.WebService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class actLogin extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_act_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btLogin(View v){
        TextInputLayout ilUsuario = findViewById(R.id.tilUsuario);
        TextInputEditText edtUsr = findViewById(R.id.txtUsuario);
        if(edtUsr.getText().toString().isEmpty()){
            ilUsuario.setError("Nombre requerido");
        }
        else
        {
            ilUsuario.setErrorEnabled(false);
        }

        TextInputLayout ilPass = findViewById(R.id.tilClave);
        TextInputEditText edtPass = findViewById(R.id.txtClave);
        if(edtUsr.getText().toString().isEmpty()){
                ilPass.setError("Clave requerida");
        }
        else
        {
            ilPass.setErrorEnabled(false);
        }

        //llamo a la API RESTFul

        String url ="https://revistas.uteq.edu.ec/ws/login.php?usr=" + edtUsr.getText().toString() + "&pass=" + edtPass.getText().toString();

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url, datos, actLogin.this, actLogin.this);

        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}