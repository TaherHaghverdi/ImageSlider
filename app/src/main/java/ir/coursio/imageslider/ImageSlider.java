package ir.coursio.imageslider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Taher on 1/11/2017.
 * Project: ImageSlider
 */

public class ImageSlider extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_slider, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Bundle bundle = getArguments();
        String imgUrl = bundle.getString("imgUrl");

        Picasso.with(getContext())
                .load(imgUrl)
                .placeholder(R.drawable.placeholder)
                .into(imageView);


        return view;
    }

    static ImageSlider newInstance(String imgUrl) {
        ImageSlider slider = new ImageSlider();
        Bundle bundle = new Bundle();
        bundle.putString("imgUrl", imgUrl);
        slider.setArguments(bundle);
        return slider;
    }
}