package com.autils.ui.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.autils.R;
import com.autils.framework.common.utils.PathUtils;
import com.autils.framework.ui.base.BaseAdapter4RecyclerView;
import com.autils.framework.ui.base.BaseViewHolder4RecyclerView;

import java.io.File;

/**
 * Created by fengyulong on 2019/4/12.
 */
public class FileTidyingTitleAdapter extends BaseAdapter4RecyclerView<File, FileTidyingTitleAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_file_tidying_title_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.initViewData(getItem(position));
    }

    public class ViewHolder extends BaseViewHolder4RecyclerView<File> {
        private File file;
        private TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initView() {
            $clicks(itemView, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.select(file);
                    }
                }
            });
            tv_name = $(R.id.tv_name);
        }

        @Override
        public void initViewData(File file) {
            this.file = file;
            tv_name.setText(file.getAbsolutePath().equals(PathUtils.SdCard()) ? "内部存储空间" : file.getName());
        }
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void select(File file);
    }
}
