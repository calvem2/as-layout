package cse340.layout;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;

public class Part3View extends ScrollView {

    /** The image views to layout out */
    protected List<ImageView> mImageViews;

    /** The margin below each image */
    int mVMargin;

    /**
     * We want to create a Pinterest-link image flow container. This part requires both XML
     * and programmatic layout.
     * <p>
     * Inflate a ConstraintLayout from part3_grid.xml, which should contain two LinearLayouts inside to evenly divide
     * the width. Remember to add the left/right LinearLayouts to the part3_grid.xml file before
     * proceeding with the remainder of this part.
     *
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
     * @param images List of drawable names, e.g. "animal_0", to put in the view.
     * @param vMargin Base vertical margin for function in px. Default is 16dp; this is defined in dimens.xml
     */
    public Part3View(Context context, List<ImageView> images, int vMargin) {

        super(context);

        mImageViews = images;
        mVMargin = vMargin;

        // TODO Design part3_grid.xml
        // TODO Inflate R.layout.part3_grid
        // TODO add it to this View

    }

    /**
     * We are overriding  onMeasure() because at this point enough information exists to lay out our views. We will need to
     * first empty both columns, then add one view at a time checking the measurements after each addition.
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Run the measurement code that Android provides to make sure we have a width and height
        // set up for the columns
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // TODO Get references to the left and right columns (LinearLayouts specified in part3_grid)
        // TODO You can use R.id for this if you set up their ID properties properly in part3_grid
        // Then remove all the views in case anything has changed...
        
        int leftHeight = 0, rightHeight = 0;

        // TODO Setup layout params for the images up since we'll be using the same params overr and over
        // Note: This is much simpler than for ConstraintLayout, it does a lot for you. Don't forget to include the margin!

        // Loop through the views
        for (ImageView img : mImageViews) {
            // TODO create a new image and set it up (see Part2View for how to do this)
            // TODO: Figure out which column to add it to based on the current column heights
            // TODO also update the appropriate column height

        }

        // let the toolkit confirm measurement properly
        // once all the images are added.
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
