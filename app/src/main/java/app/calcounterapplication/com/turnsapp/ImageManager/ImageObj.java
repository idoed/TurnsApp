package app.calcounterapplication.com.turnsapp.ImageManager;

import java.io.File;

/**
 * Created by idoed on 29/01/2018.
 */

class ImageObj {

    private File imageFile;
    private int userId;

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
