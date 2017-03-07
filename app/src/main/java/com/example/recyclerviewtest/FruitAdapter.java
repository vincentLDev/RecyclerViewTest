package com.example.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Lenovo on 17/3/6.
 */


public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View itemView) { // 构造函数中传入，View参数（RecyclerView子项的最外层布局，Items）
            super(itemView);
            fruitView = itemView;  // 用变量fruitView来保存，子项最外层布局的实例

            fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);  // 获得实例
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {  // 把需要展示的数据传进来
        mFruitList = fruitList;  // 并且赋值给全局变量
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  // 创建ViewHolder实例
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item, parent, false);  // 加载布局fruit_item
        final ViewHolder holder = new ViewHolder(view);

        holder.fruitView.setOnClickListener(new View.OnClickListener() {  // 为子项的最外层布局view，注册点击事件
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition(); // 获取用户点击位置的position
                Fruit fruit = mFruitList.get(position); // 通过position拿到相应的Fruit实例
                Toast.makeText(v.getContext(), "U clicked view " + fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {  // 为具体子项fruitImage，注册点击事件
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "U clicked image " + fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitName.setOnClickListener(new View.OnClickListener() { // 为具体子项fruitName，注册点击事件
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "U clicked  text " + fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) { // 对RecyclerView子项的数据，进行赋值
        Fruit fruit = mFruitList.get(position);  // 通过position参数得到当前Fruit的实例
        holder.fruitImage.setImageResource(fruit.getImageId()); // 将Fruit实例的数据，设置到ViewHolder中
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size(); // 告诉RecyclerView一共有多少子项，返回数据源长度
    }
}
