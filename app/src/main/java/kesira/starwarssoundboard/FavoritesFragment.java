package kesira.starwarssoundboard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.DisplayMetrics;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);

        LinearLayout ll = v.findViewById(R.id.favorites);
        LinearLayout.LayoutParams lpTop = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lpBottom = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lpTop.setMargins(0, (int) dipToPixels(v.getContext(), 11), 0, (int) dipToPixels(v.getContext(), 5));
        lp.setMargins(0, 0, 0, (int) dipToPixels(v.getContext(), 5));
        lpBottom.setMargins(0, 0, 0, (int) dipToPixels(v.getContext(), 11));

        ArrayList<String> favorites = ((MainActivity) requireActivity()).favorites;
        if (favorites.size() >= 1) {
            Button bTop = new Button(v.getContext());
            bTop.setLayoutParams(lpTop);
            bTop.setTag(favorites.get(0));
            bTop.setText(getResources().getIdentifier("@string/button_" + favorites.get(0), "string", requireActivity().getPackageName()));
            bTop.setAllCaps(false);
            bTop.setTextSize(18);
            bTop.setOnClickListener(view -> ((MainActivity) requireActivity()).playSound(view));
            registerForContextMenu(bTop);
            ll.addView(bTop);

            for (int i = 1; i < favorites.size() - 1; i++) {
                Button b = new Button(v.getContext());
                b.setLayoutParams(lp);
                b.setTag(favorites.get(i));
                b.setText(getResources().getIdentifier("@string/button_" + favorites.get(i), "string", requireActivity().getPackageName()));
                b.setAllCaps(false);
                b.setTextSize(18);
                b.setOnClickListener(view -> ((MainActivity) requireActivity()).playSound(view));
                registerForContextMenu(b);
                ll.addView(b);
            }

            if (favorites.size() >= 2) {
                Button bBottom = new Button(v.getContext());
                bBottom.setLayoutParams(lpBottom);
                bBottom.setTag(favorites.get(favorites.size() - 1));
                bBottom.setText(getResources().getIdentifier("@string/button_" + favorites.get(favorites.size() - 1), "string", requireActivity().getPackageName()));
                bBottom.setAllCaps(false);
                bBottom.setTextSize(18);
                bBottom.setOnClickListener(view -> ((MainActivity) requireActivity()).playSound(view));
                registerForContextMenu(bBottom);
                ll.addView(bBottom);
            }
        }
        return v;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu_remove, menu);
        ((MainActivity) requireActivity()).bTag = v.getTag().toString();
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
}