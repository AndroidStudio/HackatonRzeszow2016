package hackaton.rzeszow;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by WitchUser on 2016-03-18.
 */
public abstract class RecyclerScrollListener extends RecyclerView.OnScrollListener {
    private static final float HIDE_THRESHOLD = 100;
    private static final float SHOW_THRESHOLD = 50;
    private final LinearLayoutManager linearLayoutManager;
    private int scrollDist = 0;
    private boolean isVisible = true;

    public RecyclerScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) {
            int visibleItemCount = linearLayoutManager.getChildCount();
            int totalItemCount = linearLayoutManager.getItemCount();
            int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                loadMore();
            }
        }

        if (isVisible && scrollDist > HIDE_THRESHOLD) {
            hide();
            scrollDist = 0;
            isVisible = false;
        } else if (!isVisible && scrollDist < -SHOW_THRESHOLD) {
            show();
            scrollDist = 0;
            isVisible = true;
        }

        if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
            scrollDist += dy;
        }
    }

    public abstract void show();

    public abstract void hide();

    public abstract void loadMore();

}