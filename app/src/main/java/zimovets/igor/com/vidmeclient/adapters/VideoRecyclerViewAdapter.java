package zimovets.igor.com.vidmeclient.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zimovets.igor.com.vidmeclient.R;
import zimovets.igor.com.vidmeclient.data.model.video.Video;

/**
 * Created by PC on 07.10.2017.
 */

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.ViewHolder> {

    private List<Video> mVideosList;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageViewPhoto) ImageView mImageViewVideo;
        @BindView(R.id.textViewName) TextView mTextViewName;
        @BindView(R.id.textViewLike) TextView mTextViewLike;
        @BindView(R.id.progressBar) ProgressBar mProgressBar;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Video item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getFormats().get(0).getUri());

            //notifyDataSetChanged();
        }
    }

    public VideoRecyclerViewAdapter(Context context, List<Video> videosList, PostItemListener itemListener) {
        mContext = context;
        mVideosList = videosList;
        mItemListener = itemListener;
    }

    @Override
    public VideoRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.list_vdeo_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoRecyclerViewAdapter.ViewHolder holder, int position) {
        Video item = mVideosList.get(position);

        ImageView imageView = holder.mImageViewVideo;
        TextView textViewName = holder.mTextViewName;

        TextView textViewLike = holder.mTextViewLike;
        final ProgressBar progressBar = holder.mProgressBar;
        progressBar.setVisibility(View.GONE);
        //imageView.setMinimumHeight(50);
        //imageView.setMinimumWidth(50);

        //Glide.with(mContext).load(item.getWebformatURL()).fitCenter().into(holder.image);

        //ColorDrawable g = new ColorDrawable(Color.BLUE);


        textViewName.setText(item.getTitle());

        int likesCount = item.getLikesCount();
        String likes = (likesCount > 1 ?
                likesCount + " " + "likes" :
                likesCount + " " + "like");

        textViewLike.setText(likes);

        /*Picasso.with(mContext)
                .load(item.getThumbnailUrl())
                //.fit()
                //.centerCrop()
                .into(imageView);*/

        Glide
                .with(mContext)
                .load(item.getThumbnailUrl())
                //.asBitmap()
                .dontAnimate()
                .fitCenter()
                //.diskCacheStrategy(DiskCacheStrategy.RESULT)
                //.placeholder(R.drawable.place_holder)
                .into(imageView);
                                                 //.centerCrop() fullscreen
                /*.listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })*/




    }

    @Override
    public int getItemCount() {
        return mVideosList.size();
    }

    public void updateAnswers(List<Video> items) {
        mVideosList = items;
        Log.d("AnswersPresenter", String.valueOf(items.size()));

        notifyDataSetChanged();
    }

    private Video getItem(int adapterPosition) {
        return mVideosList.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(String thumbnailUrl);
    }
}
