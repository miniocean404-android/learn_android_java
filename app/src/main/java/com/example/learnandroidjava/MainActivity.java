package com.example.learnandroidjava;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnandroidjava.activity.base.db.RoomActivity;
import com.example.learnandroidjava.activity.base.db.SQLiteActivity;
import com.example.learnandroidjava.activity.base.event.BaseActivity;
import com.example.learnandroidjava.activity.base.intent.IntentParamsActivity;
import com.example.learnandroidjava.activity.base.layout.ConstraintLayoutActivity;
import com.example.learnandroidjava.activity.base.anim.FrameByFrameActivity;
import com.example.learnandroidjava.activity.base.layout.FrameLayoutActivity;
import com.example.learnandroidjava.activity.base.layout.GridLayoutActivity;
import com.example.learnandroidjava.activity.base.event.ViewBindingActivity;
import com.example.learnandroidjava.activity.base.media.MediaRecorderActivity;
import com.example.learnandroidjava.activity.base.receiver.ReceiverActivity;
import com.example.learnandroidjava.activity.base.service.ServiceActivity;
import com.example.learnandroidjava.activity.base.ui.ShapeActivity;
import com.example.learnandroidjava.activity.lib.AMapActivity;
import com.example.learnandroidjava.activity.lib.FrescoActivity;
import com.example.learnandroidjava.activity.lib.IjkplayerActivity;
import com.example.learnandroidjava.activity.base.scroll.ListViewActivity;
import com.example.learnandroidjava.activity.lib.LibGlideActivity;
import com.example.learnandroidjava.activity.lib.OkHttpActivity;
import com.example.learnandroidjava.activity.base.anim.PropAnimActivity;
import com.example.learnandroidjava.activity.base.scroll.RecyclerViewActivity;
import com.example.learnandroidjava.activity.base.layout.RelativeLayoutActivity;
import com.example.learnandroidjava.activity.base.layout.TableLayoutActivity;
import com.example.learnandroidjava.activity.base.anim.TweenActivity;
import com.example.learnandroidjava.activity.base.fragment.UseFragmentActivity;
import com.example.learnandroidjava.activity.base.scroll.ViewPage2Activity;
import com.example.learnandroidjava.activity.base.scroll.ViewPageActivity;
import com.example.learnandroidjava.activity.lib.PermissionsActivity;
import com.example.learnandroidjava.activity.lib.RxJavaActivity;
import com.gyf.immersionbar.ImmersionBar;


public class MainActivity extends AppCompatActivity {
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 解决首屏 splash 启动页问题
        setTheme(R.style.Theme_LearnAndroidJava);

        setContentView(R.layout.activity_main);

//        XCrash.init(this);
//        SpiderMan.setTheme(com.simple.spiderman.utils.R.style.SpiderManTheme_Dark);
//        Toaster.init(this.getApplication());
        ImmersionBar.with(this).transparentBar().autoDarkModeEnable(true).init();


        jump(this, R.id.go_base_btn, BaseActivity.class);
        jump(this, R.id.go_relative_layout_btn, RelativeLayoutActivity.class);
        jump(this, R.id.go_frame_layout_btn, FrameLayoutActivity.class);
        jump(this, R.id.go_table_layout_btn, TableLayoutActivity.class);
        jump(this, R.id.go_grid_layout_btn, GridLayoutActivity.class);
        jump(this, R.id.go_constraint_layout_btn, ConstraintLayoutActivity.class);
        jump(this, R.id.go_list_view_btn, ListViewActivity.class);
        jump(this, R.id.go_recycler_view_btn, RecyclerViewActivity.class);
        jump(this, R.id.go_frame_by_frame_btn, FrameByFrameActivity.class);
        jump(this, R.id.go_tween_btn, TweenActivity.class);
        jump(this, R.id.go_prop_btn, PropAnimActivity.class);
        jump(this, R.id.go_view_page_btn, ViewPageActivity.class);
        jump(this, R.id.go_ok_http_btn, OkHttpActivity.class);
        jump(this, R.id.go_use_fragment_btn, UseFragmentActivity.class);
        jump(this, R.id.go_view_page2_btn, ViewPage2Activity.class);
        jump(this, R.id.go_ijkplayer_btn, IjkplayerActivity.class);
        jump(this, R.id.go_view_binding_btn, ViewBindingActivity.class);
        jump(this, R.id.go_glide_btn, LibGlideActivity.class);
        jump(this, R.id.go_amap_btn, AMapActivity.class);
        jump(this, R.id.go_permissions_btn, PermissionsActivity.class);
        jump(this, R.id.go_receiver_btn, ReceiverActivity.class);
        jump(this, R.id.go_service_btn, ServiceActivity.class);
        jump(this, R.id.go_rx_java_btn, RxJavaActivity.class);
        jump(this, R.id.go_fresco_btn, FrescoActivity.class);
        jump(this, R.id.go_sqlite_btn, SQLiteActivity.class);
        jump(this, R.id.go_root_btn, RoomActivity.class);
        jump(this, R.id.go_intent_params_btn, IntentParamsActivity.class);
        jump(this, R.id.go_media_recorder_btn, MediaRecorderActivity.class);
        jump(this, R.id.go_compose_project_btn, App.class);
        jump(this, R.id.go_shape_btn, ShapeActivity.class);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 画面展示了
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void jump(Activity activity, @IdRes int id, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        Button btn = findViewById(id);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
