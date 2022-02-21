package kesira.starwarssoundboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SoundboardFragment extends Fragment {
    private final int layout;

    SoundboardFragment(int layout) {
        this.layout = layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(layout, container, false);
        int buttonCount = ((ViewGroup) v.findViewById(R.id.buttons)).getChildCount();
        for (int i = 1; i <= buttonCount; i++) {
            registerForContextMenu(v.findViewById(MainActivity.resources.getIdentifier("button" + i, "id", MainActivity.packageName)));
        }
        return v;
    }
}