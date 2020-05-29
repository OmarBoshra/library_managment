package com.android.librarymanagment.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.librarymanagment.R;
import com.android.librarymanagment.models.BookModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.UsersAdapterVh> implements Filterable {
    private List<BookModel> bookModelList;
    private List<BookModel> getBookModelListFiltered;
    private Context context;
    private SelectedUser selectedUser;

    public BooksAdapter(List<BookModel> bookModelList, SelectedUser selectedUser) {
        this.bookModelList = bookModelList;
        this.getBookModelListFiltered = bookModelList;
        this.selectedUser = selectedUser;
    }

    @NonNull
    @Override
    public BooksAdapter.UsersAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        return new UsersAdapterVh(LayoutInflater.from(context).inflate(R.layout.ui_row_users,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BooksAdapter.UsersAdapterVh holder, int position) {



        BookModel bookModel = bookModelList.get(position);

        String book_name = bookModel.getTitle();
        int book_acquired = bookModel.getAcquired();
        int top_rated = bookModel.getTop_rated();
        String book_duration = bookModel.getDuration();

        holder.bookName.setText(book_name);
        holder.topRated.setText(top_rated==1?"⭐":"");
        holder.acquired.setText(book_acquired==0?"purchase":book_acquired==1?"donation":"loan");
        holder.duration.setText(book_duration);

    }

    @Override
    public int getItemCount() {
        return bookModelList.size();
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if(charSequence == null | charSequence.length() == 0){
                    filterResults.count = getBookModelListFiltered.size();
                    filterResults.values = getBookModelListFiltered;

                }else{
                    String searchChr = charSequence.toString().toLowerCase();

                    List<BookModel> resultData = new ArrayList<>();

                    for(BookModel userModel: getBookModelListFiltered){
                        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", 0);

                    Boolean isCountry= pref.getBoolean("iscountry",false);

//                        if(isCountry?userModel.getCountry().toLowerCase().contains(searchChr):userModel.getCity_address().toLowerCase().contains(searchChr)){
//                            resultData.add(userModel);
//                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;

                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                bookModelList = (List<BookModel>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }


    public interface SelectedUser{

        void selectedUser(BookModel bookModel);

    }

    public class UsersAdapterVh extends RecyclerView.ViewHolder {

        TextView topRated;
        TextView bookName;
        TextView acquired;
        TextView duration;
        public UsersAdapterVh(@NonNull View itemView) {
            super(itemView);
            topRated = itemView.findViewById(R.id.top_rated);
            bookName = itemView.findViewById(R.id.book_name);
            acquired = itemView.findViewById(R.id.acquired);
            duration = itemView.findViewById(R.id.duration);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedUser.selectedUser(bookModelList.get(getAdapterPosition()));
                }
            });


        }
    }
}
