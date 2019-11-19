package com.example.projetofarmacia2.adapters;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;


import com.example.projetofarmacia2.R;
import com.example.projetofarmacia2.api_manipulacao.APIconfig;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ImagemPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mImagens;
    private Dialog dialog;

    public ImagemPagerAdapter(Context context, List<String> list) {
        mContext = context;
        mImagens = list;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mImagens.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }


    public Object instantiateItem(ViewGroup container, int position) {


        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);


        ImageView imageView  = (ImageView) itemView.findViewById(R.id.imageView);

        Picasso.get().load(APIconfig.URL+"/imagem/"+mImagens.get(position)).resize(500,500)
             .centerCrop().into(imageView);


        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}