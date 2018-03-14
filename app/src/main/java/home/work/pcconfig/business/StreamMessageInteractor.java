package home.work.pcconfig.business;


import com.github.mrvilkaman.di.PerScreen;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@PerScreen
public class StreamMessageInteractor {

    @Inject
    public StreamMessageInteractor() {
    }

    public Observable<List<SmsItem>> observableSms(){
        return Observable.just(new SmsItem("+38066603643","deadline is comming!"))
                .toList().toObservable();
    }

    public void saveSms(String number, String text){
        new SmsItem(number,text);
    }
}
