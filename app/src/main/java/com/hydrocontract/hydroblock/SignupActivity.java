package com.hydrocontract.hydroblock;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class SignupActivity extends AppCompatActivity {
    private EditText email, password,username,meter_id;
    Boolean success;
    String wallet_address;
    private TextView signup;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email1);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password1);
        meter_id = findViewById(R.id.meter_id);
        signup = findViewById(R.id.signup1);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: Added information to database: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                }
            }
        };
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignupActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                                    /***
                                     * JSON Parse From API call
                                     */
                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(Signup_API.BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                                            .build();

                                    Signup_API api = retrofit.create(Signup_API.class);
                                    Call<SignupResponse> call=api.getResponse(username.getText().toString(),email.getText().toString(),meter_id.getText().toString());
                                    call.enqueue(new Callback<SignupResponse>() {
                                        @Override
                                        public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {

                                            //In this point we got our hero list
                                            //thats damn easy right ;)
                                            SignupResponse signupResponse = response.body();
                                            System.out.println(signupResponse.getWalletAddress());
                                            wallet_address=signupResponse.getWalletAddress();
                                            if(signupResponse.getSuccess()){
                                                User u = new User(username.getText().toString(), email.getText().toString(), meter_id.getText().toString(),wallet_address);
                                                databaseReference.child("user").child(FirebaseAuth.getInstance().getUid()).setValue(u);
                                                sendVerification();
                                                setResult(8);
                                                finish();
                                            }
                                            success=signupResponse.getSuccess();
                                            //now we can do whatever we want with this list

                                        }

                                        @Override
                                        public void onFailure(Call<SignupResponse> call, Throwable t) {
                                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
            }
        });
    }
    public void sendVerification(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    setResult(8);
                    finish();
                }
                else{
                    overridePendingTransition(0,0);
                    finish();
                    overridePendingTransition(0,0);
                    startActivity(getIntent());
                }
            }
        });
    }
}
