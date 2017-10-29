package app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.sae1.raisehand.R;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import RecyclerViews.MyAdapterQuestions;
import RecyclerViews.MyAdapterReplies;
import utils.Question;
import utils.Reply;
import utils.User;

public class TeacherReplies extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Reply> listItems;
    private Field mDragger;

    private SharedPreferences mPreferences;


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView nv;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_replies);

        //get question ID with a bundle
        Bundle bundle = getIntent().getExtras();
        final String questionID = bundle.getString("questionID");

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        mPreferences = getSharedPreferences("preferences", MODE_PRIVATE);

        recyclerView = (RecyclerView) findViewById(R.id.repliesRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Gson gson = new Gson();
        String json = mPreferences.getString("currentUser", "");
        User currentUser = gson.fromJson(json, User.class);

        // Get the question the user clicked on,
        // then the replies in that question.
        final Question userQuestion = currentUser.getSingleQuestion(questionID);
        listItems=userQuestion.getReplies();

        adapter = new MyAdapterReplies(listItems, this);

        recyclerView.setAdapter(adapter);

        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String question = gson.toJson(userQuestion);

        // Go to make a new question page on FAB click
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent makeReply = new Intent(getApplicationContext().getApplicationContext(), MakeReply.class);
                makeReply.putExtra("questionID", questionID);
                makeReply.putExtra("question", question);
                Bundle bun = new Bundle();
                bun.putString("topicID", questionID);
                bun.putString("topic", question);
                startActivity(makeReply);
            }
        });

        nv = (NavigationView) findViewById(R.id.nv1);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case(R.id.nav_home):
                        Intent teacherHome = new Intent(getApplicationContext(), TeacherHomePage.class);
                        startActivity(teacherHome);
                        break;
                    case (R.id.nav_classes):
                        mDrawerLayout.closeDrawers();
                        break;
                    case (R.id.nav_notifications):
                        Intent teacherNotifications = new Intent(getApplicationContext(), TeacherNotifications.class);
                        startActivity(teacherNotifications);
                        break;
                    case (R.id.nav_students):
                        Intent teacherStudents = new Intent(getApplicationContext(), TeacherStudents.class);
                        startActivity(teacherStudents);
                        break;
                    case (R.id.nav_settings):
                        Intent teacherSettings = new Intent(getApplicationContext(), TeacherSettings.class);
                        startActivity(teacherSettings);
                        break;
                }
                return true;
            }
        });
    }
}
