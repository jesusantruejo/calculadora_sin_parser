package com.example.calculadorajesusaa;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class History extends AppCompatActivity {
    HashMap<String, ArrayList<Object>> historialCopia;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lista = findViewById(R.id.listaView);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(view);
            }

        });
        historialCopia= (HashMap<String, ArrayList<Object>>) getIntent().getSerializableExtra("historial");
        refreshList();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                String value = (String)adapter.getItemAtPosition(position);
                Intent devolucion=new Intent();
                devolucion.putExtra("devolucion",historialCopia.get(value));
                devolucion.putExtra("devolucionVisual",value);
               setResult(2,devolucion);
               finish();
            }
        });
    }
    public void sendMessage(View view) {
        Intent devolucion=new Intent();
        devolucion.putExtra("devolucionVisual","2");
        setResult(1);
        finish();
    }

    public void sendRefresh(View view) {
        refreshList();
    }
    public void refreshList(){
        String output = "";
        ArrayList texto= new ArrayList<String>();
        for (String p : this.historialCopia.keySet()) {
            texto.add(p.toString());
        }
        ArrayAdapter<String> aTexto = new ArrayAdapter<String>(this, R.layout.text, texto);
        lista.setAdapter(aTexto);
        System.out.println("" + output);
    }




}