package app.calcounterapplication.com.turnsapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.Serializable;

import app.calcounterapplication.com.turnsapp.Model.Game;
import app.calcounterapplication.com.turnsapp.R;

public class MainActivity extends AppCompatActivity  {
    private Button GameBut,nextBut;
    private LinearLayout linearLayout,linearbackground;
    private RadioButton dbzBut,rocketBut;
    private MediaPlayer dragonMedia,rocketMedia,localMedia;
private RadioGroup gameGroup;
    private Drawable dbz;
    private EditText numofplayerText;
    private int DBZCODE=1,ROCKETLEAGUECODE=2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        GameBut=(Button)findViewById(R.id.DialogBut);
        linearLayout=(LinearLayout)findViewById(R.id.chooselinear);
        dbzBut=(RadioButton)findViewById(R.id.dbzradi);
        rocketBut=(RadioButton)findViewById(R.id.rocketradio);
        dragonMedia=MediaPlayer.create(getApplicationContext() ,R.raw.dbs);
        rocketMedia=MediaPlayer.create(getApplicationContext() ,R.raw.rocketmed);
        gameGroup=(RadioGroup)findViewById(R.id.RadioGroup);
        linearbackground=(LinearLayout) findViewById(R.id.mylinearback);
        localMedia=dragonMedia;
        nextBut=(Button)findViewById(R.id.navigation_next_button);
        numofplayerText=(EditText)findViewById(R.id.numberofPlayers) ;




        //make the game options visible for the user.

        GameBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        //Changing the song while swich the radio button
gameGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        RadioButton radioButton=(RadioButton) findViewById(i);
        if(radioButton.getText().equals("Dragon Ball")){
            if(localMedia.isPlaying()) {
                localMedia.stop();
            }
            localMedia=dragonMedia;
            linearbackground.setBackgroundResource(R.drawable.dbz);

//Chack if its rocket league and also change the background
        }else if(radioButton.getText().equals("Rocket League")){
            if(localMedia.isPlaying()) {
                localMedia.stop();
            }
            localMedia.stop();
            localMedia=rocketMedia;
            linearbackground.setBackgroundResource(R.drawable.rocket);
        }
        localMedia.start();
    }

});

//Todo: if the the User put null on NumofPlayers Text The app crash;
nextBut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        String number = numofplayerText.getText().toString();
        int number1 = Integer.parseInt(number);
        if (number1 < 9 && number1 > 1) {
            if (dbzBut.isChecked() && !numofplayerText.getText().toString().isEmpty()) {
                Game game = new Game(DBZCODE, dbzBut.getText().toString(), number1);
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                Log.v("hey", "hey");
                intent.putExtra("Game", game);
                startActivity(intent);
            } else if (rocketBut.isChecked() && !number.isEmpty()) {
                Game game = new Game(ROCKETLEAGUECODE, rocketBut.getText().toString(), number1);
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("Game", game);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Choose Game and put players", Toast.LENGTH_LONG).show();
            }


        }else{
            Toast.makeText(MainActivity.this, "Put Number Between 2-8", Toast.LENGTH_SHORT).show();
        }

    }
});

    }
}


