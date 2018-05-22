package com.jikansoftware.almacenamientoarchivomemoriainterna;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private Button btnGrabar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText);
        String [] archivos = fileList();

        btnGrabar = findViewById(R.id.button);
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabar();
            }
        });

        if(verificarExiste(archivos, "usuarios.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("usuarios.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null){
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                et1.setText(todo);
            }catch (IOException e){

            }
        }
    }

    private boolean verificarExiste(String[] archivos, String archivoBuscado) {
        for(int f = 0; f < archivos.length; f++){
            if(archivoBuscado.equals(archivos[f])){
                return true;
            }
        }
        return false;
    }

    public void grabar(){
        try{
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("usuarios.txt", Activity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();
        }catch (IOException e){

        }
        Toast t = Toast.makeText(this, "El archivo fue guardado", Toast.LENGTH_SHORT);
        t.show();
        finish();

    }


}
