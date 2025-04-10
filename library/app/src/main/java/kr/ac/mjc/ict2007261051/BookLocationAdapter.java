package kr.ac.mjc.ict2007261051;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.ac.mjc.ict2007261051.dto.BookLocationDto;

public class BookLocationAdapter extends RecyclerView.Adapter<BookLocationAdapter.ViewHolder> {

    List<BookLocationDto> bookLocationList;

    public BookLocationAdapter(List<BookLocationDto> list){
        this.bookLocationList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemLocation=inflater.inflate(R.layout.item_location,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemLocation);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookLocationDto bookLocationDto=bookLocationList.get(position);
        holder.bind(bookLocationDto);
    }

    @Override
    public int getItemCount() {
        return bookLocationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView locationTv;
        TextView signTv;
        TextView statusTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            locationTv=itemView.findViewById(R.id.location_tv);
            signTv=itemView.findViewById(R.id.sign_tv);
            statusTv=itemView.findViewById(R.id.status_tv);
        }
        public void bind(BookLocationDto bookLocationDto){
            locationTv.setText(bookLocationDto.getLocation().getName());
            signTv.setText(bookLocationDto.getCallNo());
            String status=bookLocationDto.getCirculationState().getName();
            if(status.equals("대출가능")){
                int color=statusTv.getResources().getColor(R.color.green);
                statusTv.setTextColor(color);
            }
            else{
                int color=statusTv.getResources().getColor(R.color.red);
                statusTv.setTextColor(color);
            }

            statusTv.setText(status);
        }
    }
}
