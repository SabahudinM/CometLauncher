package com.example.cometlauncher;

import androidx.viewpager.widget.ViewPager;

public class SwipeVelocityListener extends ViewPager.SimpleOnPageChangeListener {

    private static final float VELOCITY_THRESHOLD = -1000f; // Adjust the threshold as needed
    private ViewPager viewPager;

    public SwipeVelocityListener(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // Calculate the swipe velocity
        float velocity = Math.abs(positionOffsetPixels / positionOffset);

        // Calculate the number of pages to swipe based on velocity
        int pagesToScroll = 1; // Default to scrolling one page

        if (velocity > VELOCITY_THRESHOLD) {
            pagesToScroll = 2; // Scroll two pages for higher velocity
        }

        // Perform the swipe
        if (positionOffsetPixels > 0) {
            viewPager.setCurrentItem(position + pagesToScroll);
        } else if (positionOffsetPixels < 0) {
            viewPager.setCurrentItem(position - pagesToScroll);
        }
    }
}

