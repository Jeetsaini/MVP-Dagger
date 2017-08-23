package myapp.jeet.com.myapp.model;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by Admin on 5/24/2017.
 */

public class PlayerSpotify {
	private Context mContext;


	public PlayerSpotify(Context context) {
		this.mContext=context;
	}

	public void playeMusic(String url)
	{
		MediaPlayer mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mediaPlayer.setDataSource(mContext, Uri.parse(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			mediaPlayer.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mediaPlayer.start();
	}


}
