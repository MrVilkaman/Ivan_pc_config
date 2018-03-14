package home.work.pcconfig.business;


import android.content.SharedPreferences;

import com.github.mrvilkaman.di.PerScreen;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

@PerScreen
public class StreamMessageInteractor {

    private static final String KEY_SMS = "KEY_SMS";
    private final SharedPreferences preferences;
    private final Gson gson;

    BehaviorSubject<List<SmsItem>> history = BehaviorSubject.create();

    @Inject
    public StreamMessageInteractor(SharedPreferences preferences) {
        this.preferences = preferences;
        gson = new Gson();
    }

    public Observable<List<SmsItem>> observableSms() {
        return history.doOnSubscribe(disposable -> checkDisk()).doOnDispose(this::saveSmsToDisk);
    }

    private void saveSmsToDisk() {
        preferences.edit().putString(KEY_SMS, gson.toJson(history.getValue())).apply();
    }

    private void checkDisk() {
        final String string = preferences.getString(KEY_SMS, "");
        if (string.isEmpty()) {
            history.onNext(new ArrayList<>());
        } else {
            history.onNext(gson.fromJson(string, new TypeToken<List<SmsItem>>() {
            }.getType()));
        }
    }

    public void saveSms(String number, String text) {
        final List<SmsItem> value = history.getValue();
        if (value.size() <=10 ) {
            value.add(new SmsItem(number, text));
            history.onNext(value);
        }
    }
}
