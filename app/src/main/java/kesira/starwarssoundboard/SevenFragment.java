package kesira.starwarssoundboard;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SevenFragment extends Fragment {

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