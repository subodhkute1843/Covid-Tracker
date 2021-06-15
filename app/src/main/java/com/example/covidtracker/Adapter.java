package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    int n=1;     //it will  help you to filter the item
    Context context;
    List<ModelClass> countryist;

    public Adapter(Context context, List<ModelClass> countryist) {
        this.context = context;
        this.countryist = countryist;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,null ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adapter.ViewHolder holder, int position) {

        //take the position of item
        ModelClass modelClass = countryist.get(position);
        if (n==1)
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getCases())));  //NumberFormat for comma btwn numbers
        else if (n == 2)
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getRecovered())));
        else if (n == 3)
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getDeaths())));
        else{
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getActive())));
        }

        holder.country.setText(modelClass.getCountry());

    }

    @Override
    public int getItemCount() {
        return countryist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cases, country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cases = itemView.findViewById(R.id.countryCase);
            country = itemView.findViewById(R.id.countryName);
        }
    }


    public void filter(String charText){
        if (charText.equals("cases"))
            n=1;
        else if (charText.equals("recovered"))
            n=2;
        else if (charText.equals("deaths"))
            n=3;
        else{
            n=4;
        }

        notifyDataSetChanged();
    }



}
