package com.example.channing.parser;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;

/**
 * Created by Channing on 11/23/2014.
 */
public class MyListFragment extends ListFragment {
    //ListFragment that displays Parse Objects
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        final ArrayList nameList = new ArrayList();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameList );
        setListAdapter(adapter);


        //Loop through the Parse Object and add values to the List View Fragment via an ArrayAdapter
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Names");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(e == null){
                    for(int i = 0; i < parseObjects.size(); i++){
                        Object object = parseObjects.get(i);
                        String firstName = ((ParseObject) object).getString("firstNameParse").toString();
                        String lastName = ((ParseObject) object).getString("lastNameParse").toString();
                        nameList.add(firstName + "" + lastName);
                    }
                }
            }
        });

    }


}
