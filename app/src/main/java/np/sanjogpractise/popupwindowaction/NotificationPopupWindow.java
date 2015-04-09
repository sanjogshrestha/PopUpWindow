package np.sanjogpractise.popupwindowaction;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sanjog Shrestha on 4/9/2015.
 */
public class NotificationPopupWindow extends PopupWindow {
    private Context mContext;
    private OnPopupWindowClickListener listener;
    private ArrayAdapter adapter;
    private List<String> list = new ArrayList<String>();
    private int width = 0;

    public NotificationPopupWindow(Context context){
        super(context);
        mContext = context;
        initView();
    }

    private void initView(){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.notification_layout, null);
        setContentView(popupView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);


        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);

        ListView listView = (ListView) popupView.findViewById(R.id.notification_list);
        adapter = new ArrayAdapter(mContext, R.layout.notification_list_item, R.id.notification_list_item, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                NotificationPopupWindow.this.dismiss();
                if(listener != null){
                    listener.onPopupWindowItemClick(position);
                }
            }
        });

    }

    public void setOnPopupWindowClickListener(OnPopupWindowClickListener listener){
        this.listener = listener;
    }

    public void changeData(List<String> mList) {
        list.addAll(mList);
        adapter.notifyDataSetChanged();
    }

    public interface OnPopupWindowClickListener{
        void onPopupWindowItemClick(int position);
    }


}

