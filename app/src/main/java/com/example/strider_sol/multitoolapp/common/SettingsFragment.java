package com.example.strider_sol.multitoolapp.common;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.strider_sol.multitoolapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private View mRootView;
    private RadioGroup mSettingRadio;
    private SharedPreferences mSharedpreferences;
    private SharedPreferences.Editor mEditor;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_settings, container, false);
        mSharedpreferences = getActivity().getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedpreferences.edit();
        mSettingRadio = (RadioGroup) mRootView.findViewById(R.id.radioGroup);

        mSettingRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioNotepad:
                        mEditor.putInt(Constant.DEFAULT_APP, Constant.NOTEPAD);
                        mEditor.commit();
                        break;
                    case R.id.radioToDo:
                        mEditor.putInt(Constant.DEFAULT_APP, Constant.TODO);
                        mEditor.commit();
                        break;
                    case R.id.radioReminder:
                        mEditor.putInt(Constant.DEFAULT_APP, Constant.REMINDER);
                        mEditor.commit();
                        break;
                    case R.id.radioDrawing:
                        mEditor.putInt(Constant.DEFAULT_APP, Constant.DRAWING);
                        mEditor.commit();
                        break;
                    case R.id.radioMovie:
                        mEditor.putInt(Constant.DEFAULT_APP, Constant.MOVIE);
                        mEditor.commit();
                        break;

                }
            }

        });
        return mRootView;
    }


}
