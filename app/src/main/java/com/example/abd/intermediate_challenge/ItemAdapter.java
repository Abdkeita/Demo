package com.example.abd.intermediate_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abd.intermediate_challenge.controller.DetailActivity;
import com.example.abd.intermediate_challenge.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by abd on 9/14/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;

    public ItemAdapter(Context applicationContext, List<Item> itemArrayList) {
        this.context = applicationContext;
        this.items = itemArrayList;
    }


    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(items.get(i).getLogin());
        viewHolder.githublink1.setText(items.get(i).getHtmlurl());


        Picasso.with(context)
                .load(items.get(i).getAvatarurl())
                .placeholder(R.drawable.loader)
                .into(viewHolder.avatarView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, githublink1;
        private ImageView avatarView;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            githublink1 = (TextView) view.findViewById(R.id.github_link);
            avatarView = (ImageView) view.findViewById(R.id.avatar);

            // on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Item ClickedDataItem = items.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("login", items.get(pos).getLogin());
                        intent.putExtra("htmlurl", items.get(pos).getHtmlurl());
                        intent.putExtra("avatarurl", items.get(pos).getAvatarurl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "you clicked" + ClickedDataItem.getLogin(), Toast.LENGTH_SHORT);
                    }
                }
            });
        }
    }
}
