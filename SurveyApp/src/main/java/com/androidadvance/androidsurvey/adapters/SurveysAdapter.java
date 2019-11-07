package com.androidadvance.androidsurvey.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SurveyDetailActivity;
import com.androidadvance.androidsurvey.models.SurveyTobeSaved;
import com.google.gson.Gson;

import java.util.List;

public class SurveysAdapter extends RecyclerView.Adapter<SurveysAdapter.SurveyViewHolder> {



    class SurveyViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtuserName;
        private final TextView txtDate;

        private SurveyViewHolder(View itemView) {
            super(itemView);
            txtuserName = itemView.findViewById(R.id.txt_user_name);
            txtDate = itemView.findViewById(R.id.txt_date);
        }
    }

    private final LayoutInflater mInflater;
    private List<SurveyTobeSaved> mSurveyTobeSaveds; // Cached copy of words

    public SurveysAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public SurveyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SurveyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SurveyViewHolder holder, int position) {
        if (mSurveyTobeSaveds != null) {
            SurveyTobeSaved current = mSurveyTobeSaveds.get(position);
            holder.txtuserName.setText(current.getUserName());
            holder.txtDate.setText("Date:" +current.getDate());
        } else {
            // Covers the case of data not being ready yet.
            holder.txtuserName.setText("No Surveys");
            holder.txtDate.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.itemView.getContext(), SurveyDetailActivity.class);
                intent.putExtra("SURVEY", new Gson().toJson(mSurveyTobeSaveds.get(position)));
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    public void setSurveysList(List<SurveyTobeSaved> surveyTobeSaveds){
        mSurveyTobeSaveds = surveyTobeSaveds;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSurveyTobeSaveds != null)
            return mSurveyTobeSaveds.size();
        else return 0;
    }
}
