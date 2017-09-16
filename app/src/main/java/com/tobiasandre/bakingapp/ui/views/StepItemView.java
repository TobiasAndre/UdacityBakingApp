package com.tobiasandre.bakingapp.ui.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Step;

import java.util.List;


/**
 * Created by Tobias Andre on 07/09/2017.
 */

public class StepItemView extends LinearLayout {

    private TextView mNumber;
    private TextView mName;
    private ImageView mVideoIcon;

    private final Callbacks mCallbacks;

    public interface Callbacks {
        void open(List<Step> steps, int position);
    }


    public StepItemView(Context context,Callbacks callbacks){
        super(context);
        View view = inflate(getContext(), R.layout.item_step,this);
        mNumber = (TextView)view.findViewById(R.id.number);
        mName = (TextView)view.findViewById(R.id.item_name);
        mVideoIcon = (ImageView)view.findViewById(R.id.video);
        this.mCallbacks = callbacks;
    }

    public View bind(List<Step> steps,int position){

        mNumber.setText(String.valueOf(steps.get(position).getIndex())+".");
        mName.setText(steps.get(position).getShortDescription());
        if(steps.get(position).getVideoURL()==null || steps.get(position).getVideoURL().isEmpty())
            mVideoIcon.setVisibility(INVISIBLE);

        //for testing
        mVideoIcon.setContentDescription(steps.get(position).getIndex() + "");
        this.setOnClickListener(view -> {
            if(mCallbacks!=null){
                mCallbacks.open(steps,position);
            }
        });
        return this;
    }


}
