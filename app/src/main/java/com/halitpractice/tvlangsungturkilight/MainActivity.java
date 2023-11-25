package com.halitpractice.tvlangsungturkilight;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;
import com.halitpractice.tvlangsungturkilight.RestApi.BaseUrl;
import com.halitpractice.tvlangsungturkilight.activities.DunyaTvActivity;
import com.halitpractice.tvlangsungturkilight.activities.DunyaTvCategoriesActivity;
import com.halitpractice.tvlangsungturkilight.activities.DunyaTvCountriesActivity;
import com.halitpractice.tvlangsungturkilight.services.GDPRConsentManager;
import com.halitpractice.tvlangsungturkilight.services.InAppUpdate;
import com.halitpractice.tvlangsungturkilight.services.InternetConnectivityChecker;
import com.halitpractice.tvlangsungturkilight.services.MarqueeTextHelper;

public class MainActivity extends AppCompatActivity implements InternetConnectivityChecker.InternetConnectivityListener {

    public static final String TERMS_AND_CONDITIONS = "TERMS_AND_CONDITIONS";
    public static final String PLANT_PLACES_PREFS = "PLANT_PLACES_PREFS";

    private long backPressedTime;
    private static final long DOUBLE_PRESS_THRESHOLD = 2000; // 2 seconds

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;  // Declare the ActionBarDrawerToggle

    private AdView mAdView;
    ImageView closedBtn;

    private InAppUpdate inAppUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find and set the Toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Canlı Tv Mobile Tv Turkish");
        }

        // Enable the back button in the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back); // Replace with your custom button icon
        }

        // Add the following lines to clear the back stack when the app starts from MainActivity
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) != 0) {
            finish();
        }

        findViewById(R.id.ulusalTvlerContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, UlusalTvActivity.class))
        );
        findViewById(R.id.ulusalTvlerCategory).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, UlusalTvCategoriesActivity.class))
        );
        findViewById(R.id.yerelTvlerContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, YerelTvActivity.class))
        );
        findViewById(R.id.yerelTvlerKategoriContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, YerelTvCategoriesActivity.class))
        );
        findViewById(R.id.cizgiFilmContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, TurkishCartoonYtbActivity.class))
        );
        findViewById(R.id.radyoDinleContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, RadyoDinleActivity.class))
        );
        findViewById(R.id.yabanciMoviesContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, TurkYabanciMovieActivity.class))
        );
        findViewById(R.id.ytbExtraTvContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, YtbExtraTvActivity.class))
        );
        findViewById(R.id.ytbExtraTvCategoryContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, YtbExtraTvCategoriesActivity.class))
        );
        findViewById(R.id.ytbExtraTvCountriesContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, YtbExtraTvCountriesActivity.class))
        );
        findViewById(R.id.gazetelerContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, GazetelerActivity.class))
        );
        findViewById(R.id.dunyaTvContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, DunyaTvActivity.class))
        );
        findViewById(R.id.dunyaTvKategori).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, DunyaTvCategoriesActivity.class))
        );
        findViewById(R.id.dunyaTvCountriesContent).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, DunyaTvCountriesActivity.class))
        );


        // Initialize your TextView where you'll display marquee text
        TextView marqueeTextView = findViewById(R.id.marqueeTextViewToolBarUnder);
        // Fetch and display marquee text using the helper method
        MarqueeTextHelper.fetchAndDisplayMarqueeText(marqueeTextView);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navView);
        navigationView.setItemIconTintList(null);

        // Initialize the ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        TextView disclaimerButton = headerView.findViewById(R.id.disclaimer);
        disclaimerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DisclaimerWebViewFetchRetrofitActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        /*
        TextView checkUpdateText = headerView.findViewById(R.id.checkForUpdate);
        checkUpdateText.setOnClickListener(v -> {
            String appPackageName = getString(R.string.app_package_name);

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
            startActivity(browserIntent);

        });
        */


        // directly change the color of the DrawerArrowDrawable, which is responsible for drawing the hamburger icon.
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white)); //

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
                if (id == R.id.turkishLiveTv) {
                    // Handle item 1 click
                    Intent intent = new Intent(MainActivity.this, UlusalTvActivity.class);
                    startActivity(intent);
                } else if (id == R.id.turkishLiveTvCategories) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, UlusalTvCategoriesActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.yerel_tv) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, YerelTvActivity.class);
                    startActivity(intent);
                } else if (id == R.id.nav_yerel_tv_kategori) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, YerelTvCategoriesActivity.class);
                    startActivity(intent);
                } else if (id == R.id.radyo) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, RadyoDinleActivity.class);
                    startActivity(intent);
                } else if (id == R.id.cartoon) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, TurkishCartoonYtbActivity.class);
                    startActivity(intent);
                } else if (id == R.id.turkYabanciFilm) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, TurkYabanciMovieActivity.class);
                    startActivity(intent);
                } else if (id == R.id.ytbExtraTv) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, YtbExtraTvActivity.class);
                    startActivity(intent);
                } else if (id == R.id.ytbExtraTvCategory) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, YtbExtraTvCategoriesActivity.class);
                    startActivity(intent);
                } else if (id == R.id.gazeteler) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, GazetelerActivity.class);
                    startActivity(intent);
                } else if (id == R.id.dunyaTvDrawer) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, DunyaTvActivity.class);
                    startActivity(intent);
                } else if (id == R.id.dunyaTvCountriesDrawer) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, DunyaTvCountriesActivity.class);
                    startActivity(intent);
                } else if (id == R.id.dunyaTvCategoryDrawer) {
                    // Handle item 2 click
                    Intent intent = new Intent(MainActivity.this, DunyaTvCategoriesActivity.class);
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        });

        // Check if the Terms and Conditions have not been accepted
        SharedPreferences sharedPreferences = getSharedPreferences(PLANT_PLACES_PREFS, Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(TERMS_AND_CONDITIONS, false)) {
            showTermsAndConditionsDialog();
        }

        mAdView = findViewById(R.id.adViewMainActivity);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        closedBtn = findViewById(R.id.closeBtnMainActivity);
        closedBtn.setOnClickListener(v -> {
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
        });


