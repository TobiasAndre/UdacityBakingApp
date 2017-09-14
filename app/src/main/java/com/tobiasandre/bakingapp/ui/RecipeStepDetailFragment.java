package com.tobiasandre.bakingapp.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.tobiasandre.bakingapp.BakingApp;
import com.tobiasandre.bakingapp.R;
import com.tobiasandre.bakingapp.model.Recipe;


/**
 * Created by Tobias Andre on 31/08/2017.
 */

public class RecipeStepDetailFragment extends Fragment {

    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;
    private Recipe mRecipe;
    private TextView mTextRecpipeDetailStep;
    private TextView mTextInfoSteps;
    private int mPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipe = new Recipe();
        if (getArguments().containsKey(PlayerActivity.ARG_STEP)) {
            mRecipe = getArguments().getParcelable(PlayerActivity.ARG_STEP);
        }
        if(getArguments().containsKey(PlayerActivity.ARG_POSITION)){
            mPosition = getArguments().getInt(PlayerActivity.ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        View rootView = inflater.inflate(R.layout.recipe_step_detail_fragment_body_part, container, false);

        mTextRecpipeDetailStep = (TextView)rootView.findViewById(R.id.recipe_step_detail_text);
        mTextInfoSteps = (TextView)rootView.findViewById(R.id.tv_info_steps);
        Button mButtonPrev = (Button)rootView.findViewById(R.id.previousStep);
        Button mButtonNext = (Button)rootView.findViewById(R.id.nextStep);
        simpleExoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.playerView);
        simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);

        showStepAt(mPosition);

        if(mButtonPrev!=null){
            mButtonPrev.setOnClickListener(view -> {
                mPosition--;
                showStepAt(mPosition);
            });
        }
        if(mButtonNext!=null){
            mButtonNext.setOnClickListener(view -> {
                mPosition++;
                showStepAt(mPosition);
            });
        }
        return rootView;
    }

    private void initializePlayer(Uri mediaUri) {
        if (player == null) {
            try {
                player = ExoPlayerFactory.newSimpleInstance(
                        BakingApp.get()
                        , new DefaultTrackSelector());

                simpleExoPlayerView.setPlayer(player);

                String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
                MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);

                player.prepare(mediaSource);
                player.setPlayWhenReady(true);

            }catch (Exception error){
                System.out.println(error.getMessage());
            }
        }
    }

    public void showStepAt(int position){
        if(player!=null){
            player.stop();
            player.release();
            player = null;
        }

        if(position > (mRecipe.getSteps().size()-1)){
            position = 0;
        }
        if(position<0){
            position = mRecipe.getSteps().size()-1;
        }
        if(mTextInfoSteps!=null){
            mTextInfoSteps.setText(""+position+"/"+(mRecipe.getSteps().size()-1));
        }
        if(mTextRecpipeDetailStep!=null) {
            mTextRecpipeDetailStep.setText(mRecipe.getSteps().get(position).getDescription());
        }
        if(simpleExoPlayerView!=null) {
            if(!mRecipe.getSteps().get(position).getVideoURL().isEmpty()) {
                simpleExoPlayerView.setVisibility(View.VISIBLE);
                initializePlayer(Uri.parse(mRecipe.getSteps().get(position).getVideoURL()));
            }else{
                simpleExoPlayerView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (player!=null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (player!=null) {
            player.stop();
            player.release();
            player=null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (player!=null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player!=null) {
            player.stop();
            player.release();
        }
    }
}
