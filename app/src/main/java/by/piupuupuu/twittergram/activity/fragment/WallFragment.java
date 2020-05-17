package by.piupuupuu.twittergram.activity.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import by.piupuupuu.twittergram.MainActivity;
import by.piupuupuu.twittergram.R;
import by.piupuupuu.twittergram.activity.adapter.PostAdapter;
import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.service.PostService;

public class WallFragment extends Fragment {

    private View view;
    private List<Story> stories;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.posts_wall_layout, container, false);

        init();
        PostAdapter postAdapter = new PostAdapter(getContext(), stories);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_wall_recycle_view);

        recyclerView.setAdapter(postAdapter);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        stories = PostService.getInstance().findAll();

        if (stories == null) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        stories.sort((story1, story2) -> {
            if (story1.getDate().isAfter(story2.getDate()))
                return 1;
            return 0;

        });

    }
}
