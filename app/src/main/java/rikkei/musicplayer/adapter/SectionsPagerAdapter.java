package rikkei.musicplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import rikkei.musicplayer.tab.FragmentAlbum;
import rikkei.musicplayer.tab.FragmentArtist;
import rikkei.musicplayer.tab.FragmentSong;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentSong fragmentSong = new FragmentSong();
                return fragmentSong;
            case 1:
                FragmentAlbum fragmentAlbum = new FragmentAlbum();
                return fragmentAlbum;
            case 2:
                FragmentArtist fragmentArtist = new FragmentArtist();
                return fragmentArtist;
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SONGS";
            case 1:
                return "ALBUMS";
            case 2:
                return "ARTISTS";
        }
        return null;
    }
}
