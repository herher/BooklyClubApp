package ws.com.wstest.Interface;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import ws.com.wstest.R;
import ws.com.wstest.controllor.Utility;

/**
 * Inscription Activity Class
 */
public class InscriptionActivity extends Activity {

    ProgressDialog prgDialog;
    TextView errorMsg;
    EditText nomET;
    EditText prenomdET;
    EditText emailET;
    EditText pwdET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_activity);

        errorMsg = (TextView)findViewById(R.id.register_error);

        nomET = (EditText)findViewById(R.id.registerNom);
        prenomdET = (EditText)findViewById(R.id.registerPrenom);
        emailET = (EditText)findViewById(R.id.registerEmail);
        pwdET = (EditText)findViewById(R.id.registerPassword);



        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Veuillez patientez...");
        prgDialog.setCancelable(false);
    }

    /**
     * Method gets triggered when Inscription button is clicked
     *
     * @param view
     */
    public void registerUser(View view){

        String nom = nomET.getText().toString();
        String prenom= prenomdET.getText().toString();
        String email = emailET.getText().toString();
        String password = pwdET.getText().toString();

        RequestParams params = new RequestParams();

        if(Utility.isNotNull(nom)&& Utility.isNotNull(prenom) && Utility.isNotNull(email) && Utility.isNotNull(password)){
            if(Utility.validate(email)){

                params.put("name", nom);
                params.put("prenom", prenom);
                params.put("email", email);
                params.put("password", password);

                invokeWS(params);
            }
            else{
                Toast.makeText(getApplicationContext(), "Entrez votre email", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "S'il vous plait entrez votre email et votre password", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void invokeWS(RequestParams params){
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://mbchir-001-site1.itempurl.com/api2/Ws/findEmail",params ,new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {

                prgDialog.hide();
                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    // When the JSON response has status boolean value assigned with true
                    if(obj.getBoolean("status")){
                        // Set Default Values for Edit View controls
                        setDefaultValues();
                        // Display successfully registered message using Toast
                        Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
                    }
                    // Else display error message
                    else{
                        errorMsg.setText(obj.getString("error_msg"));
                        Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }
            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // Hide Progress Dialog
                prgDialog.hide();
                // When Http response code is '404'
                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else{
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Method which navigates from Inscription Activity to Login Activity
     */
    public void navigatetoLoginActivity(View view){
        Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }

    /**
     * Set degault values for Edit View controls
     */
    public void setDefaultValues(){
        nomET.setText("");
        prenomdET.setText("");
        emailET.setText("");
        pwdET.setText("");
    }

}