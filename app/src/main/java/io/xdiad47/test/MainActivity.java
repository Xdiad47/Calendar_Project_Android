package io.xdiad47.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener { //, AdapterView.OnItemSelectedListener

    TextView monthYearText, textviewMonth,textviewYear,textviewYearnMonth, DateText;
    RecyclerView calendarReyclerView;
    LocalDate selectdate;

    Button buttonToDisplay;
    TextView UTCTimeZone,LocalTime,UtTimeToLocal;
//new
   // TextClock textClock;

    EditText EditClock;

   // TextView time;
   // TimePicker simpleTimePicker;

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    Button button;

    String outputText;

   // AlertDialog dialog;
    //EditText editText;

//new
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


        DateText = findViewById(R.id.Date);

        buttonToDisplay = findViewById(R.id.buttonToDisplay);
        UTCTimeZone = findViewById(R.id.UTCTimeZone);

        //Time

        EditClock = findViewById(R.id.EditClock);

       // time = findViewById(R.id.TimeDisplay);
     /*   textClock = findViewById(R.id.textClock);

        dialog = new AlertDialog.Builder(this).create();
        editText = new EditText(this);

        dialog.setTitle("Please input Time as: 12:24 or 01:04");
        dialog.setView(editText);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save Time", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textClock.setText(editText.getText());
            }
        });

        textClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(textClock.getText());
                dialog.show();
            }
        });
*/
        /*textClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mtimePickerDialog;
                mtimePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //textClock.setText(hourOfDay + ":" + minute);
                      //  time.setText(String.format("%02d:%02d", hourOfDay, minute));
                        textClock.setText(String.format("%02d:%02d", hourOfDay, minute));

                    }
                }, hour, minute, false);
                mtimePickerDialog.setTitle("Select Time");
                mtimePickerDialog.show();
            }
        }); */

        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);

              // textView.setText("Your Choice: " +textClock.getText() +" " + radioButton.getText()  ); //this textview will go for shared preferences
                //time.setText("Your Choice: " +time.getText() +" " + radioButton.getText()  );
                //textView.setText("your choice: "+EditClock.getText() + " " + radioButton.getText() );
                textView.setText(EditClock.getText() + " " + radioButton.getText() );

            }
        });




/*
        //  initiate the view's
        time = (TextView) findViewById(R.id.time);
        simpleTimePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
        simpleTimePicker.setIs24HourView(false); // used to display AM/PM mode
        // perform set on time changed listener event
        simpleTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // display a toast with changed values of time picker
                Toast.makeText(getApplicationContext(), hourOfDay + "  " + minute, Toast.LENGTH_SHORT).show();
                time.setText("Time is :: " + hourOfDay + " : " + minute); // set the current time in text view
            }
        });
*/
        //Time


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

      //  String time = textView.getText().toString();
       // String date = textviewYearnMonth.getText().toString();


       // Date.setText(textView.getText() + textviewYearnMonth.getText() );
        //Date.setText(time + date);

        buttonToDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = textView.getText().toString();
                String date = textviewYearnMonth.getText().toString();
                String ti = date +  time;
                 DateText.setText( ti);

                //new
                @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

                String inputText = date +" " + time;
                Date date1 = null;
                try {
                    date1 = inputFormat.parse(inputText);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                assert date1 != null;
                outputText = outputFormat.format(date1);
              //  UTCTimeZone.setText("GMT: ", TextView.BufferType.valueOf(outputFormat.format(date1)));
                UTCTimeZone.setText("UTC: " + outputText);


            }
        });

        //Extra to convert local time to GMT or UTC
        LocalTime = findViewById(R.id.LocalTime);

        UtTimeToLocal = findViewById(R.id.UtTimeToLocal);

        Date localTime = new Date();
        DateFormat s = new SimpleDateFormat("dd/MM/yyyy"
                + " "
                + " HH:mm:ss");
        s.setTimeZone(TimeZone.getTimeZone("GMT"));
        LocalTime.setText(""+ localTime);
        UtTimeToLocal.setText(""+s.format(localTime));


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
        selectdate.getMonthValue();
        Log.d("ZI", String.valueOf(selectdate));
        //

    }

    //added
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updatedView() {

        // combinedString = "01/" + mon + "/" + year ;

        //not working
        //String mon = textviewMonth.getText().toString();
        textviewMonth.getText().toString();
        //  Log.d("month","code is going here");
        //  textviewYearnMonth.setText(mon);
        // Log.d("month","code cross the textviewYearnMonth");
        //textviewYearnMonth.setText(mon);
      //  Date.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(selectdate));

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
        //combinedString = "01/" + month + "/" + year;
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
      //  selectdate = LocalDate.parse(combinedString, formatter);
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
         //   textviewYearnMonth.setText(message); //Try to put logic here to send the data to sashido. output:- 01/june/2021

            //combinedString = "16/09/2019";
            //combinedString = "01/" + month + "/" + year;
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
            //  selectdate = LocalDate.parse(combinedString, formatter);

            //01/06/2021
            textviewYearnMonth.setText(DateTimeFormatter.ofPattern(dayText+"/MM/yyyy",Locale.ENGLISH).format(selectdate));
        }


    }

    public void checkButton(View view) {

        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button" + radioButton.getText(), Toast.LENGTH_SHORT).show();

    }


    public java.util.Date LocalToUTC(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gmt = new Date(sdf.format(date));
        return gmt;
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