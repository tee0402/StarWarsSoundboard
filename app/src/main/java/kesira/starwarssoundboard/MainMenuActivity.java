package kesira.starwarssoundboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                if (view.getTag() == (Object) 0) {
                    intent = new Intent(view.getContext(), MainActivity.class);
                }
                else if (view.getTag() == (Object) 1) {
                    intent = new Intent(view.getContext(), MainActivity.class);
                }
                else if (view.getTag() == (Object) 2) {
                    intent = new Intent(view.getContext(), MainActivity.class);
                }
                startActivity(intent);
            }
        });
    }
}
