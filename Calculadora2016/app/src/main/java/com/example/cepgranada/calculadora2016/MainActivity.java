package com.example.cepgranada.calculadora2016;

import android.app.AlertDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    TextView tvVisor = null; //Hacemos la variable tvVisor una propiedad de la clase MainActivity

    int iOperando1;
    int iDBotonOperador; //El ID es un entero aunque el operador sea x / + -
    //int iOperando2;
    boolean bHayError = false;
    boolean bHayOperador = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    // TODO: PONER UN FONDO REALISTA (propiedad background)
    // TODO: usar decimales:
    // botonPunto
    // Controlar que haya sólo 1 punto
    // Cambiar los operandos a tipo float (tener en cuenta las conversiones)
    // Usar NaN y Infinity
    // Cambiar el fondo de tvVisor cuando hay error
    // Mostrar la operación que se va haciendo
    // Calculadora científica
    // Crear un layaout apaisado

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Este método se ejecuta siempre al principio
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View vVisor = findViewById(R.id.tvVisor); //Devuelve una referencia al control (devuelve un objeto base o View)
        tvVisor = (TextView) vVisor;
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void botonIgual(View v) {
        // obtener operando2
        String strOperando2 = tvVisor.getText().toString();
        int iOperando2 = Integer.parseInt(strOperando2);
        String resultado = "0";
        switch (iDBotonOperador) {
            case R.id.btMas:
                resultado = String.valueOf(iOperando1 + iOperando2);
                break;
            case R.id.btMenos:
                resultado = String.valueOf(iOperando1 - iOperando2);
                break;
            case R.id.btDivision:
                //Blindamos el código:
                try {
                    resultado = String.valueOf(iOperando1 / iOperando2);
                    // TODO: Usar resultado decimal
                } catch (Exception e) {
                   resultado=e.getMessage();
                    bHayError = true;
                }
                break;
            case R.id.btMultiplicacion:
                resultado = String.valueOf(iOperando1 * iOperando2);
                break;

        }
        // Mostrar resultado
        tvVisor.setText(resultado);
        bHayOperador = true;
    }

    public void botonBorrado(View v) {
        // Poner el texto en 0
        //tvVisor.setText("0"); Mejor usamos el string traducido//
        tvVisor.setText(R.string.visor_por_defecto);
    }

    public void botonOperador(View v) {
        // Guardar el operando1
        // Recupero la cadena del visor
        //Convierto a int
        String strOperando1 = tvVisor.getText().toString();
        // Convierto a entero
        iOperando1 = Integer.parseInt(strOperando1);
        // Guardo el operador
        iDBotonOperador = v.getId();
        //Borrar el visor
        //tvVisor.setText(getText(R.string.visor_por_defecto));
        bHayOperador = true;
    }

    public void btNumerico(View v) {
        if (bHayError) {
            tvVisor.setText(R.string.visor_por_defecto);
            bHayError = false;
        }
        if (bHayOperador) {
            tvVisor.setText(R.string.visor_por_defecto);
            bHayOperador = false;
        }
        // saber el boton: su ID getID()
        // saber el text getText()
        Button boton = (Button) v;
        String strBoton = boton.getText().toString(); //Quitamos el formato al texto que nos sería dado en el idioma seleccionado
        String strVisor = tvVisor.getText().toString();
        // TODO: tener en cuenta el caso de que sea el primer cero
        String strNuevoVisor = strVisor + strBoton; // concatenar strVisor + strBoton
        if (strVisor.equals(getString(R.string.visor_por_defecto))) //getString porque visor_por_defecto es un int
            strNuevoVisor = strBoton;
        //TODO: PONER NÚMERO máximo de dígitos
        tvVisor.setText(strNuevoVisor);  // guardar el nuevo texto en el visor setText()

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.cepgranada.calculadora2016/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.cepgranada.calculadora2016/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
