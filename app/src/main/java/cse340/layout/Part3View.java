package cse340.layout;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.util.Collections;
import java.util.List;

public class Part3View extends ScrollView {

    public Part3View(Context context) {
        this(context, Collections.emptyList(), 0);
    }

    /**
     * We want to create a Pinterest-link image flow container. This part requires both XML
     * and programmatic layout.
     * <p>
     * Inflate a ConstraintLayout from part3_grid.xml, which should contain two LinearLayouts inside to evenly divide
     * the width. Remember to add the left/right LinearLayouts to the part3_grid.xml file before
     * proceeding with the remainder of this part.
     * <p>
     * When the view is rotated, the columns should change width to maintain the 50%
     * split. Add each image into one of the LinearLayout columns. Track the "height" of each column
     * and insert the next image in the column with the lower height, or leftmost one if equal.
     * <p>
     * Note: Do not track the height of the Drawables in each column, but the height of the
     * ImageView on the screen. This is done as high-resolution images should not affect the
     * ordering more than lower ones. An alternative is to track the H:W aspect ratio as this will
     * be invariant with regard to photo resolution.
     * <p>
     * Each image should be vMargin from the previous image vertically (or parent if first image).
     * There should be a vMargin gap between the two columns, however, the center of the gap must be
     * in the exact center of the screen. The bottom of the last image should be flush with the
     * bottom of the column.
     * <p>
     * Each image should expand to fill the width as constrained by the margins described above. The
     * height should be such that the aspect ratio matches the source drawable.
     *
     * @param images  List of ImageViews to put in the View
     * @param vMargin Base vertical margin for function in px. Default is 16dp; this is defined in dimens.xml
     */
    public Part3View(Context context, List<ImageView> images, int vMargin) {

        super(context);

        // TODO Design part3_grid.xml
        // TODO Inflate R.layout.part3_grid
        // TODO add it to this View

        // TODO Get references to the left and right columns (LinearLayouts specified in part3_grid)
        // TODO You can use R.id for this if you set up their ID properties properly in part3_grid


        // TODO get access to res so you can find the image files
        for (int i = 0; i < images.size(); i++) {
            // TODO Use LinearLayout.LayoutParams to set the image up since we'll be adding it to a LinearLayout
            // Note: This is much simpler than for ConstraintLayout, it does a lot for you
            // TODO: Be sure to set up vMargin properly

            // TODO: Get the image's height
            // TODO: First you need to ask it to measure itself
            // TODO: Then you can get it's (measured) height

            // TODO: Figure out which column to add it to based on the current column heights
            // TODO update the appropriate height

            // TODO: Apply the layout params after the image has been added
        }
    }
}
