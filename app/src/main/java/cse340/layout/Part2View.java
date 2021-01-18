package cse340.layout;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.util.Collections;
import java.util.List;

// Documentation used
// ConstraintLayout params: https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout.LayoutParams
// ImageView scale type: https://developer.android.com/reference/android/widget/ImageView.ScaleType
public class Part2View extends ScrollView {
    /**
     * For this part, we want to create a scrollable column of images. We have extended the
     * ScrollView to accomplish this.
     *
     * Use a single LinearLayout to contain your images, adding margins as necessary to maintain
     * the desired layout. When the device is rotated, the images should fill the column,
     * utilizing the passed vMargin variable to determine spacing.
     *
     * You may not use the LayoutInflater object to accomplish the given layout. You should be
     * creating your layout from scratch using Java.
     *
     * @param context       The context passed from our active activity.
     * @param images        A list of ImageViews to put in the view.
     * @param vMargin       The base vertical margin. Passed by the activity.
     */
    public Part2View(Context context, List<ImageView> images, int vMargin) {
        super(context);

        ConstraintLayout container = new ConstraintLayout(context); // create the parent container for the images
        addView(container);                         // add it to Part2View
        container.setId(0);                         // set its ID to 0. This lets us refer to it later

        for (int i = 0; i < images.size(); i++) {
            ImageView img = images.get(i);    // The image

            // TODO: Make sure the images scales properly
            // TODO: Add the image to the interactor hierarchy
            // TODO: Set the image ID so you can refer to it later
            // TODO: Create some ConstraintLayout.LayoutParams and set them so the image will expand in X and minimize Y
            // TODO: Set the parameters to use the constraint layout so that the images look like they did in Part1

            // scale image
            img.setAdjustViewBounds(true);
            img.setScaleType(ImageView.ScaleType.FIT_CENTER);

            // set id
            img.setId(i + 1);

            // set layout params
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(vMargin, vMargin, vMargin, 0);
            params.leftToLeft = container.getId();
            params.rightToRight = container.getId();
            if (i == 0) {
                params.topToTop = container.getId();
            } else {
                params.topToBottom = images.get(i - 1).getId();
            }

            img.setLayoutParams(params);

            container.addView(img);
        }
    }

    public Part2View(Context context) {
        this(context, Collections.emptyList(), 0);
    }
}
