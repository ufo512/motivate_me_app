package com.b593.ufo404.motivateme;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.widget.RemoteViews;

import java.util.Random;

public class NewAppWidget extends AppWidgetProvider {

    public static String quotes[] = {
            "„Potrzeba czasu żeby dokonać rzeczy trudnych. Aby dokonać rzeczy niemożliwych potrzeba trochę więcej czasu.”" ,
            "„Ból jest stanem przejściowym. Duma pozostaje na zawsze.”" ,
            "„Nigdy nie rezygnuj z osiągnięcia celu tylko dlatego, że osiągnięcie go wymaga czasu. Czas i tak upłynie.”" ,
            "„Przyszłość należy do tych, którzy wierzą w piękno swoich marzeń.” Eleanor Roosevelt" ,
            "„Bądź dobrej myśli, bo po co być złej.” Stanisław Lem",
            "„Aby osiągnąć sukces pierwsze co musisz zrobić to zakochać się w ciężkiej pracy.” Siostra Mary Lauretta" ,
            "„Najważniejszy w każdym działaniu jest początek.” Platon",
            "„Tam, gdzie nie ma walki, nie ma siły.” Oprah Winfrey" ,
            "„Możesz zrobić wszystko, co chcesz jeśli tylko trzymasz się tego celu wystarczająco długo.” Helen Keller" ,
            "„Odpocznij. Pole, które odpoczęło, daje obfite plony.” Owidiusz",
            "„Być zwyciężonym i nie ulec to zwycięstwo. Zwyciężyć i osiąść na laurach to klęska.” Józef Piłsudski" ,
            "„Nie musisz odnosić sukcesów ciągle, lecz tylko wystarczająco często.”" ,
            "„Vanitas vanitatum et omnia vanitas”",
            "„Mądry człowiek nie opłakuje przegranej, lecz szuka sposobu, aby wyleczyć odniesione rany.” Szekspir" ,
            "„Nie bój się cieni. One świadczą o tym, że gdzieś znajduje się światło.” Oscar Wilde",
            "„Chęć wygranej nic nie znaczy bez chęci do przygotowania.” Juma Ikangaa" ,
            "„Najmądrzejszy jest, który wie czego nie wie” Sokrates",
            "„Ból jest przemijający, a skutki rezygnacji pozostają na zawsze.” Lance Armstrong" ,
            "„Jeśli chcesz się powiesić, powieś się na wysokim drzewie.” Talmud",
            "„Nigdy, nigdy, nigdy, nigdy się nie poddawaj.” Winston Churchill" ,
            "„Motywacja jest tym, co pozwala zacząć. Nawyk jest tym, co pozwala Ci wytrwać.” Jim Ryun" ,
            "„Sukces to suma niewielkiego wysiłku powtarzanego z dnia na dzień.” Robert Collier" ,
            "„Per aspera ad astra”",
            "„Człowiek nie jest stworzony do klęski. Człowieka można zniszczyć, ale nie pokonać.” Ernest Hamingway" ,
            "„Cudem nie jest to, że skończyłem. Cudem jest to, że miałem odwagę zacząć.” John Bingham" ,
            "„Każdy dzień to wyścig, który już się rozpoczął.”" ,
            "„Zaprzyjaźnij sie z bólem, a juz nigdy nie będziesz samotny”" ,
            "„Przegrywa nie ten który padł, lecz ten który nie chciał powstać.”" ,
            "„W konfrontacji strumienia ze skałą, strumień zawsze wygrywa - nie przez swoją siłę, ale przez wytrwałość.” Buddha" ,
            "„Nienawidziłem każdej minuty treningu, ale powtarzałem sobie 'Nie poddawaj się. Przecierp teraz i żyj resztę życia jako mistrz.'” Muhammad Ali",
            "„Aby zerwać z nawykiem, wyrób sobie inny, który go wymaże.” Mark Twain",
            "„Twój czas jest ograniczony, więc nie marnuj go na byciem kimś, kim nie jesteś.” Steve Jobs",
            "„Najlepszą zemstą jest ogromny sukces.” Frank Sinatra",
            "„Pudłujesz 100% strzałów, jeśli w ogóle ich nie wykonujesz.” Wayne Gretzky",
            "„Z życia najlepiej odchodzić jak z uczty: ani spragniony, ani pijany.” Arystoteles",
            "„Work, Work, Work, Work, Work, Work” Rihanna",
            "„Nie zadowalaj się byciem przeciętną. Przeciętność jest tak samo blisko dna jak i szczytu.”"
    };

    public static String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";
    private static MediaPlayer mp;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        //Set the image which will appear on the screen
        Intent active = new Intent(context, NewAppWidget.class);
        active.setAction(ACTION_WIDGET_RECEIVER);
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);
        remoteViews.setOnClickPendingIntent(R.id.imageButton, actionPendingIntent);
        remoteViews.setImageViewResource(R.id.imageView, R.drawable.space);

        remoteViews.setTextViewText(R.id.imageButton, randomQuote());
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (mp == null) {
            mp = MediaPlayer.create(context.getApplicationContext(), R.raw.long_road_ahead_short);
        }

        final String action = intent.getAction();

        if (ACTION_WIDGET_RECEIVER.equals(action)) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(context.getApplicationContext(), R.raw.long_road_ahead_short);

                views.setTextViewText(R.id.imageButton, randomQuote());
            }
            else {
                mp.start();
                views.setTextViewText(R.id.imageButton, randomQuote());

                //VERY IMPORTANT
                AppWidgetManager.getInstance(context).updateAppWidget(
                        new ComponentName(context, NewAppWidget.class), views);
            }

        }
        super.onReceive(context, intent);
    }

    //returns random quote from "quotes" list
    public static String randomQuote(){
        Random r = new Random();
        int high = quotes.length-1;
        int res = r.nextInt(high);
        return quotes[res];
    }

}

