package id.ac.umn.simplymeal.CategoryView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

    private Context mContext;
    private LayoutInflater layoutinflater;
    private List<Menu> data;

    public AdapterCardView(Context mContext, List<Menu> data){
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutinflater.from(mContext).inflate(R.layout.content_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Menu menuData = data.get(position);
        Log.i("cobaLog",data.get(position).getMenuName());
        holder.menuTitle.setText(menuData.getMenuName());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailMenu_Asian.class);
                intent.putExtra("menuTitle",menuData.getMenuName());;
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView menuTitle;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuTitle = itemView.findViewById(R.id.menuTitle);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
