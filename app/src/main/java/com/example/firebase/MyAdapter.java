package com.example.firebase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<Data> list;

    public MyAdapter(Context context, ArrayList<Data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data data= list.get(position);
        holder.ss.setText(data.getSs());
        holder.ds.setText(data.getDs());
        holder.hb.setText(data.getHb());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.ds.getContext()).setContentHolder(new ViewHolder(R.layout.edit_popup))
                        .setExpanded(true,1800).create();
//                dialogPlus.show();
                View view=dialogPlus.getHolderView();
                EditText ess=view.findViewById(R.id.Ess);
                EditText eds=view.findViewById(R.id.Eds);
                EditText ehb=view.findViewById(R.id.Ehb);
                EditText ecomment=view.findViewById(R.id.Ecomment);
                Button update=view.findViewById(R.id.editbtn);

                ess.setText(data.getSs());
                eds.setText(data.getDs());
                ehb.setText(data.getHb());
                ecomment.setText(data.getComment());
                dialogPlus.show();

//                update.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Map<String,Object> map=new HashMap<>();
//                            map.put("ss",ess.getText().toString());
//                            map.put("ds",eds.getText().toString());
//                            map.put("hb",ehb.getText().toString());
//                            map.put("comment",ecomment.getText().toString());
//                            DAOdata daOdata=new DAOdata();
//                        FirebaseDatabase.getInstance().getReference().child("Data").child(getRef(position).getKey()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(holder.ds.getContext(), "Updated", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//
//                    }
//                });
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.ds.getContext());
                builder.setTitle("Are you sure you want to delete?");
                builder.setMessage("Deleted data can't be refetched");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.ds.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ss,ds,hb;
        Button edit,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ss=itemView.findViewById(R.id.Vss);
            ds=itemView.findViewById(R.id.Vds);
            hb=itemView.findViewById(R.id.Vhb);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);

        }
    }
}
