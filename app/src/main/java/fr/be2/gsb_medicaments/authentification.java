package fr.be2.gsb_medicaments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.security.SecureRandom;

public class authentification extends AppCompatActivity {
private EditText codeV,mykey;
private Button btnValiderCodeV, btnValiderCle;
private LinearLayout layoutCle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        codeV = findViewById(R.id.codeV);
        mykey = findViewById(R.id.myKey);
        layoutCle = findViewById(R.id.layoutCle);
        layoutCle.setVisibility(View.INVISIBLE);

    }
    public void AfficheLayout(View v){
        layoutCle.setVisibility(View.VISIBLE);
        String myRondonKey = genererChaineAleatoire(5);
        Log.d("APPLI", "mykey"+ myRondonKey);

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
}