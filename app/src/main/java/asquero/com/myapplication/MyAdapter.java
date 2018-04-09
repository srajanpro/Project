package asquero.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    private List<ModelList>list;
    private Context context;

    public MyAdapter(List<ModelList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    /*@Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ModelList listItem = list.get(position);

        holder.contestCode.setText(""+listItem.getContestCode());
        holder.contestName.setText(listItem.getContestName());
        holder.endDate.setText(""+(listItem.getStartDate()));
        holder.startDate.setText(""+(listItem.getEndDate()));
        //holder.imageView.setImageResource(listItem.getImage());
        holder.aic.setText(listItem.getAIC());

    }*/

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView contestCode;
        public TextView contestName;
        public TextView endDate;
        public TextView startDate;
       // public ImageView imageView;
        public TextView aic;

//        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            contestCode = (TextView)itemView.findViewById(R.id.contestCode);
            contestName = (TextView)itemView.findViewById(R.id.contestName);
            endDate = (TextView)itemView.findViewById(R.id.startDateNum);
            startDate = (TextView)itemView.findViewById(R.id.endDateNum);
            //imageView = (ImageView)itemView.findViewById(R.id.imageView);
            aic = (TextView)itemView.findViewById(R.id.AICTextView);

            //linearLayout = (LinearLayout)itemView.findViewById(R.id.ll);
            //linearLayout.setAlpha(0.9f);
        }
    }
}
