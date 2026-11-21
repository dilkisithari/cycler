package lk.nd.cycler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.model.LatLng;

import lk.nd.cycler.database.DatabaseHelper;
import lk.nd.cycler.model.LocationCard;

public class SignUpActivity extends AppCompatActivity {

    TextView signInNavigationLink;
    Button signUpBtn;
    EditText signupUsernameEditText, signupEmailEditText, signUpContactEditText, signUpPasswordEditText;
    private DatabaseHelper dbHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);

        signUpBtn = findViewById(R.id.signUpBtn);
        signupUsernameEditText = findViewById(R.id.signupUsernameEditText);
        signupEmailEditText = findViewById(R.id.signupEmailEditText);
        signUpContactEditText = findViewById(R.id.signUpContactEditText);
        signUpPasswordEditText = findViewById(R.id.signUpPasswordEditText);

        signInNavigationLink = findViewById(R.id.signUpNavigationLink);
        signInNavigationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        long userId = sharedPreferences.getLong("user_id", -1); // If no user_id, return -1

        if (userId != -1) {
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        //signup
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do the signup
                String username = signupUsernameEditText.getText().toString();
                String email = signupEmailEditText.getText().toString();
                String mobile = signUpContactEditText.getText().toString();
                String password = signUpPasswordEditText.getText().toString();

                if(username.isBlank()){
                    Toast.makeText(SignUpActivity.this, "Username cannot be empty or just spaces!", Toast.LENGTH_LONG).show();
                }else if(email.isBlank()){
                    Toast.makeText(SignUpActivity.this, "Email cannot be empty or just spaces!", Toast.LENGTH_LONG).show();
                } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    Toast.makeText(SignUpActivity.this, "Invalid Email!", Toast.LENGTH_LONG).show();
                } else if(mobile.isBlank()){
                    Toast.makeText(SignUpActivity.this, "Mobile cannot be empty or just spaces!", Toast.LENGTH_LONG).show();
                } else if (!mobile.matches("^0[7-9]\\d{8}$")) {
                    Toast.makeText(SignUpActivity.this, "Invalid Mobile!", Toast.LENGTH_LONG).show();
                } else if (password.isBlank()) {
                    Toast.makeText(SignUpActivity.this, "Password cannot be empty or just spaces!", Toast.LENGTH_LONG).show();
                }else{
                    //check if the email or phone number already in the database
                    signUpBtn.setEnabled(false);
                    if(dbHelper.checkIfUserExists(email, mobile)){
                        Toast.makeText(SignUpActivity.this, "Email or Mobile number already in use!", Toast.LENGTH_LONG).show();
                        signUpBtn.setEnabled(true);
                    } else {
                        long result = dbHelper.insertUser(username, email, mobile, password);

                        if(result == -1){
                            Toast.makeText(SignUpActivity.this, "Sign up failed!", Toast.LENGTH_LONG).show();
                            signUpBtn.setEnabled(true);
                        } else {

                            // Seed the sample locations only once (first sign-up on this device)
                            if (dbHelper.getAllLocations().isEmpty()) {
                            dbHelper.addLocation(new LocationCard(1, "Pedal Paradise Kandy", "Peradeniya Road, Kandy", "4.9", 10, "https://i.ibb.co/N2Msyfm9/Designer.jpg", new LatLng(7.2803258713992935, 80.61862439538365), 300));
                            dbHelper.addLocation(new LocationCard(2, "Kandy Cycle Hub", "Udawattakele, Kandy", "4.8", 18, "https://i.ibb.co/zdmHMg1/Designer-2.jpg", new LatLng(7.2993553320926035, 80.64258465440503), 400));
                            dbHelper.addLocation(new LocationCard(3, "Ride ‘n’ Roam Kandy", "Ampitiya Road, Kandy", "4.7", 12, "https://i.ibb.co/ZRvtS4nj/Designer-4.jpg", new LatLng(7.273227833659085, 80.65791322209955), 250));
                            dbHelper.addLocation(new LocationCard(4, "Wheels of Kandy", "Near Royal Botanical Gardens, Peradeniya", "4.6", 16, "https://i.ibb.co/HfWctwG8/Designer-6.jpg", new LatLng(7.268121674212818, 80.59670191958945), 330));
                            dbHelper.addLocation(new LocationCard(5, "Cycle City Kandy", "Kandy City Center Area", "4.9", 21, "https://i.ibb.co/1fTkByLg/Designer-8.jpg", new LatLng(7.2917332431957576, 80.63713951712533), 310));
                            dbHelper.addLocation(new LocationCard(6, "Kandy Spin & Go", "Hanthana Road, Kandy", "4.9", 17, "https://i.ibb.co/9H2m1LmV/Designer-9.jpg", new LatLng(7.272619564179187, 80.63350916467733), 280));
                            dbHelper.addLocation(new LocationCard(7, "EcoRide Kandy", "Kandy Lake Round", "4.9", 19, "https://i.ibb.co/0RvbtNKw/Designer-7.jpg", new LatLng(7.290003916361178, 80.6427069135332), 300));
                            dbHelper.addLocation(new LocationCard(8, "Bike Bliss Kandy", "Katugastota Road, Kandy", "4.9", 14, "https://i.ibb.co/MyXhBBht/Designer-5.jpg", new LatLng(7.314000696340381, 80.6320053980566),400));
                            dbHelper.addLocation(new LocationCard(9, "Pedal Trails Kandy", "Senanayake Mawatha, Kandy", "4.9", 18, "https://i.ibb.co/6cVPzJzZ/Designer-3.jpg", new LatLng(7.297878803919747, 80.63715853820622), 290));
                            dbHelper.addLocation(new LocationCard(10, "Kandy Ride Zone", "Tennekumbura, Kandy", "4.9", 20, "https://i.ibb.co/N6dS5dM0/Designer-1.jpg", new LatLng(7.283016289139962, 80.66522333910862), 280));
                            }

                            Toast.makeText(SignUpActivity.this, "Sign up Success!", Toast.LENGTH_LONG).show();

                            sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            // Save user information (User ID, Username, Email, Mobile)
                            editor.putLong("user_id", result);
                            editor.putString("username", username);
                            editor.putString("email", email);
                            editor.putString("mobile", mobile);
                            editor.apply();

                            //save to app storage and go to home
                            Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }


                }

            }
        });
    }
}