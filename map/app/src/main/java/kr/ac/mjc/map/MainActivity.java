package kr.ac.mjc.map;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //권한 체크
        int findLocation=checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION);
        int coarseLocation=checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION);

        //두 권한중 한개라도 허용되어있지 않은경우
        if(findLocation!=PERMISSION_GRANTED||coarseLocation!=PERMISSION_GRANTED){
            //권한 물어보기
            requestPermissions(new String[]{
                  Manifest.permission.ACCESS_FINE_LOCATION,
                  Manifest.permission.ACCESS_COARSE_LOCATION
            },1000);
        }
        else{
            setMyLocation();
        }

    }

    public void setMyLocation(){
        LocationManager locationManager=getSystemService(LocationManager.class);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,1,this);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.mMap=googleMap;
        mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(new LatLng(37.584650, 126.925183),17)
        );

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.584646, 126.925187))
                .title("명지전문대")
        );

        setMyLocation();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.d("location","location :"+location.getLatitude());
        Log.d("location","location :"+location.getLongitude());

        if(this.mMap!=null){
            mMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                            new LatLng(location.getLatitude(),location.getLongitude()),
                            17
                    )
            );
            mMap.setMyLocationEnabled(true);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        setMyLocation();
    }
}