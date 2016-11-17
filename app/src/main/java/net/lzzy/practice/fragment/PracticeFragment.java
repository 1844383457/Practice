package net.lzzy.practice.fragment;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import net.lzzy.practice.R;
import net.lzzy.practice.activitys.QuestionActivity;
import net.lzzy.practice.adapter.RecyclerViewAdapter;
import net.lzzy.practice.logics.PracticeFactory;
import net.lzzy.practice.models.Practice;
import net.lzzy.practice.utils.AppUtils;
import net.lzzy.practice.view.CustomAlertDialog;
import net.lzzy.practice.web.PracticeJson;

import java.io.IOException;
import java.util.List;

public class PracticeFragment extends Fragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout srl;
    private List<Practice> practices;
    private PracticeFactory factory;
    private RecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        factory = PracticeFactory.getInstance();
        practices = factory.getPractices();
        if (practices.size() < 1)
            new DowPractice().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_practice_recycler);
        srl = (SwipeRefreshLayout) view.findViewById(R.id.fragment_practice_srl);
        srl.setRefreshing(false);
        initView();
        return view;
    }

    private void initView() {
        adapter = new RecyclerViewAdapter(practices);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        srl.setColorSchemeResources(R.color.colorPrimary);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (AppUtils.isConnectivity()) {
                    new DowPractice() {
                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            if (s.equals(""))
                                Toast.makeText(getContext(), "刷新失败", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                            srl.setRefreshing(false);
                        }
                    }.execute();
                } else {
                    srl.setRefreshing(false);
                    Toast.makeText(getContext(), "网络异常，请检查网络设置", Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 20;
                outRect.left = 30;
                outRect.right = 30;
                outRect.bottom = 20;
            }
        });
        adapter.setOnItemClickListener(new RecyclerViewAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (practices.get(position).isDownload()) {
                    Intent intent = new Intent(getActivity(), QuestionActivity.class);
                    startActivity(intent);
                } else {
                    final CustomAlertDialog dialog = new CustomAlertDialog(getContext(), true);
                    dialog.setTitle("提示");
                    dialog.setMessage("是否下载该练习的题目？");
                    dialog.setNegativeButton("取消", null, R.drawable.custom_alert_dialog_left_btn);
                    dialog.setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();

                        }
                    }, R.drawable.custom_alert_dialog_right_btn);
                }

            }
        });

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
//
//            @Override
//            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//                //actionState : action状态类型，有三类 ACTION_STATE_DRAG （拖曳），ACTION_STATE_SWIPE（滑动），ACTION_STATE_IDLE（静止）
//                int dragFlags = makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.UP | ItemTouchHelper.DOWN
//                        | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//支持上下左右的拖曳
//                int swipeFlags = makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//表示支持左右的滑动
//                return makeMovementFlags(dragFlags, swipeFlags);//直接返回0表示不支持拖曳和滑动
//            }
//
//            /**
//             * @param recyclerView attach的RecyclerView
//             * @param viewHolder 拖动的Item
//             * @param target 放置Item的目标位置
//             * @return boolean
//             */
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                int fromPosition = viewHolder.getAdapterPosition();//要拖曳的位置
//                int toPosition = target.getAdapterPosition();//要放置的目标位置
//                adapter.moveItem(fromPosition, toPosition);
//                return true;
//            }
//
//            /**
//             * @param viewHolder 滑动移除的Item
//             * @param direction
//             */
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();//获取要滑动删除的Item位置
//                adapter.removeItem(position);
//            }
//
//            @Override
//            public boolean isLongPressDragEnabled() {
//                return super.isLongPressDragEnabled();//不支持长按拖曳效果直接返回false
//            }
//
//            @Override
//            public boolean isItemViewSwipeEnabled() {
//                return super.isItemViewSwipeEnabled();//不支持滑动效果直接返回false
//            }
//        });
//        itemTouchHelper.attachToRecyclerView(recyclerView);



    }


    private class DowPractice extends AsyncTask<Void, Integer, String> {


        @Override
        protected String doInBackground(Void... voids) {
            try {
                return PracticeJson.getPracticeFromJson();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.equals("")) {
                try {
                   PracticeFactory.getInstance().replaceAll(PracticeJson.getPractices(s));
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

