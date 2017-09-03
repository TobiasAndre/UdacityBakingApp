package com.tobiasandre.bakingapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobias Andre on 31/08/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private final static String TAG = RecipesAdapter.class.getSimpleName();

    private final ArrayList<Step> mSteps;
    private final Callbacks mCallbacks;

    public interface Callbacks {
        void open(Step step, int position);
    }

    public StepsAdapter(ArrayList<Step> steps, Callbacks callbacks) {
        mSteps = steps;
        this.mCallbacks = callbacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_list, parent, false);
        final Context context = view.getContext();


        return new ViewHolder(view);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cleanUp();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Step step = mSteps.get(position);
        final Context mContext = holder.mView.getContext();

        holder.tv_step_description.setText(position+" . "+step.getShortDescription());
        if(step.getVideoURL().isEmpty()){
            holder.iv_play.setVisibility(View.GONE);
        }else{
            holder.iv_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallbacks.open(step,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView tv_step_description;
        ImageView iv_play;

        public ViewHolder(View view){
            super(view);
            tv_step_description = (TextView)view.findViewById(R.id.tv_step_description);
            iv_play = (ImageView)view.findViewById(R.id.iv_play);
            mView = view;
        }

        public void cleanUp() {
            final Context context = mView.getContext();
            tv_step_description.setVisibility(View.GONE);
        }
    }

    public void add(List<Step> steps) {
        mSteps.clear();
        mSteps.addAll(steps);
        notifyDataSetChanged();
    }
}
