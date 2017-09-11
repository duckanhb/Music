package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tab.AlbumsTab;
import tab.ArtistsTab;
import tab.SongTab;

/**
 * Created by DUC on 11/09/2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SongTab songTab = new SongTab();
                return songTab;
            case 1:
                AlbumsTab albumsTab = new AlbumsTab();
                return albumsTab;
            case 2:
                ArtistsTab artistsTab = new ArtistsTab();
                return artistsTab;
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
