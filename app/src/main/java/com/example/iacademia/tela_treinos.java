package com.example.iacademia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class tela_treinos extends AppCompatActivity {

    EditText etNomeExercicio, etGrupoMuscular, etSeries, etRepeticoes, etPeso;
    Button btSalvarTreino, btRetornar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_treinos);

        etNomeExercicio = findViewById(R.id.etNomeExercicio);
        etGrupoMuscular = findViewById(R.id.etGrupoMuscular);
        etSeries = findViewById(R.id.etSeries);
        etRepeticoes = findViewById(R.id.etRepeticoes);
        etPeso = findViewById(R.id.etPeso);
        btSalvarTreino = findViewById(R.id.btnSalvar);
        btRetornar = findViewById(R.id.btnVoltar);

        btSalvarTreino.setOnClickListener(view -> new EnviaJsonPost().execute());
        btRetornar.setOnClickListener(view -> finish());
    }

    private boolean validaCampos() {
        return !etNomeExercicio.getText().toString().isEmpty() &&
                !etGrupoMuscular.getText().toString().isEmpty() &&
                !etSeries.getText().toString().isEmpty() &&
                !etRepeticoes.getText().toString().isEmpty() &&
                !etPeso.getText().toString().isEmpty();
    }

    class EnviaJsonPost extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                if (!validaCampos()) {
                    return "Por favor, preencha todos os campos.";
                }

                String url = "http://192.168.1.102/academia/cadastra_treino.php";
                JSONObject jsonValores = new JSONObject();
                jsonValores.put("id_usuario", 1);
                jsonValores.put("nome_exercicio", etNomeExercicio.getText().toString());
                jsonValores.put("grupo_muscular", etGrupoMuscular.getText().toString());
                jsonValores.put("series", Integer.parseInt(etSeries.getText().toString()));
                jsonValores.put("repeticoes", Integer.parseInt(etRepeticoes.getText().toString()));
                jsonValores.put("peso", Double.parseDouble(etPeso.getText().toString()));

                conexaouniversal mandar = new conexaouniversal();
                return mandar.postJSONObject(url, jsonValores);

            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao salvar treino.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null && !result.equals("Erro ao salvar treino.")) {
                Toast.makeText(tela_treinos.this, "Treino salvo com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(tela_treinos.this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
