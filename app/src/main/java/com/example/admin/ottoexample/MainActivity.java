package com.example.admin.ottoexample;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.ottoexample.bus.GlobalBus;
import com.example.admin.ottoexample.events.Events;
import com.example.admin.ottoexample.model.TestData;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import junit.framework.Test;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GlobalBus.getBus().unregister(this);
    }

    @Subscribe
    public void getMessage(Events.FragmentActivityMessage s){
        Toast.makeText(this, s.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            TestData t = new TestData("Hello from the activity");
            Events.ActivityFragmentMessage event = new Events.ActivityFragmentMessage(t);
            GlobalBus.getBus().post(event);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }
        Bus bus = GlobalBus.getBus();

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            View button = rootView.findViewById(R.id.fragmentbutton);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Events.FragmentActivityMessage event = new Events.FragmentActivityMessage(new TestData("Fragment Click"));
                    bus.post(event);
                }
            });
            bus.register(this);
            return rootView;
        }

        @Subscribe
        public void getMessage(Events.ActivityFragmentMessage data) {
            Toast.makeText(getActivity(), data.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Produce
    public Events.ActivityFragmentMessage produceEvent() {
        TestData t = new TestData("Starting up");
        Events.ActivityFragmentMessage event = new Events.ActivityFragmentMessage(t);
        return event;
    }


}
