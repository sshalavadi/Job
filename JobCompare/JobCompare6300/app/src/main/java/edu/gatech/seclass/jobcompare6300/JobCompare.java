package edu.gatech.seclass.jobcompare6300;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import edu.gatech.seclass.jobcompare6300.entities.dao.JobWithLocation;

public class JobCompare extends AppCompatActivity {
    private boolean error;
    private TableLayout jobLCompareTable;
    private App app;
    private List<JobWithLocation> jobList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_compare);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        long[] jobId = new long[2];
        Bundle b = getIntent().getExtras();
        if (b == null) finish();

        jobId[0] = b.getLong("JobId1");
        jobId[1] = b.getLong("JobId2");
        jobList = new ArrayList<JobWithLocation>();
        jobLCompareTable = (TableLayout) findViewById(R.id.jobListTableID);
        jobLCompareTable.setStretchAllColumns(true);
        app = (App) getApplicationContext();
        jobList.add(app.db.jobOfferDao().load(jobId[0]));
        jobList.add(app.db.jobOfferDao().load(jobId[1]));
        loadData();
    }

    public void loadData() {
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int textSize = 56, smallTextSize =48, mediumTextSize = 56;

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        getSupportActionBar().setTitle("Compare offers");
        TextView textSpacer = null;
        jobLCompareTable.removeAllViews();


        textSpacer = new TextView(this);
        textSpacer.setText("");

        String[] fieldNames = new String[]{"Title", "Company", "City", "State",
                "Living index", "Yearly salary", "Yearly bonus", "Retirement benefits", "Relocation stipend", "Training fund"};
        String[][] val = new String[2][10];
        for (int i=0; i<2; i++){
            val[i] = new String[]{
                    jobList.get(i).jobOffer.getTitle() + "",
                    jobList.get(i).jobOffer.getCompany() + "",
                    jobList.get(i).getLocation().getCity() + "",
                    jobList.get(i).getLocation().getState() + "",
                    jobList.get(i).getLocation().getLivingCostIndex() + "",
                    jobList.get(i).jobOffer.getYearlySalary() + "",
                    jobList.get(i).jobOffer.getYearlyBonus() + "",
                    jobList.get(i).jobOffer.getRetirementBenefits() + "",
                    jobList.get(i).jobOffer.getRelocationStipend() + "",
                    jobList.get(i).jobOffer.getTrainingFund() + ""
            };
        }
        for(int i=0; i<fieldNames.length; i++) {
            final TextView fieldView = new TextView(this);

            fieldView.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            fieldView.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);


            fieldView.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            fieldView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            fieldView.setGravity(Gravity.LEFT);
            fieldView.setPadding(5, 0, 0, 5);
            fieldView.setBackgroundColor(Color.parseColor("#ffffff"));
            fieldView.setText(fieldNames[i]);
            fieldView.setTextColor(Color.parseColor("#000000"));

            final TextView view1 = new TextView(this);

            view1.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            view1.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

            view1.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            view1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

            view1.setGravity(Gravity.LEFT);
            view1.setPadding(5, 0, 0, 5);
            view1.setBackgroundColor(Color.parseColor("#ffffff"));
            view1.setText(val[0][i]);
            view1.setTextColor(Color.parseColor("#000000"));

            final TextView view2 = new TextView(this);
            view2.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT));
            view2.setPadding(5, 0, 0, 5);
            view2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            view2.setGravity(Gravity.LEFT);

            view2.setBackgroundColor(Color.parseColor("#f8f8f8"));
            view2.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            view2.setText(val[1][i]);

            view2.setTextColor(Color.parseColor("#000000"));

            // add table row
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new
                    TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin,
                    bottomRowMargin);
            tr.setPadding(0, 0, 0, 0);
            tr.setLayoutParams(trParams);
            tr.addView(fieldView);
            tr.addView(view1);
            tr.addView(view2);

            jobLCompareTable.addView(tr, trParams);

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
            jobLCompareTable.addView(trSep, trParamsSep);
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