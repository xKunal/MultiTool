package com.example.strider_sol.multitoolapp.notepad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.strider_sol.multitoolapp.Listener.OnStartNewFragmentListener;
import com.example.strider_sol.multitoolapp.R;
import com.example.strider_sol.multitoolapp.common.Constant;
import com.example.strider_sol.multitoolapp.common.SettingsFragment;
import com.example.strider_sol.multitoolapp.common.demo.SQLiteDemo;
import com.example.strider_sol.multitoolapp.drawing.DrawingActivity;
import com.example.strider_sol.multitoolapp.movie.MovieActivity;
import com.example.strider_sol.multitoolapp.reminder.ReminderActivity;
import com.example.strider_sol.multitoolapp.todo.ToDoActivity;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class

NotepadActivity extends AppCompatActivity implements OnStartNewFragmentListener {
    private Drawer mDrawer = null;
    private Toolbar mToolbar;
    private int DEFAULT_APP;
    private Activity mActivity;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        mActivity = this;

        SQLiteDemo database = new SQLiteDemo(this);
        database.getWritableDatabase();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mSharedPreferences = mActivity.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor= mSharedPreferences.edit();
        DEFAULT_APP = mSharedPreferences.getInt(Constant.DEFAULT_APP,0);



        AccountHeader accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.drawerback)
                .build();

        mDrawer = new DrawerBuilder()
                .withAccountHeader(accountHeader)
                .withActivity(this)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Note List")
                                .withIcon(FontAwesome.Icon.faw_file_text)
                                .withIdentifier(1),
                        new PrimaryDrawerItem().withName("Todo List")
                                .withIcon(FontAwesome.Icon.faw_list)
                                .withIdentifier(2),
                        new PrimaryDrawerItem().withName("Drawing")
                                .withIcon(FontAwesome.Icon.faw_paint_brush)
                                .withIdentifier(3),
                        new PrimaryDrawerItem().withName("Remainder")
                                .withIcon(FontAwesome.Icon.faw_clock_o)
                                .withIdentifier(4),
                        new PrimaryDrawerItem().withName("Movie")
                                .withIcon(FontAwesome.Icon.faw_video_camera)
                                .withIdentifier(5),
                        new PrimaryDrawerItem().withName("Settings")
                                .withIcon(FontAwesome.Icon.faw_cog)
                                .withIdentifier(6)


                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem instanceof Nameable) {
                            String name = ((Nameable) drawerItem).getName().getText(NotepadActivity.this);
                            mToolbar.setTitle(name);
                        }
                        if (drawerItem != null) {
                            int idOfItemClicked = (int) drawerItem.getIdentifier();
                            onTouchDrawer(idOfItemClicked);
                        }

                        return true;
                    }
                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {

                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {

                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .build();

        if(DEFAULT_APP >0){
            onTouchDrawer(DEFAULT_APP);
        }else{
            onTouchDrawer(Constant.NOTEPAD);
        }
}

    private void onTouchDrawer(int position) {
        switch (position){
            case Constant.NOTEPAD:
                openFragment(new NoteListFragment(), "Notes List");
                break;
            case Constant.DRAWING:
                startActivity(new Intent(this, DrawingActivity.class));
                break;
            case Constant.TODO:
                startActivity(new Intent(this, ToDoActivity.class));
                break;
            case Constant.MOVIE:
                startActivity(new Intent(this, MovieActivity.class));
                break;
            case Constant.REMINDER:
                startActivity(new Intent(this, ReminderActivity.class));
                break;
            case Constant.SETTINGS:
                openFragment(new SettingsFragment(), "Settings");
                break;

        }
    }

    private void openFragment(Fragment fragment, String screenTitle) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle(screenTitle);
    }

    @Override
    public void onStartNewFragment(Fragment fragment, String title) {
        openFragment(fragment, title);

    }
}

