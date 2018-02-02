package app.calcounterapplication.com.turnsapp.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import app.calcounterapplication.com.turnsapp.Adapters.ItemDragListAdapter;
import app.calcounterapplication.com.turnsapp.Fragments.Game_Board;
import app.calcounterapplication.com.turnsapp.Fragments.my_dragalble_fragment;
import app.calcounterapplication.com.turnsapp.Model.Game;
import app.calcounterapplication.com.turnsapp.Model.Player;
import app.calcounterapplication.com.turnsapp.R;


public class GameActivity extends AppCompatActivity implements ItemDragListAdapter.CallbackInterface ,my_dragalble_fragment.MovingToGameListener{

    public static final int IMAGE_GALLARY_REQUEST = 20,REQUEST_CAMERA=1;;
    static ImageView picsImage;
    public ImageButton StartGameButton;
    public  ArrayList<Player> FinalPlayerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
StartGameButton=(ImageButton)findViewById(R.id.startingTheGameBut);

     //Getting the information from the intent
        FragmentManager fm = getSupportFragmentManager();
         my_dragalble_fragment my_dragalble_fragment1 =(my_dragalble_fragment) getSupportFragmentManager().findFragmentById(R.id.mydragfrag);
        try {
            Boolean b=my_dragalble_fragment1.ChackIfGameSet();
        }
        catch (NullPointerException e) {
            Log.v("f", " b " );
        }

        final Game_Board myGameBoard=(Game_Board) getSupportFragmentManager().findFragmentById(R.id.GameBoardFrag);
        Intent intent=getIntent();
        Game game=(Game) intent.getParcelableExtra("Game");
        getIntent().putExtra("Game",game);
//        my_dragalble_fragment1.setGameDetails(gname,num);
//begin the trans
        final FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.TheGameFrame, new my_dragalble_fragment());
        ft.commit();
//        if(my_dragalble_fragment1.ChackIfGameSet()){
//            Toast.makeText(this, "Please Chack  all the Players", Toast.LENGTH_SHORT).show();
//        }


//        OnClick to chack if the game set and start the game;
        StartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Not Working :O

//                Log.v("Boolean is:",""+b);
//                if(b==true){
//                    //Testing if reciving the player list;
                    Player player=FinalPlayerList.get(0);
                    Toast.makeText(GameActivity.this, "The Player name is"+player.getPlayername(), Toast.LENGTH_SHORT).show();
//                    ft.replace(R.id.mydragfrag,new Game_Board());
//                    ft.commit();
                }
//            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("requestCode="+requestCode+"  resultcode="+resultCode,"Dam Right");
        my_dragalble_fragment.PassingToAdapter(requestCode,resultCode,data);
    }

    @Override
    public void onCameraResult(Bitmap userImage) {

    }
    @Override
    public void CallingPlayerList(ArrayList<Player> list) {
        FinalPlayerList=list;
    }
}
