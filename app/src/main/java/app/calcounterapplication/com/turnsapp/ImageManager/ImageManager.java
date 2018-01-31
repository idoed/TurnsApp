package app.calcounterapplication.com.turnsapp.ImageManager;

import android.media.Image;
import android.provider.ContactsContract;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by idoed on 29/01/2018.
 */

public class ImageManager {
    static final ImageManager me = new ImageManager();
    ArrayList<ImageObj> imageList = new ArrayList<>();

    static public ImageManager getImageManager() {
        return me;
    }

    /**
     * getting Id from the user in 3rd stage/or after receiving database
     *
     * @param userId
     * @return
     */
    public File getImageOfUser(int userId) {
        File userImage=null;


        return userImage;
    }
    //TODO: need to chack if the user is not exists.

    public void SaveImageToDb(File ImagePath,int userid){
        ImageObj image= new ImageObj(ImagePath,userid );
            imageList.add(image);
    }
    public File getImageFromNewUser(){
        File ImagePath = null;

        return ImagePath;
    }

    private File getImageForExistingUser() {
        return null;
    }

    private File getNewImageForUser() {
        return null;
    }

    private boolean isImageExist(int userId) {
        return false;
    }

    // METHOD CURRENTLY NOT SUPPORTED
    public ArrayList<File> getAllImages() {
        return null;
    }

    // METHOD CURRENTLY NOT SUPPORTED
    public void replaceUserImage(int userId) {

    }
}
