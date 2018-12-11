package edu.psu.cjr5662.blt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.content.Context;

public class LabsFragment extends Fragment implements View.OnClickListener {

    TextView lst;

    String[] softwareArray = {"Select a Software", "Creo", "Vivado", "Minitab", "Visual Studio", "Alice", "Matlab", "Moldflow",
            "Mathcad", "Inventor", "Java JDK", "Moldflow", "Netbeans"};
    Spinner softwareSpinner;

    String[] timeArray = {"Select Time Slot ","8:00 AM- 9:00 AM","9:00 AM- 10:00 AM","10:00 AM- 11:00 AM","11:00 AM- 12:00 PM",
            "12:00 PM- 1:00 PM","1:00 PM- 2:00 PM","2:00 PM- 3:00 PM","3:00 PM- 4:00 PM","4:00 PM- 5:00 PM","5:00 PM- 6:00 PM","6:00 PM- 7:00 PM","7:00 PM- 8:00 PM","8:00 PM- 9:00 PM"};
    Spinner timeSpinner;

    String[] dayArray = {"Select Day of the Week", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    Spinner daySpinner;

    String[] labArray = {"Select A Lab", "7","8","11","12","13","14","15","140","144","147","153","175","176"};
    Spinner labSpinner;

    String timeInput = null;
    String dayInput = null;
    String softwareInput = null;
    String labFromSpinner = null;
    int labInput = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_labs, container, false);

        lst = (TextView) view.findViewById(R.id.list);

        MyDBHandler dbHandler = new MyDBHandler(getActivity(), null, null, 1);
        createLabs(dbHandler);

        Button btn = (Button) view.findViewById(R.id.btnDisplay);
        btn.setOnClickListener(this);

