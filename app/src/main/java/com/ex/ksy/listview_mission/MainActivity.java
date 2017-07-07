package com.ex.ksy.listview_mission;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<MyItem> arrMyitem = new ArrayList<MyItem>();
    ArrayList<View> arrCheckeditem = new ArrayList<View>();
    LayoutInflater inflater;
    LinearLayout container;
    int count;
    int sum;
    EditText editName,editPrice,edit_delete;
    ScrollView scrollView;
    Button btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rowlistview_mission);
        container = (LinearLayout) findViewById(R.id.container);
        inflater = getLayoutInflater();
        scrollView = (ScrollView) findViewById(R.id.scrollview);
        btndelete = (Button) findViewById(R.id.removeBtn);
        editName = (EditText) findViewById(R.id.edit_name);
        editPrice = (EditText) findViewById(R.id.edit_price);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            //삭제 버튼
            public void onClick(View v) {
                for (int i = 0; i < arrCheckeditem.size(); i++) {
                    View deleteview = (View) arrCheckeditem.get(i);
                    container.removeView(deleteview);
                    arrMyitem.remove(deleteview);

                }
                clearEditText();
                arrCheckeditem.clear();
            }
        });
    }

    public void OnBtnAdd(View v) {
//      사용자로 부터 입력받을 데이터.

        String strName = editName.getText().toString();
        int iprice = Integer.parseInt(editPrice.getText().toString());
//        데이터 생성
        MyItem myitems = new MyItem(++count, strName, iprice);
        arrMyitem.add(myitems);

//        데이터를 보여줄 뷰 생성 (인플레이션)
        View inflateview = inflater.inflate(R.layout.listviewitem, null);
        container.addView(inflateview);
        TextView tv_price = (TextView) inflateview.findViewById(R.id.tv_price);
        TextView tv_name = (TextView) inflateview.findViewById(R.id.tv_name);
        TextView tv_num = (TextView) inflateview.findViewById(R.id.tv_num);
        TextView tv_date = (TextView) inflateview.findViewById(R.id.tv_date);

//        리스너 등록
        inflateview.setOnClickListener(this);
//      데이터 로딩
        tv_name.setText(myitems.getStrName());
        tv_price.setText("￦ " + myitems.getiPrice());
        tv_num.setText("" + myitems.getiNum());
        tv_date.setText(myitems.getStrDate());
//        total 업데이트
        updateTotal();

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void updateTotal() {
        sum = 0;
        for (int i = 0; i < arrMyitem.size(); i++) {
            MyItem items = arrMyitem.get(i);
            sum += items.getiPrice();
        }
        TextView tv_total = (TextView) findViewById(R.id.total);
        tv_total.setText("Total :￦" + sum);
    }

    public void minusTotal() {
        for (int i = 0; i < arrCheckeditem.size(); i++) {
            MyItem items = arrMyitem.get(i);
            sum -= items.getiPrice();

        }
        TextView tv_total = (TextView) findViewById(R.id.total);
        tv_total.setText("Total :￦" + sum);
    }
    @Override
    public void onClick(View v) {
        LinearLayout inflatedLayout = (LinearLayout) v;
        TextView tv_num = (TextView) inflatedLayout.findViewById(R.id.tv_num);
        TextView tv_price = (TextView) inflatedLayout.findViewById(R.id.tv_price);
        TextView tv_name = (TextView) inflatedLayout.findViewById(R.id.tv_name);
        TextView tv_date = (TextView) inflatedLayout.findViewById(R.id.tv_date);
        edit_delete = (EditText) findViewById(R.id.serchDelete);
        inflatedLayout.setBackgroundColor(Color.rgb(0xff, 0xbf, 0xbb));
        Toast.makeText(this, "선택된 텍스트는 " + tv_name.getText() + "입니다.", Toast.LENGTH_SHORT).show();
        editName.setText(tv_name.getText().toString());
        editPrice.setText("" + tv_price.getText());
        edit_delete.setText("" + tv_num.getText());
        arrCheckeditem.add(inflatedLayout);
    }

    public void clearEditText() {
        editName.setText("");
        editPrice.setText("");
        edit_delete.setText("");
    }
}
