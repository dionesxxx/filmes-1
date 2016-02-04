package diones.filmes.com.filmes.mvp.views;

/**
 * Created by diones_xxx on 04/02/16.
 */
public interface FilmesView extends View {

    void showConnectionErrorMessage();

    void showServerErrorMessage();

    void showUknownErrorMessage();

    void showWelcomeMessage(String message);

}
