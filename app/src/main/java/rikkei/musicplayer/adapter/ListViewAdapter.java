package rikkei.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

import rikkei.musicplayer.model.Song;
import rikkei.musicplayer.R;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<Song> mListData;
    private LayoutInflater inflater;
    private Context mContext;

    public ListViewAdapter(Context context, ArrayList<Song> mListData) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mListData = mListData;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout songLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_list_item_layout, parent, false);

        TextView songView = (TextView) songLayout.findViewById(R.id.name);
        TextView artistView = (TextView) songLayout.findViewById(R.id.artist);

        // Lấy bài hát hiện
        Song currentSong = mListData.get(position);

        // Lấy tên tiêu đề và tác
        songView.setText(currentSong.getName());
        artistView.setText(currentSong.getArtist());

        // Cài đặt tag cho mỗi bài là vị trí của mỗi
        songLayout.setTag(position);
        return songLayout;
    }
}
