package app.calcounterapplication.com.turnsapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import app.calcounterapplication.com.turnsapp.Fragments.my_dragalble_fragment;
import app.calcounterapplication.com.turnsapp.Model.Game;
import app.calcounterapplication.com.turnsapp.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



     //Getting the information from the intent
        FragmentManager fm = getSupportFragmentManager();
        my_dragalble_fragment my_dragalble_fragment1 =(my_dragalble_fragment) getSupportFragmentManager().findFragmentById(R.id.mydragfrag);

        Intent intent=getIntent();
        Game game=(Game) intent.getParcelableExtra("Game");
        getIntent().putExtra("Game",game);
//        my_dragalble_fragment1.setGameDetails(gname,num);
//begin the trans

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.TheGameFrame, new my_dragalble_fragment());
        ft.commit();







    }

}
