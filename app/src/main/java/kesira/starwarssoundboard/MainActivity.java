package kesira.starwarssoundboard;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private Favorites favorites;
    private String tagSelected;
    private final FavoritesFragment favoritesFragment = new FavoritesFragment();
    static Resources resources;
    static String packageName;

    Favorites getFavorites() {
        return favorites;
    }

    void setTagSelected(String tagSelected) {
        this.tagSelected = tagSelected;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favorites = new Favorites(this);
        resources = getResources();
        packageName = getPackageName();

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            private final Fragment[] fragments = {
                    favoritesFragment,
                    new SoundboardFragment(R.layout.fragment_one),
                    new SoundboardFragment(R.layout.fragment_two),
                    new SoundboardFragment(R.layout.fragment_three),
                    new SoundboardFragment(R.layout.fragment_four),
                    new SoundboardFragment(R.layout.fragment_five),
                    new SoundboardFragment(R.layout.fragment_six),
                    new SoundboardFragment(R.layout.fragment_seven),
                    new SoundboardFragment(R.layout.fragment_eight),
                    new SoundboardFragment(R.layout.fragment_nine)
            };

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments[position];
            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });
        String[] titles = {
                "Favorites",
                "Obi-Wan and Anakin vs. Dooku",
                "Grievous' Ship",
                "Tragedy of Darth Plagueis",
                "Utapau",
                "Palpatine Reveals Himself",
                "Mace Windu vs. Palpatine",
                "Mustafar and Palpatine's Speech",
                "Anakin vs. Obi-Wan",
                "Others"
        };
        new TabLayoutMediator(findViewById(R.id.tabLayout), viewPager, (tab, position) -> tab.setText(titles[position])).attach();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_add, menu);
        setTagSelected(v.getTag().toString());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == R.id.add) {
            favorites.add(tagSelected);
        } else if (itemID == R.id.remove) {
            favorites.remove(tagSelected);
        }
        if (favoritesFragment.isAdded()) {
            favoritesFragment.refresh();
        }
        return true;
    }

    public void playSound(View v) {
        MediaPlayer mp = MediaPlayer.create(this, resources.getIdentifier(v.getTag().toString(), "raw", packageName));
        mp.setOnCompletionListener(MediaPlayer::release);
        mp.start();
    }
}