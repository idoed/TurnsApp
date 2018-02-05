package app.calcounterapplication.com.turnsapp.Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;

/**
 * Created by idoed on 26/01/2018.
 */

public class Player implements Parcelable {
    private int playerid;
    private String playername;
    private int Wins;

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    private Bitmap Image;

    public Player(int playerid,String playername,int NumberOfWins) {
        this.playerid=playerid;
        this.playername=playername;
        this.Wins=NumberOfWins;
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

    public Player(int playerid, String playername, int NumberOfwins, Bitmap userPhoto) {
        this.playerid = playerid;
        this.playername = playername;
        //meanwhile  its not gonna happen
//        this.ImagePath=ImagePath;
        this.Wins = NumberOfwins;
        this.Image = userPhoto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(playerid);
        parcel.writeString(playername);
        parcel.writeInt(Wins);


    }
    public Player(Parcel parcel){
        this.playerid=parcel.readInt();
        this.Wins=parcel.readInt();
        this.playername=parcel.readString();

    }
    public  static final Creator<Player> CREATOR=new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel parcel) {
            return new Player(parcel);
        }

        @Override
        public Player[] newArray(int i) {
            return new Player[0];
        }
    };
}
