package comf.example.user.movieexpert;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Movie> movies = new ArrayList();
    public String FILENAME = "movies.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        try{
            while((line = bin.readLine()) != null){

            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
