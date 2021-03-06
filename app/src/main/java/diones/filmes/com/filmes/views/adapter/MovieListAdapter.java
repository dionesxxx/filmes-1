package diones.filmes.com.filmes.views.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import diones.filmes.com.filmes.R;
import diones.filmes.com.filmes.model.entities.Movie;
import diones.filmes.com.filmes.utils.Utils;
import diones.filmes.com.filmes.views.RecyclerClickListener;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private final String NOT_AVAILABLE_URL = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg";
    private final List<Movie> mMovies;
    private final RecyclerClickListener mRecyclerClickListener;
    private Context mContext;

    public MovieListAdapter(List<Movie> movies, Context context, RecyclerClickListener recyclerClickListener) {
        mMovies = movies;
        mRecyclerClickListener = recyclerClickListener;
        mContext = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(
                R.layout.item_movie, parent, false);

            return new MovieViewHolder(rootView, mRecyclerClickListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindAvenger(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_movie_release_date) TextView movieReleaseDateTextView;
        @BindView(R.id.item_movie_title)        TextView movieTitleTextView;
        @BindView(R.id.item_movie_poster)       ImageView movieThumbImageView;
        @BindColor(R.color.colorAccent)     int mColorAccent;

        public MovieViewHolder(View itemView, final RecyclerClickListener recyclerClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bindListener(itemView, recyclerClickListener);
        }

        public void bindAvenger(Movie movie) {
            movieTitleTextView.setText(movie.getOriginal_title());
            movieReleaseDateTextView.setText(Utils.getDateFormat(movie.getRelease_date()));

            if (movie.getPoster_path() == null) {
                ColorDrawable colorDrawable = new ColorDrawable(mColorAccent);
                movieThumbImageView.setDrawingCacheEnabled(true);
                movieThumbImageView.setImageDrawable(colorDrawable);

            } else {
                Glide.with(mContext)
                        .load(Utils.getImageUrl(movie.getPoster_path()))
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .crossFade()
                        .into(movieThumbImageView);
            }
        }

        private void bindListener(View itemView, final RecyclerClickListener recyclerClickListener) {
            itemView.setOnClickListener(v ->
                    recyclerClickListener.onElementClick(getPosition(), movieTitleTextView, movieThumbImageView));
        }
    }
}
