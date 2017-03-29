package kesira.starwarssoundboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FavoritesFragment extends Fragment {

    private String bTag;

    public FavoritesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);

        LinearLayout ll = (LinearLayout) v.findViewById(R.id.linear);
        LinearLayout.LayoutParams lpTop = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lpBottom = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lpTop.setMargins(0, (int) dipToPixels(v.getContext(), 11), 0, (int) dipToPixels(v.getContext(), 5));
        lp.setMargins(0, 0, 0, (int) dipToPixels(v.getContext(), 5));
        lpBottom.setMargins(0, 0, 0, (int) dipToPixels(v.getContext(), 11));

        try {
            InputStream inputStream = v.getContext().openFileInput("favorites.txt");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String tag = bufferedReader.readLine();
                Button bTop = new Button(v.getContext());
                bTop.setLayoutParams(lpTop);
                bTop.setTag(tag);
                bTop.setText(getResources().getIdentifier("@string/button_" + tag, "string", getActivity().getPackageName()));
                bTop.setAllCaps(false);
                bTop.setTextSize(18);
                bTop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).playSound(view);
                    }
                });
                registerForContextMenu(bTop);
                ll.addView(bTop);

                for (int i = 1; i <= 3; i++) {
                    Button b = new Button(v.getContext());
                    b.setLayoutParams(lp);
                    b.setTag(tag);
                    b.setText(getResources().getIdentifier("@string/button_" + tag, "string", getActivity().getPackageName()));
                    b.setAllCaps(false);
                    b.setTextSize(18);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((MainActivity)getActivity()).playSound(view);
                        }
                    });
                    registerForContextMenu(b);
                    ll.addView(b);
                }

                Button bBottom = new Button(v.getContext());
                bBottom.setLayoutParams(lpBottom);
                bBottom.setTag(tag);
                bBottom.setText(getResources().getIdentifier("@string/button_" + tag, "string", getActivity().getPackageName()));
                bBottom.setAllCaps(false);
                bBottom.setTextSize(18);
                bBottom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).playSound(view);
                    }
                });
                registerForContextMenu(bBottom);
                ll.addView(bBottom);

                inputStream.close();
            }
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu_remove, menu);
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
}