package com.mocoplex.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mocoplex.adnext.sdk.AdNextAdapter;
import com.mocoplex.demo.model.MainListItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Test App List
        listView = (ListView) findViewById(R.id.list_main);
        initLayout();

        // 1. ADNEXT SDK 초기화 (필수로 첫페이지에서 1회 호출)
        AdNextAdapter.getInstance().init(this, "ADNEXT SDK KEY");

        // 2, 사용하는 플랫폼을 초기화 합니다.
        AdNextAdapter.getInstance().initFacebook(this);
        AdNextAdapter.getInstance().initGoogle(this);
        AdNextAdapter.getInstance().initKakao(this, "KAKAO SDK KEY");

        // 3. 딥링크 처리
        AdNextAdapter.getInstance().sendInflowEvent(this, getIntent());
    }

    // Sample layout
    private void initLayout() {
        ArrayList<MainListItem> itemList = new ArrayList<MainListItem>();

        itemList.add(MainListItem.HEADER_01);
        itemList.add(MainListItem.HYBRID);

        LineNextListAdapter adapter = new LineNextListAdapter(this, itemList);
        listView.setAdapter(adapter);

        // List Click Event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = null;

                MainListItem item = (MainListItem) view.getTag();
                switch (item) {
                    case HYBRID:
                        intent = new Intent(getApplicationContext(), HybridActivity.class);
                        break;
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

    // Sample list adapter
    public class LineNextListAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<MainListItem> list;

        public LineNextListAdapter(Context context, ArrayList<MainListItem> list) {
            this.context = context;
            this.list = list;
        }


        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            MainListItem data = list.get(position);
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(data.isHeader() == true ? R.layout.main_list_header : R.layout.main_list_item, null);
            }

            TextView txtItem = (TextView) view.findViewById(R.id.text);
            txtItem.setText(data.getValue());
            view.setTag(data);

            return view;
        }
    }


}
