package bangtanrut.songklod.bookgazebo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import bangtanrut.songklod.bookgazebo.DetailActivity;
import bangtanrut.songklod.bookgazebo.GetData;
import bangtanrut.songklod.bookgazebo.MyConstant;
import bangtanrut.songklod.bookgazebo.R;

/**
 * Created by masterung on 9/10/2017 AD.
 */

public class ListViewProcess1Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview_process1, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ListView
        createListView();


    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.livProcess1);
        String tag = "10octV1";
        MyConstant myConstant = new MyConstant();

        try {

            GetData getData = new GetData(getActivity());
            getData.execute(myConstant.getUrlGetProcess1());
            String resultJSON = getData.get();
            Log.d(tag, "JSON ==> " + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);
            final String[] columnNameStrings = myConstant.getColumnProcess1Strings(); // Column Name
            final String[][] dataValueStrings = new String[columnNameStrings.length][jsonArray.length()];

            for (int i1=0;i1<dataValueStrings.length; i1+=1) {

                for (int i=0; i<jsonArray.length(); i+=1) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    dataValueStrings[i1][i] =jsonObject.getString(columnNameStrings[i1]);

                }

            }

            String[] nameStrings = new String[jsonArray.length()];
            for (int i=0; i<jsonArray.length(); i+=1) {
                Log.d(tag, "Name[" + i + "] ==> " + dataValueStrings[1][i]);
                nameStrings[i] = dataValueStrings[1][i];    //[name column][id record]
            }

            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, nameStrings);
            listView.setAdapter(stringArrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String[] dataSentStrings = new String[columnNameStrings.length];
                    for (int i=0; i<dataSentStrings.length; i+=1) {
                        dataSentStrings[i] =  columnNameStrings[i] + " = " + dataValueStrings[i][position];
                        Log.d("11octV1", "dataSent[" + i + "] ==> " + dataSentStrings[i]);
                    }

                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("DataSent", dataSentStrings);
                    getActivity().startActivity(intent);

                }
            });

        } catch (Exception e) {
            Log.d(tag, "e ==> " + e.toString());
        }


    }
}
