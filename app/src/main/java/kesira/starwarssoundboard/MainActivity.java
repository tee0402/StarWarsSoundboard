package kesira.starwarssoundboard;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    static Resources resources;
    static String packageName;
    String tagSelected;
    Favorites favorites = new Favorites(this);
    private final FavoritesFragment favoritesFragment = new FavoritesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resources = getResources();
        packageName = getPackageName();

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentStateAdapter(this) {
            private final Fragment[] fragments = {favoritesFragment, new FragmentOne(), new FragmentTwo(), new FragmentThree(), new FragmentFour(),
                    new FragmentFive(), new FragmentSix(), new FragmentSeven(), new FragmentEight(), new FragmentNine()};
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
        String[] titles = {"Favorites", "Obi-Wan and Anakin vs. Dooku", "Grievous' Ship", "Tragedy of Darth Plagueis", "Utapau",
                "Palpatine Reveals Himself", "Mace Windu vs. Palpatine", "Mustafar and Palpatine's Speech", "Anakin vs. Obi-Wan", "Others"};
        new TabLayoutMediator(findViewById(R.id.tabs), viewPager, (tab, position) -> tab.setText(titles[position])).attach();

        favorites.read();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu_add, menu);
        tagSelected = String.valueOf(v.getTag());
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
        MediaPlayer mp = MediaPlayer.create(this, resources.getIdentifier(String.valueOf(v.getTag()), "raw", packageName));
        mp.setOnCompletionListener(MediaPlayer::release);
        mp.start();
    }

    public static class FragmentOne extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_one, container, false);
            for (int i = 1; i <= 23; i++) {
                registerForContextMenu(v.findViewById(resources.getIdentifier("button" + i, "id", packageName)));
            }
            return v;
        }
    }
    public static class FragmentTwo extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_two, container, false);
            for (int i = 1; i <= 27; i++) {
                registerForContextMenu(v.findViewById(resources.getIdentifier("button" + i, "id", packageName)));
            }
            return v;
        }
    }
    public static class FragmentThree extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_three, container, false);
            for (int i = 1; i <= 14; i++) {
                registerForContextMenu(v.findViewById(resources.getIdentifier("button" + i, "id", packageName)));
            }
            return v;
        }
    }
    public static class FragmentFour extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_four, container, false);
            for (int i = 1; i <= 26; i++) {
                registerForContextMenu(v.findViewById(resources.getIdentifier("button" + i, "id", packageName)));
            }
            return v;
        }
    }
    public static class FragmentFive extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_five, container, false);
            for (int i = 1; i <= 42; i++) {
                registerForContextMenu(v.findViewById(resources.getIdentifier("button" + i, "id", packageName)));
            }
            return v;
        }
    }
    public static class FragmentSix extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_six, container, false);
            for (int i = 1; i <= 43; i++) {
                registerForContextMenu(v.findViewById(resources.getIdentifier("button" + i, "id", packageName)));
            }
            return v;
        }
    }
    public static class FragmentSeven extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_seven, container, false);
            for (int i = 1; i <= 8; i++) {
                registerForContextMenu(v.findViewById(resources.getIdentifier("button" + i, "id", packageName)));
            }
            return v;
        }
    }
    public static class FragmentEight extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_eight, container, false);
            for (int i = 1; i <= 33; i++) {
                registerForContextMenu(v.findViewById(resources.getIdentifier("button" + i, "id", packageName)));
            }
            return v;
        }
    }
    public static class FragmentNine extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_nine, container, false);
            for (int i = 1; i <= 7; i++) {
                registerForContextMenu(v.findViewById(resources.getIdentifier("button" + i, "id", packageName)));
            }
            return v;
        }
    }
}