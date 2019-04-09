package com.example.even1.endorsedsystemstudent.View;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.even1.endorsedsystemstudent.MainActivity;
import com.example.even1.endorsedsystemstudent.R;
import com.moyinzi.sakura.textviewpackage.changeablecolorfultextview.ChangeableColorfulTextView;
import com.moyinzi.sakura.textviewpackage.changeablecolorfultextview.entity.CCTVContent;
import com.moyinzi.sakura.textviewpackage.changeablecolorfultextview.handler.CCTVMenuHandler;
import com.moyinzi.sakura.textviewpackage.changeablecolorfultextview.listener.ContentChangeListener;
import com.moyinzi.sakura.textviewpackage.changeablecolorfultextview.listener.OnSelectListener;
import com.moyinzi.sakura.textviewpackage.changeablecolorfultextview.utils.CCTVContentRender;
import com.moyinzi.sakura.textviewpackage.changeablecolorfultextview.utils.CCTVController;
import com.moyinzi.sakura.textviewpackage.changeablecolorfultextview.utils.CommonAction;
import com.moyinzi.sakura.textviewpackage.changeablecolorfultextview.utils.CommonRender;

import java.util.HashMap;
import java.util.Map;

public class StartReading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final Map<Integer, CCTVContentRender> contentRenderMode = new HashMap<>();
        CommonRender.build(contentRenderMode, 1, new CommonRender.Callback() {
            @Override
            public void draw(Canvas canvas, int x1, int y1, int x2, int y2, Paint mPaint, int index) {
                int y = y2 - (y2-y1)/3;
                canvas.drawLine(x1, y, x2, y, mPaint);
            }
        });
        CommonRender.build(contentRenderMode, 2, new CommonRender.Callback() {
            @Override
            public void draw(Canvas canvas, int x1, int y1, int x2, int y2, Paint mPaint, int index) {
                if (index == 1) canvas.drawLine(x2, y1, x1, y2, mPaint);
            }
        });
        CommonRender.build(contentRenderMode, 3, new CommonRender.Callback() {
            @Override
            public void draw(Canvas canvas, int x1, int y1, int x2, int y2, Paint mPaint, int index) {
                int y = y2 - (y2-y1)/3;
                int x = (x1+x2)/2;
                canvas.drawCircle(x, y, (x2-x)/3, mPaint);
            }
        });
        CommonRender.build(contentRenderMode, 4, new CommonRender.Callback() {
            @Override
            public void draw(Canvas canvas, int x1, int y1, int x2, int y2, Paint mPaint, int index) {
                int x = (x1+x2)/2;
                int y = y2 - (y2-y1)/3;
                int yy = y2 - (y2-y1)/4;
                canvas.drawLine(x1, yy, x, y, mPaint);
                canvas.drawLine(x, y, x2, yy, mPaint);
            }
        });
        CommonRender.build(contentRenderMode, 5, new CommonRender.Callback() {
            @Override
            public void draw(Canvas canvas, int x1, int y1, int x2, int y2, Paint mPaint, int index) {
                int x = (x1+x2)/2;
                int y = y2 - (y2-y1)/3;
                int yy = y2 - (y2-y1)/2;
                int w = (yy-y)/2;
                canvas.drawRect(x-w,y,x+w,yy, mPaint);
            }
        });

        final ChangeableColorfulTextView cctv = (ChangeableColorfulTextView) findViewById(R.id.cctv);
        cctv.getConfigure()
                .setContentRenderMode(contentRenderMode)
                .setContentChangeListener(new ContentChangeListener() {
                    @Override
                    public void insert(CCTVContent content, Callback callback) {
                        Log.i("content", "insert");
                        callback.callback(content);
                    }

                    @Override
                    public void delete(CCTVContent content) {
                        Log.i("content", "delete");
                    }

                    @Override
                    public void update(CCTVContent content) {
                        Log.i("content", "update");
                    }
                })
                //.setText("ckajclswjdcolknvkijsclsdcsdvsdv\ncsdhjcvwnelkjcbujsikucnkdjc")
                .setMenu(R.layout.layout_pop_menu, new CCTVMenuHandler() {
                    public TextView mDelTv;

                    @Override
                    public void addFlag(Flag flag) {
                        switch (flag) {
                            case has_note:
                                mDelTv.setVisibility(View.VISIBLE);
                                break;
                        }
                    }

                    @Override
                    public void removeFlag(Flag flag) {
                        switch (flag) {
                            case has_note:
                                mDelTv.setVisibility(View.GONE);
                                break;
                        }
                    }

                    @Override
                    public void bindClick(final CCTVController controller, final View view, final Map<String, Action> actions) {
                        view.findViewById(R.id.tv_copy).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 获取剪贴板管理器
                                ClipboardManager clip = (ClipboardManager) controller.mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                // 设置剪贴板内容
                                clip.setPrimaryClip(ClipData.newPlainText(controller.mSelectionInfo.getContent(), controller.mSelectionInfo.getContent()));
                                if (controller.mSelectListener != null) {
                                    controller.mSelectListener.onTextSelected(controller.mSelectionInfo.getContent());
                                }
                                // 取消选中状态
                                controller.resetSelectionInfo();
                                controller.hideSelectView();
                            }
                        });
                        view.findViewById(R.id.tv_select_all).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                controller.hideSelectView();
                                controller.selectText(0, controller.mTextView.getText().length());
                                controller.isHide = false;
                                controller.showCursorHandle(controller.mStartHandle);
                                controller.showCursorHandle(controller.mEndHandle);
                                actions.get("show").done();
                            }
                        });
                        // 设置笔记
                        view.findViewById(R.id.tv_note).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 直接选择记笔记时默认设置划线
                                controller.hideSelectView();
                                controller.resetSelectionInfo();

                                // 完成记笔记的功能
                                // ...

                                final AlertDialog.Builder customizeDialog =
                                        new AlertDialog.Builder(StartReading.this);
                                final View dialogView = LayoutInflater.from(StartReading.this)
                                        .inflate(R.layout.notedialog,null);
                                customizeDialog.setTitle("笔记");
                                customizeDialog.setView(dialogView);
                                customizeDialog.setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // 获取EditView中的输入内容
                                                EditText edit_text =
                                                        (EditText) dialogView.findViewById(R.id.editText);
                                                Toast.makeText(StartReading.this,
                                                        edit_text.getText().toString(),
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                        });
                                customizeDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                customizeDialog.show();
                                Log.i("function", "note");
                            }
                        });
                        // 设置红色下划线
                        view.findViewById(R.id.red_color).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                controller.hideSelectView();
                                controller.resetSelectionInfo();
                                TextPaint mTextPaint = controller.getPaint(new TextPaint(
                                        new Paint(Paint.ANTI_ALIAS_FLAG)), Color.RED);
                                controller.showUnderLine(mTextPaint);
                            }
                        });
                        // 设置蓝色下划线
                        view.findViewById(R.id.blue_color).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                controller.hideSelectView();
                                controller.resetSelectionInfo();
                                TextPaint mTextPaint = controller.getPaint(new TextPaint(
                                        new Paint(Paint.ANTI_ALIAS_FLAG)), Color.BLUE);
                                controller.showUnderLine(mTextPaint);
                            }
                        });
                        // 删除下划线逻辑部分
                        mDelTv = (TextView) view.findViewById(R.id.selectable_delete);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                controller.delUnderline();
                                removeFlag(Flag.has_note);
                            }
                        });
                        mDelTv.setVisibility(View.GONE);

                        CommonAction.build(controller, view, R.id.black_color, 1, Color.GREEN, "");
                        CommonAction.build(controller, view, R.id.black_color2, 2, Color.RED, "");
                        CommonAction.build(controller, view, R.id.black_color3, 3, Color.BLUE, "");
                        CommonAction.build(controller, view, R.id.black_color4, 4, Color.WHITE, "");
                        CommonAction.build(controller, view, R.id.black_color5, 5, Color.GRAY, "");
                    }
                });
        cctv.build().setSelectListener(new OnSelectListener() {
            @Override
            public void onTextSelected(CharSequence content) {
                Log.i("select", content.toString());
            }
        });

    }
}
