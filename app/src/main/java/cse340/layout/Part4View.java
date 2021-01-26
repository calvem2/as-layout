package cse340.layout;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;

// Documentation used
// default search icon: https://stackoverflow.com/questions/47338053/using-android-studio-default-search-icon-for-searchview
// text vertical align: https://developer.android.com/reference/android/widget/TextView#attr_android:gravity
// aligning items to left & right of container: https://stackoverflow.com/questions/2099249/aligning-textviews-on-the-left-and-right-edges-in-android-layout
/**
 * This view should imitate an interface you've seen before and like. If you need ideas
 * about what to imitate, check out this repository: http://interactionmining.org/rico
 */
public class Part4View extends ConstraintLayout {

    public Part4View(Context context) {
        super(context);
        // TODO: Build your desired layout for part 4. Make use of a combination of XML and programmatic techniques to accomplish this.

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.part4, this);
    }

}
