package id.ac.umn.simplymeal.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.umn.simplymeal.CategoryView.Menu;
import id.ac.umn.simplymeal.CategoryView.MenuListActivity;
import id.ac.umn.simplymeal.R;

public class AdapterCategory  extends RecyclerView.Adapter<AdapterCategory.ViewHolder>{
    private Context mContext;
    private LayoutInflater layoutinflater;
    private List<Category> dataCat;

    public AdapterCategory(Context mContext, List<Category> dataCat){
        this.mContext = mContext;
        this.dataCat = dataCat;
    }

    @NonNull
    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutinflater.from(mContext).inflate(R.layout.content_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterCategory.ViewHolder holder, final int position) {
        final Category catData = dataCat.get(position);
        holder.category_name.setText(catData.getCategoryName());

        holder.category_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                Intent intent = new Intent(v.getContext(), MenuListActivity.class);
                intent.putExtra("categoryName",catData.getIdCategory()).putExtra("position",position);
                v.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return dataCat.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView category_name;
        CardView category_card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             category_name = itemView.findViewById(R.id.categoryName);
             category_card = itemView.findViewById(R.id.categoryCard);

        }
    }
}
