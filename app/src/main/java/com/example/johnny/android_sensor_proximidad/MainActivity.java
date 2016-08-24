package com.example.johnny.android_sensor_proximidad;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    /*Referencia para cambiar el color del fondo*/
    LinearLayout fondo;
    /*Referencia al sensor*/
    Sensor s;
    /*Administrador de sensores*/
    SensorManager sensorM;
    /*Para mostrar la distancia*/
    TextView txtDistancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Referencias GUI*/
        fondo = (LinearLayout) findViewById(R.id.layoutFondo);
        txtDistancia = (TextView) findViewById(R.id.txtDistancia);

        /*Se define el administrador de sensores*/
        sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);
        /*Se define el sensor de proximidad*/
        s = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        /*Se agrega al administrador de sensores*/
        sensorM.registerListener(MainActivity.this,s,
                SensorManager.SENSOR_DELAY_NORMAL);

    }


    /*Funcion que se ejecuta cuando se cambia la sensibilidad del sensor*/
    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub
    }

    /*Funcion que se ejecuta cuando se detecta algun cambio*/
    @Override
    public void onSensorChanged(SensorEvent evento) {
        //Se captura la distancia
        float valor=Float.parseFloat(String.valueOf(evento.values[0]));
        txtDistancia.setText(String.valueOf(evento.values[0]));

        /*Segun el dispositivo retorna cerca = 0cm o lejos = 5cm
        * o muestra la distancia exacta en cm*/
        if (valor < 5)
            fondo.setBackgroundColor(Color.BLUE);
        else
            fondo.setBackgroundColor(Color.RED);
    }

}
