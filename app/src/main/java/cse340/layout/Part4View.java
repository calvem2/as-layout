package cse340.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Documentation used
// default search icon: https://stackoverflow.com/questions/47338053/using-android-studio-default-search-icon-for-searchview
// text vertical align: https://developer.android.com/reference/android/widget/TextView#attr_android:gravity
// aligning items to left & right of container: https://stackoverflow.com/questions/2099249/aligning-textviews-on-the-left-and-right-edges-in-android-layout
// ImageView: https://developer.android.com/reference/android/widget/ImageView#setImageBitmap(android.graphics.Bitmap)
// guide to image views https://guides.codepath.com/android/Working-with-the-ImageView
// cropping image to square: https://stackoverflow.com/questions/26263660/crop-image-to-square-android/39423153
// TextView: https://developer.android.com/reference/android/widget/TextView
// Gravity: https://developer.android.com/reference/android/view/Gravity
// Custom buttons: https://medium.com/@addeeandra/androidstarter-1-i-want-a-round-button-636bc5553d6d
// scrollbar style: https://stackoverflow.com/questions/9494221/hide-scrollbar-in-scrollview
/**
 * This view should imitate an interface you've seen before and like. If you need ideas
 * about what to imitate, check out this repository: http://interactionmining.org/rico
 */
public class Part4View extends ConstraintLayout {

    public Part4View(Context context) {
        super(context);
        // TODO: Build your desired layout for part 4. Make use of a combination of XML and programmatic techniques to accomplish this.

        Random rand = new Random(); // to generate prices

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.part4, this);

        // get images
        List<ImageView> images = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new InputStreamReader(context.getAssets().open("data3.csv")));
            scan.useDelimiter("[,\n$]");
            while (scan.hasNextLine()) {
                String imageName = scan.next();
                String imageDescription = scan.nextLine();
                ImageView img = new ImageView(context); // create the image
                img.setContentDescription(imageDescription);
                // says that the image file is in res/drawable/[resource]
                img.setImageResource(getResources().getIdentifier(imageName, "drawable", "cse340.layout"));
                img.setAdjustViewBounds(true);          // it will adjust its bounds to preserve the aspect ratio of its drawable.
                images.add(img);
            }
        } catch (IOException e) {
            throw new IllegalStateException("data3.csv" + " not found in assets");
        } catch (InputMismatchException e) {
            throw new IllegalStateException("data3.csv" + " is malformed");
        }

        // get reference to listing containers
        LinearLayout left = this.findViewById(R.id.left);
        LinearLayout right = this.findViewById(R.id.right);

        // setup layout params
        LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(leftParams);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(leftParams);

        // set margins
        leftParams.setMargins(0, 10, 5, 0);
        rightParams.setMargins(5, 10,0, 0);
        textParams.setMargins(0, 10, 0, 15);

        for (int i = 0; i < images.size(); i++) {
            ImageView img = images.get(i);

            // get image bitmap
            Bitmap srcMap = ((BitmapDrawable) img.getDrawable()).getBitmap();
            Bitmap dstMap;

            // create new bitmap cropping img to square centered in original image
            int width  = srcMap.getWidth();
            int height = srcMap.getHeight();
            int newWidth = Math.min(height, width);
            int newHeight = (height > width)?  height - ( height - width) : height;
            int cropW = Math.max((width - height) / 2, 0);
            int cropH = Math.max((height - width) / 2, 0);
            dstMap = Bitmap.createBitmap(srcMap, cropW, cropH, newWidth, newHeight);

            // crop
            img.setImageBitmap(dstMap);

            // Construct textview for description
            TextView description = new TextView(context);
            description.setLayoutParams(textParams);

            // set description text
            int price = rand.nextInt(100);
            String descrText = price == 0 ? "Free" : "$" + price;
            descrText += " • " + img.getContentDescription().toString().replace(",", "").trim();
            description.setText(descrText);

            // format description
            description.setEllipsize(TextUtils.TruncateAt.END);
            description.setMaxLines(1);
            description.setGravity(Gravity.CENTER);
            description.setPadding(20, 0, 20, 0);
            description.setTextColor(Color.BLACK);

            // add to layout
            if (i % 2 == 0) {
                img.setLayoutParams(leftParams);
                left.addView(img);
                left.addView(description);
            } else {
                img.setLayoutParams(rightParams);
                right.addView(img);
                right.addView(description);
            }
        }
    }

}
