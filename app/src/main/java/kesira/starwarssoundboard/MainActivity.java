package kesira.starwarssoundboard;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    public String bTag;
    public ArrayList<String> favorites = new ArrayList<>();
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        try {
            InputStream inputStream = this.openFileInput("favorites.txt");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    favorites.add(str);
                }
                inputStream.close();
            }
        }
        catch (IOException e) {
            Log.e("Exception", "Reading from saved favorites failed: " + e.toString());
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_add, menu);
        bTag = v.getTag().toString();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add && !favorites.contains(bTag)) {
            favorites.add(bTag);
        }
        else if (item.getItemId() == R.id.remove) {
            favorites.remove(bTag);
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("favorites.txt", Context.MODE_PRIVATE));
            for (int i = 0; i < favorites.size(); i++) {
                outputStreamWriter.write(favorites.get(i) + "\n");
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "Writing to saved favorites failed: " + e.toString());
        }
    }

    public void playSound(View v) {
        Button b = (Button) v;
        String sound = b.getTag().toString();
        MediaPlayer mp = MediaPlayer.create(this, getResources().getIdentifier(sound,"raw",getPackageName()));
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        mp.start();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FavoritesFragment(), "Favorites");
        adapter.addFragment(new OneFragment(), "Obi-Wan and Anakin vs. Dooku");
        adapter.addFragment(new TwoFragment(), "Tragedy of Darth Plagueis");
        adapter.addFragment(new ThreeFragment(), "Utapau");
        adapter.addFragment(new FourFragment(), "Palpatine Reveals Himself");
        adapter.addFragment(new FiveFragment(), "Mace Windu vs. Palpatine");
        adapter.addFragment(new SixFragment(), "Mustafar and Palpatine's Speech");
        adapter.addFragment(new SevenFragment(), "Anakin vs. Obi-Wan");
        adapter.addFragment(new EightFragment(), "Others");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private final ArrayList<String> mFragmentTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        private void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getItemPosition(Object o) {
            return POSITION_NONE;
        }
    }
}