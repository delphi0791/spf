package com.th.android.spf;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by th on 2017/5/23.
 * 类说明:
 */

public class SpfBar extends View{
    int height, width, cellWidth;
    float barMargin;
    Paint mPaint = new Paint(1);
    ArrayList<Integer> spfList;
    int[] colors;
    int win_dp, tie_dp, lose_dp;
    RectF rectF = new RectF();

    public SpfBar(Context context) {
       this(context, null);
    }

    public SpfBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        height = dip2px(45);
        cellWidth = dip2px(12);
        barMargin = dip2px(1);
        win_dp = dip2px(45);
        tie_dp = dip2px(30);
        lose_dp = dip2px(15);
        mPaint.setAntiAlias(true);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public static int dip2px(double dipValue) {
        return (int) (dipValue * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }

    public void setValues(int[] colors, ArrayList<Integer> spfList) {
        this.colors = colors;
        this.spfList = spfList;
        if (spfList == null || spfList.size() < 1) {
            return;
        }
        int len = spfList.size();
        width = (int) (cellWidth * len + barMargin * (len - 1));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (spfList == null) {
            return;
        }

        for (int i = 0, length = spfList.size(); i < length; i++) {

            rectF.left = i * (cellWidth + barMargin);
            rectF.right = (i + 1) * cellWidth + i * barMargin;
            rectF.bottom = height;


            canvas.save();
			/*绘制相应条形背景*/
            switch (spfList.get(i)) {
                case 2:// 胜
                    rectF.top = height - win_dp;
                    mPaint.setColor(colors[0]);
                    break;
                case 1:// 平
                    rectF.top = height - tie_dp;
                    mPaint.setColor(colors[1]);
                    break;
                case 0:// 负
                    rectF.top = height - lose_dp;
                    mPaint.setColor(colors[2]);
                    break;
            }
            canvas.clipRect(rectF);
            canvas.drawRect(rectF, mPaint);
            canvas.restore();
        }
    }
}
