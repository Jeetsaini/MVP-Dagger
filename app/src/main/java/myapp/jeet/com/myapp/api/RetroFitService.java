package myapp.jeet.com.myapp.api;

import myapp.jeet.com.myapp.api.model.model.ArtistsSearch;
import myapp.jeet.com.myapp.api.model.model.Tracks;
import myapp.jeet.com.myapp.model.CityListResponse;
import myapp.jeet.com.myapp.model.Response;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Admin on 3/8/2017.
 */

public interface RetroFitService {

    @GET(Constants.ARTIST_SEARCH) Observable<ArtistsSearch> searchArtist(
            @Query(Constants.QUERY_SEARCH) String artist);

    @GET(Constants.ARTIST_TRACKS) Observable<Tracks> searchTrackList(
            @Path(Constants.PATH_ARTIST_TRACKS) String artistId);
    @GET("v1/city")
    Observable<CityListResponse> getCityList();
}
