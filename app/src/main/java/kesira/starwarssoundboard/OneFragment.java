package kesira.starwarssoundboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OneFragment extends Fragment {

    public OneFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        for (int i = 1; i <= 23; i++) {
            Button b = (Button) v.findViewById((getResources().getIdentifier("button" + i, "id",
                    v.getContext().getPackageName())));
            registerForContextMenu(b);
        }
        return v;
    }
}