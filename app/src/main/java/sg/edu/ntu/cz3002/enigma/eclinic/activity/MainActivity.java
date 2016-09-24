package sg.edu.ntu.cz3002.enigma.eclinic.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import sg.edu.ntu.cz3002.enigma.eclinic.R;
import sg.edu.ntu.cz3002.enigma.eclinic.Value;
import sg.edu.ntu.cz3002.enigma.eclinic.fragment.ChatFragment;
import sg.edu.ntu.cz3002.enigma.eclinic.fragment.ReminderFragment;
import sg.edu.ntu.cz3002.enigma.eclinic.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int _currentTab = R.id.reminders_button;

    @BindView(R.id.bottomBar) BottomBar _bottombar;
    ReminderFragment _reminderFragment;
    ChatFragment _chatFragment;
    SettingFragment _settingFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // check whether user logged in
        SharedPreferences preferences = this.getSharedPreferences(Value.preferenceFilename, Context.MODE_PRIVATE);
        if (preferences.contains(Value.authTokenPreferenceName)) {
            // continue on MainActivity
            Log.d(TAG, "Auth token exists");
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            // set up the UI components
            setFragments();
            setBottomBar();
        } else {
            // go to LoginActivity
            Log.d(TAG, "Auth token doesn't exist");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void setBottomBar() {
        _bottombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Log.d(TAG, "tab selected " + tabId);
                changeTab(tabId);
                _currentTab = _bottombar.getCurrentTabId();
            }
        });
    }

    private void setFragments() {
        _reminderFragment = ReminderFragment.newInstance(0);
        _chatFragment = ChatFragment.newInstance(0);
        _settingFragment = SettingFragment.newInstance(0);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void switchTo(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentContainer, fragment);
        transaction.commit();
    }

    private void changeTab(int tabId) {
        switch (tabId) {
            case R.id.reminders_button:
                switchTo(_reminderFragment);
                break;
            case R.id.chats_button:
                switchTo(_chatFragment);
                break;
            case R.id.settings_button:
                switchTo(_settingFragment);
                break;
        }
    }
}