package com.example.firebase;
import android.app.AlertDialog;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class MainAdapter extends FirebaseRecyclerAdapter<Data,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<Data> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull Data model) {
            holder.ss.setText(model.getSs());
            holder.ds.setText(model.getDs());
            holder.hb.setText(model.getHb());
            holder.comment.setText(model.getComment());


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.ds.getContext()).setContentHolder(new ViewHolder(R.layout.edit_popup))
                        .setExpanded(true,1700).create();
                View view=dialogPlus.getHolderView();
                EditText ess=view.findViewById(R.id.Ess);
                EditText eds=view.findViewById(R.id.Eds);
                EditText ehb=view.findViewById(R.id.Ehb);
                EditText ecomment=view.findViewById(R.id.Ecomment);
                Button update=view.findViewById(R.id.editbtn);

                ess.setText(model.getSs());
                eds.setText(model.getDs());
                ehb.setText(model.getHb());
                ecomment.setText(model.getComment());
                dialogPlus.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                            map.put("ss",ess.getText().toString());
                            map.put("ds",eds.getText().toString());
                            map.put("hb",ehb.getText().toString());
                            map.put("comment",ecomment.getText().toString());
                            DAOdata daOdata=new DAOdata();
                        FirebaseDatabase.getInstance().getReference().child("Data").child(getRef(position).getKey()).updateChildren(map)
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
        TextView ss,ds,hb,comment;
        Button edit,delete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            ss=itemView.findViewById(R.id.Vss);
            ds=itemView.findViewById(R.id.Vds);
            hb=itemView.findViewById(R.id.Vhb);
            comment=itemView.findViewById(R.id.comment);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
