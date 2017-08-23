package myapp.jeet.com.myapp.presentar;

import javax.inject.Inject;

import myapp.jeet.com.myapp.model.PlayerSpotify;

/**
 * Created by Admin on 5/24/2017.
 */

public class PlayerPresenter {
	@Inject
	PlayerSpotify mPlayerSpotify;

	private PlayerView mPlayerView;

	public PlayerPresenter(PlayerView playerView) {
		mPlayerView=playerView;
	}

	public void startPlayer(String url)
	{
		mPlayerSpotify.playeMusic(url);
		mPlayerView.playingStarted();
	}

	public interface PlayerView
	{
		void playingStarted();
		void stop();
	}
}
