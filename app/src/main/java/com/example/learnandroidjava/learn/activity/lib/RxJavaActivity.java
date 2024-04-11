package com.example.learnandroidjava.learn.activity.lib;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.learnandroidjava.databinding.ActivityRxJavaBinding;
import com.example.learnandroidjava.learn.model.bean.RxJavaResponse;
import com.example.learnandroidjava.learn.model.bean.RxJavaSuccessResponse;
import com.example.learnandroidjava.learn.model.bean.RxJavaResponse;
import com.example.learnandroidjava.learn.model.bean.RxJavaSuccessResponse;
import com.example.learnandroidjava.learn.shared.rx_observer.RxJavaRespObserver;
import com.hjq.toast.Toaster;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = RxJavaActivity.class.getSimpleName();
    private ActivityRxJavaBinding binding;
    private final String PATH = "https://picsum.photos/200/300";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRxJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rxImageShowLoading.setOnClickListener(this);
        binding.rxJavaFromArr.setOnClickListener(this);
        binding.rxJavaResp.setOnClickListener(this);
    }

    /**
     * RxJava 所有函数都成为操作符，是为了控制数据流，从起点流向终点
     * 视频链接：<a href="https://www.bilibili.com/video/BV1a3411771y?p=77&spm_id_from=pageDriver&vd_source=d898d1900c1ac2cfbd6d235613e095d3">...</a>
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == binding.rxImageShowLoading.getId()) {
            // 起点
            Observable.just(PATH)
                    // 需求 1 通过 http 获取图片
                    .map(new Function<String, Bitmap>() {
                        @Override
                        public Bitmap apply(String path) throws Throwable {
                            try {
                                Thread.sleep(2000);

                                URL url = new URL(path);

                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setConnectTimeout(5000);

                                int code = connection.getResponseCode();

                                if (code == HttpURLConnection.HTTP_OK) {
                                    InputStream inputStream = connection.getInputStream();
                                    return BitmapFactory.decodeStream(inputStream);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    })
                    // 需求 2 加水印
                    .map(new Function<Bitmap, Bitmap>() {
                        @Override
                        public Bitmap apply(Bitmap bitmap) throws Throwable {
                            Paint paint = new Paint();
                            paint.setColor(Color.RED);
                            paint.setTextSize(88);

                            return drawableTextToBitmap(bitmap, "RxJava", paint, 10, 10);
                        }
                    })
                    // 需求三 日志记录需求
                    .map(new Function<Bitmap, Bitmap>() {
                        @Override
                        public Bitmap apply(Bitmap bitmap) throws Throwable {
                            Log.i(TAG, "apply: " + "日志记录需求");
                            return bitmap;
                        }
                    })
                    // 在异步线程中订阅上方的操作
                    .subscribeOn(Schedulers.io())
                    // 在主线中观察上方的异步操作，等待完成后执行 next
                    .observeOn(AndroidSchedulers.mainThread())
                    // 关联观察者 关联 起点 和 终点 == 订阅
                    .subscribe(new Observer<Bitmap>() {
                        // 订阅成功
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            progressDialog = new ProgressDialog(RxJavaActivity.this);
                            progressDialog.setTitle("RxJava");
                            progressDialog.setMessage("Loading...");
                            progressDialog.show();
                        }

                        // 上层给的响应
                        @Override
                        public void onNext(@NonNull Bitmap bitmap) {
                            // 展示到控件上
                            binding.rxJavaImage.setImageBitmap(bitmap);
                        }

                        // 关联异常
                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        // 整个链条完全结束
                        @Override
                        public void onComplete() {
                            if (progressDialog != null) {
                                progressDialog.dismiss();
                            }
                        }
                    });
        } else if (view.getId() == binding.rxJavaFromArr.getId()) {
            String[] arr = {"1", "2", "3", "4", "5"};

            // 一次性订阅组
            CompositeDisposable compositeDisposable = new CompositeDisposable();

            Disposable disposable = Observable.fromArray(arr)
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Throwable {
                            Log.i(TAG, "accept: " + s);
                        }
                    });

            // 将Disposable对象添加到CompositeDisposable中
            compositeDisposable.add(disposable);

            // 在适当的时候取消订阅
            compositeDisposable.clear();
        } else if (view.getId() == binding.rxJavaResp.getId()) {
            Observable<RxJavaResponse> observable = testData("admin", "12345633");

            observable.subscribe(new RxJavaRespObserver() {
                @Override
                public void success(@androidx.annotation.NonNull RxJavaSuccessResponse data) {
                    Toaster.show("登录成功");
                }

                @Override
                public void error(@androidx.annotation.NonNull String message) {
                    Toaster.show(message);
                }
            });
        }
    }

    private final Bitmap drawableTextToBitmap(Bitmap bitmap, String text, Paint paint, int paddingLeft, int paddingTop) {
        Bitmap.Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取更清晰的图像采样
        paint.setFilterBitmap(true); // 过滤一些

        bitmap = bitmap.copy(bitmapConfig, true);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(text, paddingLeft, paddingTop, paint);

        return bitmap;
    }

    private Observable<RxJavaResponse> testData(String name, String pwd) {
        RxJavaResponse successResp = new RxJavaResponse();

        if (name.equals("admin") && pwd.equals("123456")) {
            successResp.code = 200;
            successResp.data = new RxJavaSuccessResponse(123, "admin");
            successResp.message = "登录成功";
        } else {
            successResp.code = 404;
            successResp.data = null;
            successResp.message = "登录失败";
        }

        return Observable.just(successResp);
    }
}