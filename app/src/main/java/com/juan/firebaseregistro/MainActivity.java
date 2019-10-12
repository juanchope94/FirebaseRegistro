package com.juan.firebaseregistro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtCorreo, edtContrasena;
    TextView txtCrear;
    Button btnIniciar;
    SignInButton btnGmail;
    CallbackManager callbackManager;
    DatabaseReference actDatabase;

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String actFecha = dateFormat.format(date).toString();

    public static final int SIGN_IN_CODE = 777;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    GoogleSignInClient  mGoogleSignInClient;


    private GoogleApiClient googleApiClient;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        edtCorreo = findViewById(R.id.editCorreo);
        edtContrasena = findViewById(R.id.editContrasena);
        txtCrear = findViewById(R.id.textCrear);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(this);
        btnGmail = findViewById(R.id.btnGmail);
        btnGmail.setOnClickListener(this);
        actDatabase = FirebaseDatabase.getInstance().getReference();




        progressDialog = new ProgressDialog(this);
        txtCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistroDatos.class));

            }
        });

        //Configurando el api de google para el inicio con gmail
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("488243293443-fd77vrrqfckoqk77d6u9f1q60arb9ddh.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);

        //Implementacion inicio de sesion con facebook
        callbackManager= CallbackManager.Factory.create();
        LoginButton loginButton =(LoginButton) findViewById(R.id.btnFace);
        loginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                progressDialog.setMessage("Iniciando");
                progressDialog.show();
                final String accestoken= loginResult.getAccessToken().getToken();
                GraphRequest request= GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        progressDialog.dismiss();
                        Intent pasar = new Intent(MainActivity.this,fragmento.class);
                        try {
                            firebaseCredencial(accestoken,1);
                            String profile_picture= "https://graph.facebook.com/"+object.getString("id")+"/picture?width=250&height=250";
                            pasar.putExtra("imagen",profile_picture);
                            pasar.putExtra("email",object.getString("email"));
                            startActivity(pasar);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields","id,email,birthday,friends");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "cancelll", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });

        printKeyHash();

    }

    private void firebaseCredencial(String accestoken, int opcion)
    {
        AuthCredential credential=null;
        if(opcion==1) {
             credential = FacebookAuthProvider.getCredential(accestoken);
        }
        else{
            credential = GoogleAuthProvider.getCredential(accestoken, null);
        }
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this, "najsdnj", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    private void printKeyHash()
    {

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.juan.firebaseregistro", PackageManager.GET_SIGNATURES);
            for(Signature signature: info.signatures)
            {
                MessageDigest md= MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("Hash", "KeyHas " +Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //verifica si ahi una sesión iniciada

    @Override
    protected void onStart() {
        super.onStart();
        // para mantener sesion activada
        FirebaseUser currentUser = mAuth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(currentUser);
    }
    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }


    @Override
    public void onClick(View v) {

        if (v.getId()== R.id.btnIniciar) {
            iniciarSesion();
        }

        if(v.getId()==R.id.btnGmail) {
            iniciarGmail();
        }
    }

    private void iniciarGmail() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,SIGN_IN_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode,resultCode,data);

        if(resultCode==Activity.RESULT_OK) {

            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
            switch ( requestCode ) {

                case SIGN_IN_CODE:

                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        firebaseCredencial(account.getIdToken(),2);
                        Intent pasar = new Intent(MainActivity.this,Principal.class);
                        pasar.putExtra("imagen",account.getPhotoUrl().toString());
                        pasar.putExtra("email",account.getEmail());
                        startActivity(pasar);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
                // The Task returned from this call is always completed, no need to attach
                // a listener.


            }
        }
    }

    public void iniciarSesion()
    {

        final String email = edtCorreo.getText().toString().trim();
        String password = edtContrasena.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(MainActivity.this, "El email es campo obligatorio", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "la constraseña es campo obligatorio", Toast.LENGTH_SHORT).show();
        }
        else {

            progressDialog.setMessage("Iniciando sesion....");
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                int pos= email.indexOf("@");
                                String nombre= email.substring(0,pos);
                                // Sign in success, update UI with the signed-in user's information
                                Intent pasar= new Intent(MainActivity.this,Principal.class);
                                pasar.putExtra("nombre",nombre);
                                startActivity(pasar);
                                Toast.makeText(MainActivity.this, "Bienvenido!", Toast.LENGTH_SHORT).show();
                                //actualizar la fecha





                            } else {
                                // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity.this, "Upss a ocurrido un error "+task.getException(), Toast.LENGTH_LONG).show();
                            }

                            progressDialog.dismiss();

                            // ...
                        }
                    });

        }


    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(getBaseContext(), fragmento.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }



}
