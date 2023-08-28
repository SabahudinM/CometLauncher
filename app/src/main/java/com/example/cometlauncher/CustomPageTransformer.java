package com.example.cometlauncher;

import android.util.Log;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class CustomPageTransformer implements ViewPager.PageTransformer {


        private static final float MAX_ROTATION = 30.0f; // Maximum rotation angle in degrees

        @Override
        public void transformPage(View page, float position) {
            int width = page.getWidth();
            int pivotX = width / 2;
            int pivotY = page.getHeight() / 2;

                if (position < 0) {
                    // Scroll to the left
                    page.setPivotX(pivotX + (pivotX * -position));
                } else {
                    // Scroll to the right
                    page.setPivotX(pivotX - (pivotX * position));
                }
                // Apply rotation
                
                page.setRotation(MAX_ROTATION * position);
            Log.e("Launcher", "Package name is null for app at position: " + position);


            }
        }

