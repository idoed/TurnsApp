package app.calcounterapplication.com.turnsapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.calcounterapplication.com.turnsapp.Adapters.ItemDragListAdapter;
import app.calcounterapplication.com.turnsapp.Adapters.OnActivityResult;
import app.calcounterapplication.com.turnsapp.Model.Game;
import app.calcounterapplication.com.turnsapp.Model.Player;
import app.calcounterapplication.com.turnsapp.R;
import app.calcounterapplication.com.turnsapp.UI.GameActivity;

import static android.R.attr.factor;
import static android.R.attr.name;


public class my_dragalble_fragment extends Fragment   {
    private Game game;

    //Array for people names with id's for the draggable list
    public static ArrayList<Pair<Long, String>> mPeopleArray;
    public static ArrayList<Player> PlayerList;

    //Array for checkbox
    public static ArrayList<Boolean> mCheckedArray;
    public ItemDragListAdapter itemDragListAdapter;

    private DragListView mDragListView;
    private boolean pickDateOption;
    public ImageButton ProfileBut;
public  int numOfPlayers;





    View view;
    public my_dragalble_fragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        Log.v("if we are here its working", "Yeah");
            itemDragListAdapter.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//Passing the game info Including number of players,Game Name and Id
        Intent gameintent = getActivity().getIntent();
        game=(Game)gameintent.getParcelableExtra("Game");
         numOfPlayers=game.getNumofplayers();

//        context=getActivity();
//        itemDragListAdapter.setContext(context);


        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_my_dragalble_fragment, container, false);
        try {

                    mDragListView = (DragListView) view.findViewById(R.id.drag_list_view_first_call);
            mDragListView.getRecyclerView().setVerticalScrollBarEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
        }

        //Todo:Delete when finish list
        mDragListView.setDragListListener(new DragListView.DragListListener() {
            @Override
            public void onItemDragStarted(int position) {
            }
            @Override
            public void onItemDragEnded(int fromPosition, int toPosition) {
                if (fromPosition != toPosition) {
                }
            }
        });

        //TODO: take from database

        mPeopleArray = new ArrayList<>();
        mCheckedArray = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            try {
                mPeopleArray.add(new Pair<>(Long.valueOf(i), "Player " + i));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            mCheckedArray.add(false);

        }
        setupListRecyclerView();


        return view;
    }

    @Override
    public void onDestroyView() {
        //TODo: handel DB here with gettag() - check this is best practice place to handle db in fragment life cycle
        super.onDestroyView();
    }

    public void setupListRecyclerView() {
        mDragListView.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemDragListAdapter listAdapter = new ItemDragListAdapter(mPeopleArray,
                R.layout.drag_exmpl, R.id.image, false,pickDateOption,getContext(),my_dragalble_fragment.this);
        mDragListView.setAdapter(listAdapter, true);
        mDragListView.setCanDragHorizontally(false);
        mDragListView.setCustomDragItem(new MyDragItem(getContext(), R.layout.drag_exmpl));

    }



    private static class MyDragItem extends DragItem {

        public MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
            CharSequence text = ((TextView) clickedView.findViewById(R.id.enterName)).getText();
            ((TextView) dragView.findViewById(R.id.enterName)).setText(text);
            dragView.setBackgroundColor(dragView.getResources().getColor(R.color.drag_color));
        }
    }
    //


    /**
     * Chacking if all chackboxes are ok, if yes Creating List OfPlayers.
     */
    public boolean ChackIfGameSet(){
        boolean Chacker=true;
        ProfileBut=(ImageButton)view.findViewById(R.id.profileButtPic);
        for (int i=0; i<numOfPlayers;i++){
            if(mCheckedArray.get(i)==false){
                i=numOfPlayers;
                PlayerList.clear();
                Chacker=false;
            }else{
                String PersonName=mPeopleArray.get(i).toString();
                // TODO: 30/01/2018  need to see how can i get path for each Image;
                Player player=new Player(i,PersonName,0);
                PlayerList.add(player);


            }
        }return Chacker;
    }

    /**
     * taking the List and send it to The Activity
     *
     * @return
     */
    public ArrayList<Player> CallingPlayerList(){
        return PlayerList;
    }


}

