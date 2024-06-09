package sg.edu.np.mad.madpractical4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private ArrayList<User> list_objects;
    // private ListActivity activity;

    private static Context context;

    public UserAdapter(ArrayList<User> list_objects, Context context) {
        this.list_objects = list_objects;
        // this.activity = activity;
        this.context = context;
    }

    // Method to create a view holder for a username
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        return new UserViewHolder(view);
    }

    // Method to bind username to a view holder
    public void onBindViewHolder(UserViewHolder holder, int position) {
        // Get position of a username
        User list_items = list_objects.get(position);

        // Set username to the view holder based on custom_activity_list.xml
        holder.name.setText(list_items.getName());

        // Set description to the view holder based on custom_activity_list.xml
        holder.description.setText(list_items.getDescription());

        String name = list_items.getName();
        char lastChar = name.charAt(name.length() - 1);

        ImageView imageView = holder.itemView.findViewById(R.id.ivLargeImage);

        if (!name.isEmpty() && lastChar == '7') {
            imageView.setVisibility(View.VISIBLE);
        }

        else {
            imageView.setVisibility(View.GONE);
        }

        // Configure setOnClickListener() for the small image on the view holder based on custom_activity_list.xml
        holder.smallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(list_items.getName());
                builder.setCancelable(true);

                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) { dialog.dismiss(); }
                });

                builder.setNegativeButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("Name" , list_items.getName());
                        intent.putExtra("Description" , list_items.getDescription());
                        context.startActivity(intent);
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    public int getItemCount() {return list_objects.size();}
}
