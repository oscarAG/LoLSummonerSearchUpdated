package oscar.alexander.garcia.lolsummonersearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import api.calls.ChampionStaticImageData;
import api.calls.ProfileIcon;
import api.calls.RankedStatsById;
import api.calls.SummonerByName;
import api.calls.Versions;
import api.objects.ChampionRankedObject;
import api.objects.RankedStatsByIdObject;

public class MainActivity extends AppCompatActivity implements SummonerByName.AsyncCallback, RankedStatsById.AsyncCallback, ChampionStaticImageData.AsyncCallback, ProfileIcon.AsyncCallback{

    public static final String API_KEY = "5ef85c1b-a4b7-4001-8b12-9a4fad596e08";
    private Spinner mRegionsSpinner;
    private Spinner mSeasonsSpinner;
    private String mRegionCode, mSeasonCode, formattedName;
    private List<ChampionRankedObject> rankedChampObjects;
    public static String imageVersion;
    //API endpoint calls
    private SummonerByName summonerObject;
    private Versions versionsObject;
    private RankedStatsById rankedStatsObject;
    private ChampionStaticImageData champImageDataObject;
    private ProfileIcon profileIconObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize view components
        initializeRegionsSpinner();
        initializeSeasonsSpinner();
    }

    //search button onClick method
    public void search(View v){
        //get values from the view components
        setRegionCode(mRegionsSpinner.getSelectedItem().toString());
        setSeasonCode(mSeasonsSpinner.getSelectedItem().toString());
        EditText nameInput = (EditText)findViewById(R.id.nameInput);
        formattedName = nameInput.getText().toString().toLowerCase().replace(" ", "");
        //execute first call
        summonerObject = new SummonerByName(mRegionCode, formattedName);
        summonerObject.registerCallBack(this);
        summonerObject.execute();
    }

    //1st call finished
    //SummonerByName endpoint
    //Executed when the summoner by name endpoint has finished loading.
    @Override
    public void summonerCallBack() {
        if(summonerObject.isSuccess()){
            Log.d("myapp", "summonerCallBack has succeeded.");
            //print info about the summoner being searched
            //summonerObject.printInfo();
            /*
            //execute second call
            versionsObject = new Versions(mRegionCode);
            versionsObject.registerCallBack(this);
            versionsObject.execute();
            */
            //execute third call
            rankedStatsObject = new RankedStatsById(mRegionCode, summonerObject.getId(), mSeasonCode);
            rankedStatsObject.registerCallBack(this);
            rankedStatsObject.execute();

        }
        else{
            Log.d("myapp", "summonerCallBack did not succeed.");
            Toast toast = Toast.makeText(this, "That summoner doesn't exist. Please try another name.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /* It appears that the most recent version can be obtained from the static champ image data endpoint, making this possibly redundant and unnecessary.
     * Ignoring it for now, I had to remove the implements on the top, so make sure you put it back... todo: determine if this is needed, probably when i'm done.
    //2nd call finished
    //Versions endpoint
    //Executed when the versions endpoint has finished loading.
    @Override
    public void versionsCallBack() {
        if(versionsObject.isSuccess()){
            Log.d("myapp", "versionsCallBack has succeeded.");
            //print versions available for static info
            versionsObject.printInfo();
            //execute third call
            rankedStatsObject = new RankedStatsById(mRegionCode, summonerObject.getId(), mSeasonCode);
            rankedStatsObject.registerCallBack(this);
            rankedStatsObject.execute();
        }
        else{
            Log.d("myapp", "versionsCallBack did not succeed.");
            Toast toast = Toast.makeText(this, "There was an issue getting some data, usually this means the servers are temporarily down.", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    */

    //3rd call finished
    //RankedStatsById endpoint
    //Executed when the ranked stats has finished loading.
    @Override
    public void rankedStatsByIdCallBack(){
        if(rankedStatsObject.isSuccess()){
            Log.d("myapp", "rankedStatsByIdCallBack has succeeded.");
            //print the champion ids and their aggregated statistics
            //rankedStatsObject.printInfo();
            //create a structured list of champion objects
            List<RankedStatsByIdObject> rankedChampRawObjects = rankedStatsObject.getRankedChampionObjects();
            rankedChampObjects = new ArrayList<>();
            for(int i=0; i < rankedChampRawObjects.size(); i++){
                RankedStatsByIdObject ro = rankedChampRawObjects.get(i);
                ChampionRankedObject co = new ChampionRankedObject();
                //set fields
                try {
                    co.setId(ro.getId());
                    co.setTotalDeathsPerSession(ro.getAggreStats().getInt("totalDeathsPerSession"));
                    co.setTotalSessionsPlayed(ro.getAggreStats().getInt("totalSessionsPlayed"));
                    co.setTotalDamageTaken(ro.getAggreStats().getInt("totalDamageTaken"));
                    co.setTotalQuadraKills(ro.getAggreStats().getInt("totalQuadraKills"));
                    co.setTotalTripleKills(ro.getAggreStats().getInt("totalTripleKills"));
                    co.setTotalMinionKills(ro.getAggreStats().getInt("totalMinionKills"));
                    co.setMaxChampionsKilled(ro.getAggreStats().getInt("maxChampionsKilled"));
                    co.setTotalDoubleKills(ro.getAggreStats().getInt("totalDoubleKills"));
                    co.setTotalPhysicalDamageDealt(ro.getAggreStats().getInt("totalPhysicalDamageDealt"));
                    co.setTotalChampionKills(ro.getAggreStats().getInt("totalChampionKills"));
                    co.setTotalAssists(ro.getAggreStats().getInt("totalAssists"));
                    co.setMostChampionKillsPerSession(ro.getAggreStats().getInt("mostChampionKillsPerSession"));
                    co.setTotalDamageDealt(ro.getAggreStats().getInt("totalDamageDealt"));
                    co.setTotalFirstBlood(ro.getAggreStats().getInt("totalFirstBlood"));
                    co.setTotalSessionsLost(ro.getAggreStats().getInt("totalSessionsLost"));
                    co.setTotalSessionsWon(ro.getAggreStats().getInt("totalSessionsWon"));
                    co.setTotalMagicDamageDealt(ro.getAggreStats().getInt("totalMagicDamageDealt"));
                    co.setTotalGoldEarned(ro.getAggreStats().getInt("totalGoldEarned"));
                    co.setTotalPentaKills(ro.getAggreStats().getInt("totalPentaKills"));
                    co.setTotalTurretsKilled(ro.getAggreStats().getInt("totalTurretsKilled"));
                    co.setMostSpellsCast(ro.getAggreStats().getInt("mostSpellsCast"));
                    co.setMaxNumDeaths(ro.getAggreStats().getInt("maxNumDeaths"));
                    co.setTotalUnrealKills(ro.getAggreStats().getInt("totalUnrealKills"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //print the object
                //co.printInfo();
                //add to list
                rankedChampObjects.add(co);
            }
            //execute 4th call
            champImageDataObject = new ChampionStaticImageData(mRegionCode);
            champImageDataObject.registerCallBack(this);
            champImageDataObject.execute();
        }
        else{
            Log.d("myapp", "rankedStatsByIdCallBack did not succeed.");
            Toast toast = Toast.makeText(this, summonerObject.getName() + " doesn't have any ranked stats for " +
                    mSeasonsSpinner.getSelectedItem().toString() + ". Please try another season.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //4th call finished
    //ChampionStaticData endpoint - image
    //Executed when static data image has finished loading.
    @Override
    public void championStaticImageDataCallBack() {
        if(champImageDataObject.isSuccess()){
            Log.d("myapp", "championStaticImageData has succeeded.");
            try {
                //Extract top level data
                JSONObject champData = new JSONObject(champImageDataObject.getJsonResponse()).getJSONObject("data");
                String staticType = new JSONObject(champImageDataObject.getJsonResponse()).getString("type");
                imageVersion = new JSONObject(champImageDataObject.getJsonResponse()).getString("version");
                //Log.d("myapp", "staticType: " + staticType + "\n" + "imageVersion: " + imageVersion);
                //get each champion object
                Iterator<?> keys = champData.keys();
                //traverse
                //todo: somewhat inefficient, runtime of 2n
                while(keys.hasNext()){
                    //todo: when updating champ objects, remember an id of 0 means it's overall stats
                    String key = (String)keys.next();
                    if(champData.get(key) instanceof JSONObject){
                        JSONObject champObject = champData.getJSONObject(key);
                        for(int i = 0; i < rankedChampObjects.size(); i++){
                            //update champ object
                            if(rankedChampObjects.get(i).getId() == champObject.getInt("id")){
                                //Log.d("myapp", "Match found.");
                                rankedChampObjects.get(i).setName(champObject.getString("name"));
                                rankedChampObjects.get(i).setTitle(champObject.getString("title"));
                                rankedChampObjects.get(i).setKey(champObject.getString("key"));
                                rankedChampObjects.get(i).setImageFull(champObject.getJSONObject("image").getString("full"));
                                rankedChampObjects.get(i).setImageSprite(champObject.getJSONObject("image").getString("sprite"));
                                rankedChampObjects.get(i).setImageGroup(champObject.getJSONObject("image").getString("group"));
                                //rankedChampObjects.get(i).printInfo(); //print
                            }
                        }
                    }
                }
                //5th call
                profileIconObject = new ProfileIcon(summonerObject.getProfileIconId());
                profileIconObject.registerCallBack(this);
                profileIconObject.execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            Log.d("myapp", "championStaticImageData did not succeed.");
        }
    }

    //5th call finished
    //Profile icon static data endpoint
    @Override
    public void profileIconCallBack() {
        if(profileIconObject.isSuccess()){
            Log.d("myapp", "profileIcon has succeeded.");
        }
        else{
            Log.d("myapp", "profileIcon did not succeed.");
        }
    }

    //dynamically initialize a spinner for the regions
    private void initializeRegionsSpinner(){
        mRegionsSpinner = (Spinner) findViewById(R.id.spr_regions);
        //mRegionsSpinner.getBackground().setColorFilter(getResources().getColor(R.color.indigo), PorterDuff.Mode.SRC_ATOP);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.regions_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        mRegionsSpinner.setAdapter(adapter);
    }

    //dynamically initialize a spinner for the seasons
    private void initializeSeasonsSpinner(){
        mSeasonsSpinner = (Spinner) findViewById(R.id.spr_seasons);
        //mSeasonsSpinner.getBackground().setColorFilter(getResources().getColor(R.color.indigo), PorterDuff.Mode.SRC_ATOP);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.seasons, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        mSeasonsSpinner.setAdapter(adapter);
    }

    //translate the season spinner selected object into api mnemonic
    private void setSeasonCode(String season){
        switch(season){
            case "Season 6":
                mSeasonCode = "SEASON2016";
                break;
            case "Season 5":
                mSeasonCode = "SEASON2015";
                break;
            case "Season 4":
                mSeasonCode = "SEASON2014";
                break;
            case "Season 3":
                mSeasonCode = "SEASON3";
                break;
            default:
                mSeasonCode = "NULL";
                break;
        }
    }

    //translate the region spinner selected object into an api mnemonic
    private void setRegionCode(String region){
        switch (region) {
            case "North America":
                mRegionCode = "na";
                break;
            case "Brazil":
                mRegionCode = "br";
                break;
            case "EU Nordic/East":
                mRegionCode = "eune";
                break;
            case "EU West":
                mRegionCode = "euw";
                break;
            case "Korea":
                mRegionCode = "kr";
                break;
            case "Latin North":
                mRegionCode = "lan";
                break;
            case "Latin South":
                mRegionCode = "las";
                break;
            case "Oceania":
                mRegionCode = "oce";
                break;
            case "PBE":
                mRegionCode = "pbe";
                break;
            case "Russia":
                mRegionCode = "ru";
                break;
            case "Turkey":
                mRegionCode = "tr";
                break;
            default:
                mRegionCode = "NULL";
                break;
        }
    }
}
