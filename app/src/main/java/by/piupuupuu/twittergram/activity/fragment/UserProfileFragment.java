package by.piupuupuu.twittergram.activity.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.user_page_layout, container, false);

        init();
        UserProfileAdapter userAdapter = new UserProfileAdapter(getContext(), stories);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.profile_layout_items_list);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        System.out.println(recyclerView);
        recyclerView.setAdapter(userAdapter);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        stories = PostService.getInstance().getStoriesByUserId();
        userPhoto = view.findViewById(R.id.profile_layout_photo);
        userPhoto.setImageResource(R.drawable.usericonfore);
        username = view.findViewById(R.id.profile_layout_username);
        username.setText(CacheService.getInstance().getUserFromCache().getNickname());
        if (stories == null) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

    }
}
