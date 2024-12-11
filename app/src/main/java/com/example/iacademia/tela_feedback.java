package com.example.iacademia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

public class tela_feedback extends AppCompatActivity {
    EditText etComentario, etNota;
    Button btSalvarFeedback, btRetornar;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_feedback);

        etComentario = findViewById(R.id.etComentario);
        etNota = findViewById(R.id.etNota);
        btSalvarFeedback = findViewById(R.id.btnSalvarFeedback);
        btRetornar = findViewById(R.id.btnVoltarFeedback);
        userId = getIntent().getIntExtra("USER_ID", -1);

        if (userId == -1) {
            Toast.makeText(this, "Erro ao carregar dados do usuário.", Toast.LENGTH_SHORT).show();
            finish();
        }

        btSalvarFeedback.setOnClickListener(view -> new EnviaJsonPost().execute());
        btRetornar.setOnClickListener(view -> finish());
    }

    private boolean validaCampos() {
        return !etComentario.getText().toString().isEmpty() &&
                !etNota.getText().toString().isEmpty();
    }

    class EnviaJsonPost extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                if (!validaCampos()) {
                    return "Por favor, preencha todos os campos.";
                }

                String url = "http://192.167.253.165/academia/cadastra_feedback.php";
                JSONObject jsonValores = new JSONObject();
                jsonValores.put("id_usuario", userId);
                jsonValores.put("comentario", etComentario.getText().toString());
                jsonValores.put("nota", Integer.parseInt(etNota.getText().toString()));

                conexaouniversal mandar = new conexaouniversal();
                String mensagem = mandar.postJSONObject(url, jsonValores);
                Log.d("RespostaServidor", "Resposta: " + mensagem);
                return mensagem != null ? mensagem : "Erro ao salvar feedback.";
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao salvar feedback.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null && !result.equals("Erro ao salvar feedback.")) {
                Toast.makeText(tela_feedback.this, "Feedback salvo com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(tela_feedback.this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}