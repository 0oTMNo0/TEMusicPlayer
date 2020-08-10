package com.example.temusicplayer;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TableLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

   private void initViewPager(){
       ViewPager viewPager = findViewById(R.id.viewPager);
       TabLayout tabLayout = findViewById(R.id.tab_Layout);
       ViewPagerAdapter viewpagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
       viewpagerAdapter.addFragments(new SongsFragment(), "Songs");
       viewpagerAdapter.addFragments(new AlbumFragment(), "Albums");
       viewPager.setAdapter(viewpagerAdapter);
       tabLayout.setupWithViewPager(viewPager);
   }
   public static class ViewPagerAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
       public ViewPagerAdapter(@NonNull FragmentManager fm) {
           super(fm);
           this.fragments = new ArrayList<>();
           this.titles = new ArrayList<>();
       }
       void addFragments(Fragment fragment, String title){
           fragments.add(fragment);
           titles.add(title);
        }
       @NonNull
       @Override
       public Fragment getItem(int position) {
           return fragments.get(position);
       }

       @Override
       public int getCount() {
           return fragments.size();
       }
       @Nullable
       @Override
       public CharSequence getPageTitle(int position){
           return titles.get(position);
       }
   }
}