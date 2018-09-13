package br.com.jcsw;

import io.reactivex.Observable;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Process {

  public static void main(String[] args) {
    System.out.println(String.format("%s [main] init thread=%s", new Date(), Thread.currentThread().getId()));

    FakeClientHttp fakeClientHttp = new FakeClientHttp();
    List<String> processSyncResult = processSync(fakeClientHttp);
    System.out.println(String.format("%s [main] processSync=%s thread=%s", new Date(), processSyncResult, Thread.currentThread().getId()));

    List<String> processAsyncByCompletableFutureResult = processAsyncByCompletableFuture(fakeClientHttp);
    System.out.println(String
        .format("%s [main] processAsyncByCompletableFutureResult=%s thread=%s", new Date(), processAsyncByCompletableFutureResult,
            Thread.currentThread().getId()));

    List<String> processAsyncByRxJavaResult = processAsyncByRxJava(fakeClientHttp);
    System.out.println(String
        .format("%s [main] processAsyncByRxJavaResult=%s thread=%s", new Date(), processAsyncByRxJavaResult,
            Thread.currentThread().getId()));

    System.out.println(String.format("%s [main] finished thread=%s", new Date(), Thread.currentThread().getId()));
  }

  private static List<String> processSync(FakeClientHttp fakeClientHttp) {
    Instant start = Instant.now();
    System.out.println(String.format("%s [processSync] init thread=%s", new Date(), Thread.currentThread().getId()));

    List<String> strings = new ArrayList<>();

    strings.add(fakeClientHttp.fakeMethod_1());
    strings.add(fakeClientHttp.fakeMethod_2());
    strings.add(fakeClientHttp.fakeMethod_3());

    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start, finish).toMillis();

    System.out.println(
        String.format("%s [processSync] finished timeElapsed=%s thread=%s", new Date(), timeElapsed, Thread.currentThread().getId()));
    return strings;
  }

  private static List<String> processAsyncByCompletableFuture(FakeClientHttp fakeClientHttp) {
    Instant start = Instant.now();
    System.out.println(String.format("%s [processAsyncByCompletableFuture] init thread=%s", new Date(), Thread.currentThread().getId()));

    List<String> strings = new ArrayList<>();

    CompletableFuture<String> completableFuture_1 = CompletableFuture.supplyAsync(fakeClientHttp::fakeMethod_1);
    CompletableFuture<String> completableFuture_2 = CompletableFuture.supplyAsync(fakeClientHttp::fakeMethod_2);
    CompletableFuture<String> completableFuture_3 = CompletableFuture.supplyAsync(fakeClientHttp::fakeMethod_3);

    try {
      strings.add(completableFuture_1.get());
      strings.add(completableFuture_2.get());
      strings.add(completableFuture_3.get());
    } catch (Exception e) {
      e.printStackTrace();
    }

    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start, finish).toMillis();

    System.out.println(
        String.format("%s [processAsyncByCompletableFuture] finished timeElapsed=%s thread=%s", new Date(), timeElapsed,
            Thread.currentThread().getId()));
    return strings;
  }


  private static List<String> processAsyncByRxJava(FakeClientHttp fakeClientHttp) {
    Instant start = Instant.now();
    System.out.println(String.format("%s [processAsyncByRxJava] init thread=%s", new Date(), Thread.currentThread().getId()));

    List<String> strings = new ArrayList<>();

    Observable<String> observable_1 = Observable.create(s -> {
      fakeClientHttp.fakeMethod_1();
      s.onComplete();
    });

    Observable<String> observable_2 = Observable.create(s -> {
      fakeClientHttp.fakeMethod_2();
      s.onComplete();
    });

    Observable<String> observable_3 = Observable.create(s -> {
      fakeClientHttp.fakeMethod_3();
      s.onComplete();
    });

    observable_1.subscribe(strings::add);
    observable_2.subscribe(strings::add);
    observable_3.subscribe(strings::add);

    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start, finish).toMillis();

    System.out.println(
        String.format("%s [processAsyncByRxJava] finished timeElapsed=%s thread=%s", new Date(), timeElapsed,
            Thread.currentThread().getId()));
    return strings;
  }

}
