package com.blundell.quicksand.demo.amazeanimation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blundell.quicksand.demo.R;

public class AmazeAnimationFragment extends android.app.Fragment {

    private ImageView carImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_amaze, container, false);

        carImage = (ImageView) root.findViewById(R.id.amaze_image);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        carImage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ExplodeAnimation animation = new ExplodeAnimation(carImage);
                        animation.animate();
                        animation.setListener(
                                new ExplodeAnimation.AnimationListener() {
                                    @Override
                                    public void onAnimationEnd(ExplodeAnimation animation) {
                                        carImage.setVisibility(View.VISIBLE);
                                    }
                                });
                    }
                });
    }
}
