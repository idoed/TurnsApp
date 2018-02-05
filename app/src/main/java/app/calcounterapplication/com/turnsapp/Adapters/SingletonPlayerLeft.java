package app.calcounterapplication.com.turnsapp.Adapters;

import app.calcounterapplication.com.turnsapp.Model.Player;

/**
 * Created by idoed on 04/02/2018.
 */

public class SingletonPlayerLeft {
    Player mPlayerLeft;

    public Player getmPlayerLeft() {
        return mPlayerLeft;
    }

    public void setmPlayerLeft(Player mPlayerLeft) {
        this.mPlayerLeft = mPlayerLeft;
    }

    private static SingletonPlayerLeft instance=null;
    public static SingletonPlayerLeft getInstance(){
        if (instance==null){
            instance=new SingletonPlayerLeft();
        }
        return instance;

    }
}
