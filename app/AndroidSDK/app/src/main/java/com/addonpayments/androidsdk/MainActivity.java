package com.addonpayments.androidsdk;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.addonpayments.androidsdk.ui.api.EnvioApi;
import com.addonpayments.androidsdk.ui.hpp.clases.Hash;
import com.addonpayments.androidsdk.validaciones.AddonPaymentsRemote;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.addonpayments.androidsdk.ui.hpp.clases.orderID.randomString;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_api, R.id.nav_hpp,
                R.id.nav_redes)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    /* Envío de operación HPP */
    public void enviarHPP(View view) throws JSONException {

        // Calculamos el TimeStamp con formato "AñoMesDíaHoraMinutosSegundos"
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
        String tiempo = s.format(new Date());

        // Generamos el Order ID
        String random = randomString(5);
        String orderID = tiempo + random;

        // Calculamos el SHA1 de los datos obligatorios que debemos enviar a Addon Payments
        String calc1 = Hash.sha1(tiempo + ".addonphptest." + orderID + ".1001.EUR");

        // Calculamos el SHA1 anterior junto con el secreto compartido
        String sha1Final = Hash.sha1(calc1 + ".secret");

        // Formamos el JSON que se va a enviar al servidor de Addon Payments
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "https://hpp.sandbox.addonpayments.com/pay";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("TIMESTAMP", tiempo);
        jsonBody.put("MERCHANT_ID", "addonphptest");
        jsonBody.put("ACCOUNT", "api");
        jsonBody.put("AMOUNT", "1001");
        jsonBody.put("ORDER_ID", orderID);
        jsonBody.put("SHA1HASH", sha1Final);
        jsonBody.put("MERCHANT_RESPONSE_URL", "https://midominio.es/response.php");
        jsonBody.put("AUTO_SETTLE_FLAG", "1");
        jsonBody.put("CURRENCY", "EUR");
        jsonBody.put("HPP_VERSION", "2");
        jsonBody.put("HPP_LANG", "ES");
        jsonBody.put("HPP_CUSTOMER_EMAIL", "test@test.com");
        jsonBody.put("HPP_CUSTOMER_PHONENUMBER_MOBILE", "914353028");
        jsonBody.put("CARD_PAYMENT_BUTTON", "Pagar ticket");
        jsonBody.put("PROD_ID", "ID de producto");
        jsonBody.put("VAR_REF", "NZ2865-Travel");
        jsonBody.put("CUST_NUM", "NZ2865");
        jsonBody.put("BILLING_CO", "ES");
        jsonBody.put("BILLING_CODE", "03201");
        jsonBody.put("SHIPPING_CO", "ES");
        jsonBody.put("SHIPPING_CODE", "03201");
        jsonBody.put("COMMENT1", "Ruta por Nueva Zelanda");
        jsonBody.put("COMMENT2", "1 unidad");
        jsonBody.put("SUPPLEMENTARY_DATA", "Calle Espronceda 7");


        final String requestBody = jsonBody.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

                try {

                    JSONObject object = new JSONObject(response);
                    String enlace = object.getString("hppPayByLink");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(enlace));
                    startActivity(browserIntent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public byte[] getBody() throws AuthFailureError {
                return requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                assert response != null;
                return super.parseNetworkResponse(response);
            }
        };

        requestQueue.add(stringRequest);
    }
    /* Fin de envío de operación HPP */

    /* Envío de operación API */
    @Override
    public void onClick(View v) {

        final EditText Numero, Mes, Any, CVV, Nombre;

        // Debe indicar la ruta de su servidor donde tenga instalada la librería para procesar las transacciones
        final String server_url = "https://midominio.es/Authorization.php";

        final AlertDialog.Builder builder;

        Numero = findViewById(R.id.numbertext);
        Mes = findViewById(R.id.mestext);
        Any = findViewById(R.id.anytext);
        CVV = findViewById(R.id.cvvtext);
        Nombre = findViewById(R.id.nombretext);

        builder = new AlertDialog.Builder(MainActivity.this);

        // Iniciamos los validadores
        if (!AddonPaymentsRemote.ValidateCardNumber(Numero)) {
            return;
        }

        if (!AddonPaymentsRemote.ValidateMesFormat(Mes)) {
            return;
        }

        if (!AddonPaymentsRemote.ValidateYearFormat(Any)) {
            return;
        }

        if (!AddonPaymentsRemote.validateCvn(CVV)) {
            return;
        }

        if (!AddonPaymentsRemote.ValidateCardHolderName(Nombre)) {
            return;
        }

        Toast.makeText(MainActivity.this, "Enviando petición", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                builder.setTitle("Respuesta del servidor");
                builder.setMessage("Petición enviada:\n\n" + response);
                builder.setPositiveButton("Cerrar ventana", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Numero.setText("");
                        Mes.setText("");
                        Any.setText("");
                        CVV.setText("");
                        Nombre.setText("");
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error en la conexión con su servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("numero", Numero.getText().toString());
                params.put("mes", Mes.getText().toString());
                params.put("any", Any.getText().toString());
                params.put("cvv", CVV.getText().toString());
                params.put("nombre", Nombre.getText().toString());
                return params;
            }
        };
        EnvioApi.getInstance(MainActivity.this).addTorequestqueue(stringRequest);
    }
    /* Fin de envío de operación API */

    /* ENLACES */
    // Botón de HOME "Inicio"
    public void inicio(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.addonpayments.com/"));
        startActivity(browserIntent);
    }

    // Botón de HOME "Partner"
    public void partner(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.addonpayments.com/es-es/registro"));
        startActivity(browserIntent);
    }

    // Botón de HOME "PCI"
    public void pci(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pcisecuritystandards.org/"));
        startActivity(browserIntent);
    }

    // Botón de HOME "Documentación"
    public void documentacion(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://desarrolladores.addonpayments.com/#!/"));
        startActivity(browserIntent);
    }

    // Botón de HOME "Llamar a soporte"
    public void soporte(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:914353028"));
        startActivity(intent);
    }

    // Botón de HOME "Email a soporte"
    public void email(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"soporte@addonpayments.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Android SDK - Addon Payments");
        intent.putExtra(Intent.EXTRA_TEXT, "Introduzca aquí su texto");
        startActivity(Intent.createChooser(intent, "Seleccione su cliente de correo"));
    }

    // Botón de Red social "Linkedin"
    public void linkedin(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/comercia-global-payments/"));
        startActivity(browserIntent);
    }

    // Botón de Red social "GitHub"
    public void github(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AddonPayments"));
        startActivity(browserIntent);
    }

    // Botón de Red social "Twitter"
    public void twitter(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/caixabank"));
        startActivity(browserIntent);
    }

    // Botón de Red social "WhatsApp"
    public void whtasapp(View view) {
        PackageManager pm = getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "Descubre el nuevo método de pago de CaixaBank https://www.addonpayments.com/";

            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Compartir con"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp no está instalado", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // Botón de Red social "Compartir"
    public void share(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Descubre el nuevo método de pago de CaixaBank https://www.addonpayments.com/");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    /* FIN ENLACES */
}

