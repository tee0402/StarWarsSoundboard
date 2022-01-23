package kesira.starwarssoundboard;

import android.content.Context;
import android.media.MediaPlayer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
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
    String bTag;
    ArrayList<String> favorites = new ArrayList<>();
    private final FavoritesFragment favoritesFragment = new FavoritesFragment();
    private final FragmentStateAdapter adapter = new FragmentStateAdapter(this) {
        private final Fragment[] fragments = {favoritesFragment, new OneFragment(), new TwoFragment(), new ThreeFragment(), new FourFragment(),
                new FiveFragment(), new SixFragment(), new SevenFragment(), new EightFragment(), new NineFragment()};
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments[position];
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        String[] titles = {"Favorites", "Obi-Wan and Anakin vs. Dooku", "Grievous' Ship", "Tragedy of Darth Plagueis", "Utapau",
                "Palpatine Reveals Himself", "Mace Windu vs. Palpatine", "Mustafar and Palpatine's Speech", "Anakin vs. Obi-Wan", "Others"};
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(titles[position])).attach();

        try {
            InputStream inputStream = openFileInput("favorites.txt");
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    favorites.add(line);
                }
                inputStream.close();
            }
        }
        catch (IOException e) {
            Log.e("Exception", "Reading from saved favorites failed: " + e.toString());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("favorites.txt", Context.MODE_PRIVATE));
            for (String favorite : favorites) {
                outputStreamWriter.write(favorite + "\n");
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "Writing to saved favorites failed: " + e.toString());
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_add, menu);
        bTag = String.valueOf(v.getTag());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add && !favorites.contains(bTag)) {
            favorites.add(bTag);
        } else if (item.getItemId() == R.id.remove) {
            favorites.remove(bTag);
        }
        if (favoritesFragment.isAdded()) {
            favoritesFragment.refresh();
        }
        return true;
    }

    public void playSound(View v) {
        Button b = (Button) v;
        String sound = String.valueOf(b.getTag());
        MediaPlayer mp = MediaPlayer.create(this, getResources().getIdentifier(sound, "raw", getPackageName()));
        mp.setOnCompletionListener(MediaPlayer::release);
        mp.start();
    }
}