        timeSpinner = (Spinner) view.findViewById(R.id.timeSpinner);
        ArrayAdapter timeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, timeArray);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);

        daySpinner = (Spinner) view.findViewById(R.id.daySpinner);
        ArrayAdapter dayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, dayArray);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        softwareSpinner = (Spinner) view.findViewById(R.id.softwareSpinner);
        ArrayAdapter softwareAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, softwareArray);
        softwareAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        softwareSpinner.setAdapter(softwareAdapter);

        labSpinner = (Spinner) view.findViewById(R.id.labSpinner);
        ArrayAdapter labAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, labArray);
        labAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        labSpinner.setAdapter(labAdapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        MyDBHandler dbHandler = new MyDBHandler(getActivity(), null, null, 1);

        timeSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeInput = timeArray[position];

                if(timeInput.equals("Select Time Slot "))
                    timeInput = null;
                else if(timeInput.equals("8:00 AM- 9:00 AM"))
                    timeInput = "8,";
                else if(timeInput.equals("9:00 AM- 10:00 AM"))
                    timeInput = "9,";
                else if(timeInput.equals("10:00 AM- 11:00 AM"))
                    timeInput = "10,";
                else if(timeInput.equals("11:00 AM- 12:00 PM"))
                    timeInput = "11,";
                else if(timeInput.equals("12:00 PM- 1:00 PM"))
                    timeInput = "12,";
                else if(timeInput.equals("1:00 PM- 2:00 PM"))
                    timeInput = "1,";
                else if(timeInput.equals("2:00 PM- 3:00 PM"))
                    timeInput = "2,";
                else if(timeInput.equals("3:00 PM- 4:00 PM"))
                    timeInput = "3,";
                else if(timeInput.equals("4:00 PM- 5:00 PM"))
                    timeInput = "4,";
                else if(timeInput.equals("5:00 PM- 6:00 PM"))
                    timeInput = "5,";
                else if(timeInput.equals("6:00 PM- 7:00 PM"))
                    timeInput = "6,";
                else if(timeInput.equals("7:00 PM- 8:00 PM"))
                    timeInput = "7,";
                else if(timeInput.equals("8:00 PM- 9:00 PM"))
                    timeInput = "8p,";
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }

        });

        daySpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayInput = dayArray[position];
                if(dayInput.equals("Select Day of the Week"))
                    dayInput = null;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }

        });

        softwareSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                softwareInput = softwareArray[position];
                if(softwareInput.equals("Select a Software"))
                    softwareInput = null;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }

        });

        labSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                labFromSpinner = labArray[position];
                if(labFromSpinner.equals("Select A Lab"))
                    labInput = 0;
                else
                    labInput = Integer.parseInt(labFromSpinner);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }

        });

        lst.setText(dbHandler.loadHandler(timeInput, dayInput, softwareInput, labInput));
    }

    public void createLabs(MyDBHandler dbHandler){
        Lab lab7M = new Lab(1, 7, 27, "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Java JDK","Monday", "8, 9, 10, 11, 12, 3, 4, 5, 6, 7,");
        dbHandler.addHandler(lab7M);
        Lab lab7T = new Lab(2, 7, 27, "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Java JDK","Tuesday", "");
        dbHandler.addHandler(lab7T);
        Lab lab7W = new Lab(3, 7, 27, "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Java JDK","Wednesday", "8, 9, 10, 12,");
        dbHandler.addHandler(lab7W);
        Lab lab7TH = new Lab(4, 7, 27, "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Java JDK","Thursday", "8, 9, 4, 5, 6,");
        dbHandler.addHandler(lab7TH);
        Lab lab7F = new Lab(5, 7, 27, "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Java JDK","Friday", "8, 9, 12, 4, 5, 6,");
        dbHandler.addHandler(lab7F);

        Lab lab8M = new Lab(6, 8, 14, "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Monday","8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab8M);
        Lab lab8T = new Lab(7, 8, 14,  "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Tuesday","8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab8T);
        Lab lab8W = new Lab(8, 8, 14,  "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Wednesday","8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab8W);
        Lab lab8TH = new Lab(9, 8, 14,  "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Thursday","8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab8TH);
        Lab lab8F = new Lab(10, 8, 14,  "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Friday","8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab8F);

        Lab lab11M = new Lab(11, 11, 28, "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Monday","10, 1, 2,");
        dbHandler.addHandler(lab11M);
        Lab lab11T = new Lab(12, 11,  28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Tuesday", "2, 5,");
        dbHandler.addHandler(lab11T);
        Lab lab11W = new Lab(13, 11, 28, "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Wednesday", "10, 5,");
        dbHandler.addHandler(lab11W);
        Lab lab11TH = new Lab(14, 11, 28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Thursday", "10, 5,");
        dbHandler.addHandler(lab11TH);
        Lab lab11F = new Lab(15, 11, 28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Friday", "12, 1, 2, 3, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab11F);

        Lab lab12M = new Lab(16, 12, 27,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Monday","8, 9, 10, 11, 12, 1, 2,");
        dbHandler.addHandler(lab12M);
        Lab lab12T = new Lab(17, 12, 27,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Tuesday", "6, 7, 8p,");
        dbHandler.addHandler(lab12T);
        Lab lab12W = new Lab(18, 12, 27,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Wednesday", "8, 9, 12,");
        dbHandler.addHandler(lab12W);
        Lab lab12TH = new Lab(19, 12, 27,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Thursday", "6, 7, 8p,");
        dbHandler.addHandler(lab12TH);
        Lab lab12F = new Lab(20, 12, 27,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Friday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All,");
        dbHandler.addHandler(lab12F);

        Lab lab13M = new Lab(21, 13, 22,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Monday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab13M);
        Lab lab13T = new Lab(22, 13, 22,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Tuesday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab13T);
        Lab lab13W = new Lab(23, 13, 22,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Wednesday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab13W);
        Lab lab13TH = new Lab(24, 13, 22,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Thursday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab13TH);
        Lab lab13F = new Lab(25, 13, 22,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Friday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab13F);

        Lab lab14M = new Lab(26, 14, 28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Monday", "8, 9, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab14M);
        Lab lab14T = new Lab(27, 14, 28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Tuesday", "11, 4, 5,");
        dbHandler.addHandler(lab14T);
        Lab lab14W = new Lab(28, 14, 28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Wednesday", "8, 9, 12, 6, 7, 8p,");
        dbHandler.addHandler(lab14W);
        Lab lab14TH = new Lab(29, 14, 28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Thursday", "11, 4, 5,");
        dbHandler.addHandler(lab14TH);
        Lab lab14F = new Lab(30, 14, 28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Java JDK","Friday", "11, 12, 4, 5, 6, 7,");
        dbHandler.addHandler(lab14F);

        Lab lab15M = new Lab(31, 15, 28, "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Java JDK","Monday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab15M);
        Lab lab15T = new Lab(32, 15, 28, "Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Java JDK","Tuesday", "10, 11,");
        dbHandler.addHandler(lab15T);
        Lab lab15W = new Lab(33, 15, 28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Java JDK","Wednesday", "8, 9, 10, 11, 1, 2, 6, 7,");
        dbHandler.addHandler(lab15W);
        Lab lab15TH = new Lab(34, 15, 28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Java JDK","Thursday", "10, 4, 5, 6, 7,");
        dbHandler.addHandler(lab15TH);
        Lab lab15F = new Lab(35, 15, 28,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Java JDK","Friday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab15F);

        Lab lab140M = new Lab(41, 140, 10,"Vivado","Monday", "");
        dbHandler.addHandler(lab140M);
        Lab lab140T = new Lab(42, 140, 10,"Vivado","Tuesday", "12, 1, 2, 3,");
        dbHandler.addHandler(lab140T);
        Lab lab140W = new Lab(43, 140, 10,"Vivado","Wednesday", "10, 11, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab140W);
        Lab lab140TH = new Lab(44, 140, 10,"Vivado","Thursday", "12, 1, 2, 3,");
        dbHandler.addHandler(lab140TH);
        Lab lab140F = new Lab(45, 140, 10,"Vivado","Friday", "8, 9,");
        dbHandler.addHandler(lab140F);

        Lab lab144M = new Lab(46, 144, 8,"Matlab","Monday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab144M);
        Lab lab144T = new Lab(47, 144, 8,"Matlab","Tuesday", "10, 11, 2, 3, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab144T);
        Lab lab144W = new Lab(48, 144, 8,"Matlab","Wednesday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab144W);
        Lab lab144TH = new Lab(49, 144, 8,"Matlab","Thursday", "10, 11, 12, 1, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab144TH);
        Lab lab144F = new Lab(50, 144, 8,"Matlab","Friday", "10, 11, 2, 3, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab144F);

        Lab lab147M = new Lab(51, 147, 41,"Vivado, Visual Studio, Matlab, Inventor, Netbeans","Monday", "8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8p, All");
        dbHandler.addHandler(lab147M);
        Lab lab147T = new Lab(52, 147, 41,"Vivado, Visual Studio, Matlab, Inventor, Netbeans","Tuesday", "8, 9, 7, 8p,");
        dbHandler.addHandler(lab147T);
        Lab lab147W = new Lab(53, 147, 41,"Vivado, Visual Studio, Matlab, Inventor, Netbeans","Wednesday", "8, 9, 12, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab147W);
        Lab lab147TH = new Lab(54, 147, 41,"Vivado, Visual Studio, Matlab, Inventor, Netbeans","Thursday", "8, 9, 6, 7, 8p,");
        dbHandler.addHandler(lab147TH);
        Lab lab147F = new Lab(55, 147, 41,"Vivado, Visual Studio, Matlab, Inventor, Netbeans","Friday", "10, 2, 3, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab147F);

        Lab lab153M = new Lab(56, 153, 39,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Monday", "12, 1, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab153M);
        Lab lab153T = new Lab(57, 153, 39,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Tuesday", "12, 1, 3,");
        dbHandler.addHandler(lab153T);
        Lab lab153W = new Lab(58, 153, 39,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Wednesday", "8, 9, 12, 1, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab153W);
        Lab lab153TH = new Lab(59, 153, 39,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Thursday", "3, 4, 5,");
        dbHandler.addHandler(lab153TH);
        Lab lab153F = new Lab(60, 153, 39,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Friday", "8, 9, 11, 12, 1, 3, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab153F);

        Lab lab175M = new Lab(61, 175, 49,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Monday", "10, 2, 3, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab175M);
        Lab lab175T = new Lab(62, 175, 49,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Tuesday", "11, 2, 6, 7, 8p,");
        dbHandler.addHandler(lab175T);
        Lab lab175W = new Lab(63, 175, 49,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Wednesday", "10, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab175W);
        Lab lab175TH = new Lab(64, 175, 49,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Thursday", "8, 9, 6, 7, 8p,");
        dbHandler.addHandler(lab175TH);
        Lab lab175F = new Lab(65, 175, 49,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Friday", "10, 2, 3, 4, 5, 6, 7, 8p,");
        dbHandler.addHandler(lab175F);

        Lab lab176M = new Lab(66, 176, 49,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Monday", "6, 7, 8p,");
        dbHandler.addHandler(lab176M);
        Lab lab176T = new Lab(67, 176, 49,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Tuesday", "3, 4, 5, 8p,");
        dbHandler.addHandler(lab176T);
        Lab lab176W = new Lab(68, 176, 49,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Wednesday", "6, 7,");
        dbHandler.addHandler(lab176W);
        Lab lab176TH = new Lab(69, 176, 49,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Thursday", "3, 4, 5, 8p,");
        dbHandler.addHandler(lab176TH);
        Lab lab176F = new Lab(70, 176, 49,"Creo, Minitab, Visual Studio, Alice, Matlab, Moldflow, Mathcad, Inventor, Netbeans","Friday", "6, 7, 8p,");
        dbHandler.addHandler(lab176F);

    }
}
