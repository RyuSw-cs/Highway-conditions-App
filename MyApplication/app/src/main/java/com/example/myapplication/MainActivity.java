
package com.example.myapplication;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private Button b1,b2,b3,b4,b5,b6;
    private TextView t1;
    private ImageView iv;
    private DrawerLayout drawerLayout;
    private View drawer;
    private FragmentContainerView fview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer = (View) findViewById(R.id.drawerView);
        b1 = findViewById(R.id.button3);
        b2 = findViewById(R.id.button4);
        b3 = findViewById(R.id.button_push);
        b4 = findViewById(R.id.button_theme);
        b5 = findViewById(R.id.button_app);
        b6 = findViewById(R.id.button_location);

        fview = findViewById(R.id.nav_host_fragment);
        iv = findViewById(R.id.imageView3);
        iv.bringToFront();
        drawerLayout.setDrawerListener(listener);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button3 :
                        drawerLayout.openDrawer(drawer);
                        b1.setVisibility(View.INVISIBLE);
                        iv.setVisibility(View.INVISIBLE);
                        drawer.bringToFront();
                        drawerLayout.bringToFront();
                        break ;
                    case R.id.button_push :
                        Log.d("푸시 텍스트", "onClick: ");

                        break ;
                    case R.id.button_theme :

                        break ;
                    case R.id.button_app :
                        Toast.makeText(MainActivity.this, "android "+getVersionInfo(getApplicationContext()), Toast.LENGTH_SHORT).show();
                        break ;
                    case R.id.button_location :

                        break ;
                    case R.id.button13 :

                        break ;
                }
            }
        } ;
        b1.setOnClickListener(onClickListener);
        b2.setOnClickListener(onClickListener);
        b3.setOnClickListener(onClickListener);
        b4.setOnClickListener(onClickListener);
        b5.setOnClickListener(onClickListener);
        b6.setOnClickListener(onClickListener);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        getSupportActionBar().hide();

    }
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
            b1.setVisibility(View.VISIBLE);
            iv.setVisibility(View.VISIBLE);
            iv.bringToFront();
            fview.bringToFront();
        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
    public String getVersionInfo(Context context){
        String version = null;
        try {
            PackageInfo i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = i.versionName;
        } catch(PackageManager.NameNotFoundException e) { }
        return version;
    }

}