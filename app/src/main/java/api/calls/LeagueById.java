package api.calls;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import oscar.alexander.garcia.lolsummonersearch.MainActivity;

/**
 * Get the league information or a summoner.
 * Division, tier, etc. If a response of 400 is returned, the player isn't ranked for the season.
 * /api/lol/{region}/v2.5/league/by-summoner/{summonerIds}/entry
 * Created by Oscar on 3/22/2016.
 */
public class LeagueById extends AsyncTask<Void, Void, Void>{
    private AsyncCallback asyncCallback;
    private String jsonResponse;
    private boolean isSuccess;
    private URL url;
    private long id;
    private String division;
    private String tier;
    private int wins;
    private int losses;

    public LeagueById(long id){
        this.id = id;
        try {
            url = new URL("https://"+MainActivity.mRegionCode+".api.pvp.net/api/lol/"+MainActivity.mRegionCode+"/v2.5/league/by-summoner/"+id+"/entry?api_key="+MainActivity.API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public interface AsyncCallback{
        void leagueCallBack();
    }

    public void registerCallBack(AsyncCallback cb){
        this.asyncCallback = cb;
    }

    @Override
    protected Void doInBackground(Void... params) {
        loadJSON(url);
        return null;
    }

    @Override
    protected void onPostExecute(Void v){
        //parse info
        if(jsonResponse != null){
            Log.d("myapp", jsonResponse);
            try {
                JSONObject obj = new JSONObject(jsonResponse);
                JSONArray arr = obj.getJSONArray(String.valueOf(id));
                for(int i = 0; i < arr.length(); i++){
                    if(arr.getJSONObject(i).getString("queue").equals("RANKED_SOLO_5x5")){
                        JSONArray entriesSubArray = arr.getJSONObject(0).getJSONArray("entries"); //For division, hot streak, etc.
                        setDivision(entriesSubArray.getJSONObject(0).getString("division"));
                        setTier(arr.getJSONObject(0).getString("tier"));
                        setWins(entriesSubArray.getJSONObject(0).getInt("wins"));
                        setLosses(entriesSubArray.getJSONObject(0).getInt("losses"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            isSuccess = true;
        }
        else{
            Log.d("myapp", "League jsonResponse was null");
            isSuccess = false;
        }
        asyncCallback.leagueCallBack();
    }

    private void loadJSON(URL url){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp;
            while(null != (strTemp = br.readLine())){
                jsonResponse = strTemp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}
