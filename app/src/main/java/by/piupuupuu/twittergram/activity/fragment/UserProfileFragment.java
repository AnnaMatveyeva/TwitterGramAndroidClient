package by.piupuupuu.twittergram.activity.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import by.piupuupuu.twittergram.MainActivity;
import by.piupuupuu.twittergram.R;
import by.piupuupuu.twittergram.activity.adapter.UserProfileAdapter;
import by.piupuupuu.twittergram.cache.CacheService;
import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.service.PostService;

public class UserProfileFragment extends Fragment {

    private View view;
    private List<Story> stories;
    private ImageView userPhoto;
    private TextView username;
    private Button logoutBtn;
    private TextView didntPost;
    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.user_page_layout, container, false);

        init();
        UserProfileAdapter userAdapter = new UserProfileAdapter(getContext(), stories);

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        System.out.println(recyclerView);
        recyclerView.setAdapter(userAdapter);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        recyclerView = view.findViewById(R.id.profile_layout_items_list);
        stories = PostService.getInstance().getStoriesByUserId();
        userPhoto = view.findViewById(R.id.profile_layout_photo);
        userPhoto.setImageResource(R.drawable.usericonfore);
        username = view.findViewById(R.id.profile_layout_username);
        username.setText(CacheService.getInstance().getUserFromCache().getNickname());
        logoutBtn = view.findViewById(R.id.profile_logout);
        didntPost = view.findViewById(R.id.didntPost);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheService.getInstance().logout();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        if (stories.isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
            didntPost.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            didntPost.setVisibility(View.INVISIBLE);

        }
        if (stories == null) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

    }
}
