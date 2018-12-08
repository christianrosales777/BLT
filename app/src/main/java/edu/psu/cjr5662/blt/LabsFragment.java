package edu.psu.cjr5662.blt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Spinner;

public class LabsFragment extends Fragment implements View.OnClickListener {

    TextView lst;
    int cnt = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_labs, container, false);

        lst = (TextView) view.findViewById(R.id.list);

        Button btn = (Button) view.findViewById(R.id.btnDisplay);
        btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        MyDBHandler dbHandler = new MyDBHandler(getActivity(), null, null, 1);

        if(cnt == 1)
            createLabs(dbHandler);

        lst.setText(dbHandler.loadHandler("Netbeans"));

        cnt++;

/**
        final Spinner sSpinner = (Spinner) v.findViewById(R.id.softwareSpinner);
        sSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                softwareSearched = sSpinner.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        Spinner softwareSpinner = (Spinner) v.findViewById(R.id.softwareSpinner);
        String softwareSearched = softwareSpinner.getSelectedItem().toString();

        Spinner lSpinner = (Spinner) v.findViewById(R.id.labSpinner);
        String labSearched = lSpinner.getSelectedItem().toString();
        int labNumberSearched = Integer.parseInt(labSearched);
 **/
    }

    public void createLabs(MyDBHandler dbHandler){
        Lab lab7I = new Lab(1, 7, 27, "Inventor");
        dbHandler.addHandler(lab7I);
        Lab lab7A = new Lab(2, 7, 27, "All");
        dbHandler.addHandler(lab7A);

        Lab lab8 = new Lab(3, 8, 14, "All");
        dbHandler.addHandler(lab8);
        Lab lab8G = new Lab(4, 8, 14, "Good");
        dbHandler.addHandler(lab8G);

        Lab lab11 = new Lab(5, 11, 28, "All");
        dbHandler.addHandler(lab11);
        Lab lab12 = new Lab(6, 12, 27, "All");
        dbHandler.addHandler(lab12);

        Lab lab13 = new Lab(7, 13, 22, "All");
        dbHandler.addHandler(lab13);
        Lab lab13G = new Lab(8, 13, 22, "Good");
        dbHandler.addHandler(lab13G);

        Lab lab14 = new Lab(9, 14, 28, "All");
        dbHandler.addHandler(lab14);

        Lab lab15I = new Lab(10, 15, 28, "Inventor");
        dbHandler.addHandler(lab15I);
        Lab lab15A = new Lab(11, 15, 28, "All");
        dbHandler.addHandler(lab15A);

        Lab lab111 = new Lab(12, 111, 9, "All");
        dbHandler.addHandler(lab111);

        Lab lab140 = new Lab(13, 140, 10, "All");
        dbHandler.addHandler(lab140);
        Lab lab140G = new Lab(14, 140, 10, "Good");
        dbHandler.addHandler(lab140G);

        Lab lab144 = new Lab(15, 144, 8, "All");
        dbHandler.addHandler(lab144);

        Lab lab147 = new Lab(16, 147, 41, "Netbeans");
        dbHandler.addHandler(lab147);
        Lab lab147I = new Lab(17, 147, 41, "Inventor");
        dbHandler.addHandler(lab147I);
        Lab lab147A = new Lab(18, 147, 41, "All");
        dbHandler.addHandler(lab147A);
        Lab lab147G = new Lab(19, 147, 41, "Good");
        dbHandler.addHandler(lab147G);

        Lab lab153I = new Lab(20, 153, 39, "Inventor");
        dbHandler.addHandler(lab153I);
        Lab lab153A = new Lab(21, 153, 39, "All");
        dbHandler.addHandler(lab153A);

        Lab lab175I = new Lab(22, 175, 49, "Inventor");
        dbHandler.addHandler(lab175I);
        Lab lab175A = new Lab(23, 175, 49, "All");
        dbHandler.addHandler(lab175A);

        Lab lab176I = new Lab(24, 176, 49, "Inventor");
        dbHandler.addHandler(lab176I);
        Lab lab176A = new Lab(25, 176, 49, "All");
        dbHandler.addHandler(lab176A);

    }
}
