package com.sxmd.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author cy
 * @date 2019年12月18日 9:33
 * Version 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception{
       Observable.interval(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(x->{
                    System.out.println("hello ");
                });

        System.out.println("结束");
    }
}
