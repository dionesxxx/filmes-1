package diones.filmes.com.filmes.injector.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import diones.filmes.com.filmes.domain.MovieDetailsUsecase;
import diones.filmes.com.filmes.injector.Activity;
import diones.filmes.com.filmes.model.repository.Repository;
import rx.Scheduler;

@Module
public class MovieInformationModule {

    private final int mMovieId;

    public MovieInformationModule(int movieId) {
        mMovieId = movieId;
    }

    @Provides @Activity MovieDetailsUsecase provideGetCharacterInformationUsecase (
            Repository repository) {

        return new MovieDetailsUsecase(mMovieId, repository);
    }

}
