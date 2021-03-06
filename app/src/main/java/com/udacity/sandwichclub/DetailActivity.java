package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    @BindView(R.id.origin_tv) TextView originTextView;
    @BindView(R.id.origin_label_tv) TextView originLabelTextView;
    @BindView(R.id.description_tv) TextView descriptionTextView;
    @BindView(R.id.also_known_tv) TextView alsoKnownTextView;
    @BindView(R.id.also_known_label_tv) TextView alsoKnownLabelTextView;
    @BindView(R.id.ingredients_tv) TextView ingredientsTextView;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            closeOnError();
            return;
        }
        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        Log.i(TAG, "placeOfOrigin is " + placeOfOrigin);
        String description = sandwich.getDescription();
        List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
        List<String> ingredients = sandwich.getIngredients();

        if (TextUtils.isEmpty(placeOfOrigin)){
            originLabelTextView.setVisibility(View.GONE);
            originTextView.setVisibility(View.GONE);
        } else {
            originTextView.setText(placeOfOrigin);
        }

        descriptionTextView.setText(description);

        if (alsoKnownAs.size() == 0){
            alsoKnownLabelTextView.setVisibility(View.GONE);
            alsoKnownTextView.setVisibility(View.GONE);
        } else {
            alsoKnownTextView.setText(convertArrayToString(alsoKnownAs));
        }

        ingredientsTextView.setText(convertArrayToString(ingredients));
    }

    private String convertArrayToString(List<String> array){
        StringBuilder builder = new StringBuilder();
        for (String value : array) {
            builder.append(","+ value);
        }
        builder.deleteCharAt(0);
        return builder.toString();
    }
}
