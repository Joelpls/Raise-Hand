package utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.MainActivity;
import app.TeacherNotifications;

public class Question {

    // description of question
    private String questionDescription;

    // votes on questions
    private String studentRating;

    // Title of question
    private String questionTitle;

    // Time question was created
    private String creationTime;

    // Array of replies to the
    private List<Reply> replies;

    // Owner ID
    private String ownerID;

    //username of creator
    private String username;

    //if question has been endorsed
    private Boolean endorsed;

    //Topic the quesiton falls into
    private Topics parent;

    //ID set by the database- never set this yourself
    private String questionID;
    //For inserting into the database these are needed
    private String TAG= Question.class.getSimpleName();
    private String tag_string_req= "question_req";

    public Question(String questionDescription, String studentRating, String questionTitle, String creationTime,
                    List<Reply> replies, String ownerID, String username, Boolean endorsed, Topics parent, String questionID) {
        this.questionDescription = questionDescription;
        this.studentRating = studentRating;
        this.questionTitle = questionTitle;
        this.creationTime = creationTime;
        this.replies = replies;
        this.ownerID = ownerID;
        this.username=username;
        this.endorsed=endorsed;
        this.parent=parent;
        this.questionID=questionID;
    }

    public Question(){
        this.questionDescription = null;
        this.studentRating = "0";
        this.questionTitle = null;
        this.creationTime = null;
        this.replies = new ArrayList<Reply>();
        this.ownerID = null;
        this.username=null;
        this.endorsed=false;
    }

    public String getQuestionID(){ return questionID; }

    public Boolean questionEndorsemenet(){ return endorsed; }

    public String getQuestionUsername(){ return username; }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public String getStudentRating() {
        return studentRating;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public Topics getParent(){ return parent; }

    public String getQuestionId(){return questionID; }

    public void setQuestionDescription(String questionDescription) { this.questionDescription = questionDescription; }

    public void setStudentRating(String studentRating) { this.studentRating = studentRating; }

    public void setQuestionUsername(String username){this.username=username;}

    public void setEndorsed(Boolean endorsed){this.endorsed=endorsed; }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public void setParent(Topics t){this.parent=t;}

    public void setQuestionID(String s){this.questionID=s;}


    //Given a question, it will push this question to the database
    public void add_question_to_database(){
        //Description
        String desc=this.getQuestionDescription();
        //encoding spaces with a + sign for the url
        desc=desc.replaceAll(" ","+");
        //Title
        String title=this.getQuestionTitle();
        title=title.replaceAll(" ","+");
        //Owner ID
        String OID=this.getOwnerID();

        //Username
        String username=this.username;
        String url=URLS.URL_QUESTIONS+"?desc="+desc+"&title="+title+"&OID="+OID+"&username="+username+"&TID="+parent.get_ID();
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, response.toString());
                        String phpResponse = response.toString();
                        //in the php file, the user information is stored in an array with : as a delimiter between the variable name and actual value
                        if (phpResponse.contains("Done")) {
                            Toast.makeText(MainActivity.getInstance(), "Success: question added", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.getInstance(), "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }
        );
        // Adding request to request queue
        MainActivity.getInstance().addToRequestQueue(req, tag_string_req);

    }

    public void upVote(){
        String url=URLS.URL_UPVOTE+"?QID="+this.questionID;
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, response.toString());
                        String phpResponse = response.toString();
                        //in the php file, the user information is stored in an array with : as a delimiter between the variable name and actual value
                        if (phpResponse.contains("Done")) {
                            Toast.makeText(MainActivity.getInstance(), "Success: upvote completed", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.getInstance(), "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }
        );
        // Adding request to request queue
        MainActivity.getInstance().addToRequestQueue(req, tag_string_req);
    }

}
