package app.calcounterapplication.com.turnsapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import app.calcounterapplication.com.turnsapp.Adapters.SingletonArrayPlayers;
import app.calcounterapplication.com.turnsapp.Adapters.SingletonPlayerLeft;
import app.calcounterapplication.com.turnsapp.Adapters.SingletonPlayerRight;
import app.calcounterapplication.com.turnsapp.Adapters.SingletonQueuePlayers;
import app.calcounterapplication.com.turnsapp.Model.Player;
import app.calcounterapplication.com.turnsapp.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class GameBoard extends Fragment {
    private ArrayList<Player> mFinalPlayerList, mMegaFinalList;
    private TextView player1txt, player2txt, player3txt, player4txt, player5txt, player6txt, player7txt, player8txt;
    private TextView scoreplayer1txt, scoreplayer2txt, scoreplayer3txt, scoreplayer4txt, scoreplayer5txt, scoreplayer6txt, scoreplayer7txt, scoreplayer8txt;
    private Queue<Player> mGameQueueLine,mGameQueueLine2;
    private OnFragmentInteractionListener mListener;
    private CircleImageView LeftPic, RightPic;
    private Player mPlayingPlayerRight, mPlayingPlayerLeft;
    //Creating Veribale that represent which player won (Left OR Right)
    private int mWinnerCode, mLeaderScore;
    private RelativeLayout mRelativeP3,mRelativeP4,mRelativeP5,mRelativeP6,mRelativeP7,mRelativeP8;

    public GameBoard() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game__board, container, false);

        mRelativeP3=view.findViewById(R.id.layoutplayer3);
        mRelativeP4=view.findViewById(R.id.layoutplayer4);
        mRelativeP5=view.findViewById(R.id.layoutplayer5);
        mRelativeP6=view.findViewById(R.id.layoutplayer6);
        mRelativeP7=view.findViewById(R.id.layoutplayer7);
        mRelativeP8=view.findViewById(R.id.layoutplayer8);


        scoreplayer3txt = view.findViewById(R.id.scoreplayer3txt);
        scoreplayer4txt = view.findViewById(R.id.scoreplayer4txt);
        scoreplayer5txt = view.findViewById(R.id.scoreplayer5txt);
        scoreplayer6txt = view.findViewById(R.id.scoreplayer6txt);
        scoreplayer7txt = view.findViewById(R.id.scoreplayer7txt);
        scoreplayer8txt = view.findViewById(R.id.scoreplayer8txt);

        player3txt = view.findViewById(R.id.textPlayer3);
        player4txt = view.findViewById(R.id.textPlayer4);
        player5txt = view.findViewById(R.id.textPlayer5);
        player6txt = view.findViewById(R.id.textPlayer6);
        player7txt = view.findViewById(R.id.textPlayer7);
        player8txt = view.findViewById(R.id.textPlayer7);
        mFinalPlayerList = SingletonArrayPlayers.getInstance().getMyPlayerList();
          Queue<Player> mMyPlayersQueue = new LinkedList<Player>();
