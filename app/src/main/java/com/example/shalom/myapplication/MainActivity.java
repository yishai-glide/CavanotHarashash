package com.example.shalom.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.sourceforge.zmanim.*;
import net.sourceforge.zmanim.hebrewcalendar.HebrewDateFormatter;
import net.sourceforge.zmanim.hebrewcalendar.JewishCalendar;
import net.sourceforge.zmanim.hebrewcalendar.JewishDate;
import net.sourceforge.zmanim.util.*;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //  Button button = (Button)findViewById((R.id.bntSetDate));

        //final Button button = findViewById(R.id.bntSetDate);
        //btnUpdateDate = (Button) findViewById(R.id.bntSetDate);

//        btnUpdateDate.setOnClickListener(MainActivity.this);


        JewishCalendar jd = new JewishCalendar(); // current date 23 Nissan, 5773
        HebrewDateFormatter hdf = new HebrewDateFormatter();
        hdf.setHebrewFormat(true); //set formatted to Hebrew

        hdf.format(jd);
        EditText txtYear = (EditText) findViewById(R.id.txtYear);
        EditText txtMonth = (EditText) findViewById(R.id.txtMonth);

        EditText txtDay = (EditText) findViewById(R.id.txtDay);

        txtYear.setText(Integer.toString(jd.getJewishYear()));
        txtMonth.setText(Integer.toString(jd.getJewishMonth()));
        txtDay.setText(Integer.toString(jd.getJewishDayOfMonth()));

        //Button btn1 = (Button)findViewById(R.id.bntSetDate);


        times();


    }

    public void onClick(View v) {
        times();
        //EditText txtDay = (EditText) findViewById(R.id.txtDay);
        //txtDay.setText("asf");
    }

    public void times() {
        JewishCalendar jd = new JewishCalendar(); // current date 23 Nissan, 5773
        HebrewDateFormatter hdf = new HebrewDateFormatter();
        hdf.setHebrewFormat(true); //set formatted to Hebrew
        hdf.format(jd);

        EditText txtYear = (EditText) findViewById(R.id.txtYear);
        EditText txtMonth = (EditText) findViewById(R.id.txtMonth);
        EditText txtDay = (EditText) findViewById(R.id.txtDay);


        jd.setJewishDate(Integer.parseInt(txtYear.getText().toString()),Integer.parseInt(txtMonth.getText().toString()),Integer.parseInt(txtDay.getText().toString()));

        //Button b1 = (Button) findViewById(R.id.button2);
        TextView lblDate = (TextView) findViewById(R.id.lblDate);
        TextView lblCavana = (TextView) findViewById(R.id.lblCavana);
        //jd.setInIsrael(true);
        //jd.setGregorianDate(2016,03,15);
        //jd.setJewishDate(5777, 1, 17);
        String yearMeotCavana;
        String yearAlafimCavana;
        String yearAsarotCavana;
        String yearAchadotCavana;
        String monthCavana;
        String weekDayCavana;
        String monthDayCavana;

        //String asd;
        weekDayCavana=CavanaSeven(jd.getDayOfWeek());//jd.getDayOfWeek());// + " " + hdf.format(jd);
        monthCavana=monthCavana(jd.getJewishMonth());
        yearAchadotCavana=CavanaTen(jd.getJewishYear()%10);
        yearAsarotCavana=CavanaTen((jd.getJewishYear()/10)%10);
        yearMeotCavana=CavanaTen((jd.getJewishYear()/100)%10);
        yearAlafimCavana=CavanaSix(jd.getJewishYear()/1000+1);
        monthDayCavana=strDayMonthCavana(jd.getJewishDayOfMonth(), jd);
        //lblDate.setText(hdf.format(jd.getDayOfWeek());
        //int year=jd.getJewishYear();
        //int y2=year%100;
        //int y3=y2/10;
        //y3=12345;
        lblDate.setText("יום " + weekDay2string(jd.getDayOfWeek()) + " " + hdf.format(jd));
        lblCavana.setText(weekDayCavana + " דפרצוף " + monthDayCavana + " ד" + monthCavana + " ד" + yearAchadotCavana + " ד" + yearAsarotCavana + " ד" + yearMeotCavana + " ד" + yearAlafimCavana);
        //lblCavana.setText(Integer.toString(jd.getDayOfWeek()));
        //yearMeotCavana=CavanaTen((jd.getJewishYear()%100)/10);


    }

    public String monthCavana(int month)
    {
        String cavanah="error";

        if (month==13)
        {
            return "מ\"ה וב\"ן דמ\"ה וב\"ן";
        }
        cavanah=CavanaSix(month%6);
        if (month<=6)
        {
            cavanah=cavanah + " דב\"ן דמ\"ה וב\"ן דב\"ן דזו\"ן הנקרא נוקבא";
        }
        else
        {
            cavanah=cavanah + "דמ\"ה דמ\"ה ומ\"ה דב\"ן דזו\"ן הנקרא דוכרא";
        }
        return cavanah;
    }

    public String strDayMonthCavana(int intDay, JewishCalendar jd) {
        String strCavana;
        strCavana="error";
        if (intDay == 1) {
            if (HodeshMale(jd)) {
                strCavana = "חיצוניות דכתר (אריך)";
            } else {
                strCavana = "פנימיות וחיצוניות דכתר (אריך)";
            }
        }
        else if (intDay == 30)
        {
            strCavana = "פנימיות דכתר (אריך)";
        }
        else if (intDay>1 && intDay<=8)
        {
            strCavana="חכמה (אבא)";
        }
        else if (intDay>8 && intDay<=15)
        {
            strCavana="בינה (אימא)";
        }
        else if (intDay>15 && intDay<=22)
        {
            strCavana="ו\"ק (ז\"א)";
        }
        else if (intDay>22 && intDay<=29)
        {
            strCavana="מל' (נוק')";
        }
        return strCavana;
    }

    public boolean HodeshMale(JewishCalendar jd)
    {
        if (jd.getJewishMonth()==8) //heshvan
        {
            return jd.isCheshvanLong();
        }
        if (jd.getJewishMonth()==9) //kislev
        {
            return !jd.isKislevShort();
        }
        if (jd.getJewishMonth()%2==0)
        {
            return false;
        }
        if (jd.getJewishMonth()%2==1)
        {
            return true;
        }
        return true;
    }

    public String CavanaTen(int num)
    {
        String Cavana;
        switch (num) {
            case 0:  Cavana = "חכמה";
                break;
            case 1:  Cavana = "בינה";
                break;
            case 2:  Cavana = "דעת";
                break;
            case 3:  Cavana= "חסד";
                break;
            case 4:  Cavana = "גבורה";
                break;
            case 5:  Cavana = "תפארת";
                break;
            case 6:  Cavana = "נצח";
                break;
            case 7:  Cavana = "הוד";
                break;
            case 8:  Cavana = "יסוד";
                break;
            case 9:  Cavana = "מלכות";
                break;
            default: Cavana = "Invalid month";
                break;
        }

        return Cavana;
    }

    public String CavanaSix(int num)
    {
        String Cavana;
        switch (num) {
            case 1:  Cavana = "חסד";
                break;
            case 2:  Cavana = "גבורה";
                break;
            case 3:  Cavana= "תפארת";
                break;
            case 4:  Cavana = "נצח";
                break;
            case 5:  Cavana = "הוד";
                break;
            case 6:  Cavana = "יסוד";
                break;
            default: Cavana = "Invalid month";
                break;
        }

        return Cavana;
    }

    public String CavanaSeven(int intDay){
        String stringDay;

        switch (intDay) {
            case 1:  stringDay = "חסד";
                break;
            case 2:  stringDay = "גבורה";
                break;
            case 3:  stringDay = "תפארת";
                break;
            case 4:  stringDay = "נצח";
                break;
            case 5:  stringDay = "הוד";
                break;
            case 6:  stringDay = "יסוד";
                break;
            case 7:  stringDay = "מלכות";
                break;
            default: stringDay = "Invalid day";
                break;
        }

        return  stringDay;

    }


    public String weekDay2string(int intDay){
        String stringDay;

        switch (intDay) {
            case 1:  stringDay = "ראשון";
                break;
            case 2:  stringDay = "שני";
                break;
            case 3:  stringDay = "שלישי";
                break;
            case 4:  stringDay = "רביעי";
                break;
            case 5:  stringDay = "חמישי";
                break;
            case 6:  stringDay = "שישי";
                break;
            case 7:  stringDay = "שבת";
                break;
            default: stringDay = "Invalid day";
                break;
        }

        return  stringDay;

    }


    public String month2string(int intMonth){
        String strMonth;

        switch (intMonth) {
            case 1:  strMonth = "ראשון";
                break;
            case 2:  strMonth = "שני";
                break;
            case 3:  strMonth = "שלישי";
                break;
            case 4:  strMonth = "רביעי";
                break;
            case 5:  strMonth = "חמישי";
                break;
            case 6:  strMonth = "שישי";
                break;
            case 7:  strMonth = "שבת";
                break;
            default: strMonth = "Invalid month";
                break;
        }

        return  strMonth;

    }
}
