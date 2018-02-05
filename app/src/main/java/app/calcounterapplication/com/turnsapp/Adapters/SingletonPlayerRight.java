package app.calcounterapplication.com.turnsapp.Adapters;

import app.calcounterapplication.com.turnsapp.Model.Player;

/**
 * Created by idoed on 04/02/2018.
 */

public class SingletonPlayerRight {
    Player mPlayerRight;

    public Player getmPlayerRight() {
        return mPlayerRight;
    }

    public void setmPlayerRight(Player mPlayerRight) {
        this.mPlayerRight = mPlayerRight;
    }

    private static SingletonPlayerRight instance=null;
    public static SingletonPlayerRight getInstance(){
        if (instance==null){
            instance=new SingletonPlayerRight();
        }
        return instance;

    }
}
