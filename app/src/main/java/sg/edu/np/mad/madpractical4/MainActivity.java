package sg.edu.np.mad.madpractical4;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String desc = intent.getStringExtra("Description");

        TextView textViewName = findViewById(R.id.tvName);
        textViewName.setText(name);

        TextView textViewDesc = findViewById(R.id.tvDescription);
        textViewDesc.setText(desc);

       Button btnFollow = findViewById(R.id.btnFollow);

       btnFollow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (user.followed) {
                   btnFollow.setText("Unfollow");
                   user.followed = false;
                   Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
               }

               else {
                   btnFollow.setText("Follow");
                   user.followed = true;
                   Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
               }
           }
       });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}