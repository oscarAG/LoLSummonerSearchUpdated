package api.calls;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import api.objects.ChampionRankedObject;
import oscar.alexander.garcia.lolsummonersearch.MainActivity;

/**
 * Static Data
 * Created by Oscar on 3/20/2016.
 */
public class AllChampionsSquareImage extends AsyncTask<Void, Void, Void> {
    private AsyncCallback asyncCallback;
    private List<URL> urls;
    private List<Bitmap> championSquareImages;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public AllChampionsSquareImage(List<ChampionRankedObject> champObjects){
        success = false;
        this.championSquareImages = new ArrayList<>();
        this.urls = new ArrayList<>();
        try {
            for(int i=0; i < champObjects.size(); i++){
                if(champObjects.get(i).getId() != 0){
                    urls.add(new URL("http://ddragon.leagueoflegends.com/cdn/"+MainActivity.imageVersion+"/img/champion/"+champObjects.get(i).getKey()+".png"));
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.success = false;
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(int i = 0; i < urls.size(); i++){
            try {
                championSquareImages.add(BitmapFactory.decodeStream((InputStream) urls.get(i).getContent()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("myapp", championSquareImages.size() + " champion square images initialized.");
        return null;
    }

    @Override
    protected void onPostExecute(Void v){
        success = true;
        asyncCallback.allChampionSquaresCallBack();
    }

    public List<Bitmap> getChampionSquareImages() {
        return championSquareImages;
    }

    public interface AsyncCallback{
        void allChampionSquaresCallBack();
    }

    public void registerCallBack(AsyncCallback cb){
        this.asyncCallback = cb;
    }
}
