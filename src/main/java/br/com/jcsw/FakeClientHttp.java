package br.com.jcsw;

import java.util.Date;
import java.util.concurrent.TimeUnit;

class FakeClientHttp {

  String fakeMethod_1() {
    System.out.println(String.format("%s [fakeMethod_1] init thread=%s", new Date(), Thread.currentThread().getId()));

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(String.format("%s [fakeMethod_1] finished thread=%s", new Date(), Thread.currentThread().getId()));
    return "fakeMethod_1";
  }

  String fakeMethod_2() {
    System.out.println(String.format("%s [fakeMethod_2] init thread=%s", new Date(), Thread.currentThread().getId()));

    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(String.format("%s [fakeMethod_2] finished thread=%s", new Date(), Thread.currentThread().getId()));
    return "fakeMethod_2";
  }

  String fakeMethod_3() {
    System.out.println(String.format("%s [fakeMethod_3] init thread=%s", new Date(), Thread.currentThread().getId()));

    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(String.format("%s [fakeMethod_3] finished thread=%s", new Date(), Thread.currentThread().getId()));
    return "fakeMethod_3";
  }

}
