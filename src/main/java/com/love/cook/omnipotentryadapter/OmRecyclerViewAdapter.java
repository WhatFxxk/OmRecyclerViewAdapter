package com.love.cook.omnipotentryadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghaixin on 17/8/29.
 */
public abstract class OmRecyclerViewAdapter<T> extends RecyclerView.Adapter<OmRecyclerViewAdapter.ViewHolder> {
/**/
    private Context context;
    private List<T> data;
    private int layoutRes;

    public OmRecyclerViewAdapter(Context context, List<T> data, int layoutRes) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        this.context = context;
        this.layoutRes = layoutRes;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(layoutRes, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        bindData(holder, position, data);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Map<Integer, View> cacheView;
        public ViewHolder(View itemView) {
            super(itemView);
            cacheView = new HashMap<>();
        }

        /**
         * 获取itemView中的childView
         *
         * @param resId
         * @return
         */
        public View getView(int resId) {
            View view = null;
            // 判断Map缓存中是否包含我们要实例化的View
            if (cacheView.containsKey(resId)) {
                // 可以直接返回
                view = cacheView.get(resId);
            } else {
                // 实例化一个，添加到缓存中
                view = itemView.findViewById(resId);
                cacheView.put(resId, view);
            }
            return view;
        }
    }

    public abstract void bindData(ViewHolder holder, int position, List<T> data);
}
