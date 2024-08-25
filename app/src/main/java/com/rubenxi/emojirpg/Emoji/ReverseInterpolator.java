package com.rubenxi.emojirpg.Emoji;

import android.view.animation.Interpolator;

public class ReverseInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        return Math.abs(input - 1f);
    }
}