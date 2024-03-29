package com.example.week5app.contentprovider;

import android.app.Application;
import android.hardware.lights.LightState;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/* public class MovieRepository {
    private MovieDAO mMovieDao;
    private LiveData<List<MovieDataBaseAttributes>> mAllMovieDataBase;

    MovieRepository(Application application) {
        MovieDataBase db = MovieDataBase.getDataBase(application);
        mMovieDao = db.movieDAO();
        mAllMovieDataBase = mMovieDao.getAllMovieAttributes();
    }

    LiveData<List<MovieDataBaseAttributes>> getmAllMovieDataBase() {
        return mAllMovieDataBase;
    }

    void insert(MovieDataBaseAttributes movieDataBaseAttributes) {
        MovieDataBase.databaseWriteExecutor.execute(() -> mMovieDao.addSearchTitle(movieDataBaseAttributes));

        void deleteAll(){
            MovieDataBase.databaseWriteExecutor.execute(() -> {
                mMovieDao.deleteAllMovies();
            });
        }
    }
}
*/

public class MovieRepository {
    private MovieDAO mMovieDao;
    private LiveData<List<MovieDataBaseAttributes>> mAllMovies;

    public MovieRepository(Application application) {
        MovieDataBase db = MovieDataBase.getDataBase(application);
        mMovieDao = db.movieDAO();
        mAllMovies = mMovieDao.getAllMovieAttributes();
    }
    LiveData<List<MovieDataBaseAttributes>> getmAllMovies() {
        return mAllMovies;
    }
    void insert(MovieDataBaseAttributes movieDataBaseAttributes) {
        MovieDataBase.databaseWriteExecutor.execute(() -> {mMovieDao.addMovies(movieDataBaseAttributes);});
    }

    void deleteAllMoviesInRecord(){
        MovieDataBase.databaseWriteExecutor.execute(()->{
            mMovieDao.deleteAllMovies();
        });
    }
    void deleteAll(){
        MovieDataBase.databaseWriteExecutor.execute(()->{
            mMovieDao.deleteitems();
        });
    }
}