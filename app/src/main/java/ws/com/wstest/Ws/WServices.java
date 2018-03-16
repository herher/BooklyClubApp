package ws.com.wstest.Ws;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import ws.com.wstest.Interface.LoginActivity;
import ws.com.wstest.R;

/**
 * Created by meher on 16/03/2018.
 */

public class WServices {
    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    ProgressDialog prgDialog;
    RequestParams params = new RequestParams();
    LoginActivity currentActi;
   // TextView errorMsg;

    public WServices(LoginActivity currentActi) {
        super();
        prgDialog = new ProgressDialog(currentActi);
        params = new RequestParams();
        this.currentActi = currentActi;
       // errorMsg = (TextView) currentActi.findViewById(R.id.login_error);

    }

    public void invokeWS(RequestParams params){
        // Show Progress Dialog
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
                    Log.d("resultat ",obj.getString("Email"));
                    if(!obj.getString("Email").isEmpty()){
                        Toast.makeText(currentActi.getApplicationContext(), "Communication reussie", Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(), obj.getString("id"), Toast.LENGTH_LONG).show();

                        currentActi.navigatetoAcceuilActivity();
                    }

                    else{
                        currentActi.errorMsg.setText(obj.getString("error_msg"));
                        Toast.makeText(currentActi.getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(currentActi.getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(currentActi.getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if(statusCode == 500){
                    Toast.makeText(currentActi.getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else{
                    Toast.makeText(currentActi.getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
