package com.example.iacademia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

public class cadNovoUsuario extends AppCompatActivity {
    Button btretorna, btregistra;
    EditText edtlogin, edtsenha, edtendereco, edtfone, edtcidade;
    usuario usrtemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_novo_usuario);
        btretorna = findViewById(R.id.btregretornar);
        btregistra = findViewById(R.id.btregregistrar);
        edtlogin = findViewById(R.id.edtreglogin);
        edtsenha = findViewById(R.id.edtregsenha);
        edtendereco = findViewById(R.id.edtregendereco);
        edtfone = findViewById(R.id.edtregfone);
        edtcidade = findViewById(R.id.edtregcidade);

        btregistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usrtemp = new usuario(
                        edtlogin.getText().toString(),
                        edtsenha.getText().toString(),
                        edtendereco.getText().toString(),
                        edtfone.getText().toString(),
                        edtcidade.getText().toString()
                );
                new Enviajsonpost().execute();
            }
        });

        btretorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    class Enviajsonpost extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... arg0) {
            try {
                String url = "http://192.167.253.165/academia/cadastra_usuario.php";
                JSONObject jsonValores = new JSONObject();
                jsonValores.put("nome", usrtemp.getNome());
                jsonValores.put("senha", usrtemp.getSenha());
                jsonValores.put("endereco", usrtemp.getEndereco());
                jsonValores.put("fone", usrtemp.getFone());
                jsonValores.put("cidade", usrtemp.getCidade());

                conexaouniversal mandar = new conexaouniversal();
                String mensagem = mandar.postJSONObject(url, jsonValores);
                Log.d("RespostaServidor", "Resposta: " + mensagem);
                return mensagem != null ? mensagem : "Erro ao enviar cadastro.";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao enviar cadastro.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("CadastroResultado", "Resultado: " + result);
            if (result != null && result.contains("sucesso")) {
                Toast.makeText(cadNovoUsuario.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(cadNovoUsuario.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }, 3000);
            } else {
                Toast.makeText(cadNovoUsuario.this, "Erro ao realizar cadastro. Detalhes: " + result, Toast.LENGTH_LONG).show();
            }
        }
    }
}
