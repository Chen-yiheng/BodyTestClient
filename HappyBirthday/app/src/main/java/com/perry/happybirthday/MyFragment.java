package com.perry.happybirthday;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.io.File;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by perry on 2018/2/1.
 */

public class MyFragment extends Fragment implements View.OnClickListener {
    private ImageView play;
    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        play = view.findViewById(R.id.play);
        play.setOnClickListener(this);
        videoView = view.findViewById(R.id.video);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play.setVisibility(View.VISIBLE);
                videoView.stopPlayback();
                initVideoPath();
            }
        });
        initVideoPath();
        return view;
    }

    private void initVideoPath() {
        String uri = "android.resource://" + getActivity().getPackageName() + "/" + R
                .raw.myvedio;
        videoView.setVideoURI(Uri.parse(uri));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                play.setVisibility(View.GONE);
                videoView.start();
                break;


        }

    }


}
















