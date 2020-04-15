package id.ac.umn.simplymeal.CategoryView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.umn.simplymeal.R;

public class AdapterCardView extends RecyclerView.Adapter<AdapterCardView.ViewHolder> {
    private LayoutInflater layoutinflater;
    private List<String> data;

    public AdapterCardView(Context context, List<String> data){
        this.layoutinflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutinflater.inflate(R.layout.content_asian_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String namaMenu = data.get(position);
        holder.menuTitle.setText(namaMenu);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailMenu_Asian.class);
                intent.putExtra("menuTitle",namaMenu).putExtra("position", position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menuTitle;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuTitle = itemView.findViewById(R.id.menuTitle);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
