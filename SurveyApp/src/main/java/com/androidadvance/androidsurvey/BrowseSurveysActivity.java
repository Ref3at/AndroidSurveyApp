package com.androidadvance.androidsurvey;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.androidadvance.androidsurvey.adapters.SurveysAdapter;
import com.androidadvance.androidsurvey.models.SurveyTobeSaved;
import com.androidadvance.androidsurvey.viewModel.SurveyToBeSavedViewModel;

import java.nio.file.Files;
import java.util.List;

public class BrowseSurveysActivity extends AppCompatActivity {

    private SurveyToBeSavedViewModel mSurveyPojoViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_surveys);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final SurveysAdapter adapter = new SurveysAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        //view Model sec
        mSurveyPojoViewModel = new ViewModelProvider(this).get(SurveyToBeSavedViewModel.class);
        mSurveyPojoViewModel.getAllSurveys().observe(this, new Observer<List<SurveyTobeSaved>>() {
            @Override
            public void onChanged(@Nullable final List<SurveyTobeSaved> surveyList) {
                // Update the cached copy of the words in the adapter.
                Log.e("surveyListSize", surveyList.size() + "");
                adapter.setSurveysList(surveyList);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_delete:
                showADeletelert();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showADeletelert() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Surveys")
                .setMessage("Are you sure you want to delete all surveys?")
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        mSurveyPojoViewModel.deleteAll();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
