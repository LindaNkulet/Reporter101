package mobilewebfinal.linda.com.reporter101;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.DateFormat;
import java.util.Date;


public class LocationActivity extends AppCompatActivity  {
    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    TextView tvLocation;
    //LocationRequest mLocationRequest;
    //GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    String mLastUpdateTime;
    private FusedLocationProviderClient mFusedLocationClient;
    Button btnLocation;

    private GoogleApiClient mGoogleApiClient = null;
    //private Location mLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        tvLocation = (TextView) findViewById(R.id.textView);
        btnLocation=(Button)findViewById(R.id.buttonLocation);



        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

    }

    public void getLocation(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(LocationActivity.this, "First enable LOCATION ACCESS in settings.", Toast.LENGTH_LONG).show();
            return;
        }
        //int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Toast.makeText(LocationActivity.this, "location is successful", Toast.LENGTH_LONG).show();

                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Toast.makeText(LocationActivity.this, "location is not null", Toast.LENGTH_LONG).show();
                            //LocationServices.FusedLocationApi.requestLocationUpdates(locationApiClient,locationRequest, this);
                            // Logic to handle location object
                            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                            String lat = String.valueOf(location.getLatitude());
                            String lng = String.valueOf(location.getLongitude());
                            tvLocation.setText(
                                    "At Time: " + mLastUpdateTime + "\n" +
                                    "Latitude: " + lat + "\n" +
                                    "Longitude: " + lng + "\n" +
                                    "Accuracy: " + location.getAccuracy() + "\n" +
                                    "Provider: " + location.getProvider());
                        }
                        else{
                            Toast.makeText(LocationActivity.this,"Location not displayed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }









}
