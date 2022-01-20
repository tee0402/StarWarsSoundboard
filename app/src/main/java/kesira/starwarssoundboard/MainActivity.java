package kesira.starwarssoundboard;

import android.content.Context;
import android.media.MediaPlayer;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    public String bTag;
    public ArrayList<String> favorites = new ArrayList<>();
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
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
        mp.setOnCompletionListener(MediaPlayer::release);
        mp.start();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FavoritesFragment(), "Favorites");
        adapter.addFragment(new OneFragment(), "Obi-Wan and Anakin vs. Dooku");
        adapter.addFragment(new TwoFragment(), "Grievous' Ship");
        adapter.addFragment(new ThreeFragment(), "Tragedy of Darth Plagueis");
        adapter.addFragment(new FourFragment(), "Utapau");
        adapter.addFragment(new FiveFragment(), "Palpatine Reveals Himself");
        adapter.addFragment(new SixFragment(), "Mace Windu vs. Palpatine");
        adapter.addFragment(new SevenFragment(), "Mustafar and Palpatine's Speech");
        adapter.addFragment(new EightFragment(), "Anakin vs. Obi-Wan");
        adapter.addFragment(new NineFragment(), "Others");
        viewPager.setAdapter(adapter);
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private final ArrayList<String> mFragmentTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        private void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @NonNull
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
        public int getItemPosition(@NonNull Object o) {
            return POSITION_NONE;
        }
    }
}