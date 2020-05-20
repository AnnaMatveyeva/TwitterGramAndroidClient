package by.piupuupuu.twittergram.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import by.piupuupuu.twittergram.R;
import by.piupuupuu.twittergram.activity.adapter.PostAdapter;
import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.service.PostService;
import de.hdodenhof.circleimageview.CircleImageView;

public class SearchFragment extends Fragment {

    private View view;
    private List<Story> stories;
    private RecyclerView recyclerView;
    private CircleImageView searchImage;
    private Button searchButton;
    private EditText searchLine;
    private TextView notFoundText;
    private PostAdapter postAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.search_layout, container, false);

        init();
        postAdapter = new PostAdapter(getContext(), stories, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView viewById = (TextView) view.findViewById(R.id.username_story_item);
                searchLine.setText(viewById.getText().toString());
                searchButton.callOnClick();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        System.out.println(recyclerView);
        recyclerView.setAdapter(postAdapter);

        return view;
    }

    private void init() {
        stories = new ArrayList<>();
        recyclerView = view.findViewById(R.id.stories_list_search);
        searchButton = view.findViewById(R.id.searchBtn);
        searchImage = view.findViewById(R.id.search_image);
        searchImage.setImageResource(R.drawable.searchicon);

        searchLine = view.findViewById(R.id.searchLine);
        notFoundText = view.findViewById(R.id.notFoundText);
        recyclerView.setVisibility(View.VISIBLE);
        notFoundText.setVisibility(View.INVISIBLE);

        searchButton.setOnClickListener(v -> {
            String text = searchLine.getText().toString();
            if (!text.isEmpty()) {

                stories.clear();
                if (text.startsWith("@")) {
                    stories.addAll(PostService.getInstance()
                            .findAllByAuthor(text.substring(1)));
                } else {
                    stories.addAll(PostService.getInstance()
                            .findStoriesByText(searchLine.getText().toString()));
                }
                if (stories.isEmpty()) {
                    recyclerView.setVisibility(View.INVISIBLE);
                    notFoundText.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);

                    postAdapter.notifyDataSetChanged();
                    notFoundText.setVisibility(View.INVISIBLE);
                }
            }

        });

    }
}
