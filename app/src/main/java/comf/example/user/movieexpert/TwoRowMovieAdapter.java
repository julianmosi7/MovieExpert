package comf.example.user.movieexpert;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TwoRowMovieAdapter extends BaseAdapter {
    private List<Movie> movies = new ArrayList<>();
    private int layoutId;
    private LayoutInflater inflater;

    public TwoRowMovieAdapter(Context ctx, int layoutId, List<Movie> movies){
        this.movies = movies;
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = movies.get(position);
        View listItem = (convertView == null) ? inflater.inflate(this.layoutId, null) : convertView;

        ImageView view = (ImageView) convertView;
        if(view == null){
            //view = new ImageView();
        }
        String url = movie.getBackdrop_path();
        //ImageView imgview = convertView.findViewById(R.id.imageView);
        Picasso.get().load(url).into(view);
        return listItem;
    }
}