//Creating Queue to see whos playing next;
        for (int i = 0; i < mFinalPlayerList.size(); i++) {
            Player p = mFinalPlayerList.get(i);
            mMyPlayersQueue.add(p);

        }

        /**
         * Setting the game for the first time polling 2 Players  from the Queue and setting the rest.
         *
         */
        mPlayingPlayerRight = mMyPlayersQueue.poll();
        SingletonPlayerRight.getInstance().setmPlayerRight(mPlayingPlayerRight);
        mPlayingPlayerLeft = mMyPlayersQueue.poll();
        SingletonPlayerLeft.getInstance().setmPlayerLeft(mPlayingPlayerLeft);
         final Bitmap mPicPlayerLeft = mPlayingPlayerLeft.getImage();
         Bitmap mPicPlayerRight = mPlayingPlayerRight.getImage();
        RightPic = (CircleImageView) view.findViewById(R.id.picPlayerRight);
        LeftPic = (CircleImageView) view.findViewById(R.id.picPlayerLeft);
        RightPic.setImageBitmap(mPicPlayerRight);
        LeftPic.setImageBitmap(mPicPlayerLeft);
        SingletonQueuePlayers.getInstance().setMyPlayerQueue(mMyPlayersQueue);
        SettingTheGame(mMyPlayersQueue);


        //Case the Right Player Won
        RightPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayingPlayerRight=SingletonPlayerRight.getInstance().getmPlayerRight();
                mPlayingPlayerLeft=SingletonPlayerLeft.getInstance().getmPlayerLeft();
                Log.v("I Was Clicked", "");
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("Winner !~");
                dialog.setMessage(" " + mPlayingPlayerRight.getPlayername() + " Won the game??");
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Be careful next time", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int mWinNum=mPlayingPlayerRight.getWins();
                        mWinNum++;
                        mPlayingPlayerRight.setWins(mWinNum);
                        SingletonPlayerRight.getInstance().setmPlayerRight(mPlayingPlayerRight);
                        mWinnerCode = 2;
                        new MyAsyncTask(mWinnerCode,mPlayingPlayerLeft).execute();
                        mGameQueueLine =SingletonQueuePlayers.getInstance().getMyPlayerQueue();
                        mPlayingPlayerLeft=SingletonPlayerLeft.getInstance().getmPlayerLeft();
                        Bitmap LoosingPlayerPic=mPlayingPlayerLeft.getImage();
                        LeftPic.setImageBitmap(LoosingPlayerPic);
                        SettingTheGame(mGameQueueLine);

                    }
                });

                dialog.show();

            }
        });
        //Case the Left Player Won
        LeftPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayingPlayerRight=SingletonPlayerRight.getInstance().getmPlayerRight();
                mPlayingPlayerLeft=SingletonPlayerLeft.getInstance().getmPlayerLeft();

                Log.v("I Was Clicked", "");
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("Winner !~");
                dialog.setMessage(" " + mPlayingPlayerLeft.getPlayername() + " Won the game??");
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Be careful next time", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mWinnerCode = 1;
                        int mWinNum=mPlayingPlayerLeft.getWins();
                        mWinNum++;
                        mPlayingPlayerLeft.setWins(mWinNum);
                        SingletonPlayerLeft.getInstance().setmPlayerLeft(mPlayingPlayerLeft);
                        Toast.makeText(getContext(), "playerleft got "+mPlayingPlayerLeft.getWins(), Toast.LENGTH_SHORT).show();
                        new MyAsyncTask(mWinnerCode,mPlayingPlayerRight).execute();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mGameQueueLine =SingletonQueuePlayers.getInstance().getMyPlayerQueue();
                        mPlayingPlayerRight=SingletonPlayerRight.getInstance().getmPlayerRight();
                        Bitmap LoosingPlayerPic=mPlayingPlayerRight.getImage();
                        RightPic.setImageBitmap(LoosingPlayerPic);
                        SettingTheGame(mGameQueueLine);
                    }
                });
                dialog.show();
            }
        });

        return view;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void SettingTheGame( Queue<Player> MySettingQueue) {

        Queue<Player> mMyOriginalQueue=new LinkedList<Player>();

        Player player;
        Toast.makeText(getContext(), "my Queue size is"+MySettingQueue.size(), Toast.LENGTH_SHORT).show();

        int i = 2;
        while (!MySettingQueue.isEmpty()) {



            switch  (i) {

                case 2:
                     player = MySettingQueue.poll();
                    player3txt.setText(player.getPlayername());
                    scoreplayer3txt.setText("Score: " + player.getWins());
                    Toast.makeText(getContext(), "Player Name : "+player.getPlayername(), Toast.LENGTH_SHORT).show();
                    mMyOriginalQueue.add(player);
                    i++;

                    mRelativeP3.setVisibility(View.VISIBLE);
                    break;
                case 3:
                     player = MySettingQueue.poll();
                    mMyOriginalQueue.add(player);
                    player4txt.setText(player.getPlayername());
                    mRelativeP4.setVisibility(View.VISIBLE);
                    scoreplayer4txt.setText("Score: " + player.getWins());
                    i++;
                    break;
                case 4:
                     player = MySettingQueue.poll();
                  mMyOriginalQueue.add(player);
                    player5txt.setText(player.getPlayername());
                    mRelativeP5.setVisibility(View.VISIBLE);
                    scoreplayer5txt.setText("Score: " + player.getWins());
                    i++;
                    break;
                case 5:

                     player= MySettingQueue.poll();
                    mMyOriginalQueue.add(player);
                    player6txt.setText(player.getPlayername());
                    mRelativeP6.setVisibility(View.VISIBLE);
                    scoreplayer6txt.setText("Score: " + player.getWins());
                    i++;
                    break;
                case 6:
                     player = MySettingQueue.poll();
                    mMyOriginalQueue.add(player);
                    player7txt.setText(player.getPlayername());
                    mRelativeP7.setVisibility(View.VISIBLE);
                    scoreplayer7txt.setText("Score: " + player.getWins());
                    i++;
                    break;
                case 7:
                     player = MySettingQueue.poll();
                    mMyOriginalQueue.add(player);
                    player8txt.setText(player.getPlayername());
                    mRelativeP8.setVisibility(View.VISIBLE);
                    scoreplayer8txt.setText("Score: " + player.getWins());
                    i++;
                    break;


            }
        }
            SingletonQueuePlayers.getInstance().setMyPlayerQueue(mMyOriginalQueue);
        }
    }

    class MyAsyncTask extends AsyncTask<Void,Void,Void>{
        int position;
        Player mLoosingPlayer;
        Queue<Player> mMyOriginalQueue=new LinkedList<Player>();
        public MyAsyncTask(int winnerCode, Player loosingPlayer){
            position=winnerCode;
            mLoosingPlayer=loosingPlayer;
        }
        @Override
        protected Void doInBackground(Void... Void) {

            mMyOriginalQueue =SingletonQueuePlayers.getInstance().getMyPlayerQueue();


            if(position==1) {
                mMyOriginalQueue.add(mLoosingPlayer);
                mLoosingPlayer=mMyOriginalQueue.poll();
                SingletonPlayerRight.getInstance().setmPlayerRight(mLoosingPlayer);

            }else if(position==2){
                Log.e("its got here","");
                mMyOriginalQueue.add(mLoosingPlayer);

                mLoosingPlayer=mMyOriginalQueue.poll();
                SingletonPlayerLeft.getInstance().setmPlayerLeft(mLoosingPlayer);


            }
            else{

            }
            try {
                SingletonQueuePlayers.getInstance().setMyPlayerQueue(mMyOriginalQueue);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPreExecute() {


            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            }



        }








