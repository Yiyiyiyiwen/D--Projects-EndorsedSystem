package com.example.even1.endorsedsystemstudent.View.MyHome;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.even1.endorsedsystemstudent.R;
import com.example.even1.endorsedsystemstudent.Util.setListViewHeight;
import com.example.even1.endorsedsystemstudent.View.CustomizedView.MultiChoiceDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class mSet extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listView;
    private String[] name = {"账户安全","个人偏好","清除缓存"};
    private ArrayAdapter<String> adapter;

    private setListViewHeight setListViewHeight;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_m_set,container,false);

        listView = (ListView)view.findViewById(R.id.listview);
        init();
        return view;
    }

    private void init() {
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,name);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        setListViewHeight.setListViewHeightBasedOnChildren(listView,1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:

                break;
            case 1:
                showMultiChoiceDialogFragment(view);
                break;
            default:
                break;
        }
    }

    public void showMultiChoiceDialogFragment(View view) {
        MultiChoiceDialogFragment multiChoiceDialogFragment = new MultiChoiceDialogFragment();
        final String[] items = {"武侠小说", "推理小说", "悬疑小说", "历史小说", "军事小说", "言情小说",
                "科幻小说", "网游小说", "穿越小说", "玄幻小说"};
        final List<Integer> integerList = new ArrayList<>();
        multiChoiceDialogFragment.show("请选择你喜爱的作品风格", items, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    integerList.add(which);
                } else {
                    integerList.remove(which);
                }
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hint = "";
                for (int i = 0; i < integerList.size(); i++) {
                    hint = items[integerList.get(i)] + hint;
                }
            }
        }, getActivity().getFragmentManager());
    }
}
