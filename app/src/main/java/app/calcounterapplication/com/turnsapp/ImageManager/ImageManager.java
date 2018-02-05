package app.calcounterapplication.com.turnsapp.ImageManager;

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

    public File getImageOfUser(int userId) {
        File userImage;
        if (isImageExist(userId)){
            userImage = getImageForExistingUser();
        }else{
            userImage = getNewImageForUser(userId);
        }

        return userImage;
    }

    private File getImageForExistingUser() {
        return null;
    }

    private File getNewImageForUser(int userId) {
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
