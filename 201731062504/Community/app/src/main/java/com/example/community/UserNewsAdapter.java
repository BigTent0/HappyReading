package com.example.community;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserNewsAdapter extends RecyclerView.Adapter<UserNewsAdapter.ViewHolder> {

    boolean flag=true;
    private List<UserNews> userNewsList;
    static class ViewHolder extends RecyclerView.ViewHolder{

        View userNewsView;//点击view

        ImageView userPicPath;//个人头像
        TextView userName;// 发布人
        TextView datetime;// 发布时间
        TextView address;//发布地址
        ImageView focus;
        TextView content;// 趣事内容
        ImageView picPath;// 趣事图片路径
        ImageView forwardImg;// 转发图片
        TextView forward;// 转发数量
        ImageView commentImg;// 评论图片
        TextView comment;// 评论数量
        ImageView thumbUpImg;// 点赞图片
        TextView thumbUp;// 点赞数量

        public ViewHolder(View view){
            super(view);

            userNewsView=view;//点击view

            userPicPath=(ImageView) view.findViewById(R.id.userPicPath);
            userName=(TextView)view.findViewById(R.id.userName);
            datetime=(TextView)view.findViewById(R.id.datetime);
            address=(TextView) view.findViewById(R.id.address);
            focus=(ImageView) view.findViewById(R.id.focus);
            content=(TextView)view.findViewById(R.id.content);
            picPath=(ImageView) view.findViewById(R.id.picPath);
            forwardImg=(ImageView) view.findViewById(R.id.forwardImg);
            forward=(TextView)view.findViewById(R.id.forward);
            commentImg=(ImageView) view.findViewById(R.id.commentImg);
            comment=(TextView)view.findViewById(R.id.comment);
            thumbUpImg=(ImageView) view.findViewById(R.id.thumbUpImg);
            thumbUp=(TextView)view.findViewById(R.id.thumbUp);
        }
    }
    public UserNewsAdapter(List<UserNews>userNewsList1){
        userNewsList=userNewsList1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_news_item,parent,false);
          final  ViewHolder holder=new ViewHolder(view);
          //点击用户名跳转至详情页
          holder.userName.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int position=holder.getAdapterPosition();
                  UserNews userNews=userNewsList.get(position);

                  //点击用户名称使其跳转到该用户所对应的详情介绍界面
//                  Intent intent=new Intent(UserNewsAdapter.this,User_DetailsActivity.class);
//                  startActivity(intent);
//                  Adapter是一个java类并不是一个Activity，普通的java类并不能正确获得上下文环境，
//                  因为这个类没有在AndroidManifest文件里面注册。所以在进行条状的时候只要需要手动获取上下文环境就可以了，
                  Intent intent=new Intent(v.getContext(),User_DetailsActivity.class);
                  v.getContext().startActivity(intent);
              }
          });
        //点击用户头像跳转至详情页
          holder.userPicPath.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent(v.getContext(),User_DetailsActivity.class);
                  v.getContext().startActivity(intent);
              }
          });
          //关注与取消关注
          holder.focus.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int position=holder.getAdapterPosition();
                  UserNews userNews=userNewsList.get(position);
                  if(flag==true){
                      holder.focus.setImageResource(R.drawable.focused);
                      flag=false;
                  }
                  else{
                      holder.focus.setImageResource(R.drawable.focus);
                      flag=true;
                  }
              }
          });
          //转发与取消转发
          holder.forwardImg.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int position=holder.getAdapterPosition();
                  UserNews userNews=userNewsList.get(position);
                  if(flag==true){
                      holder.forwardImg.setImageResource(R.drawable.forward2);
                      flag=false;
                  }
                  else{
                      holder.forwardImg.setImageResource(R.drawable.forward);
                      flag=true;
                  }
              }
          });
        //点赞与取消点赞
        holder.thumbUpImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                UserNews userNews=userNewsList.get(position);
                if(flag==true){
                    holder.thumbUpImg.setImageResource(R.drawable.thumbup2);
                    flag=false;
                }
                else{
                    holder.thumbUpImg.setImageResource(R.drawable.thumbup);
                    flag=true;
                }
            }
        });
        //评论与取消评论
        holder.commentImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==true){
                    holder.commentImg.setImageResource(R.drawable.comment2);
                    flag=false;
                }
                else{
                    holder.commentImg.setImageResource(R.drawable.comment);
                    flag=true;
                }
            }
        });
          return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        UserNews userNews=userNewsList.get(position);

        holder.userPicPath.setImageResource(userNews.getUserPicPath());
        holder.userName.setText(userNews.getUserName());
        holder.datetime.setText(userNews.getDatetime());
        holder.address.setText(userNews.getAddress());
        holder.focus.setImageResource(userNews.getLook());
        holder.content.setText(userNews.getContent());
        holder.picPath.setImageResource(userNews.getPicPath());
        holder.forwardImg.setImageResource(userNews.getForwardImg());
        holder.forward.setText(userNews.getForward());
        holder.commentImg.setImageResource(userNews.getCommentImg());
        holder.comment.setText(userNews.getComment());
        holder.thumbUpImg.setImageResource(userNews.getThumbUpImg());
        holder.thumbUp.setText(userNews.getThumbUp());


    }

    @Override
    public long getItemId(int position) {
        return userNewsList.size();
    }
//这里的返回值不能为0或者负数，不然无法显示布局
    @Override
    public int getItemCount() {
        return userNewsList.size();
    }
}