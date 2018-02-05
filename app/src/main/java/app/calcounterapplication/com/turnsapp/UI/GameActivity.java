package app.calcounterapplication.com.turnsapp.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import app.calcounterapplication.com.turnsapp.Adapters.ItemDragListAdapter;
import app.calcounterapplication.com.turnsapp.Adapters.SingletonArrayPlayers;
import app.calcounterapplication.com.turnsapp.Fragments.GameBoard;
import app.calcounterapplication.com.turnsapp.Fragments.MyDraggableFragment;
import app.calcounterapplication.com.turnsapp.Model.Game;
import app.calcounterapplication.com.turnsapp.Model.Player;
import app.calcounterapplication.com.turnsapp.R;


public class GameActivity extends AppCompatActivity implements ItemDragListAdapter.CallbackInterface, MyDraggableFragment.MovingToGameListener {

    public static final int IMAGE_GALLARY_REQUEST = 20, REQUEST_CAMERA = 1;
    private static final String TAG = "GameActivity";
    static ImageView picsImage;
    public ImageButton mStartGameButton;
    public ArrayList<Player> mFinalPlayerList;
    private MyDraggableFragment mMyDraggableFragment;
    private GameBoard mGameBoard;
    private FragmentTransaction ft;
    private  FragmentManager fm;
    private FrameLayout mFragmentLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        mFragmentLayout.findViewById(R.id.GameBoardFrag);
        setSupportActionBar(toolbar);
//        StartGameButton = (ImageButton) findViewById(R.id.startingTheGameBut);

        //Getting the information from the intent
         fm = getSupportFragmentManager();
        mMyDraggableFragment = new MyDraggableFragment();
        mGameBoard=new GameBoard();
//     GameBoard myGameBoard = (GameBoard) getSupportFragmentManager().findFragmentById(R.id.GameBoardFrag);
        Intent intent = getIntent();
        Game game = (Game) intent.getParcelableExtra("Game");
        mStartGameButton=(ImageButton)findViewById(R.id.startingTheGameBut);

        getIntent().putExtra("Game", game);
//        mMyDraggableFragment.setGameDetails(gname,num);
//begin the trans
        fm.beginTransaction().replace(R.id.TheGameFrame,mMyDraggableFragment).commit();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("requestCode=" + requestCode + "  resultcode=" + resultCode, "Dam Right");
        MyDraggableFragment.PassingToAdapter(requestCode, resultCode, data);
    }

    @Override
    public void onCameraResult(Bitmap userImage) {

    }

    @Override
    public void CallingPlayerList(ArrayList<Player> list) {
        mFinalPlayerList = list;
    }

    public void onStartGameClicked(View view) {
        mStartGameButton.setVisibility(View.INVISIBLE);
        Log.e(TAG, "onStartGameClicked: ");
        mMyDraggableFragment.checkIfGameSet();
        Bundle myFragmentBundle=new Bundle();
        myFragmentBundle.putParcelableArrayList("PLAYERLIST",  mFinalPlayerList);
        SingletonArrayPlayers.getInstance().setMyPlayerList(mFinalPlayerList);

       mGameBoard.setArguments(myFragmentBundle);
      fm.beginTransaction().replace(R.id.TheGameFrame,mGameBoard).commit();
        Log.v("starting the Methood","please");
//        mGameBoard.GettingFinalPlayerList(mFinalPlayerList);




    }
}
