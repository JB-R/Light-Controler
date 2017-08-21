package tounzcompany.light_controler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.ArrayList;

import tounzcompany.light_controler.R;

import static com.philips.lighting.hue.sdk.PHHueSDK.getStoredSDKObject;


public class HomePage extends AppCompatActivity {

    private GridView gridView;
    private PHHueSDK phHueSDK;
    private PHBridgeResourcesCache cache;
    private PHBridge bridge;
    private PHLightState phLightState;
    private ArrayList<PHLight> allLights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        phHueSDK = getStoredSDKObject(); // return in phHueSDK the instance of PHHueSDK of previous activity
        bridge = phHueSDK.getSelectedBridge();

        cacheUpdate();

        displayAllLights();

        onClickOnAnyLight();

    }

    void displayAllLights() {
        gridView = (GridView) findViewById(R.id.grid_view);
        AdapterLights adapter = new AdapterLights(allLights, HomePage.this);
        gridView.setAdapter(adapter);
    }

    void onClickOnAnyLight() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                turnOnLightState(allLights.get(position));
            }
        });
    }

    void turnOnLightState(PHLight phLight) {
        phLightState = new PHLightState(phLight.getLastKnownLightState());
        if (phLightState.isOn()) {
            phLightState.setOn(false);
        } else {
            phLightState.setOn(true);
        }
        bridge.updateLightState(phLight,phLightState);
        cacheUpdate();
    }

    void cacheUpdate(){
        cache = bridge.getResourceCache();
        allLights = new ArrayList<>(cache.getAllLights());
    }
}

