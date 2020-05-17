package by.piupuupuu.twittergram.activity.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import by.piupuupuu.twittergram.R;
import by.piupuupuu.twittergram.model.Story;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    List<Story> stories;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_story_item, parent, false);
        return new UserProfileViewHolder(view);
    }


    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((UserProfileViewHolder) holder).setStoryDetails(stories.get(position));
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    class UserProfileViewHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private TextView postDate;

        UserProfileViewHolder(@NonNull final View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.profile_itemText);
            postDate = itemView.findViewById(R.id.profile_item_date);

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        void setStoryDetails(final Story story) {
            text.setText(story.getText());
            postDate.setText(story.getDate().toString());
        }
    }
}
