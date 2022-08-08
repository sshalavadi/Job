package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.entities.dao.JobWithLocation;

public class JobList extends AppCompatActivity {
    private TableLayout jobListTable;
    private int rows;
    private CheckBox[] cbList;
    private App app;
    private List<JobWithLocation> jobList;
    private long currentJobId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);
    }

    @Override
    protected void onStart() {
        super.onStart();

        jobListTable = (TableLayout) findViewById(R.id.jobListTableID);
        jobListTable.setStretchAllColumns(true);
        app = (App) getApplicationContext();
        jobList = app.db.jobOfferDao().getAll();
        currentJobId = app.getCurrentJob().jobOffer.id;
        loadData();
    }

    public void loadData() {
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 56, smallTextSize =48, mediumTextSize = 56;

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        rows = jobList.size();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Offers (" + String.valueOf(rows) + ")");
        TextView textSpacer = null;
        jobListTable.removeAllViews();
        cbList = new CheckBox[rows];
        // -1 means heading row
        for(int i = -1; i < rows; i ++) {
            if (i == -1) {
                textSpacer = new TextView(this);
                textSpacer.setText("");
            }
            // data columns
            final CheckBox cb = new CheckBox(this);
            cb.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            cb.setGravity(Gravity.CENTER);
            cb.setPadding(5, 15, 0, 15);
            if (i == -1) {
                cb.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                cb.setGravity(Gravity.CENTER);
                cb.setBackgroundColor(Color.parseColor("#f0f0f0"));
                cb.setEnabled(false);
            }
            else {
                cb.setBackgroundColor(Color.parseColor("#f8f8f8"));
                cbList[i] = cb;
            }

            final TextView titleView = new TextView(this);
            if (i == -1) {
                titleView.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                titleView.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
            titleView.setGravity(Gravity.LEFT);
            titleView.setPadding(5, 0, 0, 5);
            if (i == -1) {
                titleView.setText("Title");
                titleView.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }
            else {
                titleView.setBackgroundColor(Color.parseColor("#ffffff"));
                titleView.setText(jobList.get(i).jobOffer.getTitle() + "");
            }
            titleView.setTextColor(Color.parseColor("#000000"));
            final TextView companyView = new TextView(this);
            if (i == -1) {
                companyView.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                companyView.setPadding(5, 5, 0, 5);
                companyView.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            }
            else {
                companyView.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                companyView.setPadding(5, 0, 0, 5);
                companyView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
            companyView.setGravity(Gravity.LEFT);
            if (i == -1) {
                companyView.setBackgroundColor(Color.parseColor("#f0f0f0"));
                companyView.setText("Company");
            }
            else {
                companyView.setBackgroundColor(Color.parseColor("#f8f8f8"));
                companyView.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                companyView.setText(jobList.get(i).jobOffer.getCompany() + "");
            }
            companyView.setTextColor(Color.parseColor("#000000"));

            // add table row
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new
                    TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin,
                    bottomRowMargin);
            tr.setPadding(0,0,0,0);
            tr.setLayoutParams(trParams);
            if (i > -1 && jobList.get(i).jobOffer.id == currentJobId) {
                cb.setBackgroundColor(Color.parseColor("#f8f800"));
                titleView.setBackgroundColor(Color.parseColor("#f8f800"));
                companyView.setBackgroundColor(Color.parseColor("#f8f800"));
            }
            tr.addView(cb);
            tr.addView(titleView);
            tr.addView(companyView);

            if (i > -1) {
                int finalI = i;
                tr.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TableRow tr = (TableRow) v;
                        CheckBox check = (CheckBox)tr.getVirtualChildAt(0);
                        check.setChecked(!check.isChecked());
                    }
                });
            }
            jobListTable.addView(tr, trParams);

            if (i > -1) {
                // add separator row
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new
                        TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin,
                        rightRowMargin, bottomRowMargin);
                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 3;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#000000"));//d9d9d9
                tvSep.setHeight(1);
                trSep.addView(tvSep);
                jobListTable.addView(trSep, trParamsSep);
            }
        }
    }

    public void onClick(View v) {
        int[] flags = new int[rows];
        int count = 0;
        for (int i=0; i<rows; i++) {
            if (cbList[i].isChecked()) {
                flags[count] = i;
                count++;
            }
        }
        if (count != 2) {
            for (int i=0; i<count; i++){
                cbList[flags[i]].setError("Please select two offer to compare");
            }
        }
        else {
            Intent intent = new Intent(this, JobCompare.class);
            intent.putExtra("JobId1", jobList.get(flags[0]).jobOffer.id);
            intent.putExtra("JobId2", jobList.get(flags[1]).jobOffer.id);
            startActivity(intent);
        }
    }

    public void delete(View v) {
        int[] flags = new int[rows];
        int count = 0;
        for (int i=0; i<rows; i++) {
            if (cbList[i].isChecked()) {
                flags[count] = i;
                count++;
            }
        }
        if (count > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Confirm delete");
            builder.setMessage("Are you sure you want to delete the offer(s)?");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (int i=0; i<cbList.length; i++){
                                if (cbList[i].isChecked()){
                                    if (jobList.get(i).jobOffer.id == currentJobId) app.setCurrentJob(null);
                                    app.db.jobOfferDao().delete(jobList.get(i).jobOffer);
                                }
                            }
                            finish();
                            startActivity(getIntent());
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void set_to_current(View v) {
        int[] flags = new int[rows];
        int count = 0;
        for (int i=0; i<rows; i++) {
            if (cbList[i].isChecked()) {
                flags[count] = i;
                count++;
            }
        }
        if (count > 1) {
            for (int i=0; i<count; i++){
                cbList[flags[i]].setError("Only one offer can be current job!");
            }
        }
        else if (count == 1){
            app.setCurrentJob(jobList.get(flags[0]));
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}