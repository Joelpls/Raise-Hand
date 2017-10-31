package RecyclerViews;

/**
 * Created by jaggarwal on 10/9/17.
 */

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

import utils.Question;

import static com.example.sae1.raisehand.R.layout.question;

public class MyAdapterQuestionsStudent extends RecyclerView.Adapter<MyAdapterQuestionsStudent.ViewHolder> {

    private List<Question> listItems;
    private Context context;

    public MyAdapterQuestionsStudent(List<Question> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public MyAdapterQuestionsStudent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_student_questions, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapterQuestionsStudent.ViewHolder holder, int position) {
        final Question listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getQuestionTitle());
        holder.textViewDesc.setText(listItem.getQuestionDescription());

        Gson gson = new Gson();
        final String question = gson.toJson(listItem);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent studentReplies = new Intent(context.getApplicationContext(), com.example.sae1.raisehand.studentReplies.class);
                studentReplies.putExtra("questionID", listItem.getQuestionID());
                studentReplies.putExtra("question", question);

                Bundle bundle = new Bundle();
                bundle.putString("questionID", listItem.getQuestionID());
                bundle.putString("question", question);
                context.getApplicationContext().startActivity(studentReplies);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutStudentQuestions);
        }
    }
}
