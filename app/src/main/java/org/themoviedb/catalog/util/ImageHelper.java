package org.themoviedb.catalog.util;

import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.themoviedb.catalog.R;

public class ImageHelper {

    public static void showImage(ImageView image, String url) {
        Picasso.get().load(Uri.parse(url)).error(R.drawable.no_image).into(image);
    }
}
