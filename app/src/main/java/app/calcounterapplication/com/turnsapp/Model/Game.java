package app.calcounterapplication.com.turnsapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by idoed on 26/01/2018.
 */

public class Game implements Parcelable {
    private int id;
    private String name;
    private int numofplayers;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumofplayers() {
        return numofplayers;
    }

    public void setNumofplayers(int numofplayers) {
        this.numofplayers = numofplayers;
    }

    public Game(int id, String name, int numofplayers) {
        this.id = id;
        this.name = name;
        this.numofplayers = numofplayers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeInt(this.numofplayers);
        parcel.writeInt(this.id);

    }
public Game(Parcel parcel){
    this.name=parcel.readString();
    this.numofplayers=parcel.readInt();
    this.id=parcel.readInt();
}
public  static final Creator<Game> CREATOR= new Creator<Game>() {
    @Override
    public Game createFromParcel(Parcel parcel) {
        return new Game(parcel);
    }

    @Override
    public Game[] newArray(int i) {
        return new Game[0];
    }
};

    }



