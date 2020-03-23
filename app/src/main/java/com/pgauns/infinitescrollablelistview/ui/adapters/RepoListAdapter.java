package com.pgauns.infinitescrollablelistview.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgauns.infinitescrollablelistview.R;
import com.pgauns.infinitescrollablelistview.data.data_models.Repository;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepoListAdapter extends BaseAdapter {
    private Context context;
    private List<Repository> repositoryList;
    private LayoutInflater inflter;

    public RepoListAdapter(Context applicationContext, List<Repository> repositories) {
        this.repositoryList = repositories;
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
    }

    public void updateDataSource(List<Repository> repositories) {
        this.repositoryList.addAll(repositories);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return repositoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if ( convertView == null )
        {
        /* There is no view at this position, we create a new one.
           In this case by inflating an xml layout */
            convertView = inflter.inflate(R.layout.repo_lisview_item, null);
            holder = new ViewHolder();
            holder.createView(convertView);
            convertView.setTag(holder);
            Log.d("Listview", String.format("Create New position - %d", i));
        }
        else
        {
            /* We recycle a View that already exists */
            holder = (ViewHolder) convertView.getTag();
        }
        Repository repository = repositoryList.get(i);
        holder.loadView(repository, context);
        return convertView;
    }

    static class ViewHolder{
        TextView nameTextview;
        TextView descriptionTextview;
        ImageView iconImageView;

        void createView(View view) {
            nameTextview = view.findViewById(R.id.list_item_title);
            descriptionTextview = view.findViewById(R.id.list_item_description);
            iconImageView = view.findViewById(R.id.list_item_icon);
        }

        void loadView(Repository repository, Context context) {
            nameTextview.setText(repository.getName());
            descriptionTextview.setText(repository.getDescription());
            Picasso.with(context).load(repository.getOwner().getAvatar_url()).fit().centerCrop()
                    .placeholder(R.drawable.ic_avatar_default)
                    .error(R.drawable.ic_error_loading)
                    .into(iconImageView);
        }
    }
}
