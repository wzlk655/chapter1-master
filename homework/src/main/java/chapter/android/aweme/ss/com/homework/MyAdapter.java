package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static final String TAG="MyAdapter";
    private int mNumberItems;
    private static int viewHolderCount;
    private List<Message> mMessages;
    private final ListItemClickListener mOnClickListener;

    public MyAdapter(List<Message> messages, ListItemClickListener listener) {
        mNumberItems=messages.size();
        mMessages=messages;
        viewHolderCount=0;
        mOnClickListener=listener;
        Log.d(TAG,"Created");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context=viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MyViewHolder viewHolder=new MyViewHolder(view);

        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        Log.d(TAG, "onBindViewHolder: #" + position);
        myViewHolder.bind(position);

    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

         private final CircleImageView iv_avatar;
         private final ImageView robot_notice;
         private final TextView tv_title;
         private  final TextView tv_description;
         private final TextView tv_time;

        public MyViewHolder(View view){
            super(view);
            iv_avatar=view.findViewById(R.id.iv_avatar);
            robot_notice=view.findViewById(R.id.robot_notice);
            tv_title=view.findViewById(R.id.tv_title);
            tv_description=view.findViewById(R.id.tv_description);
            tv_time=view.findViewById(R.id.tv_time);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onListItemClick(getAdapterPosition());
                }
            });
        }

        public void bind(int position){

            switch (mMessages.get(position).getIcon()){
                case "TYPE_ROBOT": {
                    iv_avatar.setImageResource(R.drawable.session_robot);
                    break;
                }
                case "TYPE_GAME": {
                    iv_avatar.setImageResource(R.drawable.icon_micro_game_comment);
                    break;
                }
                case "TYPE_SYSTEM": {
                    iv_avatar.setImageResource(R.drawable.session_system_notice);
                    break;
                }
                case "TYPE_STRANGER": iv_avatar.setImageResource(R.drawable.session_stranger);break;
                case "TYPE_USER": iv_avatar.setImageResource(R.drawable.icon_girl);break;
                default: iv_avatar.setImageResource(R.drawable.icon_blacksend_touch);break;
            }

            if(mMessages.get(position).isOfficial())
                robot_notice.setVisibility(View.VISIBLE);
            else
                robot_notice.setVisibility(View.INVISIBLE);
            tv_title.setText(mMessages.get(position).getTitle());
            tv_description.setText(mMessages.get(position).getDescription());
            tv_time.setText(mMessages.get(position).getTime());
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
