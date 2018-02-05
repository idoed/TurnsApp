package app.calcounterapplication.com.turnsapp.Adapters;

import java.util.ArrayList;
import java.util.Queue;

import app.calcounterapplication.com.turnsapp.Model.Player;

/**
 * Created by idoed on 04/02/2018.
 */

public class SingletonQueuePlayers {





    Queue<Player> MyPlayerQueue;

    public Queue<Player> getMyPlayerQueue() {
        return MyPlayerQueue;
    }

    public void setMyPlayerQueue(Queue<Player> myPlayerQueue) {
        MyPlayerQueue = myPlayerQueue;
    }

    private static SingletonQueuePlayers instance=null;
    public static SingletonQueuePlayers getInstance(){
        if (instance==null){
            instance=new SingletonQueuePlayers();
        }
        return instance;

    }

}


