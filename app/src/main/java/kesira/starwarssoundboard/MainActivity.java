package kesira.starwarssoundboard;

import android.media.MediaPlayer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String tagSelected;
    Favorites favorites = new Favorites(this);
    private final FavoritesFragment favoritesFragment = new FavoritesFragment();
    private final FragmentStateAdapter adapter = new FragmentStateAdapter(this) {
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

        favorites.read();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_add, menu);
        tagSelected = String.valueOf(v.getTag());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            favorites.add(tagSelected);
        } else if (item.getItemId() == R.id.remove) {
            favorites.remove(tagSelected);
        }
        if (favoritesFragment.isAdded()) {
            favoritesFragment.refresh();
        }
        return true;
    }

    public void playSound(View v) {
        Button b = (Button) v;
        String tagClicked = String.valueOf(b.getTag());
        MediaPlayer mp = MediaPlayer.create(this, getResources().getIdentifier(tagClicked, "raw", getPackageName()));
        mp.setOnCompletionListener(MediaPlayer::release);
        mp.start();
    }

    public static class FragmentOne extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_one, container, false);
            for (int i = 1; i <= 23; i++) {
                Button b = v.findViewById((getResources().getIdentifier("button" + i, "id",
                        v.getContext().getPackageName())));
                registerForContextMenu(b);
            }
            return v;
        }
    }
    public static class FragmentTwo extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_two, container, false);
            for (int i = 1; i <= 27; i++) {
                Button b = v.findViewById((getResources().getIdentifier("button" + i, "id",
                        v.getContext().getPackageName())));
                registerForContextMenu(b);
            }
            return v;
        }
    }
    public static class FragmentThree extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_three, container, false);
            for (int i = 1; i <= 14; i++) {
                Button b = v.findViewById((getResources().getIdentifier("button" + i, "id",
                        v.getContext().getPackageName())));
                registerForContextMenu(b);
            }
            return v;
        }
    }
    public static class FragmentFour extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_four, container, false);
            for (int i = 1; i <= 26; i++) {
                Button b = v.findViewById((getResources().getIdentifier("button" + i, "id",
                        v.getContext().getPackageName())));
                registerForContextMenu(b);
            }
            return v;
        }
    }
    public static class FragmentFive extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_five, container, false);
            for (int i = 1; i <= 42; i++) {
                Button b = v.findViewById((getResources().getIdentifier("button" + i, "id",
                        v.getContext().getPackageName())));
                registerForContextMenu(b);
            }
            return v;
        }
    }
    public static class FragmentSix extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_six, container, false);
            for (int i = 1; i <= 43; i++) {
                Button b = v.findViewById((getResources().getIdentifier("button" + i, "id",
                        v.getContext().getPackageName())));
                registerForContextMenu(b);
            }
            return v;
        }
    }
    public static class FragmentSeven extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_seven, container, false);
            for (int i = 1; i <= 8; i++) {
                Button b = v.findViewById((getResources().getIdentifier("button" + i, "id",
                        v.getContext().getPackageName())));
                registerForContextMenu(b);
            }
            return v;
        }
    }
    public static class FragmentEight extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_eight, container, false);
            for (int i = 1; i <= 33; i++) {
                Button b = v.findViewById((getResources().getIdentifier("button" + i, "id",
                        v.getContext().getPackageName())));
                registerForContextMenu(b);
            }
            return v;
        }
    }
    public static class FragmentNine extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_nine, container, false);
            for (int i = 1; i <= 7; i++) {
                Button b = v.findViewById((getResources().getIdentifier("button" + i, "id",
                        v.getContext().getPackageName())));
                registerForContextMenu(b);
            }
            return v;
        }
    }
}