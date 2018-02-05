package app.calcounterapplication.com.turnsapp.Adapters;

import java.util.ArrayList;

import app.calcounterapplication.com.turnsapp.Model.Player;

/**
 * Created by idoed on 03/02/2018.
 */

public class SingletonArrayPlayers {
    public ArrayList<Player> getMyPlayerList() {
        return MyPlayerList;
    }

    public void setMyPlayerList(ArrayList<Player> myPlayerList) {
        MyPlayerList = myPlayerList;
    }

    ArrayList<Player> MyPlayerList;
    private static SingletonArrayPlayers instance=null;
    public static SingletonArrayPlayers getInstance(){
        if (instance==null){
            instance=new SingletonArrayPlayers();
        }
        return instance;

    }

    }

