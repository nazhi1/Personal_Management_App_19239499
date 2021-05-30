package com.example.personal_managemen_app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList usr_id, user_id,user_fname, user_lname, user_age, user_gender,user_address;
    Button button_update;


        public MyAdapter(Context context, Activity activity, ArrayList user_id, ArrayList user_fname, ArrayList user_lname, ArrayList user_age, ArrayList user_gender, ArrayList user_address) {
        this.context = context;
        this.activity = activity;
        this.user_id = user_id;
        this.user_fname = user_fname;
        this.user_lname = user_lname;
        this.user_age = user_age;
        this.user_gender = user_gender;
        this.user_address = user_address;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.friend_layout, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
       // holder.user_id.setText(String.valueOf(usr_id.get(position)));
        holder.first_name.setText(String.valueOf(user_fname.get(position)));
        holder.last_name.setText(String.valueOf(user_lname.get(position)));
        holder.address.setText(String.valueOf(user_address.get(position)));
        holder.age.setText(String.valueOf(user_age.get(position)));
        holder.gender.setText(String.valueOf(user_gender.get(position)));
        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //Toast.makeText(context, "To update", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(user_id.get(position)));
                intent.putExtra("first_name", String.valueOf(user_fname.get(position)));
                intent.putExtra("last_name", String.valueOf(user_lname.get(position)));
                intent.putExtra("user_age", String.valueOf(user_age.get(position)));
                intent.putExtra("gender", String.valueOf(user_gender.get(position)));
                intent.putExtra("user_address", String.valueOf(user_address.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

       // String s =String.valueOf()

       // Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

    }


    @Override
    public int getItemCount() {
        return user_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id, first_name,last_name,age,address,gender;
        LinearLayout mainLayout;
        Button btn_update,btn_delete;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_id=itemView.findViewById(R.id.user_id);
            first_name = itemView.findViewById(R.id.F_name);
            last_name = itemView.findViewById(R.id.L_name);
            age = itemView.findViewById(R.id.Age);
            address = itemView.findViewById(R.id.Address);
            gender=itemView.findViewById(R.id.Gender);
            btn_update=itemView.findViewById(R.id.update);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
