package com.example.project_tr;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    private static ArrayList<model> filerList;
    //private static ArrayList<model> filerList;
    ////private  ArrayList<model> filerList;
    //   public static Object get;
    ArrayList<model> dataholder;


    public myadapter(ArrayList < model > dataholder)
    {
        this.dataholder = dataholder;
        notifyDataSetChanged();
    }

    public static void pb(ArrayList<model> fileredList)
    {
        pb p = new pb();
        p.dataholder = fileredList;
//       public setFileredList();
//       {
//           this = fileredList;
//       }
    }

//    public void setFileredList(ArrayList<model> filteredList) {
//        this.dataholder = filteredList;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        holder.dname.setText(dataholder.get(position).getName());
        holder.dsource.setText(dataholder.get(position).getSource());
        holder.ddestination.setText(dataholder.get(position).getDestination());
        holder.ddate.setText(dataholder.get(position).getDate());
        holder.dcontact.setText(dataholder.get(position).getEmail());
    }

    @Override
    public int getItemCount()
    {
        return dataholder.size();
    }
    ///////////////////
    private static class pb {
        public ArrayList<model> dataholder;
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        TextView dname,dsource,ddestination,ddate,dcontact;
        public myviewholder(@NonNull View itemView)
        {

            super(itemView);
            dname=(TextView)itemView.findViewById(R.id.displayname);
            dsource=(TextView)itemView.findViewById(R.id.displaysource);
            ddestination=(TextView)itemView.findViewById(R.id.displaydestination);
            ddate=(TextView)itemView.findViewById(R.id.displaydate);
            dcontact=(TextView)itemView.findViewById(R.id.displayemail);
        }
    }

    ////////////// CODE FOR SEARCH BAR //////////////////////////////////
//    public void setFilteredList(ArrayList<model> filteredList)
//    {
//        this.dataholder = filteredList;
//        notifyDataSetChanged();
//    }

}
