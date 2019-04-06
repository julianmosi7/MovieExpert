package comf.example.user.movieexpert;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

        //save the movies to the movies list
        readAssets();

        //bind to adapter
        bindAdaptertoGridView(mGridView);


    }

    private void bindAdaptertoGridView(GridView gv){
        movieAdapter = new TwoRowMovieAdapter(this, R.layout.view, movies);
        gv.setAdapter(movieAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menue, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.menu_sort:
                sort_dialog();
                break;
        }
        return super.onOptionsItemSelected(item);
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
                jString += line;
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
                String popularity = item.getString("popularity");
                String poster_path = item.getString("poster_path");
                String overview = item.getString("overview");
                String release_date = item.getString("release_date");
                Movie movie = new Movie(Integer.parseInt(id), Double.parseDouble(vote_average), title, Double.parseDouble(popularity), poster_path, overview, sdf.parse(release_date));
                movies.add(movie);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Movie m :
                movies) {
            System.out.println(m.toString());
        }
    }

    private void sort_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the sort method you want")
                    .setSingleChoiceItems(R.array.items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Toast.makeText(getApplicationContext(), "Sorted by Most popular first", Toast.LENGTH_LONG).show();
                                //sort by most popular first



                                break;
                            case 1:
                                Toast.makeText(getApplicationContext(), "Sorted by Highest-rated first", Toast.LENGTH_LONG).show();
                                Collections.sort(movies, new Comparator<Movie>() {
                                    @Override
                                    public int compare(Movie o1, Movie o2) {
                                        return Double.compare(o1.getVote_average(), o2.getVote_average());
                                    }
                                });
                        }
                       }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                builder.show();



    }

}
