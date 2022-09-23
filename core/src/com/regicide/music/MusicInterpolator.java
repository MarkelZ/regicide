package com.regicide.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

public class MusicInterpolator {
    public enum SongChoice {
        S1,
        S2
    }

    private Music song1;
    private Music song2;

    private float timer;
    private float fadeTime;
    private float volume;

    private SongChoice currentChoice;
    private SongChoice nextChoice;
    private boolean isInterpolating;
    private boolean isPlaying;

    /**
     * Creates a new MusicInterpolator with two songs.
     * It can smoothly fade in/out between the songs.
     * Initialized with default values of a fade time of 1 second,
     * and maximum volume of 1.f (volume at 100 %).
     * 
     * @param handle1 FileHandle to first song.
     * @param handle2 FileHandle to second song.
     */
    public MusicInterpolator(FileHandle handle1, FileHandle handle2) {
        this(handle1, handle2, 1.0f, 1.0f);
    }

    /**
     * Creates a new MusicInterpolator with two songs.
     * It can smoothly fade in/out between the songs.
     * 
     * @param handle1  FileHandle to first song.
     * @param handle2  FileHandle to second song.
     * @param fadeTime Time taken to fade between two songs in seconds.
     * @param volume   Volume the songs can be played at. Ranges from 0 to 1.
     */
    public MusicInterpolator(FileHandle handle1, FileHandle handle2, float fadeTime, float volume) {
        song1 = Gdx.audio.newMusic(handle1);
        song1.setLooping(true);
        song1.setVolume(volume);
        song2 = Gdx.audio.newMusic(handle2);
        song2.setLooping(true);
        song2.setVolume(volume);

        timer = 0.f;
        this.fadeTime = fadeTime;
        this.volume = volume;

        currentChoice = SongChoice.S1;
        nextChoice = currentChoice;
        isInterpolating = false;
        isPlaying = false;
    }

    // Update the player.
    public void update(float tdelta) {
        if (!isPlaying)
            return;

        // If isInterpolating, update timer and interpolate between songs
        if (isInterpolating) {
            timer += tdelta;
            if (currentChoice == SongChoice.S1)
                interpolate(song1, song2);
            else
                interpolate(song2, song1);

            // If not interpolating, check if nextSong is different from current song,
            // and if so, start interpolating between the current song and the next one
        } else if (nextChoice != currentChoice) {
            currentChoice = nextChoice;
            if (nextChoice == SongChoice.S1)
                startInterpolation(song1, song2);
            else
                startInterpolation(song2, song1);
        }
    }

    // Interpolate between s1 and s2.
    // s1 will fade in and s2 will fade out.
    private void interpolate(Music s1, Music s2) {
        if (timer >= fadeTime) {
            isInterpolating = false;
            timer = 0.f;
            s2.stop();
            s1.setVolume(volume);
        } else {
            float progress = timer / fadeTime;
            float volumeS1 = volume * progress;
            s1.setVolume(volumeS1);
            s2.setVolume(volume - volumeS1);
        }
    }

    // s1 will fade in and s2 will fade out.
    private void startInterpolation(Music s1, Music s2) {
        s1.setVolume(0.f);
        s1.play();
        s1.setPosition(s2.getPosition());
        s2.setVolume(volume);
        isInterpolating = true;
        timer = 0.f;
    }

    /**
     * Change to chosen song.
     * 
     * If chosen song is already palying, do nothing.
     * If asked to change to new song while interpolating, the player will
     * first carry out the interpolation, and change to new song afterwards.
     * If multiple choices are made while interpolating, only the last one
     * will be taken into account.
     * 
     * @param choice next song to play.
     */
    public void changeToSong(SongChoice choice) {
        if (choice != null)
            nextChoice = choice;
    }

    // Play or resume play.
    // If it was paused while interpolating, it is resumed.
    public void play() {
        isPlaying = true;
        getCurrentSong().play();
        if (isInterpolating)
            getOtherSong().play();
    }

    // Pause music and interpolation.
    public void pause() {
        isPlaying = false;
        getCurrentSong().pause();
        getOtherSong().pause();
    }

    private Music getCurrentSong() {
        return currentChoice == SongChoice.S1 ? song1 : song2;
    }

    private Music getOtherSong() {
        return currentChoice == SongChoice.S1 ? song2 : song1;
    }

    // Getters and setters

    // Is the player currently playing music.
    public boolean isPlaying() {
        return isPlaying;
    }

    // Get maximum volume of player.
    public float getVolume() {
        return volume;
    }

    // Set maximum volume of player.
    public void setVolume(float value) {
        if (value >= 1.f)
            volume = 1.f;
        else if (value <= 0.f)
            volume = 0.f;
        else
            volume = value;

        if (!isInterpolating) {
            getCurrentSong().setVolume(volume);
        }
    }

    // Get playback position in seconds.
    public float getPosition() {
        return getCurrentSong().getPosition();
    }

    // Get time taken to fade in/out between songs.
    public float getFadeTime() {
        return fadeTime;
    }

    // Set time taken to fade in/out between songs.
    public void setFadeTime(float value) {
        if (value <= 0.f)
            fadeTime = 0.f;
        else
            fadeTime = value;
    }

    // Is player currently interpolating between the songs.
    public boolean isInterpolating() {
        return isInterpolating;
    }

    public SongChoice getCurrentSongChoice() {
        return currentChoice;
    }
}
