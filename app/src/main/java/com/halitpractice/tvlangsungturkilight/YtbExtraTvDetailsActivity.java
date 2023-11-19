package com.halitpractice.tvlangsungturkilight;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class YtbExtraTvDetailsActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;
    private ImageButton backButton;
    private ImageButton switchOrientationButton;

    private Button redirectButton;
    private ImageView sameVideoButton; // Add the same video button
    private TextView messageTextView;

    private AdView mAdView;
    ImageView closedBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ytb_extra_tv_details);

        backButton = findViewById(R.id.ytb_extra_back_button_cartoon);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
//                Intent intent = new Intent(IndiaCartoonYoutubeDetailsActivity.this, IndiaCartoonYoutubeActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

        redirectButton = findViewById(R.id.ytb_extra_redirectButtonCartoon);
        sameVideoButton = findViewById(R.id.ytb_extra_sameVideoButtonCartoon); // Find the same video button
        switchOrientationButton = findViewById(R.id.ytb_extra_switch_orientation_button_cartoon);
        messageTextView = findViewById(R.id.ytb_extra_messageTextViewCartoon);

        switchOrientationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Orientation", "Button clicked");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            }
        });

        youTubePlayerView = findViewById(R.id.ytb_extra_tv_player_view_cartoon);
        getLifecycle().addObserver(youTubePlayerView);

        String selectedVideoId = getIntent().getStringExtra("live_url");
        if (selectedVideoId != null) {
            loadSelectedVideo(youTubePlayerView, selectedVideoId);
        }

        // Set OnClickListener for the same video button
        sameVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                // Open the YouTube app with the same video ID
                String youtubeAppUrl = "https://www.youtube.com/watch?v=" + selectedVideoId;
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeAppUrl));
                startActivity(appIntent);
                */

                // Show a confirmation dialog before redirecting
                showConfirmationDialog(selectedVideoId);
            }
        });

        mAdView = findViewById(R.id.adViewYtbExtraTvDetails);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        closedBtn = findViewById(R.id.closeBtnYtbExtraTvDetails);
        closedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdView.getVisibility() == View.VISIBLE) {
                    mAdView.setVisibility(View.GONE);
                } else {
                    mAdView.setVisibility(View.VISIBLE);
                }

                if (closedBtn.getVisibility() == View.VISIBLE) {
                    closedBtn.setVisibility(View.GONE);
                } else {
                    closedBtn.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void loadSelectedVideo(YouTubePlayerView playerView, String videoId) {
        Log.d("DetailsActivity", "loadSelectedVideo called");
        boolean isVideoAvailable = true; // Replace with your logic to check video availability

        if (isVideoAvailable) {
            playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0);

                    redirectButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String youtubeAppUrl = "https://www.youtube.com/watch?v=" + videoId;
                            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeAppUrl));
                            startActivity(appIntent);
                        }
                    });
                }
            });
        } else {
//            Toast.makeText(this, "video not working", Toast.LENGTH_LONG).show();
            messageTextView.setText("Bu video mevcut değil. YouTube'da izleyin.");
            redirectButton.setVisibility(View.VISIBLE);
        }
    }

    private void showConfirmationDialog(final String videoId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("Are you sure you want to open this video in the YouTube app or website?")
//        builder.setMessage("Bu videoyu YouTube uygulamasında veya web sitesinde açmak istediğinizden emin misiniz?")
//        builder.setMessage("Are you sure you want to open this movie in the YouTube app?")
        builder.setMessage("Bu filmi YouTube uygulamasında açmak istediğinizden emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Open the YouTube app or website
                        String youtubeAppUrl = "https://www.youtube.com/watch?v=" + videoId;
                        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeAppUrl));
                        startActivity(appIntent);
                    }
                })
                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing, stay in the current video
                    }
                })
                .show();
    }
}
