package com.example.farazahmed.recyclerview_multiple_viewtypes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static java.lang.Character.getName;

/**
 * Created by FarazAhmed on 4/18/2017.
 */

public class Adaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private boolean isHeader;
    private boolean isFooter;
    private List<String> data;
    private Context context;
    public static final int VIEW_HEADER = 0;
    public static final int VIEW_FOOTER = 2;
    public static final int VIEW_ITEM = 1;

    public Adaptor(Context context, List<String> data, boolean isHeader, boolean isFooter) {

        this.context = context;
        this.data = data;
        this.isHeader = isHeader;
        this.isFooter = isFooter;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_HEADER) {
                return new headView(LayoutInflater.from(context).inflate(R.layout.header, parent, false));
        } else if (viewType == VIEW_FOOTER) {
            return new footView(LayoutInflater.from(context).inflate(R.layout.footer, parent, false));
        } else {
            return new itemView(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof headView) {
            ((headView) holder).head.setText("this is a header");
        } else if (holder instanceof footView) {
            ((footView) holder).foot.setText("this is a footer");
        } else
            ((itemView) holder).item.setText(getName(position));

    }

    @Override
    public int getItemCount() {
        int itemCount = data.size();

        if (isHeader) {
            itemCount += 1;
        }
        if (isFooter) {
            itemCount -= 1;
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader && isHeader(position)) {
            return VIEW_HEADER;
        } else if (isFooter && isFooter(position)) {
            return VIEW_FOOTER;
        } else
            return VIEW_ITEM;
    }

    private boolean isFooter(int position) {
        return position == getItemCount() - 1;
    }

    private boolean isHeader(int position) {
        return position == 0;
    }

    public class headView extends RecyclerView.ViewHolder {

        protected TextView head;

        public headView(View itemView) {
            super(itemView);
            head = (TextView) itemView.findViewById(R.id.headtext);
        }
    }

    public class footView extends RecyclerView.ViewHolder {

        protected TextView foot;

        public footView(View itemView) {
            super(itemView);
            foot = (TextView) itemView.findViewById(R.id.footertext);
        }
    }

    public class itemView extends RecyclerView.ViewHolder {

        protected TextView item;

        public itemView(View itemView) {
            super(itemView);

            item = (TextView) itemView.findViewById(R.id.itemview);
        }
    }


}
