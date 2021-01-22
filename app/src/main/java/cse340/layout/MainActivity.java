package cse340.layout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.support.constraint.ConstraintLayout;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a driver activity for the Layout exercise. The main activity view consists of
 * of tab bar located at the bottom of the window and a RelativeLayout where it displays
 * the appropriate view for the selected tab.
 */

/**
 * The following images are used according to the fair use license on https://unsplash.com/license
 *  duckling
 *  monkeys
 *  tiger
 *  dolphin
 *  racoons
 *  okapi
 *  baby white fox
 *  husky puppies
 *  chameleon
 *  The hedgehog is from https://www.pexels.com/
 *  The corgi is courtesy of Mia Nasahura
 *  The squirrel and mallard are courtesy of Zach Cheung
 *  The heron is courtesy of Lauren Bricker
 *  The bee is courtesy of My Tran
 *  The Shiba Inu is courtesy of Art Station https://cdnb.artstation.com
 */


public class MainActivity extends TabActivity {
    /** Default params for tab views, just match parents. */
    public static final RelativeLayout.LayoutParams PARAMS = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vertical margin for Part 3 and 4 in px converted from dp.
        final int defaultMargin = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin);

        // Get tab related views.
        ConstraintLayout contents = findViewById(R.id.tab_contents);  // Our primary view.
        TabView nav = findViewById(R.id.bottom_nav);     // Tabs at the bottom of screen.
        contents.setBackgroundColor(Color.WHITE);

        // Get the vMargin from the resources, used in all but part 1
        int vMargin = getIntent().getIntExtra("vMargin", defaultMargin);

        // Register callback on item selected. This is called each time a tab is pressed.
        nav.setOnNavigationItemSelectedListener((item) -> {
            contents.removeAllViews();  // Clear the currently displayed view.

            // Set tab contents based on selected item.
            switch (item.getItemId()) {
                case R.id.action_part_1:
                    setCurrentTabId(R.id.action_part_1);
                    contents.addView(getLayoutInflater().inflate(R.layout.part1, null), PARAMS);
                    return true;
                case R.id.action_part_2:
                    setCurrentTabId(R.id.action_part_2);
                    contents.addView(new Part2View(this, getImages("data2.csv"), vMargin), PARAMS);
                    return true;
                case R.id.action_part_3:
                    setCurrentTabId(R.id.action_part_3);
                    contents.addView(new Part3View(this, getImages("data3.csv"), vMargin), PARAMS);
                    return true;
                case R.id.action_part_4:
                    setCurrentTabId(R.id.action_part_4);
                    contents.addView(new Part4View(this), PARAMS);
                    return true;
                default:
                    Log.e("CSE340","Unrecognized nav item selected: " + item.getTitle());
            }
            return false;
        });

        nav.setSelectedItemId(getSavedTabId(savedInstanceState, R.id.action_part_1));
    }


    /**
     * Retrieves the images and their accessible content descriptions from the speficied csv file.
     * @param fileName The image file. Format should be <resourcename>, <accessible description>
     * @return Returns a list of ImageViews with that resource and description
     */
    @NonNull
    public List<ImageView> getImages(String fileName) {
        List<ImageView> imageList = new ArrayList<>();

        try {
            Scanner scan = new Scanner(new InputStreamReader(getAssets().open(fileName)));
            scan.useDelimiter("[,\n$]");
            while (scan.hasNextLine()) {
                String imageName = scan.next();
                String imageDescription = scan.nextLine();
                ImageView img = new ImageView(this); // create the image
                img.setContentDescription(imageDescription);
                // says that the image file is in res/drawable/[resource]
                img.setImageResource(getResources().getIdentifier(imageName, "drawable", "cse340.layout"));
                img.setAdjustViewBounds(true);          // it will adjust its bounds to preserve the aspect ratio of its drawable.
                imageList.add(img);
            }
        } catch (IOException e) {
            throw new IllegalStateException(fileName + " not found in assets");
        } catch (InputMismatchException e) {
            throw new IllegalStateException(fileName + " is malformed");
        }
        return imageList;
    }
}
