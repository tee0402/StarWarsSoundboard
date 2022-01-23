package kesira.starwarssoundboard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    private MainActivity mainActivity;
    private ArrayList<String> favorites;
    private LinearLayout ll;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);
        ll = v.findViewById(R.id.favorites);
        context = v.getContext();

        mainActivity = (MainActivity) requireActivity();
        favorites = mainActivity.favorites;
        addButtons();

        return v;
    }

    private void addButtons() {
        int numFavorites = favorites.size();
        for (int i = 0; i < numFavorites; i++) {
            Button b = new Button(context);
            if (i != numFavorites - 1) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics()));
                b.setLayoutParams(lp);
            }
            b.setTag(favorites.get(i));
            b.setText(getResources().getIdentifier("@string/button_" + favorites.get(i), "string", context.getPackageName()));
            b.setAllCaps(false);
            b.setTextSize(18);
            b.setOnClickListener(mainActivity::playSound);
            registerForContextMenu(b);
            ll.addView(b);
        }
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = mainActivity.getMenuInflater();
        inflater.inflate(R.menu.context_menu_remove, menu);
        mainActivity.bTag = String.valueOf(v.getTag());
    }

    void refresh() {
        ll.removeAllViews();
        addButtons();
    }
}