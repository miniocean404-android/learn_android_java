package com.example.learnandroidjava;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnandroidjava.activity.BaseActivity;
import com.example.learnandroidjava.activity.ConstraintLayoutActivity;
import com.example.learnandroidjava.activity.FrameByFrameActivity;
import com.example.learnandroidjava.activity.FrameLayoutActivity;
import com.example.learnandroidjava.activity.GridLayoutActivity;
import com.example.learnandroidjava.activity.IjkplayerActivity;
import com.example.learnandroidjava.activity.ListViewActivity;
import com.example.learnandroidjava.activity.OkHttpActivity;
import com.example.learnandroidjava.activity.PropAnimActivity;
import com.example.learnandroidjava.activity.RecyclerViewActivity;
import com.example.learnandroidjava.activity.RelativeLayoutActivity;
import com.example.learnandroidjava.activity.TableLayoutActivity;
import com.example.learnandroidjava.activity.TweenActivity;
import com.example.learnandroidjava.activity.UseFragmentActivity;
import com.example.learnandroidjava.activity.ViewPage2Activity;
import com.example.learnandroidjava.activity.ViewPageActivity;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.Toaster;
import com.simple.spiderman.SpiderMan;

import java.util.List;

import xcrash.XCrash;


public class MainActivity extends AppCompatActivity {
    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XCrash.init(this);
        SpiderMan.setTheme(com.simple.spiderman.utils.R.style.SpiderManTheme_Dark);
        Toaster.init(this.getApplication());

        ImmersionBar.with(this).transparentBar().autoDarkModeEnable(true).init();

        // AndroidManifest.xml 申请前需要在 android 中添加对应的权限
        XXPermissions.with(this)
                // 申请单个权限
                .permission(Permission.RECORD_AUDIO)
                // 申请多个权限
                .permission(Permission.Group.CALENDAR)
                // 设置权限请求拦截器（局部设置）
                //.interceptor(new PermissionInterceptor())
                // 设置不触发错误检测机制（局部设置）
                //.unchecked()
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                        if (!allGranted) {
                            Toaster.show("获取部分权限成功，但部分权限未正常授予");
                            return;
                        }
                        Toaster.show("获取录音和日历权限成功");
                    }

                    @Override
                    public void onDenied(@NonNull List<String> permissions, boolean doNotAskAgain) {
                        if (doNotAskAgain) {
                            Toaster.show("被永久拒绝授权，请手动授予录音和日历权限");
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(MainActivity.this, permissions);
                        } else {
                            Toaster.show("获取录音和日历权限失败");
                        }
                    }
                });

        jump(this,R.id.go_base_btn, BaseActivity.class);
        jump(this,R.id.go_relative_layout_btn, RelativeLayoutActivity.class);
        jump(this,R.id.go_frame_layout_btn, FrameLayoutActivity.class);
        jump(this,R.id.go_table_layout_btn, TableLayoutActivity.class);
        jump(this,R.id.go_grid_layout_btn, GridLayoutActivity.class);
        jump(this,R.id.go_constraint_layout_btn, ConstraintLayoutActivity.class);
        jump(this,R.id.go_list_view_btn, ListViewActivity.class);
        jump(this,R.id.go_recycler_view_btn, RecyclerViewActivity.class);
        jump(this,R.id.go_frame_by_frame_btn, FrameByFrameActivity.class);
        jump(this,R.id.go_tween_btn, TweenActivity.class);
        jump(this,R.id.go_prop_btn, PropAnimActivity.class);
        jump(this,R.id.go_view_page_btn, ViewPageActivity.class);
        jump(this,R.id.go_ok_http_btn, OkHttpActivity.class);
        jump(this,R.id.go_use_fragment_btn, UseFragmentActivity.class);
        jump(this,R.id.go_view_page2_btn, ViewPage2Activity.class);
        jump(this,R.id.go_ijkplayer_btn, IjkplayerActivity.class);
    }

    private void jump(Activity activity,@IdRes int id,Class<?> cls){
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
