package com.example.lenovo.sensorstest;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor sPressure;
    private Sensor sLight;
    private Sensor sTemparature;
    private Sensor sHumidity;
    private TextView tvPressure, tvLight, tvTemparature, tvHumidty;
    private Button bGraph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sTemparature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_PRESSURE) {
            float roomlight = event.values[0];
            // Do something with this sensor data.
            tvPressure.setText("" + String.valueOf(roomlight));
        }
        else if(event.sensor.getType()==Sensor.TYPE_LIGHT) {
            float roompressure = event.values[0];
            // Do something with this sensor data.
            tvLight.setText("" + String.valueOf(roompressure));
        }
        else if(event.sensor.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE) {
            float roomtemparature = event.values[0];
            // Do something with this sensor data.
            tvTemparature.setText("" + String.valueOf(roomtemparature));
        }
        else if(event.sensor.getType()==Sensor.TYPE_RELATIVE_HUMIDITY) {
            float roomhumidity = event.values[0];
            // Do something with this sensor data.
            tvHumidty.setText("" + String.valueOf(roomhumidity));
        }
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mSensorManager.registerListener(this, sPressure, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, sLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, sTemparature, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, sHumidity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void plotGraph(View view)
    {
        Intent intent = new Intent(FromActivity.this, PlotGraphs.class);
        startActivity(intent);
    }
}
