package kesira.starwarssoundboard;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FavoritesFragment extends Fragment {
    private LinearLayout linearLayout;
    private Context context;
    private MainActivity mainActivity;
    private Favorites favorites;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);
        linearLayout = v.findViewById(R.id.favorites);
        context = v.getContext();

        mainActivity = (MainActivity) requireActivity();
        favorites = mainActivity.getFavorites();
        addButtons();

        return v;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        mainActivity.getMenuInflater().inflate(R.menu.context_menu_remove, menu);
        mainActivity.setTagSelected(v.getTag().toString());
    }

    void refresh() {
        linearLayout.removeAllViews();
        addButtons();
    }

    private void addButtons() {
        LinearLayout.LayoutParams bottomMargin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        bottomMargin.setMargins(0, 0, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, MainActivity.resources.getDisplayMetrics()));
        int numFavorites = favorites.size();
        for (int i = 0; i < numFavorites; i++) {
            Button b = new Button(context);
            if (i != numFavorites - 1) {
                b.setLayoutParams(bottomMargin);
            }
            String favorite = favorites.get(i);
            b.setTag(favorite);
            b.setText(MainActivity.resources.getIdentifier("@string/button_" + favorite, "string", MainActivity.packageName));
            b.setAllCaps(false);
            b.setTextSize(18);
            b.setOnClickListener(mainActivity::playSound);
            registerForContextMenu(b);
            linearLayout.addView(b);
        }
    }
}