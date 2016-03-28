package oscar.alexander.garcia.lolsummonersearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import oscar.alexander.garcia.lolsummonersearch.MainActivity;
import oscar.alexander.garcia.lolsummonersearch.R;

/**
 * Populate the scrollview in activity_ranked.xml
 * Created by Oscar on 3/27/2016.
 */
public class ChampionsAdapter extends BaseAdapter {
    private Context context;

    public ChampionsAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainActivity.rankedChampObjects.size(); //don't count the overall item
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            return populate(inflater, parent, position);
        }
        else{
            return populate(inflater, parent, position);
        }

    }

    private View populate(LayoutInflater inflater, ViewGroup parent, int position){
        // get layout from custom scrollview item
        View listView = inflater.inflate(R.layout.custom_listview_item, parent, false);
        // Champion name
        TextView textView = (TextView) listView.findViewById(R.id.grid_item_label);
        textView.setText(MainActivity.rankedChampObjects.get(position).getName());
        // Champion image
        ImageView imageView = (ImageView) listView.findViewById(R.id.grid_item_image);
        imageView.setImageBitmap(MainActivity.rankedChampObjects.get(position).getChampionIcon());
        // games value text view
        TextView totalGamesValueTV = (TextView) listView.findViewById(R.id.tv_games_played_value);
        totalGamesValueTV.setText(String.valueOf(MainActivity.rankedChampObjects.get(position).getTotalSessionsPlayed()));
        // win ratio text view
        TextView winRatioValueTV = (TextView) listView.findViewById(R.id.tv_grid_win_ratio_value);
        String winRatio = new DecimalFormat("##.##").format(((double) MainActivity.rankedChampObjects.get(position).getTotalSessionsWon() / ((double) MainActivity.rankedChampObjects.get(position).getTotalSessionsPlayed())) * 100)+"%";
        winRatioValueTV.setText(winRatio);
        return listView;
    }
}
