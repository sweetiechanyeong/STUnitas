package com.sweetiechanyeong.stunitastestha.model.kakaoimageapidatapaging;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import com.sweetiechanyeong.stunitastestha.databinding.RvKakaoImageItemBinding;
import com.sweetiechanyeong.stunitastestha.model.kakaoimageapidata.Document;
import com.sweetiechanyeong.stunitastestha.R;
import com.sweetiechanyeong.stunitastestha.presenter.KaKaoAdapterHolderPresenter;


public class KakaoPagingAdapter extends PagedListAdapter<Document, KakaoPagingAdapter.ViewHolder> {


    public KakaoPagingAdapter() {
        super(DIFF_CALLBACK);


    }/* 생성자 */


    @NonNull
    @Override
    public KakaoPagingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        // databinding 라이브러리를 이용할시

        RvKakaoImageItemBinding binding = RvKakaoImageItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        KakaoPagingAdapter.ViewHolder viewHolder = new KakaoPagingAdapter.ViewHolder(binding);
        return viewHolder;


        //findViewByID를 이용할시

//        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_kakao_image_item, viewGroup, false);
//        KakaoPagingAdapter.ViewHolder viewHolder = new KakaoPagingAdapter.ViewHolder(itemView);
//        return viewHolder;

    }/* onCreateViewHolder() */


    @Override
    public void onBindViewHolder(@NonNull final KakaoPagingAdapter.ViewHolder viewHolder, int i) {

        Document document = getItem(i);
        viewHolder.presenter.setDocument(document);
        viewHolder.presenter.setPosition(i);
        viewHolder.presenter.bind();

    }/* onBindViewHolder() */


    private static DiffUtil.ItemCallback<Document> DIFF_CALLBACK = new DiffUtil.ItemCallback<Document>() {

        @Override
        public boolean areItemsTheSame(@NonNull Document document, @NonNull Document t1) {
            return document.getImageUrl() == t1.getImageUrl();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Document document, @NonNull Document t1) {
            return document.equals(t1);
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder implements KaKaoAdapterHolderPresenter.View {

//        ImageView imv, imgNotFOund404;
//        ProgressBar progressBar;


        //        findViewById 이용시
//        ViewHolder(View itemView) {
//            super(itemView);
//            // 프레센터 생성
//            presenter = new KaKaoAdapterHolderPresenter();
//            presenter.setView(this);
//
//            progressBar = itemView.findViewById(R.id.progressBar);
//            imv = itemView.findViewById(R.id.imv);
//            imgNotFOund404 = itemView.findViewById(R.id.imgNotFOund404);
//
//
//        }/* 생성자 */


        KaKaoAdapterHolderPresenter presenter;
        RvKakaoImageItemBinding binding;

        public ViewHolder(RvKakaoImageItemBinding binding) {
            super(binding.getRoot());
            presenter = new KaKaoAdapterHolderPresenter();
            presenter.setView(this);
            this.binding = binding;

        }/* 생성자 */


        @Override
        public void initScreeen() {

            // 프로그레스 바 표시
            binding.progressBar.setVisibility(View.VISIBLE);

            // 이미지 찾지 못함 숨김
            binding.imgNotFOund404.setVisibility(View.INVISIBLE);

        }/* initScreeen() */

        @Override
        public void setImage(Document document) {
            Glide.with(binding.imv.getContext()).load(document.getImageUrl()).listener(new RequestListener<Drawable>() {

                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                    // 프로그레스 바 숨김
                    binding.progressBar.setVisibility(View.INVISIBLE);

                    // 이미지 찾지 못함 표시
                    binding.imgNotFOund404.setVisibility(View.VISIBLE);

                    Glide.with(binding.imv.getContext()).load(R.drawable.error).into(binding.imgNotFOund404);

                    return false;
                }/* onLoadFailed() */

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    // 프로그레스 바 숨김
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    return false;
                }/* onResourceReady() */

            }).into(binding.imv);

        }/* setImage() */
    }// class

}// class