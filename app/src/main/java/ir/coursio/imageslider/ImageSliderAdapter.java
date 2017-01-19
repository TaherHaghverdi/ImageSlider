package ir.coursio.imageslider;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Taher on 1/11/2017.
 * Project: ImageSlider
 */

public class ImageSliderAdapter extends FragmentPagerAdapter {
    private String[] imgUrls;

    public ImageSliderAdapter(FragmentManager fm, String[] imgUrls) {
        super(fm);
        this.imgUrls = imgUrls;
    }

    @Override
    public int getCount() {
        return imgUrls.length;
    }

    @Override
    public Fragment getItem(int position) {
        ImageSlider fragment = new ImageSlider();
        return ImageSlider.newInstance(imgUrls[position]);
    }
}
