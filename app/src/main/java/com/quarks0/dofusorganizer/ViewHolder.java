package com.quarks0.dofusorganizer;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Quarks0 on 4/9/2016.
 * A view holder to reduce findviewbyid calls. Particularly useful for scrolling views.
 */
public class ViewHolder {
    TextView text;
    ImageView icon;
    ProgressBar progress;
    int position;
    boolean toggle;
}
