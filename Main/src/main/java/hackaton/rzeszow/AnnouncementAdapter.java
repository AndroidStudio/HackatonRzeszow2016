package hackaton.rzeszow;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {

    private final MainActivity context;
    private LayoutInflater layoutInflater;
    private int size = 10;

    public AnnouncementAdapter(MainActivity context) {
        this.context = context;
        this.layoutInflater = context.getLayoutInflater();
    }

    @Override
    public AnnouncementAdapter.AnnouncementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnnouncementViewHolder(layoutInflater.inflate(R.layout.announcment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AnnouncementAdapter.AnnouncementViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AnnouncementDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void addItems() {
        size += 10;
        notifyDataSetChanged();
    }

    public class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        public AnnouncementViewHolder(View itemView) {
            super(itemView);
        }
    }
}
