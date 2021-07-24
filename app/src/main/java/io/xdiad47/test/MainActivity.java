package io.xdiad47.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener { //, AdapterView.OnItemSelectedListener

    TextView monthYearText, textviewMonth,textviewYear,textviewYearnMonth;
    RecyclerView calendarReyclerView;
    LocalDate selectdate;

   private Spinner month_spinner,spinYear;
    String [] months;

    String combinedString;
    String selectedMonth;

    String seltmont;
    String selctYear;


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewMonth = findViewById(R.id.textviewMonth);//new
        textviewYear = findViewById(R.id.textviewYear);//new

        //for testing
        textviewYearnMonth = findViewById(R.id.textviewYearnMonth);//new

        month_spinner = findViewById(R.id.month_spinner);
        spinYear = findViewById(R.id.yearspin);


        populateSpinnerMonth();
        populateSpinnerYear();



        initWidget();
       // selectdate = LocalDate.now();
        getSelectDate();
        setMonthView();

       // month_spinner.setOnItemSelectedListener(this);
        //spinYear.setOnItemSelectedListener(this);

        //---- on click listener ---//

        month_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getId() == R.id.month_spinner){
                    seltmont = parent.getSelectedItem().toString();

                    Toast.makeText(MainActivity.this, "Selected Month: " + seltmont, Toast.LENGTH_SHORT).show();
                    textviewMonth.setText(seltmont);


                    //added
                    getSelectDate();
                    updatedView();
                    setMonthView();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

             if (parent.getId() == R.id.yearspin){
                 selctYear = parent.getSelectedItem().toString();
                 Toast.makeText(MainActivity.this, "Selected Year" + selctYear, Toast.LENGTH_SHORT).show();
                 textviewYear.setText(selctYear);
                 getSelectDate();
                 setMonthView();
             }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // ---- on click listener ---//


   /*     String month = month_spinner.getSelectedItem().toString();
        String year= spinYear.getSelectedItem().toString();



       // String month = textviewMonth.getText().toString();
        //String year = textviewYear.getText().toString();


        //combinedString = "16/09/2019";
        combinedString = "01/" + month + "/" + year ;

       // combinedString = "01/" + mon + "/" + year ;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
        selectdate = LocalDate.parse(combinedString, formatter); */

     /*   //not working
        String mon = textviewMonth.getText().toString();
        Log.d("month","code is going here");
        textviewYearnMonth.setText(mon);
        Log.d("month","code cross the textviewYearnMonth"); */
      //  textviewYearnMonth.setText(seltmont);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getSelectDate() {

        //added
        String month = month_spinner.getSelectedItem().toString();
        String year = spinYear.getSelectedItem().toString();


        //String month = textviewMonth.getText().toString();
        //String year = textviewYear.getText().toString();


        //combinedString = "16/09/2019";
        combinedString = "01/" + month + "/" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
        selectdate = LocalDate.parse(combinedString, formatter);
        //

    }

    //added
    private void updatedView() {

        // combinedString = "01/" + mon + "/" + year ;

        //not working
        //String mon = textviewMonth.getText().toString();
        textviewMonth.getText().toString();
        //  Log.d("month","code is going here");
        //  textviewYearnMonth.setText(mon);
        // Log.d("month","code cross the textviewYearnMonth");
        //textviewYearnMonth.setText(mon);

    }



    private void populateSpinnerYear() {

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = 1950; i <= thisYear; i++){
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,years);
        spinYear.setAdapter(adapter);

    }

    private void populateSpinnerMonth() {

        months = new DateFormatSymbols().getMonths();
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month_spinner.setAdapter(monthAdapter);
        

    }

//new
   /* private final View.OnTouchListener Spinner_OnTouch = new View.OnTouchListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // populateSpinnerMonth();
            if (event.getAction() == MotionEvent.ACTION_UP) {
                populateSpinnerMonth();
                setMonthView();
            }
            return false;
        }

    };*/


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {


   /*     //added
        String month = month_spinner.getSelectedItem().toString();
        String year = spinYear.getSelectedItem().toString();


        //String month = textviewMonth.getText().toString();
        //String year = textviewYear.getText().toString();


        //combinedString = "16/09/2019";
        combinedString = "01/" + month + "/" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
        selectdate = LocalDate.parse(combinedString, formatter);
        //
*/


        monthYearText.setText(monthYearFromDate(selectdate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectdate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7); //for calendar columns
        calendarReyclerView.setLayoutManager(layoutManager);
        calendarReyclerView.setAdapter(calendarAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date) {

        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectdate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidget() {

        calendarReyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV); //try to add here spinner i.e., call spinner

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view) {



        selectdate = selectdate.minusMonths(1);
        setMonthView();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view) {

        selectdate = selectdate.plusMonths(1);
        setMonthView();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {

        if(!dayText.equals(""))
        {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectdate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            //Put Textview here and settext(message);
            textviewYearnMonth.setText(message);
        }


    }


   /* @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.month_spinner){
           selectedMonth = parent.getSelectedItem().toString();
            Toast.makeText(this,"Selected Month: " + selectedMonth, Toast.LENGTH_SHORT).show();
            textviewMonth.setText(selectedMonth);

            setMonthView();


        }else  if (parent.getId() == R.id.yearspin){
            String selectedYear = parent.getSelectedItem().toString();
            Toast.makeText(this, "Selected Year: " + selectedYear, Toast.LENGTH_SHORT).show();
            textviewYear.setText(selectedYear);

            setMonthView();
         //   textviewYearnMonth.setText(selectedYear);
        } // Put these two textview i.e., year and month in one textview then try to put in main calendar view textview i.e., monthYearText
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    } */
}