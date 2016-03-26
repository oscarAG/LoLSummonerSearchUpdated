package oscar.alexander.garcia.lolsummonersearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


public class RankedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranked);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Toast toast = Toast.makeText(this, MainActivity.summoner.getFormattedName(), Toast.LENGTH_LONG);
        toast.show();
    }
}
