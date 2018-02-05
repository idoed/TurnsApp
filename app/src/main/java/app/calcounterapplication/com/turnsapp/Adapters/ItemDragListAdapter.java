package app.calcounterapplication.com.turnsapp.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItemAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import app.calcounterapplication.com.turnsapp.Fragments.MyDraggableFragment;
import app.calcounterapplication.com.turnsapp.R;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static app.calcounterapplication.com.turnsapp.Fragments.MyDraggableFragment.mCheckedArray;
import static app.calcounterapplication.com.turnsapp.Fragments.MyDraggableFragment.mPeopleArray;
import static app.calcounterapplication.com.turnsapp.Fragments.MyDraggableFragment.mPhotosList;

public class ItemDragListAdapter extends DragItemAdapter<Pair<Long, String>, ItemDragListAdapter.ViewHolder> implements OnActivityResult{

    public Fragment myContext;

    int ClassCount = 0;
    static int StaticClassCount = 0;
//    public ImageButton myImage;
    public MyDraggableFragment myDrag=new MyDraggableFragment();

    public static final int IMAGE_GALLARY_REQUEST = 20,REQUEST_CAMERA=1;;
    private int mLayoutId;
    private int mGrabHandleId;
    boolean mPickDateOption = false;
    public CircleImageView myImage;
    private AlertDialog theDialog;
    Context mContext;
public static int PositionOfPicture;

    public interface CallbackInterface{

        /**
         * Callback invoked when activity result
         */
        void onCameraResult(Bitmap userImage);
    }


