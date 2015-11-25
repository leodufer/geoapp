package caceres.ernesto.py.edu.uninter.geoapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FotosActivity extends ActionBarActivity {

    List<String> fotos;
    ListView fotosListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos);
       fotosListView = (ListView)findViewById(R.id.listView);

        Bundle extra = getIntent().getExtras();
        String id = extra.getString("id");

        Firebase myFirebaseRef = new Firebase("https://fortune.firebaseio.com/locales/"+id+"/foto");
        myFirebaseRef.keepSynced(true);
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fotos = new ArrayList<String>();
                for (DataSnapshot localSnapshot : dataSnapshot.getChildren()) {
                    String foto = (String) localSnapshot.getValue(String.class);
                    fotos.add(foto);
                }

                FotoAdapter adapter = new FotoAdapter(fotos,getApplicationContext());
                fotosListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("Firebase", firebaseError.getMessage());
            }
        });

    }
}
