package RecyclerViews;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sae1.raisehand.R;
import com.google.gson.Gson;

import java.util.List;

import app.RepliesReply;
import utils.Reply;

public class MyAdapterReplies extends RecyclerView.Adapter<MyAdapterReplies.ViewHolder> {

    private List<Reply> listItems;
    private Context context;

    public MyAdapterReplies(List<Reply> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_teacher_replies, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Reply listItem = listItems.get(position);
        holder.textViewTime.setText(listItem.getReplyTimestamp());
        holder.textViewH.setText(listItem.getReply());
        holder.textViewP.setText("Points: "+ listItem.getReplyUpvotes());
       // holder.textViewReplies.setText("This reply has "+listItem.getReplies().size()+" replies to it. Click to view");
        if(listItem.getReplyEndorsed()){
            holder.textViewE.setText("Endorsed!");
        }
        Gson gson = new Gson();
        final String rep = gson.toJson(listItem);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reply = new Intent(context.getApplicationContext(), RepliesReply.class);
                reply.putExtra("replyID", listItem.getReplyID());
                reply.putExtra("reply", rep);
                Bundle bundle = new Bundle();
                bundle.putString("replyID", listItem.getReplyID());
                bundle.putString("reply", rep);
                context.getApplicationContext().startActivity(reply);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewH, textViewE, textViewP, textViewTime, textViewReplies;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewH = (TextView) itemView.findViewById(R.id.textViewH);
            textViewE= (TextView) itemView.findViewById(R.id.textViewE);
            textViewP= (TextView) itemView.findViewById(R.id.textViewP);
            textViewTime= (TextView) itemView.findViewById(R.id.textViewTime);
            textViewReplies= (TextView) itemView.findViewById(R.id.textViewReplies);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutTeacherReplies);
        }
    }

}
