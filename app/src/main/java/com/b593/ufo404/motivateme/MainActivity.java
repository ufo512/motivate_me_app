package com.b593.ufo404.motivateme;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.TestOnly;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#FF9CFFFC"));
        }

        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        float maxX = mdispSize.x;
        float maxY = mdispSize.y;

        KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        KonfettiView konfettiView2 = findViewById(R.id.viewKonfetti2);
        konfettiView.build()
                .addColors(
                        Color.parseColor("#E22424"),  //red
                        Color.parseColor("#00F72D"),  //green
                        Color.parseColor("#008CFF"),  //blue
                        Color.parseColor("#CCC800")   //yellow
                )
                .setDirection(-30.0, 60.0)
                .setSpeed(6f, 8f)
                .setFadeOutEnabled(false)
                .setTimeToLive(5000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .setPosition(-100f, -100f, maxY/4+150f, maxY/4-150f)
                .stream(150, 300000L);
        konfettiView2.build()
                .addColors(
                        Color.parseColor("#E22424"),
                        Color.parseColor("#00F72D"),
                        Color.parseColor("#008CFF"),
                        Color.parseColor("#CCC800")
                )
                .setDirection(-150, -240)
                .setSpeed(6f, 8f)
                .setFadeOutEnabled(false)
                .setTimeToLive(5000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .setPosition(maxX+100f, maxX+100f, maxY/4+150f, maxY/4-150f)
                .stream(150, 300000L);

        final ImageView imgMail = findViewById(R.id.imgMail);
        imgMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgMail.setImageResource(R.mipmap.ic_mailoutline);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_info, null);
                mBuilder.setView(mView);

                Button btnQuoteOk = mView.findViewById(R.id.btnQuoteOk);
                TextView tvQuote = mView.findViewById(R.id.tvQuote);
                TextView tvAuthor = mView.findViewById(R.id.tvAuthor);

                Tuple<String, String> quoteTuple = getAuthor(NewAppWidget.randomQuote());
                tvQuote.setText(quoteTuple.x);
                tvAuthor.setText(quoteTuple.y);


                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                btnQuoteOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_back, null);
        mBuilder.setView(mView);
        Button btnBackOk = mView.findViewById(R.id.btnBackOk);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        btnBackOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //String -> Tuple
    //given a string, trims all characters before "” " and returns Tuple<String, String> with x being quote and y being author
    public static Tuple<String, String> getAuthor(String string){
        Tuple<String, String> tuple = new Tuple<>("", "");
        if (string.length() > 2){
            if(string.contains("” ")){
                return new Tuple<>(string.substring(0, string.indexOf("” ")+1), "— "+string.substring(string.indexOf("” ")+2, string.length()));
            }else if(string.contains("”")){
                return new Tuple<>(string.substring(0, string.indexOf("”")+1), "");
            }else{
                return tuple;
            }
        }else {
            return tuple;
        }
    }
}