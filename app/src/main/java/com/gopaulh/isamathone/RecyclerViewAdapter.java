package com.gopaulh.isamathone;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private final Context mContext ;
    private final List<String> names;
    private final List<Integer> drawables;
    private Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<String> names, List<Integer> drawables) {
        this.mContext = mContext;
        this.names = names;
        this.drawables = drawables;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tv_book_title.setText(names.get(holder.getAdapterPosition()));
        holder.img_book_thumbnail.setImageResource(drawables.get(holder.getAdapterPosition()));
        holder.cardView.setOnClickListener(v -> {
            myDialog = new Dialog(mContext);
            myDialog.setContentView(R.layout.question_options_dialog);
            final TextView topic = myDialog.findViewById(R.id.dialog_name_id);
            topic.setText(names.get(holder.getAdapterPosition()));
            final TextView digits_number = myDialog.findViewById(R.id.digits_number);
            final ImageView digits_minus = myDialog.findViewById(R.id.digits_minus);
            final ImageView digits_plus = myDialog.findViewById(R.id.digits_plus);
            digits_minus.setOnClickListener(v1 -> { if (digits_number.getText().toString().equals("Hard")) digits_number.setText("Easy");});
            digits_plus.setOnClickListener(v2 -> { if (digits_number.getText().toString().equals("Easy")) digits_number.setText("Hard");});
            final Button dialog_confirm = myDialog.findViewById(R.id.dialog_confirm);
            dialog_confirm.setOnClickListener(v3 -> {
                Intent intent = new Intent(mContext,GameActivity.class);
                intent.putExtra("Info",new String[]{digits_number.getText().toString(),names.get(holder.getAdapterPosition())});
                myDialog.cancel();
                mContext.startActivity(intent);
            });
            myDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView ;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_book_title = itemView.findViewById(R.id.book_title_id) ;
            img_book_thumbnail = itemView.findViewById(R.id.book_img_id);
            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }
}
