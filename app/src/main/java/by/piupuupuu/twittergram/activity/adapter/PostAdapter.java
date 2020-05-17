package by.piupuupuu.twittergram.activity.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import by.piupuupuu.twittergram.R;
import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.service.PostService;
import de.hdodenhof.circleimageview.CircleImageView;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Story> stories;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_story_item, parent, false);
        return new StoryViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StoryViewHolder) holder).setStoryDetails(stories.get(position));
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    class StoryViewHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private TextView username;
        private TextView postDate;
        private CircleImageView image;
        private ImageView white_like;
        private ImageView red_like;

        StoryViewHolder(@NonNull final View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.story_itemText);
            username = itemView.findViewById(R.id.username_story_item);
            postDate = itemView.findViewById(R.id.post_item_date);
            image = itemView.findViewById(R.id.profile_photo);
            white_like = itemView.findViewById(R.id.image_heart);
            red_like = itemView.findViewById(R.id.image_heart_red);

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        void setStoryDetails(final Story story) {
            text.setText(story.getText());
            username.setText(story.getUsername());
            postDate.setText(story.getDate().toString());
            image.setImageResource(R.drawable.usericonfore);
            if (story.getIsLiked()) {
                red_like.setVisibility(ImageView.VISIBLE);
                white_like.setVisibility(ImageView.INVISIBLE);
            }

            white_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostService.getInstance().sendLike(String.valueOf(story.getStoryId()));
                    red_like.setVisibility(ImageView.VISIBLE);
                    white_like.setVisibility(ImageView.INVISIBLE);
                }
            });

            red_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostService.getInstance().deleteLike(String.valueOf(story.getStoryId()));
                    red_like.setVisibility(ImageView.INVISIBLE);
                    white_like.setVisibility(ImageView.VISIBLE);
                }
            });

        }
    }
}
