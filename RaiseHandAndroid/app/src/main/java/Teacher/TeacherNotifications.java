package Teacher;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sae1.raisehand.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import RecyclerViews.ListItemTeacherNotifications;
import RecyclerViews.MyAdapterNotifications;
import Utilities.ActivitiesNames;
import Utilities.NavUtil;

/**
 *
 * This class has notifications for the teacher.
 * Notifications will include recent questions and replies submitted.
 * @author joel2
 */
public class TeacherNotifications extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItemTeacherNotifications> listItems;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private NavigationView nv;
    private Field mDragger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notifications);

        // Setting up the recycler view
        recyclerView = (RecyclerView) findViewById(R.id.notificationRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sampe of notification items
        listItems = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            ListItemTeacherNotifications listItem = new ListItemTeacherNotifications("Notification " + (i+1),
                                                                                     "Dummy text. I'm here to notify you!");
            listItems.add(listItem);
        }

        // Adapter to display the questions as recycler views. (cards on the screen)
        adapter = new MyAdapterNotifications(listItems, this);

        recyclerView.setAdapter(adapter);

        // Get the nav menu
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        // create the drawer layout (the thing you swipe from the side)
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        // add the menu items to the drawer
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

//        slideOutMenu();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // populate the navigation buttons to go to the correct place
        nv = (NavigationView) findViewById(R.id.nv1);
        NavUtil.setNavMenu(nv, ActivitiesNames.NOTIFICATIONS, getApplicationContext(), mDrawerLayout);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    private void slideOutMenu(){

        try {
            mDragger = mDrawerLayout.getClass().getDeclaredField(
                    "mLeftDragger");//mRightDragger for right obviously
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        mDragger.setAccessible(true);
        ViewDragHelper draggerObj = null;
        try {
            draggerObj = (ViewDragHelper) mDragger
                    .get(mDrawerLayout);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field mEdgeSize = null;
        try {
            mEdgeSize = draggerObj.getClass().getDeclaredField(
                    "mEdgeSize");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        mEdgeSize.setAccessible(true);
        int edge = 0;
        try {
            edge = mEdgeSize.getInt(draggerObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            mEdgeSize.setInt(draggerObj, edge * 25);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
*/
}
