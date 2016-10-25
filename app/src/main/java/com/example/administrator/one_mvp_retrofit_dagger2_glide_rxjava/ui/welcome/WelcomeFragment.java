package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.main.MainActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.GifView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link WelcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private GifView imageView;
    private TextView welcome_fragment_tv;
    private Animation animation;

    private String mParam1;
    private String mParam2;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WelcomeFragment newInstance(String param1, String param2) {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        imageView = (GifView) view.findViewById(R.id.welcome_fragment_iv);
        welcome_fragment_tv = (TextView) view.findViewById(R.id.welcome_fragment_tv);
        startImgAnimation();
        return view;
    }

    public void startImgAnimation(){
        switch (mParam1){
            case Const.READING_GUIDE:
                imageView.setMovieResource(R.raw.reading_guide);
                break;
            case Const.MUSIC_GUIDE:
                imageView.setMovieResource(R.raw.music_guide);
                setPause();
                break;
            case Const.MOVIE_GUIDE:
                imageView.setMovieResource(R.raw.movie_guide);
                setPause();
                break;
            case Const.ONE_GUIDE:
                initAnimation();
                imageView.setMovieResource(R.raw.one_guide);
                setPause();
                break;
        }
    }

    public void initAnimation(){
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_welcome_btn);
        animation.setFillAfter(true);
    }

    public void startBtn(){
        welcome_fragment_tv.setVisibility(View.VISIBLE);
        welcome_fragment_tv.startAnimation(animation);
        welcome_fragment_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
    }
    public void stopBtn(){
        welcome_fragment_tv.setVisibility(View.GONE);
    }

    public void setPause(){
        imageView.setPaused(true);
    }

    public void start(){
        imageView.setPaused(false);
    }
}
