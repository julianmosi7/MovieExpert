package comf.example.user.movieexpert;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.prefs.BackingStoreException;

public class MainActivity extends AppCompatActivity {

    List<Movie> movies = new ArrayList();
    public String FILENAME = "movies.json";
    private GridView mGridView;
    private TwoRowMovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intializing gridview
        mGridView = findViewById(R.id.grid_view);

        //bind to adapter
        bindAdaptertoGridView(mGridView);

        //save the movies to the movies list
        readAssets();
    }

    private void bindAdaptertoGridView(GridView gv){
        movieAdapter = new TwoRowMovieAdapter(this, R.layout.view, movies);
        gv.setAdapter(movieAdapter);
    }

    private InputStream getInputStreamForAsset(String filename){
        AssetManager assets = getAssets();
        try{
            return assets.open(filename);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private void readAssets(){
        InputStream in = getInputStreamForAsset(FILENAME);
        BufferedReader bin = new BufferedReader(new InputStreamReader(in));
        String line;
        String jString = "";
        try{
            while((line = bin.readLine()) != null){
                jString = jString+line;
            }
            readJson(jString);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void readJson(String jString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            JSONObject jsonObject = new JSONObject(jString);
            JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0;i < results.length();i++){
                JSONObject item = results.getJSONObject(i);
                String id = item.getString("id");
                String vote_average = item.getString("vote_average");
                String title = item.getString("title");
                String backdrop_path = item.getString("backdrop_path");
                String overview = item.getString("overview");
                String release_date = item.getString("release_date");
                Movie movie = new Movie(Integer.parseInt(id), Double.parseDouble(vote_average), title, backdrop_path, overview, sdf.parse(release_date));
                movies.add(movie);
                movieAdapter.notifyDataSetChanged();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
