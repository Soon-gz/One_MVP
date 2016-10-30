package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading.ItemEntity;

import java.util.List;

public class CustomAdapter extends BaseAdapter
		implements OnScrollListener, PinnedHeaderListView.PinnedHeaderAdapter {
	
	private static final String TAG = CustomAdapter.class.getSimpleName();

	private Context mContext;
	private List<ItemEntity> mData;
	private LayoutInflater mLayoutInflater;

	public CustomAdapter(Context pContext, List<ItemEntity> pData) {
		mContext = pContext;
		mData = pData;
		mLayoutInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.item_pinedlv, null);
            
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            viewHolder.contentIcon = (ImageView) convertView.findViewById(R.id.content_icon);
            viewHolder.text_reading_2 = (TextView) convertView.findViewById(R.id.text_reading_2);
			viewHolder.text_reading_3 = (TextView) convertView.findViewById(R.id.text_reading_3);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemEntity itemEntity = (ItemEntity) getItem(position);
        viewHolder.content.setText(itemEntity.getContent());
		viewHolder.text_reading_2.setText(itemEntity.getmText2());
		viewHolder.text_reading_3.setText(itemEntity.getmText3());
		String type = itemEntity.getType();
		if (Const.ESSAY.equals(type)){
			viewHolder.contentIcon.setImageResource(R.drawable.essay_image);
		}else if (Const.SERIAL.equals(type)){
			viewHolder.contentIcon.setImageResource(R.drawable.serial_image);
		}else{
			viewHolder.contentIcon.setImageResource(R.drawable.question_image);
		}

		//Log.e("TAG00", "当前位置"+position+"的text2和3："+itemEntity.getmText2()+","+itemEntity.getmText3());
        if ( needTitle(position)) {
            viewHolder.title.setText(itemEntity.getTitle());
            viewHolder.title.setVisibility(View.VISIBLE);
        } else {
            viewHolder.title.setVisibility(View.GONE);
        }
        return convertView;
	}
	
	@Override
	public int getCount() {
		if (null != mData) {
			return mData.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (null != mData && position < getCount()) {
			return mData.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
						 int visibleItemCount, int totalItemCount) {
		
//		if ( view instanceof PinnedHeaderListView) {
//			((PinnedHeaderListView) view).controlPinnedHeader(firstVisibleItem);
//		}
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}
	
	
	@Override
	public int getPinnedHeaderState(int position) {
		if (position == 0){
			return PinnedHeaderListView.PinnedHeaderAdapter.PINNED_HEADER_GONE;
		}
		if (getCount() == 0 || position < 0) {
			return PinnedHeaderListView.PinnedHeaderAdapter.PINNED_HEADER_GONE;
		}
		
		if (isMove(position) == true) {
			return PinnedHeaderListView.PinnedHeaderAdapter.PINNED_HEADER_PUSHED_UP;
		}
		
		return PinnedHeaderListView.PinnedHeaderAdapter.PINNED_HEADER_VISIBLE;
	}

	@Override
	public void configurePinnedHeader(View headerView, int position, int alpaha) {
		ItemEntity itemEntity = (ItemEntity) getItem(position);
		String headerValue = itemEntity.getTitle();
		
		Log.e(TAG, "header = " + headerValue);
		
		if (!TextUtils.isEmpty(headerValue)) {
			TextView headerTextView = (TextView) headerView.findViewById(R.id.header);
			headerTextView.setText( headerValue );
		}
	}

	private boolean needTitle(int position) {
		if (position == 0) {
			return true;
		}
		
        if (position < 0) {
            return false;
        }
		 
		ItemEntity currentEntity = (ItemEntity) getItem(position);
		ItemEntity previousEntity = (ItemEntity) getItem(position - 1);
		if (null == currentEntity || null == previousEntity) {
            return false;
        }
		
		String currentTitle = currentEntity.getTitle();
		String previousTitle = previousEntity.getTitle();
		if (null == previousTitle || null == currentTitle) {
            return false;
        }
		
		if (currentTitle.equals(previousTitle)) {
			return false;
		}
		
		return true;
	}


	private boolean isMove(int position) {
	    ItemEntity currentEntity = (ItemEntity) getItem(position-1);
	    ItemEntity nextEntity = (ItemEntity) getItem(position);
	    if (null == currentEntity || null == nextEntity) {
            return false;
        }

	    String currentTitle = currentEntity.getTitle();
	    String nextTitle = nextEntity.getTitle();
	    if (null == currentTitle || null == nextTitle) {
            return false;
        }
	    
	    if (!currentTitle.equals(nextTitle)) {
            return true;
        }
	    return false;
	}
	
	private class ViewHolder {
        TextView title;
        TextView content;
		TextView text_reading_2;
		TextView text_reading_3;
        ImageView contentIcon;
    }
}
