package id.ac.umn.simplymeal.CategoryView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.umn.simplymeal.R;

public class MenuViewHolder extends RecyclerView.ViewHolder{

    View mView;
    public TextView menu_title;
    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        menu_title = (TextView)itemView.findViewById(R.id.menuTitle);

    }


}