    //Constructor
    public ItemDragListAdapter(ArrayList<Pair<Long, String>> list, int layoutId,
                               int grabHandleId, boolean dragOnLongPress, boolean pickDateOption, Context context,ArrayList<Pair<Long,Bitmap>> photolist) {
        super(dragOnLongPress);
        mLayoutId = layoutId;
        mGrabHandleId = grabHandleId;

        mContext = context;
        setHasStableIds(true);
        setItemList(list);


    }
    public void ItemDragListAdapter1(ArrayList<Pair<Long, String>> list, int layoutId,
                               int grabHandleId, boolean pickDateOption, Context context) {
        mLayoutId = layoutId;
        mGrabHandleId = grabHandleId;
        mContext = context;
        setHasStableIds(true);
        setItemList(list);

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String text = mItemList.get(position).second;
        Bitmap myBitmap=mPhotosList.get(position).second;

        ClassCount++;
        StaticClassCount++;

        if(holder.mCheckBox.isChecked()){
            Log.wtf("here", "count: " +ClassCount+ "  position:  " + position );
        }
        holder.myImage.setImageBitmap(myBitmap);
        holder.mEditText.setText(text);
        holder.itemView.setTag(text);


    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).first;
    }

    public class ViewHolder extends DragItemAdapter<Pair<Long, String>, ItemDragListAdapter.ViewHolder>.ViewHolder {
        public ImageButton mDeleteImageButton;
        public CheckBox mCheckBox;


        //People name vars
        public EditText mEditText;
        public String mText;
        public String mOldText;
        public CircleImageView myImage;

        //

        private android.support.v7.app.AlertDialog.Builder dialog;



        public ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId);

            mEditText = (EditText) itemView.findViewById(R.id.enterName);
            mText = mEditText.getText().toString();
            myImage=(CircleImageView) itemView.findViewById(R.id.profileButtPic);
            mDeleteImageButton = (ImageButton) itemView.findViewById(R.id.delete_draggable_item);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox_draggable_list_item);

            //Delete Button was clicked listener
            mDeleteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Check which index was selected by comparing to edit text
                    int i = 0;
                    for (Pair temp : mPeopleArray) {
                        if (temp.second.equals("" + mOldText)) {
                            break;
                        }
                        i++;
                    }
                    mPeopleArray.remove(i);
                    notifyDataSetChanged();
                }
            });
            //Upload picture to the Button;
            myImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int TheImagePosition=getAdapterPosition();
                    PositionOfPicture=TheImagePosition;
                    dialog = new android.support.v7.app.AlertDialog.Builder((Activity) view.getContext());
                    dialog.setTitle("Upload Photo");
                    dialog.setMessage("Choose Between 2 Options");
                    dialog.setCancelable(false);

                    //Setting the Image Caputre (New Photo)
                    dialog.setPositiveButton("Take New Photo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //Changing each picture to User Pic;
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            ((Activity)mContext).startActivityForResult(intent,REQUEST_CAMERA);

                        }
                    });
                    //Set The Existing Photo Option
                    dialog.setNegativeButton("Choose exist photo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent intent=new Intent(Intent.ACTION_PICK);
                            //*Where do we want to find the data?
                            File pictureDerectory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                            String path=pictureDerectory.getPath();
                            //Finaly get URI representation=
                            Uri data1=Uri.parse(path);
                            //set the Data and type
                            intent.setDataAndType(data1,"image/*");
                            //WE WILL invoke that activity and get somthing from it.

                            ((Activity)mContext).startActivityForResult(intent,IMAGE_GALLARY_REQUEST);

                        }
                    });
                    dialog.show();
                }
            });


            //Change Name Listener
            mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //User clicked on edit text
                    if (hasFocus) {
                        //Get text before edited
                        mOldText = mEditText.getText().toString();

                        //Set Cancel item visible
                        mDeleteImageButton.setVisibility(View.VISIBLE);
                    }
                    //User leaved edit text
                    else {
                        //Set new text
                        mText = mEditText.getText().toString();

                        //Set delete button invisible
                        mDeleteImageButton.setVisibility(View.GONE);

                        //Set new value in the array
                        int i = 0;
                        Long mTempLong = -1l;
                        //Check which index was selected by comparing to old edit text
                        for (Pair temp : mPeopleArray) {
                            if (temp.second.equals("" + mOldText)) {
                                mTempLong = (long) temp.first;
                                break;
                            }
                            i++;
                        }
                        //If long id was found set the data in the array.
                        if (mTempLong != -1) {
                           mPeopleArray.set(i, Pair.create(mTempLong, mText));
                        }
                    }
                }
            });
            //CheckBox change listener
            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    //Check if no new name was signed to this person
                    if (mOldText == null || mOldText.isEmpty()) {

                        //Check name of currently checked box
                        EditText mCurrentName = (EditText) itemView.findViewById(R.id.enterName);
                        //set check condition in is checked array
                        int i = 0;
                        //Check which index was selected by comparing to edit text
                        for (Pair temp : mPeopleArray) {
                            if (temp.second.equals("" + mCurrentName.getText())) {
                                break;
                            }
                            i++;
                        }
                        mCheckedArray.set(i, isChecked);
                    }
                    //if name is currently being added check the array by the old name
                    else {
                        //set check condition in is checked array
                        int i = 0;
                        //Check which index was selected by comparing to edit text
                        for (Pair temp : mPeopleArray) {
                            if (temp.second.equals("" + mOldText)) {
                                break;
                            }
                            i++;
                        }
                        //Sets the currently being added checkbox in final array
                        try {
                            mCheckedArray.set(i, isChecked);
                        }
                        catch (IndexOutOfBoundsException e)  {
                            Log.v("INDEx out of Bounds except","itemDragList");

                        }
                    }

                }
            });
        }
        }
        //List item is clicked listener

        public void onItemClicked(View view) {
            Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
        }


        public boolean onItemLongClicked(View view) {
            Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    @Override
    public void onActivityResult(int requestCode, int ResultCode, Intent data) {

        Log.v("requestCode="+requestCode+"  resultcode="+ResultCode+"and my if is"+RESULT_OK,"Dam Right");
        if(ResultCode==RESULT_OK){
            //if we are here everything processed successfully;
            if(requestCode==IMAGE_GALLARY_REQUEST){
                //if we are here we got the image

                Uri imageUri=data.getData();
                //the address of the image on the sd
                InputStream inputStream ;

                try{
                    inputStream=mContext.getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    Bitmap myBitmap=image;
                    int i = 0;
                    Long mTempLong = -1l;
                    for (Pair temp :mPhotosList){
                        if (((Long) temp.first).intValue() ==  PositionOfPicture){
                            mTempLong = (Long) temp.first;
//                            myImage.setImageBitmap(myBitmap);
                            break;
                        }
                        i++;
                    }
                    if (mTempLong != -1) {
                        mPhotosList.set(i, Pair.create(mTempLong, myBitmap));
                        notifyDataSetChanged();
                    }
                    Toast.makeText(mContext, "Position of picture in Int"+PositionOfPicture+" and position in mTempLong "+mTempLong, Toast.LENGTH_SHORT).show();
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                    Toast.makeText(mContext,"Unable to open",Toast.LENGTH_LONG).show();
                    //getBitmap from string

                }

            }
        }else if(requestCode==REQUEST_CAMERA){
            Bundle bundle=data.getExtras();
            Bitmap image=(Bitmap) bundle.get("data");
            myImage.setImageBitmap(image);

        }
    }
}