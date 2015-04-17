package com.blundell.quicksand.demo.amazeanimation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blundell.quicksand.Quicksand;
import com.blundell.quicksand.demo.R;
import com.blundell.quicksand.demo.activitytransition.FromHereActivity;
import com.blundell.quicksand.demo.viewanimation.ViewAnimateActivity;

public class AmazeAnimationFragment extends android.app.Fragment {

    private ImageView carImage;
    private View resetButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        carImage = (ImageView) root.findViewById(R.id.main_image);
        resetButton = root.findViewById(R.id.main_reset_button);

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
        resetButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Quicksand.resetTrap("NewKey");
                        Quicksand.resetTrap(FromHereActivity.KEY_MY_ACTIVITY_TRANSITION);
                        Quicksand.resetTrap(ViewAnimateActivity.KEY_ANIM_SHOW_HIDE);
                    }
                });
    }
}
