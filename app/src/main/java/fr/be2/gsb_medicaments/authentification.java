package fr.be2.gsb_medicaments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.security.SecureRandom;

public class authentification extends AppCompatActivity {
private EditText codeV,mykey;
private Button btnValiderCodeV, btnValiderCle;
private LinearLayout layoutCle;
String myRandomKey;
private static final String PREF_NAME = "UserPrefs";
private static final String KEY_USER_STATUS = "userStatus";
    private static final String SECURETOKEN = "BethElicheva5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        codeV = findViewById(R.id.codeV);
        mykey = findViewById(R.id.myKey);
        layoutCle = findViewById(R.id.layoutCle);
        layoutCle.setVisibility(View.INVISIBLE);
        setUserStatus("NON CONNECTE");
    }
    public void AfficheLayout(View v){

        layoutCle.setVisibility(View.VISIBLE);
        myRandomKey = genererChaineAleatoire(5);
        Log.d("APPLI", "mykey"+ myRandomKey);
        String codeVis = codeV.getText().toString();

        // Vous pouvez maintenant utiliser la méthode sendKeyByEmail
        // avec le codeV, secureKey, et token comme paramètres
        String secureKey = "votre_secureKey";
        String token = SECURETOKEN;
        SendKeyTask sendKeyTask = new SendKeyTask(getApplicationContext());
        sendKeyTask.execute(codeVis, secureKey, token);

    }
    private String genererChaineAleatoire(int longueur) {
        String caracteresPermis = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder chaineAleatoire = new StringBuilder();

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < longueur; i++) {
            int index = random.nextInt(caracteresPermis.length());
            char caractereAleatoire = caracteresPermis.charAt(index);
            chaineAleatoire.append(caractereAleatoire);
        }

        return chaineAleatoire.toString();
    }
    public void AfficheLayout2(View v){
        String key = mykey.getText().toString().trim();
        if (key.equals(myRandomKey)) {
            showToast("Les chaînes A et B sont égales.");
            setUserStatus("authentification=OK");
            Intent authIntent = new Intent(this, MainActivity.class);
            startActivity(authIntent);
            finish();
        } else {
            showToast("Les chaînes A et B ne sont pas égales.");
            setUserStatus("authentification=KO");
        }

    }
    private void showToast(String montexte){

        Toast.makeText(this, montexte, Toast.LENGTH_LONG).show();


    }
    private void setUserStatus(String status) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_STATUS, status);
        editor.apply();
    }
}