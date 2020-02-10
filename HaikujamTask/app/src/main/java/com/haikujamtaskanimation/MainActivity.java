package com.haikujamtaskanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int count = 0;
    private Button button;
    private MorphingButton btnDone;
    private RelativeLayout mLLLayout;
    private boolean isMorphed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.main_activity);
        init();

    }

    private void init() {
        button = findViewById(R.id.button_next);
        btnDone = findViewById(R.id.btnDone);
        mLLLayout = findViewById(R.id.ll_linearlayout);
        button.setOnClickListener(this);

        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(500)
                .cornerRadius((int) getResources().getDimension(R.dimen.activity_horizontal_margin))
                .width((int) getResources().getDimension(R.dimen._300sdp))
                .height((int) getResources().getDimension(R.dimen._300sdp))
                .color(Color.WHITE) // normal state color
                .text("CLick Here")
                .colorPressed(Color.GRAY); // pressed state color
        btnDone.morph(square);

//To animate from circle to rect-round to this demo purpose
        if (!isMorphed) {
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isMorphed = true;
                    MorphingButton.Params circle = MorphingButton.Params.create()//To animate in circle from rect-round
                            .duration(500)
                            .cornerRadius((int) getResources().getDimension(R.dimen._300sdp))
                            .width((int) getResources().getDimension(R.dimen._300sdp))
                            .height((int) getResources().getDimension(R.dimen._300sdp))
                            .color(Color.WHITE); // normal state color
                    btnDone.morph(circle);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mLLLayout.setVisibility(View.VISIBLE);
                            button.setVisibility(View.VISIBLE);
                            YoYo.with(Techniques.FadeIn)
                                    .duration(200)
                                    .playOn(findViewById(R.id.ll_linearlayout));
                        }
                    }, 500);

                }
            });
        }
        else {
            mLLLayout.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View v) {

        count = count + 1;
        switch (count) {
            case 1:
                YoYo.with(Techniques.TakingOff)
                        .duration(700)
                        .playOn(findViewById(R.id.img_one));
                YoYo.with(Techniques.Wobble)
                        .duration(700)
                        .playOn(findViewById(R.id.img_one_inner));
                findViewById(R.id.img_one_inner).setBackgroundColor(getResources().getColor(R.color.colorRed));

                break;
            case 2:
                YoYo.with(Techniques.TakingOff)
                        .duration(700)
                        .playOn(findViewById(R.id.img_two));
                YoYo.with(Techniques.Wobble)
                        .duration(700)
                        .playOn(findViewById(R.id.img_two_inner));
                findViewById(R.id.img_two_inner).setBackgroundColor(getResources().getColor(R.color.colorGreen));
                break;
            case 3:
                YoYo.with(Techniques.TakingOff)
                        .duration(700)

                        .playOn(findViewById(R.id.img_three));
                YoYo.with(Techniques.Wobble)
                        .duration(700)
                        .playOn(findViewById(R.id.img_three_inner));
                findViewById(R.id.img_three_inner).setBackgroundColor(getResources().getColor(R.color.colorBlue));
                button.setText("Try by Yourself >");
                break;
            case 4:
                mLLLayout.setVisibility(View.GONE);
                MorphingButton.Params square = MorphingButton.Params.create()
                        .duration(500)
                        .cornerRadius((int) getResources().getDimension(R.dimen.activity_horizontal_margin))
                        .width((int) getResources().getDimension(R.dimen._300sdp))
                        .height((int) getResources().getDimension(R.dimen._300sdp))
                        .color(Color.WHITE) // normal state color
                        .text("Done")
                        .colorPressed(Color.GRAY); // pressed state color
                btnDone.morph(square);

                break;
        }

    }
}
