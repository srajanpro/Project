package asquero.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mansi on 12/4/18.
 */

public class EndedListAdapter extends RecyclerView.Adapter<EndedListAdapter.ViewHolder> {
    private List<EndedList> list;
    private Context context;

    public EndedListAdapter(List<EndedList> listEnded, Ended ended) {
        this.list = listEnded;
        this.context = ended;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ended_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EndedList listItem = list.get(position);

        holder.contestCode.setText(""+listItem.getContestCode());
        holder.contestName.setText(listItem.getContestName());
        holder.endDate.setText(""+(listItem.getStartDate()));
        holder.startDate.setText(""+(listItem.getEndDate()));
        //holder.imageView.setImageResource(listItem.getImage());
        holder.aic.setText(listItem.getAIC());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView contestCode;
        public TextView contestName;
        public TextView endDate;
        public TextView startDate;
        // public ImageView imageView;
        public TextView aic;

        public ViewHolder(View itemView) {
            super(itemView);
            contestCode = (TextView)itemView.findViewById(R.id.contestCode);
            contestName = (TextView)itemView.findViewById(R.id.contestName);
            endDate = (TextView)itemView.findViewById(R.id.startDateNum);
            startDate = (TextView)itemView.findViewById(R.id.endDateNum);
            //imageView = (ImageView)itemView.findViewById(R.id.imageView);
            aic = (TextView)itemView.findViewById(R.id.AICTextView);
        }
    }
}