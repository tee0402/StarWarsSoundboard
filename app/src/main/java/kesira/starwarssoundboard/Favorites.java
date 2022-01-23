package kesira.starwarssoundboard;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Favorites {
    private final ArrayList<String> favorites = new ArrayList<>();
    private final Context context;

    Favorites(Context context) {
        this.context = context;
    }

    void read() {
        try {
            InputStream inputStream = context.openFileInput("favorites.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                favorites.add(line);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            write();
        } catch (IOException e) {
            Log.e("Exception", "Reading from saved favorites failed: " + e.toString());
        }
    }

    void write() {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("favorites.txt", Context.MODE_PRIVATE));
            for (String favorite : favorites) {
                outputStreamWriter.write(favorite + "\n");
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "Writing to saved favorites failed: " + e.toString());
        }
    }

    void add(String favorite) {
        if (!favorites.contains(favorite)) {
            favorites.add(favorite);
            write();
        }
    }

    void remove(String favorite) {
        favorites.remove(favorite);
        write();
    }

    ArrayList<String> getFavorites() {
        return favorites;
    }
}
