package caceres.ernesto.py.edu.uninter.geoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MapsActivity extends FragmentActivity {

    private final String MAIN_SNIPPET = "UNINTER - Sede CENTRAL";
    private final String MAIN_TITLE = "Sede Cidad del Este";
    private List<Local> locales = new ArrayList();
    private GoogleMap mMap;
    private HashMap<Marker, Local> mHashMap = new HashMap<Marker, Local>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        Firebase myFirebaseRef = new Firebase("https://fortune.firebaseio.com/locales");
        myFirebaseRef.keepSynced(true);
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Locales", dataSnapshot.getChildrenCount() + " locales");
                for (DataSnapshot localSnapshot : dataSnapshot.getChildren()) {
                    Local local = (Local) localSnapshot.getValue(Local.class);
                    local.set_id(localSnapshot.getKey());
                    Log.i("Local", local.toString());
                    locales.add(local);
                    addLocalMap(local);
                   mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
                       @Override
                       public void onInfoWindowClick(Marker marker) {
                           final Local l = mHashMap.get(marker);
                           AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                           builder.setTitle(l.getTitulo());
                           String disponible = l.getDisponible()? "Disponible":"No Disoponible";
                           builder.setMessage(l.getDescripcion()
                                   +"\n"+l.getDireccion()
                                   +"\nTel:"+l.getTelefono()
                                   +"\nPrecio: G."+l.getPrecio()
                                   +"\nEstado:"+disponible
                                    );

                           AlertDialog dialog = builder.create();
                           dialog.setButton("LLamar", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Crouton.makeText(MapsActivity.this,"Llamando al "+l.getTelefono(),Style.INFO).show();
                                   Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:"+l.getTelefono()));
                                   startActivity(i);
                               }
                           });
                           dialog.setButton2("Ver Fotos", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   Crouton.makeText(MapsActivity.this, "Ver fotos de " + l.get_id(), Style.INFO).show();

                                   Intent i = new Intent(MapsActivity.this.getApplicationContext(), FotosActivity.class);
                                   i.putExtra("id", l.get_id());
                                   startActivity(i);
                               }
                           });
                           dialog.show();


                       }
                   });
                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            Local l = mHashMap.get(marker);
                            Crouton.makeText(MapsActivity.this,l.getDescripcion(),Style.INFO).show();
                            return false;
                        }
                    });
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("Locales",firebaseError.getMessage());
            }
        });
    }

    private void addLocalMap(Local local) {
        Marker lMarker = this.mMap.addMarker(new MarkerOptions()
                .title(local.getTitulo())
                .snippet("Ver más detalles")
                .position(new LatLng(local.getLat(), local.getLng())));
        mHashMap.put(lMarker,local);
    }

    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (this.mMap == null) {
            this.mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (this.mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        LatLng uninter = new LatLng(-25.502871699909246d, -54.63431775569916d);
        this.mMap.setMyLocationEnabled(true);
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uninter, 16.0f));
        Marker marker = this.mMap.addMarker(new MarkerOptions()
                .title("UNINTER - Sede CENTRAL")
                .snippet("Sede Cidad del Este")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_uninter))
                .position(uninter));

    }
}
