package gifshow.yxcorp.com.rxjavause;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity,xiong";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button btnGetRequest = findViewById(R.id.btn_request);
    btnGetRequest.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        retrofitAndRxjava();
      }
    });
  }

  public void retrofitAndRxjava(){
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://fy.iciba.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    
    // 创建接口实例
    ApiService apiService = retrofit.create(ApiService.class);
    
    Observable<Translation> observable = apiService.getAcibaTranslation();

    observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Translation>() {
          @Override
          public void onSubscribe(Disposable d) {
            Log.d(TAG, "onSubscribe: 开始用subscribe连接");
          }

          @Override
          public void onNext(Translation translation) {
            translation.show();
          }

          @Override
          public void onError(Throwable e) {
            Log.d(TAG, "onError: 请求失败");
          }

          @Override
          public void onComplete() {
            Log.d(TAG, "onComplete: 请求成功");
          }
        });
  }

  public void rxjavaBestUse2() {
    Observable.just(1)
        .map(new Function<Integer, String>() {
          @Override
          public String apply(Integer integer) throws Exception {
            Log.e("taoge", "map 1 " + Thread.currentThread().getId() + "");
            return integer.toString();
          }
        })
        .observeOn(Schedulers.newThread())
        .map(new Function<String, Integer>() {
          @Override
          public Integer apply(String s) throws Exception {
            Log.e("taoge", "map 2 " + Thread.currentThread().getId() + "");
            return s.hashCode();
          }
        })
        .observeOn(Schedulers.newThread())
        .subscribe(new Consumer<Integer>() {
          @Override
          public void accept(Integer integer) throws Exception {
            Log.e("taoge", "onNext " + Thread.currentThread().getId() + "");
          }
        });
  }

  public void rxjavaBestUse() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> e) throws Exception {
        e.onNext(1);
        e.onNext(2);
        e.onNext(3);
        e.onComplete();
      }
    }).subscribe(new Observer<Integer>() {
      @Override
      public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
      }

      @Override
      public void onNext(Integer integer) {
        Log.d(TAG, "onNext: " + integer);
      }

      @Override
      public void onError(Throwable e) {
        Log.d(TAG, "onError: ");
      }

      @Override
      public void onComplete() {
        Log.d(TAG, "onComplete: ");
      }
    });
  }

  public void rxjavaTest() {
    Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> e) throws Exception {
        e.onNext(1);
        e.onNext(2);
        e.onNext(3);
        e.onComplete();
      }
    });

    Observer<Integer> observer = new Observer<Integer>() {
      @Override
      public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
      }

      @Override
      public void onNext(Integer integer) {
        Log.d(TAG, "onNext: " + integer);
      }

      @Override
      public void onError(Throwable e) {
        Log.d(TAG, "onError: ");
      }

      @Override
      public void onComplete() {
        Log.d(TAG, "onComplete: ");
      }
    };

    observable.subscribe(observer);

  }
}
