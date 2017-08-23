package myapp.jeet.com.myapp.ui.player;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import myapp.jeet.com.myapp.R;
import myapp.jeet.com.myapp.api.model.model.Artist;
import myapp.jeet.com.myapp.presentar.PlayerPresenter;
import myapp.jeet.com.myapp.spotify.BaseApplication;

/**
 * Created by Admin on 5/24/2017.
 */

public class SpotifyPlayerActivity extends AppCompatActivity implements PlayerPresenter.PlayerView{
	private PlayerPresenter mPlayerPresenter;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spotify_player_activity);
		initPrsenter();
		initializeDagger();

		ImageView songImage=(ImageView)findViewById(R.id.song_image);
		Artist artist=getIntent().getParcelableExtra("artist");
		Picasso.with(SpotifyPlayerActivity.this).load(artist.artistImages.get(0).url).into(songImage);
        mPlayerPresenter.startPlayer(artist.href);

	}

	private void initPrsenter()
	{
		mPlayerPresenter=new PlayerPresenter(this);
	}

	private void initializeDagger()
	{
		BaseApplication baseApplication=(BaseApplication)getApplication();
		baseApplication.getMainComponent(SpotifyPlayerActivity.this).inject(this);
	}

	@Override
	public void playingStarted() {

	}

	@Override
	public void stop() {

	}
}
