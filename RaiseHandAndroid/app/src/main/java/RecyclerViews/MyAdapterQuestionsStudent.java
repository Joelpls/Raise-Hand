package RecyclerViews;

/**
 * @author jaggarwal
 * How to view the list of questions on the student side of the app
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sae1.raisehand.R;
import Student.StudentReplies;
import com.google.gson.Gson;
import java.util.List;

import Utilities.Question;
import Utilities.StringParse;

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

        holder.textViewTimestamp.setText(StringParse.parseTimeStamp(listItem.getCreationTime()));
        holder.textViewHead.setText(listItem.getQuestionTitle());
        holder.textViewDesc.setText(listItem.getQuestionDescription());
        holder.textViewPoints.setText("Points: " + listItem.getStudentRating());
        if (listItem.questionEndorsemenet()){
            holder.textViewEndorsed.setText("Endorsed!");
        }


        Gson gson = new Gson();
        final String question = gson.toJson(listItem);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent studentReplies = new Intent(context.getApplicationContext(), StudentReplies.class);
                studentReplies.putExtra("questionID", listItem.getQuestionID());
                studentReplies.putExtra("question", question);
                context.getApplicationContext().startActivity(studentReplies);
            }
        });
        holder.report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                listItem.report();
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
        public TextView textViewPoints;
        public TextView textViewEndorsed;
        public TextView textViewTimestamp;
        public LinearLayout linearLayout;
        public Button report;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTimestamp = (TextView) itemView.findViewById(R.id.textViewTimestamp);
            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            textViewPoints = (TextView) itemView.findViewById(R.id.textViewPoints);
            textViewEndorsed = (TextView) itemView.findViewById(R.id.textViewEndorsed);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutStudentQuestions);
            report= (Button) itemView.findViewById(R.id.report);
        }
    }
}
