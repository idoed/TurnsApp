package app.calcounterapplication.com.turnsapp.Model;

import java.io.File;

/**
 * Created by idoed on 26/01/2018.
 */

public class Player {
    private int playerid;
    private String playername;
    private int Wins;
    private File ImagePath;

    public Player(String name) {
    }


    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
    }

    public Player(int playerid, String playername,int NumberOfwins) {
        this.playerid = playerid;
        this.playername = playername;
        //meanwhile  its not gonna happen
//        this.ImagePath=ImagePath;
        this.Wins=NumberOfwins;
    }
}
