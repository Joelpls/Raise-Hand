package utils;

import java.sql.Time;

/**
 * Created by sae1 on 10/4/17.
 */

public class Reply {
    private String reply;
    private int student_rating;
    private boolean endorsed;
    private Time time_stamp;

    public Reply(String reply, int rating, boolean endorsed, Time time_stamp){
        this.reply=reply;
        this.student_rating=rating;
        this.endorsed=endorsed;
        this.time_stamp=time_stamp;
    }

    public int getUpVotes(Reply r){
        return r.student_rating;
    }

    public String getQuestion(Reply r){
        return r.reply;
    }

    public boolean endorsed(Reply r){
        return r.endorsed;
    }

    public Time time_stamp(Reply r){
        return r.time_stamp;
    }
}
