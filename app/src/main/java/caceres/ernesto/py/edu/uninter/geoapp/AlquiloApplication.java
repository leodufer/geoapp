package caceres.ernesto.py.edu.uninter.geoapp;

import android.app.Application;
import com.firebase.client.Firebase;

public class AlquiloApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
