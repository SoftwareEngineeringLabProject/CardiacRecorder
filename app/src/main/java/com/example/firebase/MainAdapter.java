package com.example.firebase;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainAdapter extends FirebaseRecyclerAdapter<Data,MainAdapter.myViewHolder> {

    public MainAdapter(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }
    int pos;
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull Data model) {
            pos=position;
            holder.ss.setText(model.getSs());
            int temp=Integer.parseInt(holder.ss.getText().toString());
            if(temp<90 || temp>140){
                holder.ss.setTextColor(Color.RED);
            }
            holder.ds.setText(model.getDs());
            temp=Integer.parseInt(holder.ds.getText().toString());
            if(temp<60 || temp>90){
                holder.ds.setTextColor(Color.RED);
            }
            holder.hb.setText(model.getHb());
            holder.comment.setText(model.getComment());
            holder.date.setText(model.getDate());
            holder.time.setText(model.getTime());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.ds.getContext()).setContentHolder(new ViewHolder(R.layout.edit_popup))
                        .setExpanded(true,2300).create();
                View view=dialogPlus.getHolderView();
                EditText ess=view.findViewById(R.id.Ess);
                EditText eds=view.findViewById(R.id.Eds);
                EditText ehb=view.findViewById(R.id.Ehb);
                EditText ecomment=view.findViewById(R.id.Ecomment);
                EditText edate=view.findViewById(R.id.Edate);
                EditText etime=view.findViewById(R.id.Etime);
                Button update=view.findViewById(R.id.editbtn);

                ess.setText(model.getSs());
                eds.setText(model.getDs());
                ehb.setText(model.getHb());
                ecomment.setText(model.getComment());
                edate.setText(model.getDate());
                etime.setText(model.getTime());

                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tss=ess.getText().toString();
                        String tds=eds.getText().toString();
                        String thb=ehb.getText().toString();
                        if(tss.matches("[0-9]+") && tds.matches("[0-9]+") && thb.matches("[0-9]+")) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("ss", ess.getText().toString());
                            map.put("ds", eds.getText().toString());
                            map.put("hb", ehb.getText().toString());
                            map.put("comment", ecomment.getText().toString());
                            map.put("date", edate.getText().toString());
                            map.put("time", etime.getText().toString());
                            DAOdata daOdata = new DAOdata();
                            FirebaseDatabase.getInstance().getReference().child("Data").child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(holder.ds.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(holder.ds.getContext(), "Failed to Update", Toast.LENGTH_SHORT).show();
                                            dialogPlus.dismiss();
                                        }
                                    });
                        }
                        else{
                            Toast.makeText(holder.ds.getContext(), "Invalid Values", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
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
                        FirebaseDatabase.getInstance().getReference().child("Data")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.ds.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView ss,ds,hb,comment,date,time;
        Button edit,delete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            ss=itemView.findViewById(R.id.Vss);
            ds=itemView.findViewById(R.id.Vds);
            hb=itemView.findViewById(R.id.Vhb);
            comment=itemView.findViewById(R.id.Vcomment);
            date=itemView.findViewById(R.id.Vdate);
            time=itemView.findViewById(R.id.Vtime);
            edit=itemView.findViewById(R.id.Vedit);
            delete=itemView.findViewById(R.id.Vdelete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), ss.getText().toString()+"/"+ds.getText().toString(), Toast.LENGTH_SHORT).show();
                    final DialogPlus dialogPlus= DialogPlus.newDialog(itemView.getContext()).setContentHolder(new ViewHolder(R.layout.details))
                            .setExpanded(true,2300).create();
                    View view=dialogPlus.getHolderView();
                    TextView dss=view.findViewById(R.id.dss);
                    TextView dds=view.findViewById(R.id.dds);
                    TextView dhb=view.findViewById(R.id.dhb);
                    TextView dcomment=view.findViewById(R.id.dcomment);
                    TextView dtime=view.findViewById(R.id.dtime);
                    TextView ddate=view.findViewById(R.id.ddate);
                    dss.setText(ss.getText().toString());
                    dds.setText(ds.getText().toString());
                    dhb.setText(hb.getText().toString());
                    dcomment.setText(comment.getText().toString());
                    dtime.setText(time.getText().toString());
                    ddate.setText(date.getText().toString());
                    dialogPlus.show();

                }
            });

        }
    }
}
