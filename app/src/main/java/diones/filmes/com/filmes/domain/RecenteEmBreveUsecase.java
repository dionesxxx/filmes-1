package diones.filmes.com.filmes.domain;

import java.util.List;

import javax.inject.Inject;

import diones.filmes.com.filmes.BuildConfig;
import diones.filmes.com.filmes.model.entities.Movie;
import diones.filmes.com.filmes.model.repository.MovieRepository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RecenteEmBreveUsecase implements Usecase<List<Movie>> {

    public final static int DEFAULT_MOVIE_PAGE = 1;
    public static final int DEFAULT_MOVIES_LIMIT = 20;
    private int mMoviePage = DEFAULT_MOVIE_PAGE;
    private final MovieRepository mMovieRepository;
    private int mCurrentPage;

    @Inject
    public RecenteEmBreveUsecase(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    @Override
    public Observable<List<Movie>> execute() {
        increaseOffset();
        return mMovieRepository.getEmBreveMovies(BuildConfig.MOVIE_PUBLIC_KEY, mCurrentPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    private void increaseOffset() {
        mCurrentPage += mMoviePage;
    }
}
