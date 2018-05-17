package ubi.soft.testlibraries.ui.activities;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import ubi.soft.testlibraries.R;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_POSITION = "position";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        collapsingToolbar.setTitle("Valami név");
        // Set title of Detail page

        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();
        String[] places = resources.getStringArray(R.array.places);
        collapsingToolbar.setTitle(places[position % places.length]);

        String[] placeDetails = resources.getStringArray(R.array.place_details);
        TextView placeDetail = findViewById(R.id.place_detail);
        placeDetail.setText(placeDetails[position % placeDetails.length]);

        String[] placeLocations = resources.getStringArray(R.array.place_locations);
        TextView placeLocation = findViewById(R.id.place_location);
        placeLocation.setText(placeLocations[position % placeLocations.length]);

        TypedArray placePictures = resources.obtainTypedArray(R.array.places_picture);
        ImageView placePicture = findViewById(R.id.image);
        placePicture.setImageDrawable(placePictures.getDrawable(position % placePictures.length()));

        placePictures.recycle();
    }

    public byte[] extractImageToByte(String imgPath, Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }
}
