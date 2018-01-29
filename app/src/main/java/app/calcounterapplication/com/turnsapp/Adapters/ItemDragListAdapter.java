package app.calcounterapplication.com.turnsapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItemAdapter;

import java.util.ArrayList;

import app.calcounterapplication.com.turnsapp.ImageManager.ImageManager;
import app.calcounterapplication.com.turnsapp.R;

import static app.calcounterapplication.com.turnsapp.Fragments.my_dragalble_fragment.mCheckedArray;
import static app.calcounterapplication.com.turnsapp.Fragments.my_dragalble_fragment.mPeopleArray;

/**
 * Created by idoed on 27/01/2018.
 */

public class ItemDragListAdapter extends DragItemAdapter<Pair<Long, String>, ItemDragListAdapter.ViewHolder> {

    int classcount = 0;
    static int staticclasscount = 0;


    private int mLayoutId;
    private int mGrabHandleId;
    boolean mPickDateOption = false;


    private AlertDialog theDialog;
    Context mContext;


    //Constructor
    public ItemDragListAdapter(ArrayList<Pair<Long, String>> list, int layoutId,
                               int grabHandleId, boolean dragOnLongPress, boolean pickDateOption, Context context) {
        super(dragOnLongPress);
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
        classcount++;
        staticclasscount++;

        if(holder.mCheckBox.isChecked()){
            Log.wtf("here", "count: " +classcount+ "  position:  " + position );
        }
//

        holder.mEditText.setText(text);
        holder.itemView.setTag(text);

//
//
//        holder.mCheckBox.setChecked(DraggableListFragment.mCheckedArray.get(i7000));

    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).first;
    }

    public class ViewHolder extends DragItemAdapter<Pair<Long, String>, ItemDragListAdapter.ViewHolder>.ViewHolder {
        public ImageButton mDeleteImageButton;
        public CheckBox mCheckBox;
        public Button myImage;


        //chosen dates vars
        public TextView chosedDays;
        public TextView chosedMonthandYear;

        //People name vars
        public EditText mEditText;
        public String mText;
        public String mOldText;
        public static final int REQUEST_CAMERA=1;;

        public ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId);

            mEditText = (EditText) itemView.findViewById(R.id.enterName);
            mText = mEditText.getText().toString();
            myImage=(Button) itemView.findViewById(R.id.profileButtPic);
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
//                    //Changing each picture to User Pic;
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    mContext.startActivity(intent);

//
//
//
//
//                    int i=0;
//                    for(Pair temp :mPeopleArray){
//
//                    }
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
                        mCheckedArray.set(i, isChecked);
                    }



                }
            });
        }





//            // Setting dialog view at the bottom of the window
//            Window window = theDialog.getWindow();
//            window.setGravity(Gravity.BOTTOM);
//            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);


        }

        //List item is clicked listener

        public void onItemClicked(View view) {
            Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
        }


        public boolean onItemLongClicked(View view) {
            Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
