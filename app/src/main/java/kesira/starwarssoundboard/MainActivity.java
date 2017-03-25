package kesira.starwarssoundboard;

import android.app.ActionBar;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void playSound(View v) {
        Button b = (Button) v;
        String sound = b.getTag().toString();
        MediaPlayer mp = MediaPlayer.create(this, getResources().getIdentifier(sound,"raw",getPackageName()));
        mp.start();
    }
}
