package np.sanjogpractise.popupwindowaction;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    final Context context = this;
    NotificationPopupWindow notificationwindow;
    private String[] notification_list = {"notificationwindow","notificationwindow","notificationwindow"};

    private Button button,button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button1 = (Button)findViewById(R.id.button1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               settings();
                System.out.println("screen_button_dimens="+ v.getBottom()+ "px" + v.getTop()+ "px" + v.getRight()+ "px" + v.getLeft()+ "px");
                System.out.println("button2="+button1.getRight() +button1.getWidth());

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_settings);
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            settings1();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Rect locateView(View v)
    {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try
        {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException npe)
        {
            //Happens when the view doesn't exist on screen anymore.
            return null;
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + v.getWidth();
        location.bottom = location.top + v.getHeight();
        return location;
    }

    private void settings() {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.notification_layout, null);
        final ListView notificationlist = (ListView)promptsView.findViewById(R.id.notification_list);
        ArrayAdapter simpleAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.notification_list_item, notification_list);
        notificationlist.setAdapter(simpleAdapter);
        notificationwindow = new NotificationPopupWindow(MainActivity.this);

        Rect someRect = locateView(button);
        notificationwindow.showAtLocation(button, Gravity.TOP|Gravity.RIGHT, someRect.right,someRect.top);
        notificationwindow.changeData(Arrays.asList(notification_list));



    } public static float convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        return screenWidth;
    }
    private int getScreenHeight() {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        return screenHeight;
    }

    private void settings1() {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.notification_layout, null);
        final ListView notificationlist = (ListView)promptsView.findViewById(R.id.notification_list);
        ArrayAdapter simpleAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.notification_list_item, notification_list);
        notificationlist.setAdapter(simpleAdapter);
        notificationwindow = new NotificationPopupWindow(MainActivity.this);
        notificationwindow.showAtLocation(promptsView, Gravity.LEFT|Gravity.TOP, getScreenWidth()-(int)convertDpToPixel(100),(int)convertDpToPixel(73));
        notificationwindow.changeData(Arrays.asList(notification_list));
        System.out.println("screen_width="+getScreenWidth() + "px");
        System.out.println("screen_height="+getScreenHeight() + "px");


    }
    @Override
    public void onClick(View v) {

    }
}
