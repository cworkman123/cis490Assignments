package com.example.channing.parser;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.PushService;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Parse.initialize(this, "2nGG5G9dJQzqKPwSo3aOSMQqACxjRw7DwaZN5xzu", "kuPUqVcYKayvTyt8ulT8RJ61UIJUnJ1BGVGmSnvP");
        //Default Activity to handle push notifications
        PushService.setDefaultPushCallback(this, MyActivity.class);

    }

    public void viewList(View view){
        ListFragment fr = null;
        if(view == findViewById(R.id.ButtonParseList)){
            fr = new MyListFragment();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container,fr);
        fragmentTransaction.commit();
    }


    public void pushParse(View button){
        final EditText firstNameField = (EditText) findViewById(R.id.EditFirstName);
        String firstName = firstNameField.getText().toString();

        final EditText lastNameField = (EditText) findViewById(R.id.EditLastName);
        String lastName = lastNameField.getText().toString();

        ParseObject names = new ParseObject("Names");
        names.put("firstNameParse",firstName);
        names.put("lastNameParse",lastName);
        names.saveInBackground();


        Context context = getApplicationContext();
        CharSequence text = "Names Pushed";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            return rootView;
        }


    }
}
