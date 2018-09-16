package com.arit.demo.localstorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.arit.demo.localstorage.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    private String displayname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @BindView(R.id.tvShowPreference) TextView tvShowPreference;

    @OnClick(R.id.btnGetPref)
    public void showPref(){
        SharedPreferences pref = getApplicationContext()
                .getSharedPreferences("settings", MODE_PRIVATE);
        this.displayname = pref.getString("display-name", "");

        this.tvShowPreference.setText(this.displayname);
    }

    @OnClick(R.id.btnGetRealmData)
    public void showRealmData(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class)
                                 .findFirst();

                tvShowPreference.setText(user.getFirstname());
            }
        });
    }
}
