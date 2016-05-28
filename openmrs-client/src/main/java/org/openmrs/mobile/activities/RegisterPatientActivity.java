/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.mobile.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import org.openmrs.mobile.R;
import java.util.Calendar;

public class RegisterPatientActivity extends ACBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_register_patients);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        RadioGroup gen=(RadioGroup)findViewById(R.id.gender);
        final TextView e=(TextView)findViewById(R.id.gendererror);


        gen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup rGroup, int checkedId)
            {
                e.setVisibility(View.GONE);
                RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked)
                {
                    //tv.setText("Checked:" + checkedRadioButton.getText());
                }
            }
        });

        final EditText dobEditText=(EditText)findViewById(R.id.dob);

        if (dobEditText != null) {
            dobEditText.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Calendar currentDate=Calendar.getInstance();
                    int cYear=currentDate.get(Calendar.YEAR);
                    int cMonth=currentDate.get(Calendar.MONTH);
                    int cDay=currentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker=new DatePickerDialog(RegisterPatientActivity.this, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            dobEditText.setText(selectedday+"/"+selectedmonth+"/"+selectedyear);
                        }
                    },cYear, cMonth, cDay);
                    mDatePicker.setTitle("Select date");
                    mDatePicker.show();  }
            });
        }


    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public void confirm(View v)
    {
        EditText edfname = (EditText) findViewById(R.id.firstname);
        EditText edlname = (EditText) findViewById(R.id.surname);
        EditText eddob=(EditText)findViewById(R.id.dob);
        EditText edyr=(EditText)findViewById(R.id.estyr);
        EditText edmonth=(EditText)findViewById(R.id.estmonth);
        EditText edaddr1=(EditText)findViewById(R.id.addr1);
        EditText edaddr2=(EditText)findViewById(R.id.addr2);
        EditText edcity=(EditText)findViewById(R.id.city);
        EditText edstate=(EditText)findViewById(R.id.state);
        EditText edcountry=(EditText)findViewById(R.id.country);
        EditText edpostal=(EditText)findViewById(R.id.postal);

        RadioGroup gen=(RadioGroup)findViewById(R.id.gender);


        TextView fnameerror=(TextView)findViewById(R.id.fnameerror);
        TextView lnameerror=(TextView)findViewById(R.id.lnameerror);
        TextView doberror=(TextView)findViewById(R.id.doberror);
        TextView gendererror=(TextView)findViewById(R.id.gendererror);
        TextView addrerror=(TextView)findViewById(R.id.addrerror);

        fnameerror.setVisibility(View.GONE);
        lnameerror.setVisibility(View.GONE);
        doberror.setVisibility(View.GONE);
        gendererror.setVisibility(View.GONE);
        addrerror.setVisibility(View.GONE);




        if(isEmpty(edfname))
            fnameerror.setVisibility(View.VISIBLE);
        if(isEmpty(edlname))
            lnameerror.setVisibility(View.VISIBLE);

        if(isEmpty(eddob)&& (isEmpty(edyr)||isEmpty(edmonth)))
            doberror.setVisibility(View.VISIBLE);

        if(isEmpty(edaddr1) && isEmpty(edaddr2) && isEmpty(edcity) && isEmpty(edstate)
                && isEmpty(edcountry) && isEmpty(edpostal))
            addrerror.setVisibility(View.VISIBLE);


        if (gen.getCheckedRadioButtonId() == -1)
            gendererror.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
