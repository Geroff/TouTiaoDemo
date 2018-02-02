package com.example.lgf.toutiao;

import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;

import com.example.lgf.toutiao.widget.helper.BottomNavigationViewHelper;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main_toolbar);
        toolbar.setBackgroundResource(R.color.colorAccent);
        drawerLayout = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.CYAN);
            getWindow().setNavigationBarColor(Color.CYAN);

            // 最近任务栏显示设置
            ActivityManager.TaskDescription tDesc = new ActivityManager.TaskDescription(
                    getResources().getString(R.string.app_name),
                    BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_square));
            setTaskDescription(tDesc);
        }

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation, R.string.close_navigation);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        // 设置底部菜单显示样式
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int width = point.x;
        int height = point.y;
        Rect rect = new Rect(0, height, 0, height);
        bottomNavigationView.getHeight();
        rect.offset(width / 8, -56);

        // 设置高亮的目标
        TapTargetSequence targetSequence = new TapTargetSequence(this).
                targets(
                        TapTarget.forToolbarMenuItem(toolbar, R.id.menu_main_toolbar_search, "点击这里进行搜索")
                                .cancelable(false) // 设置是否点击外圈取消显示，默认为true
                                .drawShadow(true) // 设置是否设置阴影，默认不设置
                                .targetCircleColor(R.color.white) // 目标圆圈颜色
                                .transparentTarget(false) // 目标圆圈颜色是否透明，默认false，为true，设置targetCircleColor无效
                                .outerCircleColor(R.color.colorAccent) // 设置目标外部圆圈及目标菜单图标的颜色
                                .dimColor(R.color.black) // 设置目标外圈以外的颜色，默认为透明
//                                .id(1) // 设置目标id（唯一）
                        ,
                        TapTarget.forToolbarNavigationIcon(toolbar, "点击这里展开侧栏")
                                .dimColor(R.color.black)
                                .outerCircleColor(R.color.colorAccent)
//                                .id(2)
                        ,
                        TapTarget.forBounds(rect, "点击这里切换新闻", "双击回到顶部\n再次双击刷新新闻")
                                .dimColor(R.color.black)
                                .outerCircleColor(R.color.colorAccent)
                                .transparentTarget(true)
//                                .id(3)
                ).listener(new TapTargetSequence.Listener() {
            @Override
            public void onSequenceFinish() {

            }

            @Override
            public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

            }

            @Override
            public void onSequenceCanceled(TapTarget lastTarget) {

            }
        });
        // 开始显示
        targetSequence.start();
    }
}