//        App Auto Update enabled
        inAppUpdate = new InAppUpdate(MainActivity.this);
        inAppUpdate.checkForAppUpdate();

        // Create an instance of GDPRConsentManager
        GDPRConsentManager gdprConsentManager = new GDPRConsentManager(MainActivity.this);
        // Call the method to request consent and initialize ads
        gdprConsentManager.requestConsentAndInitializeAds();

        // Check internet connectivity when your activity is created
        new InternetConnectivityChecker(this, this).execute();

    }

    @Override
    public void onInternetConnectivityChecked(boolean isInternetAvailable) {
        if (isInternetAvailable) {
            // Internet is available, continue with your app logic
        } else {
            // No internet connection
            showNoInternetWarning();
        }
    }

    private void showNoInternetWarning() {
        Toast.makeText(this, "No internet connection. Please check your internet settings.", Toast.LENGTH_LONG).show();
    }

    private void showTermsAndConditionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(Html.fromHtml("Bu uygulamayı kullanarak, <a href=\"" + BaseUrl.getTermsAndConditionsUrl() + "\">Kullanım Koşulları</a> ve " +
                "<a href=\"" + BaseUrl.getPrivacyPolicyUrl() + "\">Gizlilik Politikası</a> maddelerini kabul etmiş olursunuz"))
                .setPositiveButton("Kabul Ediyorum", (dialog, which) -> {
                    // Kabul işlemini burada ele al
                    SharedPreferences prefs = getSharedPreferences(PLANT_PLACES_PREFS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putBoolean(TERMS_AND_CONDITIONS, true);
                    edit.apply();
                })
                .setNegativeButton("İptal", (dialog, which) -> finish());

        // Dialog gösterimi
        AlertDialog dialog = builder.create();
        dialog.show();

        // Linkleri tıklanabilir yap
        TextView messageText = dialog.findViewById(android.R.id.message);
        if (messageText != null) {
            messageText.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }


    // Handle the ActionBarDrawerToggle when the user taps the app bar's home button

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backPressedTime + DOUBLE_PRESS_THRESHOLD > System.currentTimeMillis()) {
                // If the second press is within the threshold, show a confirmation dialog
//                showExitConfirmationDialog();
                showFirstConfirmationDialog();
            } else {
                // Record the timestamp of the first press
                backPressedTime = System.currentTimeMillis();
                Toast.makeText(this, "Çıkmak için tekrar basın", Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

            }
            return true; // Consume the event, preventing default back button behavior
        }
        return super.onKeyDown(keyCode, event);
    }

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_MESSAGE_COUNTER = "MessageCounter";

    private void showFirstConfirmationDialog() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int messageCounter = prefs.getInt(PREF_MESSAGE_COUNTER, 0);
        final int[] finalMessageCounter = {messageCounter};
        if (messageCounter < 2) {
            new AlertDialog.Builder(this)
//                    .setTitle("Important Announcement:")
                    .setTitle("Önemli Duyuru:")
//                    .setMessage("You are ready to close the application. It is important to read this announcement carefully before closing. To restart the application, please touch the application icon on your device's home screen. If you have completely closed the application, please reopen it by using the icon on the home screen, not from the recent apps. Always use this method for the best performance and functionality.")
                    .setMessage("Uygulamayı kapatmaya hazırsınız. Kapatmadan önce bu duyuruyu dikkatlice okumanız önemlidir. Uygulamayı yeniden başlatmak için, lütfen cihazınızın ana ekranındaki uygulama simgesine dokunun. Eğer uygulamayı tamamen kapatmışsanız, lütfen yeniden açmak için son kullanılanlar listesini değil, her zaman ana ekrandaki simgeyi kullanın. En iyi performans ve işlevsellik için bu yöntemi tercih edin.")
//                    .setPositiveButton("OK", (dialog, which) -> {
                    .setPositiveButton("TAMAM", (dialog, which) -> {
                        finalMessageCounter[0]++;
                        prefs.edit().putInt(PREF_MESSAGE_COUNTER, finalMessageCounter[0]).apply();
                        showExitConfirmationDialog();
                    })
                    .show();
        } else {
            showExitConfirmationDialog();
        }
    }


    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
//                .setTitle("Exiting the Application!")
                .setTitle("Uygulamadan Çıkmak Üzeresiniz!")
//                .setMessage("Are you sure you want to close the application?")
                .setMessage("Uygulamayı kapatmak istediğinizden emin misiniz?")
//                .setPositiveButton("Yes", (dialog, which) -> {
                .setPositiveButton("Evet", (dialog, which) -> {
                    finishAffinity(); // Finish all activities in the task
                    finishAndRemoveTask(); // This can be used for API 21 and above
                })
//                .setNegativeButton("No", (dialog, which) -> {
                .setNegativeButton("Hayır", (dialog, which) -> {
                    // The user prefers not to close the app; do nothing
                })
                .show();
    }

    /*
    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit the App?")
                .setMessage("Are you sure you want to close the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the app
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The user prefers not to close the app; do nothing
                    }
                })
                .show();
    }
    */
}