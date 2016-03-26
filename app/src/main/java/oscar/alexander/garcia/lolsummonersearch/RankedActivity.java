package oscar.alexander.garcia.lolsummonersearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RankedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranked);
        setViews();
    }

    private void setViews(){
        //Season
        TextView season = (TextView)findViewById(R.id.tv_season);
        season.setText(MainActivity.selectedSeason);
        //Profile Icon
        ImageView profileIcon = (ImageView)findViewById(R.id.iv_icon);
        profileIcon.setImageBitmap(MainActivity.summoner.getProfileIcon());
        //Summoner name
        TextView summonerName = (TextView)findViewById(R.id.tv_name);
        summonerName.setText(MainActivity.summoner.getFormattedName());
        //Rank
        TextView rank = (TextView)findViewById(R.id.tv_rank);
        if(!MainActivity.selectedSeason.equals("Season 6")){
            String error = MainActivity.selectedSeason + " rank unavailable.";
            rank.setText(error); //not ranked for current season
        }
        else{
            String rankText = MainActivity.summoner.getDivision() + " " + MainActivity.summoner.getTier();
            rank.setText(rankText); //currently ranked
        }
        //Wins
        TextView winsText = (TextView)findViewById(R.id.tv_winsText);
        String winsStr = getString(R.string.wins_txt) + " ";
        winsText.setText(winsStr);
        TextView winsValue = (TextView)findViewById(R.id.tv_winsValueText);
        String winsValueStr = String.valueOf(MainActivity.summoner.getRanked_solo_wins()) + "   ";
        winsValue.setText(winsValueStr);
        //Losses
        TextView lossesText = (TextView)findViewById(R.id.tv_lossesText);
        String lossesStr = getString(R.string.losses_txt) + " ";
        lossesText.setText(lossesStr);
        TextView lossesValue = (TextView)findViewById(R.id.tv_lossesTextValue);
        lossesValue.setText(String.valueOf(MainActivity.summoner.getRanked_solo_losses()));
    }
}
