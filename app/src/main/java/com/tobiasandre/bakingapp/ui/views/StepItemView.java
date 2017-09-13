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

    TextView number;
    TextView name;
    ImageView videoIcon;

    private final Callbacks mCallbacks;

    public interface Callbacks {
        void open(List<Step> steps, int position);
    }


    public StepItemView(Context context,Callbacks callbacks){
        super(context);
        View view = inflate(getContext(), R.layout.item_step,this);
        number = (TextView)view.findViewById(R.id.number);
        name = (TextView)view.findViewById(R.id.item_name);
        videoIcon = (ImageView)view.findViewById(R.id.video);
        this.mCallbacks = callbacks;
    }

    public View bind(List<Step> steps,int position){

        number.setText(String.valueOf(steps.get(position).getIndex())+".");
        name.setText(steps.get(position).getShortDescription());
        if(steps.get(position).getVideoURL()==null || steps.get(position).getVideoURL().isEmpty())
            videoIcon.setVisibility(INVISIBLE);

        //for testing
        videoIcon.setContentDescription(steps.get(position).getIndex() + "");
        this.setOnClickListener(view -> {
            if(mCallbacks!=null){
                mCallbacks.open(steps,position);
            }
        });
        return this;
    }


}
